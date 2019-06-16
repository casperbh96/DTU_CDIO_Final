package main.java.DataAccess.dao;

import main.java.Core.REL_RecipeResourceDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static main.java.DataAccess.dao.Connector.*;
import static main.java.DataAccess.dao.SQL_Manipulation.static_parameterBuilder;
import static main.java.DataAccess.dao.SQL_Manipulation.static_setIntPreparedStatements;

public class DAO_REL_RecipeResource implements I_DAL_REL_RecipeResource {

    private List<REL_RecipeResourceDTO> resultSetWhileLoop(ResultSet resultset) throws SQLException {
        REL_RecipeResourceDTO relRecipeResource = null;
        List<REL_RecipeResourceDTO> relRecipeResourceList = new ArrayList<>();

        try{
            while (resultset.next()) {
                relRecipeResource = new REL_RecipeResourceDTO(resultset.getInt(1), resultset.getInt(2), resultset.getDate(3), resultset.getDouble(4),resultset.getDouble(5));
                relRecipeResourceList.add(relRecipeResource);
            }
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
        return relRecipeResourceList;
    }

    private PreparedStatement setCreatePreparedStatement(PreparedStatement pStmt, REL_RecipeResourceDTO relRecipeResource) throws SQLException {
        try{
            pStmt.setInt(1, relRecipeResource.getResouceId());
            pStmt.setInt(2, relRecipeResource.getRecipeId());
            pStmt.setDate(3, relRecipeResource.getRecipeEndDate());
            pStmt.setDouble(4, relRecipeResource.getResourceAmount());
            pStmt.setDouble(5, relRecipeResource.getTolerance());

        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    private PreparedStatement setUpdatePreparedStatement(PreparedStatement pStmt, REL_RecipeResourceDTO relRecipeResource) throws SQLException {
        try{
            pStmt.setDouble(1, relRecipeResource.getResourceAmount());
            pStmt.setDouble(2, relRecipeResource.getTolerance());
            pStmt.setInt(3, relRecipeResource.getResouceId());
            pStmt.setInt(4, relRecipeResource.getRecipeId());
            pStmt.setDate(5, relRecipeResource.getRecipeEndDate());
        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    @Override
    public REL_RecipeResourceDTO createSingleRecipeResource(REL_RecipeResourceDTO singleRecipeResource) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO rel_recipes_resources (resource_id, recipe_id, recipe_end_date, resource_amount, tolerance) VALUES (?,?,?,?,?)");

            setCreatePreparedStatement(pStmt, singleRecipeResource);

            pStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readSingleRecipeResourcebyId(singleRecipeResource.getResouceId(), singleRecipeResource.getRecipeId(), singleRecipeResource.getRecipeEndDate());
    }

    @Override
    public List<REL_RecipeResourceDTO> createMultipleRecipeResources(List<REL_RecipeResourceDTO> listOfRecipeResources) throws SQLException {
        List<Integer> resIdList = new ArrayList<>();
        List<Integer> recIdList = new ArrayList<>();
        List<Date> recDateList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO rel_recipes_resources (resource_id, recipe_id, recipe_end_date, resource_amount, tolerance) VALUES (?,?,?,?,?)");

            for (REL_RecipeResourceDTO relRecipeResource : listOfRecipeResources) {
                resIdList.add(relRecipeResource.getResouceId());
                recIdList.add(relRecipeResource.getRecipeId());
                recDateList.add(relRecipeResource.getRecipeEndDate());

                setCreatePreparedStatement(pStmt, relRecipeResource);

                pStmt.addBatch();
            }
            pStmt.executeBatch();
            static_commitTransAction(conn);
        } catch (BatchUpdateException batchEx) {
            throw new BatchUpdateException(batchEx);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readMultipleRecipeResourcesByList(resIdList,recIdList,recDateList);
    }

    @Override
    public REL_RecipeResourceDTO readSingleRecipeResourcebyId(int resourceId, int recipeId, Date recipeEndDate) throws SQLException {
        REL_RecipeResourceDTO relRecipeResource = null;

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM rel_recipes_resources WHERE resource_id = ? AND recipe_id = ? AND recipe_end_date = ?");

            pStmt.setInt(1, resourceId);
            pStmt.setInt(2, recipeId);
            pStmt.setDate(3, recipeEndDate);
            ResultSet resultset = pStmt.executeQuery();

            // Move pointer to first row before Id, then to row with Id (fix)
            resultset.beforeFirst();
            resultset.next();

            relRecipeResource = new REL_RecipeResourceDTO(resultset.getInt(1), resultset.getInt(2), resultset.getDate(3), resultset.getDouble(4),resultset.getDouble(5));
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return relRecipeResource;
    }

    @Override
    public List<REL_RecipeResourceDTO> readMultipleRecipeResourcesByList(List<Integer> listOfResourceIds, List<Integer> listOfRecipeIds, List<Date> listOfRecipeEndDates) throws SQLException {
        List<REL_RecipeResourceDTO> relRecipeResources = new ArrayList<>();

        String resourceIdsParameters = static_parameterBuilder(listOfResourceIds);

        String recipeIdsParameters = static_parameterBuilder(listOfRecipeIds);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < listOfRecipeEndDates.size(); i++) {
            if (i == listOfRecipeEndDates.size() - 1) {
                builder.append("?");
            } else {
                builder.append("?,");
            }
        }
        String recipeEndDatesParameters = builder.toString();

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM rel_recipes_resources WHERE resource_id IN (" + resourceIdsParameters + "recipe_id IN (" + recipeIdsParameters + ") AND recipe_end_date IN (" + recipeEndDatesParameters + ")");

//            static_setIntPreparedStatements(pStmt,listOfRecipeIds);

            for (int i = 0; i < listOfRecipeIds.size(); i++) {
                pStmt.setInt(i+1, listOfResourceIds.get(i));
                pStmt.setInt(i+1+listOfResourceIds.size(), listOfRecipeIds.get(i));
                pStmt.setDate(i+1+listOfResourceIds.size()+listOfRecipeIds.size(), listOfRecipeEndDates.get(i));
            }

            ResultSet resultset = pStmt.executeQuery();

            relRecipeResources = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return relRecipeResources;
    }


    @Override
    public List<REL_RecipeResourceDTO> readRecipeResourcebySearch(String keyword) throws SQLException {
        List<REL_RecipeResourceDTO> relRecipeResources = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("select * from rel_recipes_resources " +
                    "WHERE resource_id LIKE ? OR recipe_id LIKE ? OR recipe_end_date LIKE ? OR resource_amount LIKE ? OR tolerance LIKE ?");
            pStmt.setString(1, "%" + keyword + "%");
            pStmt.setString(2, "%" + keyword + "%");
            pStmt.setString(3, "%" + keyword + "%");
            pStmt.setString(4, "%" + keyword + "%");
            pStmt.setString(5, "%" + keyword + "%");

            ResultSet resultset = pStmt.executeQuery();

            relRecipeResources = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return relRecipeResources;
    }

    @Override
    public List<REL_RecipeResourceDTO> readAllRecipeResources() throws SQLException {
        List<REL_RecipeResourceDTO> relRecipeResources = new ArrayList<>();

        try (Connection connection = static_createConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM rel_recipes_resources");
            ResultSet resultset = pStmt.executeQuery();

            relRecipeResources = resultSetWhileLoop(resultset);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relRecipeResources;
    }

    @Override
    public REL_RecipeResourceDTO updateSingleRecipeResource(REL_RecipeResourceDTO recipeResource) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("UPDATE rel_recipe_resources SET resource_amount = ?, tolerance = ? WHERE resource_id = ? AND recipe_id = ? AND recipe_end_date = ?");

            setUpdatePreparedStatement(pStmt, recipeResource);

            pStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readSingleRecipeResourcebyId(recipeResource.getResouceId(),recipeResource.getRecipeId(),recipeResource.getRecipeEndDate());
    }

    @Override
    public List<REL_RecipeResourceDTO> updateMultipleRecipeResources(List<REL_RecipeResourceDTO> listOfRecipeResources) throws SQLException {
        List<Integer> resIdList = new ArrayList<>();
        List<Integer> recIdList = new ArrayList<>();
        List<Date> recDateList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("UPDATE rel_recipe_resources SET resource_amount = ?, tolerance = ? WHERE resource_id = ? AND recipe_id = ? AND recipe_end_date = ?");

            for (REL_RecipeResourceDTO relRecipeResource : listOfRecipeResources) {
                resIdList.add(relRecipeResource.getResouceId());
                recIdList.add(relRecipeResource.getRecipeId());
                recDateList.add(relRecipeResource.getRecipeEndDate());

                setUpdatePreparedStatement(pStmt, relRecipeResource);
                pStmt.addBatch();
            }

            pStmt.executeBatch();
            static_commitTransAction(conn);

        } catch (BatchUpdateException batchEx){
            throw new BatchUpdateException(batchEx);
        }
        return readMultipleRecipeResourcesByList(resIdList,recIdList,recDateList);
    }

    @Override
    public void deleteSingleRecipeResource(int resourceId, int recipeId, Date recipeEndDate) throws SQLException {
        try(Connection connection = static_createConnection()){

            PreparedStatement pStmt = connection.prepareStatement("DELETE FROM rel_recipes_resources WHERE resource_id = ? AND recipe_id = ? AND recipe_end_date = ?");

            pStmt.setInt(1, resourceId);
            pStmt.setInt(2, recipeId);
            pStmt.setDate(3, recipeEndDate);
            pStmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMultipleRecipeResources(List<Integer> listOfResourceIds, List<Integer> listOfRecipeIds, List<Date> listOfRecipeEndDates) throws SQLException {
        try (Connection connection = static_createConnection()) {
            static_startTransAction(connection);
            PreparedStatement pStmt = connection.prepareStatement("DELETE FROM rel_recipes_resources WHERE resource_id = ? AND recipe_id = ? AND recipe_end_date = ?");

            for (int i = 0; i < listOfResourceIds.size(); i++) {

                pStmt.setInt(1, listOfResourceIds.get(i));
                pStmt.setInt(2, listOfRecipeIds.get(i));
                pStmt.setDate(3, listOfRecipeEndDates.get(i));

                pStmt.addBatch();
            }
            pStmt.executeBatch();
            static_commitTransAction(connection);

        }  catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }
}
