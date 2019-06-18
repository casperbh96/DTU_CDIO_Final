package main.java.BusinessLogic;

import main.java.Core.REL_RecipeResourceDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class BLLRecipeResource implements I_BLLRecipeResource {


    @Override
    public REL_RecipeResourceDTO createSingleRecipeResource(REL_RecipeResourceDTO singleRecipeResource) throws SQLException {
        return null;
    }

    @Override
    public List<REL_RecipeResourceDTO> createMultipleRecipeResources(List<REL_RecipeResourceDTO> listOfRecipeResources) throws SQLException {
        return null;
    }

    @Override
    public REL_RecipeResourceDTO readSingleRecipeResourcebyId(int resourceId, int recipeId, Date recipeEndDate) throws SQLException {
        return null;
    }

    @Override
    public List<REL_RecipeResourceDTO> readMultipleRecipeResourcesByList(List<Integer> listOfResourceIds, List<Integer> listOfRecipeIds, List<Date> listOfRecipeEndDates) throws SQLException {
        return null;
    }

    @Override
    public List<REL_RecipeResourceDTO> readRecipeResourcebySearch(String keyword) throws SQLException {
        return null;
    }

    @Override
    public List<REL_RecipeResourceDTO> readAllRecipeResources() throws SQLException {
        return null;
    }

    @Override
    public REL_RecipeResourceDTO updateSingleRecipeResource(REL_RecipeResourceDTO recipeResource) throws SQLException {
        return null;
    }

    @Override
    public List<REL_RecipeResourceDTO> updateMultipleRecipeResources(List<REL_RecipeResourceDTO> listOfRecipeResources) throws SQLException {
        return null;
    }

    @Override
    public void deleteSingleRecipeResource(int resourceId, int recipeId, Date recipeEndDate) throws SQLException {

    }

    @Override
    public void deleteMultipleRecipeResources(List<Integer> listOfResourceIds, List<Integer> listOfRecipeIds, List<Date> listOfRecipeEndDates) throws SQLException {

    }
}
