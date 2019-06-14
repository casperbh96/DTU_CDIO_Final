package main.java.DataAccess.dao;

import main.java.Core.RecipeDTO;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface I_DAL_Recipe extends Serializable {

    RecipeDTO createSingleRecipe( RecipeDTO singleRecipe ) throws SQLException;
    List<RecipeDTO> createMultipleRecipes( List<RecipeDTO> listOfRecipes ) throws SQLException;

    RecipeDTO readSingleRecipeById(int recipeId, Date recipeEndDate) throws SQLException;
    List<RecipeDTO> readMultipleRecipesByList (List<Integer> listOfRecipeIds, List<Date> listOfRecipeEndDates) throws SQLException;
    List<RecipeDTO> readRecipeBySearch(String keyword) throws SQLException;
    List<RecipeDTO> readAllRecipes() throws SQLException;

    RecipeDTO updateSingleRecipe(RecipeDTO recipe) throws SQLException;
    List<RecipeDTO> updateMultipleRecipes(List<RecipeDTO> listOfRecipes) throws SQLException;

    RecipeDTO setRecipeEndDateSingleRecipe(int recipeid, Date recipeEndDate) throws SQLException;
    List<RecipeDTO> setRecipeEndDateMultipleRecipes(List<Integer> recipeIds, List<Date> recipeEndDateBeforeChangeList) throws SQLException;
}
