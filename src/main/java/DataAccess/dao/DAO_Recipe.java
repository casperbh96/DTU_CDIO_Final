package DataAccess.dao;

import DataAccess.dto.RecipeDTO;
import DataAccess.dto.ResourceDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static DataAccess.dao.Connector.static_commitTransAction;
import static DataAccess.dao.Connector.static_createConnection;
import static DataAccess.dao.Connector.static_startTransAction;
import static DataAccess.dao.Connector.*;

public class DAO_Recipe implements I_DAL_Recipe {
    @Override
    public RecipeDTO createSingleRecipe(RecipeDTO singleRecipe) {
        return null;
    }

    @Override
    public List<RecipeDTO> createMultipleRecipes(List<RecipeDTO> listOfRecipes) {
        return null;
    }

    @Override
    public RecipeDTO readSingleRecipebyId(int RecipeId) {
        return null;
    }

    @Override
    public List<RecipeDTO> readMultipleRecipesByList(List<Integer> listOfRecipeIds) throws SQLException {
        RecipeDTO res;
        List<RecipeDTO> recipeList = new ArrayList<>();

        // Produce string with number of ? equal to size of listOfResourceIds
        String parameters = static_parameterBuilder(listOfRecipeIds);


        try (Connection conn = static_createConnection()) {
            // Turns into SELECT * FROM resources WHERE resource_id IN () with the string builder in the parenthesis
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM recipes WHERE recipe_id IN (" + parameters + ")");

            // Set each of the ? to the corresponding Id from listOfResourceIds
            int index = 1;
            for (int i : listOfRecipeIds) {
                pStmt.setInt(index++, i);
            }

            ResultSet resultset = pStmt.executeQuery();

            while (resultset.next()) {
                PreparedStatement pStmt2 = conn.prepareStatement("SELECT * FROM rel_recipes_resources WHERE recipe_id = ? AND recipe_end_date = ?");

                pStmt2.setInt(1,resultset.getInt(1));
                pStmt2.setTimestamp(2,resultset.getTimestamp(2));

                ResultSet resultSet2 = pStmt2.executeQuery();

                //loop over the used resources in current iterations recipe
                List<ResourceDTO> resourceAmounts = new ArrayList<>();
                ResourceDTO resourceAmount;
                while(resultSet2.next()){
                    //resourceAmount = new ResourceDTO(resultSet2.getInt(1),resultSet2.getDouble(4));
                    //resourceAmounts.add(resourceAmount);
                }

                res = new RecipeDTO(resultset.getInt(1),resultset.getTimestamp(2),resultset.getString(3),
                        resultset.getDouble(4),resultset.getInt(5), resourceAmounts);
                recipeList.add(res);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return recipeList;
    }

    @Override
    public List<RecipeDTO> readRecipebySearch(String keyword) {
        return null;
    }

    @Override
    public List<RecipeDTO> readAllRecipes() {
        return null;
    }

    @Override
    public RecipeDTO updateSingleRecipe(RecipeDTO Recipe) {
        return null;
    }

    @Override
    public List<RecipeDTO> updateMultipleRecipes(List<RecipeDTO> listOfRecipes) {
        return null;
    }

    @Override
    public RecipeDTO deleteSingleRecipe(RecipeDTO Recipe) {
        return null;
    }

    @Override
    public List<RecipeDTO> deleteMultipleRecipes(List<RecipeDTO> listOfRecipes) {
        return null;
    }
}
