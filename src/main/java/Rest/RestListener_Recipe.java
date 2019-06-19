package main.java.Rest;

import main.java.BusinessLogic.BLLRecipe;
import main.java.BusinessLogic.I_BLLRecipe;
import main.java.Core.RecipeDTO;
import main.java.Core.ResourceDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@SuppressWarnings("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("recipe")
public class RestListener_Recipe implements I_RestListener_Recipe{
    I_BLLRecipe recipeBLL = new BLLRecipe();

    @Path("create")
    @POST
    public Response createRecipe(RecipeDTO recipe) {
        try{
            recipeBLL.createRecipe(null, null, null, null);
        } catch(SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }
        return Response.ok().build();
    }

    @Path("get")
    @GET
    public Response getAllRecipes() {
        return null;
    }

    @Path("get/{recipeid}/{enddate}")
    @GET
    public Response getRecipeById(@PathParam("recipeid") int recipeId, @PathParam("enddate") String recipeEndDate) {
        return null;
    }

    @Path("get/search/{keyword}")
    @GET
    public Response getRecipesBySearch(@PathParam("keyword") String search) {
        return null;
    }

    @Path("update")
    @PUT
    public Response updateRecipe(RecipeDTO user) {
        return null;
    }

    @Path("delete/{recipeid}/{enddate}")
    @DELETE
    public Response deleteRecipe(@PathParam("recipeid") int recipeId, @PathParam("enddate") String recipeEndDate) {
        return null;
    }
}
