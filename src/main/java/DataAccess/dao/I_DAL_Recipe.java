package main.java.DataAccess.dao;

import main.java.Core.RecipeDTO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface I_DAL_Recipe extends Serializable {

    RecipeDTO createSingleRecipe( RecipeDTO singleRecipe ) throws SQLException;
    List<RecipeDTO> createMultipleRecipes( List<RecipeDTO> listOfRecipes ) throws SQLException;

    RecipeDTO readSingleRecipeById(int recipeId ) throws SQLException;
    List<RecipeDTO> readMultipleRecipesByList (List<Integer> listOfRecipeIds) throws SQLException;
    List<RecipeDTO> readRecipeBySearch(String keyword) throws SQLException;
    List<RecipeDTO> readAllRecipes() throws SQLException;

    RecipeDTO updateSingleRecipe(RecipeDTO recipe) throws SQLException;
    List<RecipeDTO> updateMultipleRecipes(List<RecipeDTO> listOfRecipes) throws SQLException;

    RecipeDTO setRecipeEndDateSingleRecipe(RecipeDTO recipe) throws SQLException;
    List<RecipeDTO> setRecipeEndDateMultipleRecipes(List<RecipeDTO> listOfRecipes) throws SQLException;
}
