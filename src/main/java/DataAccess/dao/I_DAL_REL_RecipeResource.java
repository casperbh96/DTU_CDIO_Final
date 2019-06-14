package main.java.DataAccess.dao;

import main.java.Core.REL_RecipeResourceDTO;

import java.sql.SQLException;
import java.util.List;

public interface I_DAL_REL_RecipeResource {

    REL_RecipeResourceDTO createSingleRecipeResource(REL_RecipeResourceDTO singleRecipeResource ) throws SQLException;
    List<REL_RecipeResourceDTO> createMultipleRecipeResources(List<REL_RecipeResourceDTO> listOfRecipeResources ) throws SQLException;

    REL_RecipeResourceDTO readSingleRecipeResourcebyId(int recipeResourceId ) throws SQLException;
    List<REL_RecipeResourceDTO> readMultipleRecipeResourcesByList (List<Integer> listOfRecipeResourceIds) throws SQLException;
    List<REL_RecipeResourceDTO> readRecipeResourcebySearch(String keyword) throws SQLException;
    List<REL_RecipeResourceDTO> readAllRecipeResources() throws SQLException;

    REL_RecipeResourceDTO updateSingleRecipeResource(REL_RecipeResourceDTO recipeResource) throws SQLException;
    List<REL_RecipeResourceDTO> updateMultipleRecipeResources(List<REL_RecipeResourceDTO> listOfRecipeResources) throws SQLException;

    REL_RecipeResourceDTO deleteSingleRecipeResource(int recipeResourceId) throws SQLException;
    REL_RecipeResourceDTO deleteMultipleRecipeResources(List<Integer> listOfRecipeResourceIds) throws SQLException;

}
