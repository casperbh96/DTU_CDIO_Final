package main.java.Rest;

import main.java.BusinessLogic.*;
import main.java.Core.REL_RecipeResourceDTO;
import main.java.Core.RecipeDTO;
import main.java.Core.ResourceDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("recipe")
public class RestListener_Recipe implements I_RestListener_Recipe{
    I_BLLRecipe recipeBLL = new BLLRecipe();
    I_BLLRecipeResource recipeResourceBLL = new BLLRecipeResource();
    I_BLLResource resourceBLL = new BLLResource();

    @Path("create")
    @POST
    public Response createRecipe(RecipeDTO recipe) {
        RecipeDTO rec = null;

        try{
            rec = recipeBLL.createRecipeButNotIncludedResources(recipe);
        } catch(SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }
        return Response.ok(rec).build();
    }

    @Path("get")
    @GET
    public Response getAllRecipes() {
        List<RecipeDTO> recipeList = null;
        try{
            recipeList = recipeBLL.getAllRecipes();
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(recipeList).build();
    }

    @Path("get/{recipeid}")
    @GET
    public Response getAllResourcesByRecipeId(@PathParam("recipeid") int recipeId) {
        List<REL_RecipeResourceDTO> recipeResourceList = null;
        List<ResourceDTO> resourceList = new ArrayList<>();
        List<Integer> listOfResourceIds = new ArrayList<>();

        try{
            recipeResourceList = recipeResourceBLL.readAllRecipeResourcesByRecipeId(recipeId);
            for(REL_RecipeResourceDTO recRes : recipeResourceList){
                listOfResourceIds.add(recRes.getResouceId());
            }
            if(listOfResourceIds.size() > 0){
                resourceList = resourceBLL.readMultipleResourcesByList(listOfResourceIds);
            }
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(resourceList).build();
    }

    @Path("get/{recipeid}/{enddate}")
    @GET
    public Response getRecipeById(@PathParam("recipeid") int recipeId, @PathParam("enddate") String recipeEndDate) {
        System.out.println(recipeId);
        System.out.println(recipeEndDate);

        RecipeDTO recipe = null;
        try{
            recipe = recipeBLL.getRecipeById(recipeId, Date.valueOf(recipeEndDate));
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(recipe).build();
    }

    @Path("get/search/{keyword}")
    @GET
    public Response getRecipesBySearch(@PathParam("keyword") String search) {
        System.out.println(search);
        List<RecipeDTO> recipe = null;
        try{
            recipe = recipeBLL.getRecipeBySearch(search);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(recipe).build();
    }

    @Path("update")
    @PUT
    public Response updateRecipe(RecipeDTO recipe) {
        System.out.println(recipe);
        RecipeDTO returnUser = null;
        try{
            returnUser = recipeBLL.updateRecipeButNotResources(recipe);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnUser).build();
    }

    @Path("delete/{recipeid}/{enddate}")
    @DELETE
    public Response deleteRecipe(@PathParam("recipeid") int recipeId, @PathParam("enddate") String recipeEndDate) {
        System.out.println(recipeId);
        System.out.println(recipeEndDate);

        RecipeDTO recipe = null;
        try{
            recipe = recipeBLL.setRecipeEndDateSingleRecipe(recipeId, Date.valueOf(recipeEndDate));
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(recipe).build();
    }
}