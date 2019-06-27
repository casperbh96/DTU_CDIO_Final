package main.java.Rest;

import main.java.BusinessLogic.BLLResource;
import main.java.BusinessLogic.I_BLLResource;
import main.java.Core.ResourceDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("resources")
public class RestListener_Resource implements I_RestListener_Resource {
    I_BLLResource resourceBLL = new BLLResource();

    @Path("create")
    @POST
    public Response createResource(ResourceDTO res) {
        System.out.println(res);

        ResourceDTO returnRes = null;
        try{
            returnRes = resourceBLL.createSingleResource(res);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnRes).build();
    }

    @Path("get")
    @GET
    public Response getAllResources() {
        List<ResourceDTO> res = null;
        List<ResourceDTO> returnRes = new ArrayList<>();
        try{
            res = resourceBLL.readAllResources();
            for(ResourceDTO r : res){
                if(r.getInactive() == false){
                    returnRes.add(r);
                }
            }

        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnRes).build();
    }

    @Path("get/{id}")
    @GET
    public Response getResourceById(@PathParam("id") int resId) {
        System.out.println(resId);
        ResourceDTO res = null;
        try{
            res = resourceBLL.readSingleResourcebyId(resId);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(res).build();
    }

    @Path("get/newid")
    @GET
    public Response getNewResourceId() {
        List<ResourceDTO> res = null;
        ResourceDTO resDTO = new ResourceDTO();
        int id;
        try{
            res = resourceBLL.readAllResources();
            resDTO.setResourceId(res.get(res.size()-1).getResourceId()+1);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(resDTO).build();
    }

    @Path("get/search/{keyword}")
    @GET
    public Response getResourcesBySearch(@PathParam("keyword") String search) {
        System.out.println(search);
        List<ResourceDTO> res = null;
        try{
            res = resourceBLL.readResourcebySearch(search);
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
            returnResource = resourceBLL.updateSingleResource(res);
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
            returnResource = resourceBLL.setInactiveSingleResource(res.getResourceId());
        } catch (SQLException ex) {
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnResource).build();
    }
}
