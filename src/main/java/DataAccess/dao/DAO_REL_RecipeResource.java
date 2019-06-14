package main.java.DataAccess.dao;

import main.java.Core.REL_RecipeResourceDTO;

import java.sql.SQLException;
import java.util.List;

public class DAO_REL_RecipeResource implements I_DAL_REL_RecipeResource {
    @Override
    public REL_RecipeResourceDTO createSingleRecipeResource(REL_RecipeResourceDTO singleRecipeResource) throws SQLException {
        return null;
    }

    @Override
    public List<REL_RecipeResourceDTO> createMultipleRecipeResources(List<REL_RecipeResourceDTO> listOfRecipeResources) throws SQLException {
        return null;
    }

    @Override
    public REL_RecipeResourceDTO readSingleRecipeResourcebyId(int recipeResourceId) throws SQLException {
        return null;
    }

    @Override
    public List<REL_RecipeResourceDTO> readMultipleRecipeResourcesByList(List<Integer> listOfRecipeResourceIds) throws SQLException {
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
    public REL_RecipeResourceDTO deleteSingleRecipeResource(int recipeResourceId) throws SQLException {
        return null;
    }

    @Override
    public REL_RecipeResourceDTO deleteMultipleRecipeResources(List<Integer> listOfRecipeResourceIds) throws SQLException {
        return null;
    }
}
