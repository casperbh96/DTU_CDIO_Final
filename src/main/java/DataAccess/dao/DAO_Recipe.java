package main.java.DataAccess.dao;

import main.java.Core.RecipeDTO;
import main.java.Core.ResourceBatchDTO;
import main.java.Core.StringToSqlDateConverter;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static main.java.DataAccess.dao.Connector.static_commitTransAction;
import static main.java.DataAccess.dao.Connector.static_createConnection;
import static main.java.DataAccess.dao.Connector.static_startTransAction;
import static main.java.DataAccess.dao.SQL_Manipulation.static_parameterBuilder;
import static main.java.DataAccess.dao.SQL_Manipulation.static_setIntPreparedStatements;

public class DAO_Recipe implements I_DAL_Recipe {

    private List<RecipeDTO> resultSetWhileLoop(ResultSet resultset) throws SQLException {
        RecipeDTO recipe = null;
        List<RecipeDTO> recipeList = new ArrayList<>();

        try{
            while (resultset.next()) {
                recipe = new RecipeDTO(resultset.getInt(1), resultset.getDate(2), resultset.getString(3), resultset.getDouble(4),resultset.getInt(5));
                recipeList.add(recipe);
            }
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
        return recipeList;
    }

    private PreparedStatement setCreatePreparedStatement(PreparedStatement pStmt, RecipeDTO recipe) throws SQLException {
        try{
            pStmt.setInt(1, recipe.getRecipeId());
            pStmt.setDate(2, recipe.getRecipeEndDate());
            pStmt.setString(3, recipe.getRecipeName());
            pStmt.setDouble(4, recipe.getProductAmount());
            pStmt.setInt(5, recipe.getAuthorUserId());

        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    private PreparedStatement setUpdatePreparedStatement(PreparedStatement pStmt, RecipeDTO recipe) throws SQLException {
        try{
            pStmt.setString(1, recipe.getRecipeName());
            pStmt.setDouble(2, recipe.getProductAmount());
            pStmt.setInt(3, recipe.getAuthorUserId());
            pStmt.setInt(4, recipe.getRecipeId());
            pStmt.setDate(5, recipe.getRecipeEndDate());
        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    @Override
    public RecipeDTO createSingleRecipe(RecipeDTO singleRecipe) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO recipes (recipe_id, recipe_end_date, recipe_name, product_amount, author_id_user_id) VALUES (?,?,?,?,?)");

            setCreatePreparedStatement(pStmt, singleRecipe);

            pStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readSingleRecipeById(singleRecipe.getRecipeId(),singleRecipe.getRecipeEndDate());
    }

    @Override
    public List<RecipeDTO> createMultipleRecipes(List<RecipeDTO> listOfRecipes) throws SQLException {
        List<Integer> idList = new ArrayList<>();
        List<Date> dateList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO recipes (recipe_id, recipe_end_date, recipe_name, product_amount, author_id_user_id) VALUES (?,?,?,?,?)");

            for (RecipeDTO recipe : listOfRecipes) {
                idList.add(recipe.getRecipeId());
                dateList.add(recipe.getRecipeEndDate());

                setCreatePreparedStatement(pStmt, recipe);

                pStmt.addBatch();
            }
            pStmt.executeBatch();
            static_commitTransAction(conn);
        } catch (BatchUpdateException batchEx) {
            throw new BatchUpdateException(batchEx);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readMultipleRecipesByList(idList,dateList);
    }

    @Override
    public RecipeDTO readSingleRecipeById(int recipeId, Date recipeEndDate) throws SQLException {
        RecipeDTO recipe = null;
        System.out.println("SQL DATE: " + recipeEndDate);

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM recipes WHERE recipe_id = ? AND recipe_end_date = ?");

            pStmt.setInt(1, recipeId);
            pStmt.setDate(2, recipeEndDate);
            ResultSet resultset = pStmt.executeQuery();

            // Move pointer to first row before Id, then to row with Id (fix)
            resultset.beforeFirst();
            resultset.next();

            recipe = new RecipeDTO(resultset.getInt(1), resultset.getDate(2), resultset.getString(3), resultset.getDouble(4),resultset.getInt(5));
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return recipe;
    }

    @Override
    public List<RecipeDTO> readMultipleRecipesByList(List<Integer> listOfRecipeIds, List<Date> listOfRecipeEndDates) throws SQLException {
        List<RecipeDTO> recipeList = new ArrayList<>();

        String parameters = static_parameterBuilder(listOfRecipeIds);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < listOfRecipeEndDates.size(); i++) {
            if (i == listOfRecipeEndDates.size() - 1) {
                builder.append("?");
            } else {
                builder.append("?,");
            }
        }
        String parameters2 = builder.toString();

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM recipes WHERE recipe_id IN (" + parameters + ") AND recipe_end_date IN (" + parameters2 + ")");

//            static_setIntPreparedStatements(pStmt,listOfRecipeIds);

            for (int i = 0; i < listOfRecipeIds.size(); i++) {
                pStmt.setInt(i+1, listOfRecipeIds.get(i));
                pStmt.setDate(i+listOfRecipeEndDates.size() + 1, listOfRecipeEndDates.get(i));
            }

            ResultSet resultset = pStmt.executeQuery();

            recipeList = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return recipeList;
    }

    @Override
    public List<RecipeDTO> readRecipeBySearch(String keyword) throws SQLException {
        List<RecipeDTO> recipeList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("select * from recipes " +
                    "WHERE recipe_id LIKE ? OR recipe_end_date LIKE ? OR recipe_name LIKE ? OR product_amount LIKE ? OR author_id_user_id LIKE ?");
            pStmt.setString(1, "%" + keyword + "%");
            pStmt.setString(2, "%" + keyword + "%");
            pStmt.setString(3, "%" + keyword + "%");
            pStmt.setString(4, "%" + keyword + "%");
            pStmt.setString(5, "%" + keyword + "%");

            ResultSet resultset = pStmt.executeQuery();

            recipeList = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return recipeList;
    }

    @Override
    public List<RecipeDTO> readAllRecipes() throws SQLException {
        List<RecipeDTO> recipeList = new ArrayList<>();

        try (Connection connection = static_createConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM recipes");
            ResultSet resultset = pStmt.executeQuery();

            recipeList = resultSetWhileLoop(resultset);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    @Override
    public RecipeDTO updateSingleRecipe(RecipeDTO recipe) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("UPDATE recipes SET recipe_name = ?, product_amount = ?, author_id_user_id = ? WHERE recipe_id = ? AND recipe_end_date = ?");

            setUpdatePreparedStatement(pStmt, recipe);

            pStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readSingleRecipeById(recipe.getRecipeId(),recipe.getRecipeEndDate());
    }

    @Override
    public List<RecipeDTO> updateMultipleRecipes(List<RecipeDTO> listOfRecipes) throws SQLException {
        List<Integer> idList = new ArrayList<>();
        List<Date> dateList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("UPDATE recipes SET recipe_name = ?, product_amount = ?, author_id_user_id = ? WHERE recipe_id = ? AND recipe_end_date = ?");

            for (RecipeDTO recipe : listOfRecipes) {
                idList.add(recipe.getRecipeId());
                dateList.add(recipe.getRecipeEndDate());

                setUpdatePreparedStatement(pStmt, recipe);
                pStmt.addBatch();
            }

            pStmt.executeBatch();
            static_commitTransAction(conn);

        } catch (BatchUpdateException batchEx){
            throw new BatchUpdateException(batchEx);
        }
        return readMultipleRecipesByList(idList, dateList);
    }

    @Override
    public RecipeDTO setRecipeEndDateSingleRecipe(int recipeId, Date recipeEndDateBefore) throws SQLException {
        java.util.Date uDate = new java.util.Date();
        java.sql.Date currDate = new java.sql.Date(uDate.getTime());

        try(Connection connection = static_createConnection()){

            PreparedStatement pStmt = connection.prepareStatement("UPDATE recipes SET recipe_end_date = ? WHERE recipe_id = ? AND recipe_end_date = ?");

            pStmt.setDate(1, currDate);
            pStmt.setInt(2, recipeId);
            pStmt.setDate(3, recipeEndDateBefore);
            pStmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return readSingleRecipeById(recipeId, currDate);
    }

    @Override
    public List<RecipeDTO> setRecipeEndDateMultipleRecipes(List<Integer> recipeIds, List<Date> recipeEndDateBeforeChangeList) throws SQLException {
        List<Integer> idList = new ArrayList<>();
        List<Date> dateList = new ArrayList<>();

        java.util.Date uDate = new java.util.Date();
        java.sql.Date currDate = new java.sql.Date(uDate.getTime());

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("UPDATE recipes SET recipe_end_date = ? WHERE recipe_id = ? AND recipe_end_date = ?");

            for (int i = 0; i < recipeIds.size(); i++) {
                idList.add(recipeIds.get(i));
                dateList.add(currDate);

                pStmt.setDate(1, currDate);
                pStmt.setInt(2, recipeIds.get(i));
                pStmt.setDate(3, recipeEndDateBeforeChangeList.get(i));

                pStmt.addBatch();
            }
            pStmt.executeBatch();
            static_commitTransAction(conn);

        } catch (BatchUpdateException batchEx) {
            throw new BatchUpdateException(batchEx);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readMultipleRecipesByList(recipeIds, dateList);
    }
}
