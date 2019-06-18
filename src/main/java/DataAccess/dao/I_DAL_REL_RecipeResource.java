package main.java.DataAccess.dao;

import main.java.Core.REL_RecipeResourceDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface I_DAL_REL_RecipeResource {

    REL_RecipeResourceDTO createSingleRecipeResource(REL_RecipeResourceDTO singleRecipeResource ) throws SQLException;
    List<REL_RecipeResourceDTO> createMultipleRecipeResources(List<REL_RecipeResourceDTO> listOfRecipeResources ) throws SQLException;

    REL_RecipeResourceDTO readSingleRecipeResourcebyId(int resourceId, int recipeId, Date recipeEndDate) throws SQLException;
    List<REL_RecipeResourceDTO> readResourcesForRecipe(int recipeId, Date recipeEndDate) throws SQLException;
    List<REL_RecipeResourceDTO> readMultipleRecipeResourcesByList (List<Integer> listOfResourceIds, List<Integer> listOfRecipeIds, List<Date> listOfRecipeEndDates) throws SQLException;
    List<REL_RecipeResourceDTO> readRecipeResourcebySearch(String keyword) throws SQLException;
    List<REL_RecipeResourceDTO> readAllRecipeResources() throws SQLException;

    REL_RecipeResourceDTO updateSingleRecipeResource(REL_RecipeResourceDTO recipeResource) throws SQLException;
    List<REL_RecipeResourceDTO> updateMultipleRecipeResources(List<REL_RecipeResourceDTO> listOfRecipeResources) throws SQLException;

    void deleteSingleRecipeResource(int resourceId, int recipeId, Date recipeEndDate) throws SQLException;
    void deleteMultipleRecipeResources(List<Integer> listOfResourceIds, List<Integer> listOfRecipeIds, List<Date> listOfRecipeEndDates) throws SQLException;

}
