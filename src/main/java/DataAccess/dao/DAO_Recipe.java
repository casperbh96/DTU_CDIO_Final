package DataAccess.dao;

import DataAccess.dto.RecipeDTO;
import DataAccess.dto.ResourceDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static DataAccess.dao.Connector.*;

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
