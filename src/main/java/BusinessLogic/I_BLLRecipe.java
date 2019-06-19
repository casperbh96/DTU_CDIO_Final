package main.java.BusinessLogic;

import main.java.Core.REL_RecipeResourceDTO;
import main.java.Core.RecipeDTO;
import main.java.Core.ResourceDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface I_BLLRecipe {

    RecipeDTO createRecipeButNotIncludedResources(RecipeDTO recipe) throws SQLException;
    RecipeDTO createRecipe(RecipeDTO singleRecipe, List<Integer> listOfResourceIds, List<Double> resourceAmounts, List<Double> tolerances) throws SQLException;
    List<RecipeDTO> createRecipes(List<RecipeDTO> listOfRecipes, List<List<Integer>> listOfListOfResourceIds, List<List<Double>> listOfListOfResourceAmounts, List<List<Double>> listOfListOfTolerances) throws SQLException;

    RecipeDTO getRecipeById(int recipeId, Date recipeEndDate) throws SQLException;
    RecipeDTO getActiveRecipeById(int recipeId) throws SQLException;
    List<RecipeDTO> getRecipesByList(List<Integer> listOfRecipeIds, List<Date> listOfRecipeEndDates) throws SQLException;
    List<RecipeDTO> getRecipeBySearch(String keyword) throws SQLException;
    List<RecipeDTO> getAllRecipes() throws SQLException;
    List<RecipeDTO> getAllActiveRecipes() throws SQLException;
    List<REL_RecipeResourceDTO> getAllResourcesForRecipe(int recipeId) throws SQLException;

    RecipeDTO updateRecipe(RecipeDTO recipe, List<Integer> resourceIds, List<Double> resourceAmounts, List<Double> tolerances) throws SQLException;
//    List<RecipeDTO> updateMultipleRecipes(List<RecipeDTO> listOfRecipes) throws SQLException;

    RecipeDTO setRecipeEndDateSingleRecipe(int recipeid, Date recipeEndDate) throws SQLException;
    List<RecipeDTO> setRecipeEndDateMultipleRecipes(List<Integer> recipeIds, List<Date> recipeEndDateBeforeChangeList) throws SQLException;

}
