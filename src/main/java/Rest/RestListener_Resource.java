package main.java.Rest;

import main.java.BusinessLogic.BLLResource;
import main.java.Core.ResourceDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("resources")
public class RestListener_Resource implements I_RestListener_Resource {
    @Path("create")
    @POST
    public Response createResource(ResourceDTO res) {
        System.out.println(res);

        ResourceDTO returnRes = null;
        try{
            returnRes = new BLLResource().createSingleResource(res);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnRes).build();
    }

    @Path("get")
    @GET
    public Response getAllResources() {
        List<ResourceDTO> res = null;
        try{
            res = new BLLResource().readAllResources();
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(res).build();
    }

    @Path("get/{id}")
    @GET
    public Response getResourceById(@PathParam("id") int resId) {
        System.out.println(resId);
        ResourceDTO res = null;
        try{
            res = new BLLResource().readSingleResourcebyId(resId);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(res).build();
    }

    @Path("get/search/{keyword}")
    @GET
    public Response getResourcesBySearch(@PathParam("keyword") String search) {
        System.out.println(search);
        List<ResourceDTO> res = null;
        try{
            res = new BLLResource().readResourcebySearch(search);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(res).build();
    }

    @Path("update")
    @PUT
    public Response updateResource(ResourceDTO res) {
        System.out.println(res);
        ResourceDTO returnResource = null;
        try{
            returnResource = new BLLResource().updateSingleResource(res);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnResource).build();
    }

    @Path("delete")
    @DELETE
    public Response deleteResource(ResourceDTO res) {
        System.out.println(res);
        ResourceDTO returnResource = null;
        try{
            returnResource = new BLLResource().setInactiveSingleResource(res.getResourceId());
        } catch (SQLException ex) {
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnResource).build();
    }
}
