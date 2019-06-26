package main.java.Rest;

import main.java.Core.REST_DTO_Recipe_withRelResources;
import main.java.Core.RecipeDTO;

import javax.ws.rs.core.Response;
import java.text.ParseException;

public interface I_RestListener_Recipe {
    Response createRecipe(RecipeDTO recipe);
    Response getAllRecipes();
    Response getNewRecipeId();
    Response getRecipeById(int recipeId, String recipeEndDate) throws ParseException;
    Response getRecipesBySearch(String search);
    Response getAllResourcesByRecipeId(int recipeId);
    Response getAllRelRecipeResourcesAndResourcesByRecipeId(int recipeId);
    Response updateRecipe(RecipeDTO recipe);
    Response deleteRecipe(int recipeId, String recipeEndDate);


    Response TestRecipe(REST_DTO_Recipe_withRelResources restTest);
}
