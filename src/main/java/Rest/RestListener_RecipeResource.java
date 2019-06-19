package main.java.Rest;

import main.java.BusinessLogic.BLLRecipeResource;
import main.java.BusinessLogic.I_BLLRecipeResource;
import main.java.Core.REL_RecipeResourceDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("reciperesources")
public class RestListener_RecipeResource implements I_RestListener_RecipeResource{
    I_BLLRecipeResource recipeResourceBLL = new BLLRecipeResource();
    @Path("create")
    @POST
    public Response createRecipeResource(REL_RecipeResourceDTO recRes) {
        System.out.println(recRes);

        REL_RecipeResourceDTO returnRecipeResource = null;
        try{
            returnRecipeResource = recipeResourceBLL.createSingleRecipeResource(recRes);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnRecipeResource).build();
    }

    @Path("get")
    @GET
    public Response getAllRecipeResources() {
        List<REL_RecipeResourceDTO> returnRecipeResourceList = null;
        try{
            returnRecipeResourceList = recipeResourceBLL.readAllRecipeResources();
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnRecipeResourceList).build();
    }

    @Path("get/{resourceid}/{recipeid}/{recipeenddate}")
    @GET
    public Response getRecipeResourceById(@PathParam("resourceid") int resourceId,
                                          @PathParam("recipeid") int recipeId,@PathParam("recipeenddate") String recipeEndDate) {
        System.out.println(resourceId);
        System.out.println(recipeId);
        System.out.println(recipeEndDate);

        REL_RecipeResourceDTO recipeResource = null;
        try{
            recipeResource = recipeResourceBLL.readSingleRecipeResourcebyId(resourceId, recipeId, Date.valueOf(recipeEndDate));
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(recipeResource).build();
    }

    @Path("get/search/{keyword}")
    @GET
    public Response getRecipeResourcesBySearch(@PathParam("keyword") String search) {
        System.out.println(search);
        List<REL_RecipeResourceDTO> recipeResourceList = null;
        try{
            recipeResourceList = recipeResourceBLL.readRecipeResourcebySearch(search);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(recipeResourceList).build();
    }

    @Path("update")
    @PUT
    public Response updateRecipeResource(REL_RecipeResourceDTO recRes) {
        System.out.println(recRes);
        REL_RecipeResourceDTO recipeResource = null;
        try{
            recipeResource = recipeResourceBLL.updateSingleRecipeResource(recRes);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(recipeResource).build();
    }

    @Path("delete/{resourceid}/{recipeid}/{recipeenddate}")
    @DELETE
    public Response deleteRecipeResource(@PathParam("resourceid") int resourceId,
                                         @PathParam("recipeid") int recipeId,@PathParam("recipeenddate") String recipeEndDate) {
        System.out.println(resourceId);
        System.out.println(recipeId);
        System.out.println(recipeEndDate);

        try{
            recipeResourceBLL.deleteSingleRecipeResource(resourceId, recipeId, Date.valueOf(recipeEndDate));
        } catch (SQLException ex) {
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok().build();
    }
}
