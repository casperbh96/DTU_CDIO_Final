package main.java.BusinessLogic;

import main.java.Core.REL_RecipeResourceDTO;
import main.java.DataAccess.dao.DAO_REL_RecipeResource;
import main.java.DataAccess.dao.I_DAL_REL_RecipeResource;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class BLLRecipeResource implements I_BLLRecipeResource {
    I_DAL_REL_RecipeResource daoRecRes = new DAO_REL_RecipeResource();

    @Override
    public REL_RecipeResourceDTO createSingleRecipeResource(REL_RecipeResourceDTO singleRecipeResource) throws SQLException {
        return daoRecRes.createSingleRecipeResource(singleRecipeResource);
    }

    @Override
    public List<REL_RecipeResourceDTO> createMultipleRecipeResources(List<REL_RecipeResourceDTO> listOfRecipeResources) throws SQLException {
        return daoRecRes.createMultipleRecipeResources(listOfRecipeResources);
    }

    @Override
    public REL_RecipeResourceDTO readSingleRecipeResourcebyId(int resourceId, int recipeId, Date recipeEndDate) throws SQLException {
        return daoRecRes.readSingleRecipeResourcebyId(resourceId, recipeId, recipeEndDate);
    }

    @Override
    public List<REL_RecipeResourceDTO> readAllRecipeResourcesByRecipeId(int recipeId) throws SQLException {
        return daoRecRes.readAllRecipeResourcesByRecipeId(recipeId);
    }

    @Override
    public List<REL_RecipeResourceDTO> readResourcesForRecipe(int recipeId, Date recipeEndDate) throws SQLException {
        return daoRecRes.readResourcesForRecipe(recipeId, recipeEndDate);
    }

    @Override
public List<REL_RecipeResourceDTO> readMultipleRecipeResourcesByList(List<Integer> listOfResourceIds, List<Integer> listOfRecipeIds, List<Date> listOfRecipeEndDates) throws SQLException {
        return daoRecRes.readMultipleRecipeResourcesByList(listOfResourceIds, listOfRecipeIds, listOfRecipeEndDates);
    }

    @Override
    public List<REL_RecipeResourceDTO> readRecipeResourcebySearch(String keyword) throws SQLException {
        return daoRecRes.readRecipeResourcebySearch(keyword);
    }

    @Override
    public List<REL_RecipeResourceDTO> readAllRecipeResources() throws SQLException {
        return daoRecRes.readAllRecipeResources();
    }

    @Override
    public REL_RecipeResourceDTO updateSingleRecipeResource(REL_RecipeResourceDTO recipeResource) throws SQLException {
        return daoRecRes.updateSingleRecipeResource(recipeResource);
    }

    @Override
    public List<REL_RecipeResourceDTO> updateMultipleRecipeResources(List<REL_RecipeResourceDTO> listOfRecipeResources) throws SQLException {
        return daoRecRes.updateMultipleRecipeResources(listOfRecipeResources);
    }

    @Override
    public void deleteSingleRecipeResource(int resourceId, int recipeId, Date recipeEndDate) throws SQLException {
        daoRecRes.deleteSingleRecipeResource(resourceId, recipeId, recipeEndDate);
    }

    @Override
    public void deleteMultipleRecipeResources(List<Integer> listOfResourceIds, List<Integer> listOfRecipeIds, List<Date> listOfRecipeEndDates) throws SQLException {
        daoRecRes.deleteMultipleRecipeResources(listOfResourceIds, listOfRecipeIds, listOfRecipeEndDates);
    }
}
