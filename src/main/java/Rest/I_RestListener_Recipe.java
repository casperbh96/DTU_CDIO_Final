package main.java.Rest;

import main.java.Core.RecipeDTO;

import javax.ws.rs.core.Response;

public interface I_RestListener_Recipe {
    Response createRecipe(RecipeDTO recipe);
    Response getAllRecipes();
    Response getRecipeById(int recipeId, String recipeEndDate);
    Response getRecipesBySearch(String search);
    Response updateRecipe(RecipeDTO user);
    Response deleteRecipe(int recipeId, String recipeEndDate);
}
