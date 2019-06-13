package main.java.DataAccess.dao;

import main.java.Core.RecipeDTO;

import java.sql.*;
import java.util.List;

public class DAO_Recipe implements I_DAL_Recipe {
    @Override
    public RecipeDTO createSingleRecipe(RecipeDTO singleRecipe) throws SQLException {
        return null;
    }

    @Override
    public List<RecipeDTO> createMultipleRecipes(List<RecipeDTO> listOfRecipes) throws SQLException {
        return null;
    }

    @Override
    public RecipeDTO readSingleRecipebyId(int recipeId) throws SQLException {
        return null;
    }

    @Override
    public List<RecipeDTO> readMultipleRecipesByList(List<Integer> listOfRecipeIds) throws SQLException {
        return null;
    }

    @Override
    public List<RecipeDTO> readRecipebySearch(String keyword) throws SQLException {
        return null;
    }

    @Override
    public List<RecipeDTO> readAllRecipes() throws SQLException {
        return null;
    }

    @Override
    public RecipeDTO updateSingleRecipe(RecipeDTO recipe) throws SQLException {
        return null;
    }

    @Override
    public List<RecipeDTO> updateMultipleRecipes(List<RecipeDTO> listOfRecipes) throws SQLException {
        return null;
    }
}
