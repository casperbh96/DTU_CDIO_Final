package main.java.Rest;

import main.java.BusinessLogic.*;
import main.java.Core.REL_RecipeResourceDTO;
import main.java.Core.RecipeDTO;
import main.java.Core.ResourceDTO;
import main.java.Core.StringToSqlDateConverter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Path("get/newid")
    @GET
    public Response getNewRecipeId() {
        List<RecipeDTO> recipeList = null;
        RecipeDTO rec = new RecipeDTO();
        try{
            recipeList = recipeBLL.getAllRecipes();
            rec.setRecipeId(recipeList.get(recipeList.size()-1).getRecipeId()+1);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(rec).build();
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

    @Path("get/resources/resourcebatches/{recipeid}")
    @GET
    public Response getAllResourcesWithResourceBatchesByRecipeId(@PathParam("recipeid") int recipeId) {
        Object[] recipeResourceList;

        try{
            recipeResourceList = recipeBLL.getAllResourcesAndResourceBatchesByRecipeId(recipeId);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(recipeResourceList).build();
    }

    @Path("get/relreciperesources/resources/{recipeid}")
    @GET
    public Response getAllRelRecipeResourcesAndResourcesByRecipeId(@PathParam("recipeid") int recipeId) {
        Object[] relRecipeResourceAndResourceList;

        try{
            relRecipeResourceAndResourceList = recipeBLL.getAllRelRecipeResourcesAndResourcesByRecipeId(recipeId);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(relRecipeResourceAndResourceList).build();
    }

    @Path("get/{recipeid}/{enddate}")
    @GET
    public Response getRecipeById(@PathParam("recipeid") int recipeId, @PathParam("enddate") String recipeEndDate) throws ParseException {
        //System.out.println(recipeId + " " + new StringToSqlDateConverter().convertStringToDate(recipeEndDate));

        RecipeDTO recipe = null;
        try{
            recipe = recipeBLL.getRecipeById(recipeId, new StringToSqlDateConverter().convertStringToDate(recipeEndDate));
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        System.out.println(recipe.toString());
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
        System.out.println(recipe.toString());
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
            recipe = recipeBLL.setRecipeEndDateSingleRecipe(recipeId, new StringToSqlDateConverter().convertStringToDate(recipeEndDate));
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        } catch (ParseException e) {
            return Response.status(400).entity("ParseException: " + e.getMessage()).build();
        }

        return Response.ok(recipe).build();
    }
}
