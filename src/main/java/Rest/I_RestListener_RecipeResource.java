package main.java.Rest;

import main.java.Core.REL_RecipeResourceDTO;

import javax.ws.rs.core.Response;

public interface I_RestListener_RecipeResource {
    Response createRecipeResource(REL_RecipeResourceDTO recRes);
    Response getAllRecipeResources();
    Response getRecipeResourceById(int resourceId, int recipeId, String recipeEndDate);
    Response getRecipeResourcesBySearch(String search);
    Response updateRecipeResource(REL_RecipeResourceDTO recRes);
    Response deleteRecipeResource(int resourceId, int recipeId, String recipeEndDate);
}
