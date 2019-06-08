package DataAccess.dao;

import DataAccess.dto.RecipeDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static DataAccess.dao.Connector.static_commitTransAction;
import static DataAccess.dao.Connector.static_createConnection;
import static DataAccess.dao.Connector.static_startTransAction;
import static DataAccess.dao.Connector.*;

public class DAO_Recipe implements main.java.dal.I_DAL_Recipe {
    @Override
    public RecipeDTO createSingleRecipe(RecipeDTO singleRecipe) {
        return null;
    }

    @Override
    public List<RecipeDTO> createMultipleRecipes(List<RecipeDTO> listOfRecipes) {
        return null;
    }

    @Override
    public RecipeDTO readSingleRecipebyId(int RecipeId) {
        return null;
    }

    @Override
    public List<RecipeDTO> readMultipleRecipesByList(List<Integer> listOfRecipeIds) {
        return null;
    }

    @Override
    public List<RecipeDTO> readRecipebySearch(String keyword) {
        return null;
    }

    @Override
    public List<RecipeDTO> readAllRecipes() {
        return null;
    }

    @Override
    public RecipeDTO updateSingleRecipe(RecipeDTO Recipe) {
        return null;
    }

    @Override
    public List<RecipeDTO> updateMultipleRecipes(List<RecipeDTO> listOfRecipes) {
        return null;
    }

    @Override
    public RecipeDTO deleteSingleRecipe(RecipeDTO Recipe) {
        return null;
    }

    @Override
    public List<RecipeDTO> deleteMultipleRecipes(List<RecipeDTO> listOfRecipes) {
        return null;
    }
}
