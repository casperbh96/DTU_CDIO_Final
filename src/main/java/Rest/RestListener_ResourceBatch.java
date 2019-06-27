package main.java.Rest;

import main.java.BusinessLogic.BLLResourceBatch;
import main.java.BusinessLogic.I_BLLResourceBatch;
import main.java.Core.ResourceBatchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("resourcebatch")
public class RestListener_ResourceBatch implements I_RestListener_ResourceBatch {
    I_BLLResourceBatch resourceBatchBLL = new BLLResourceBatch();

    @Path("create")
    @POST
    public Response createResourceBatch(ResourceBatchDTO resourceBatch) {
        System.out.println(resourceBatch);

        ResourceBatchDTO returnResourceBatch = null;
        try{
            returnResourceBatch = resourceBatchBLL.createSingleResourceBatch(resourceBatch);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnResourceBatch).build();
    }

    @Path("get")
    @GET
    public Response getAllResourceBatches() {
        List<ResourceBatchDTO> resourceBatchList = null;
        List<ResourceBatchDTO> resourceReturn = new ArrayList<>();
        try{
            resourceBatchList = resourceBatchBLL.readAllResourceBatchs();
            for(ResourceBatchDTO r : resourceBatchList){
                if(r.getIsLeftover() == false){
                    resourceReturn.add(r);
                }
            }
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(resourceReturn).build();
    }

    @Path("get/{resourceid}")
    @GET
    public Response getResourceBatchById(@PathParam("resourceid") int resourceBatchId) {
        System.out.println(resourceBatchId);
        ResourceBatchDTO resourceBatch = null;
        try{
            resourceBatch = resourceBatchBLL.readSingleResourceBatchById(resourceBatchId);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(resourceBatch).build();
    }

    @Path("get/search/{keyword}")
    @GET
    public Response getResourceBatchesBySearch(String search) {
        System.out.println(search);
        List<ResourceBatchDTO> resourceBatchList = null;
        try{
            resourceBatchList = resourceBatchBLL.readResourceBatchBySearch(search);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(resourceBatchList).build();
    }

    @Path("update")
    @PUT
    public Response updateResourceBatch(ResourceBatchDTO resourceBatch) {
        System.out.println(resourceBatch);
        ResourceBatchDTO returnResourceBatch = null;
        try{
            returnResourceBatch = resourceBatchBLL.updateSingleResourceBatch(resourceBatch);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnResourceBatch).build();
    }

    @Path("delete/{resourceid}")
    @DELETE
    public Response deleteResourceBatch(@PathParam("resourceid") int resourceBatchId) {
        System.out.println(resourceBatchId);
        ResourceBatchDTO returnResourceBatch = null;
        try{
            returnResourceBatch = resourceBatchBLL.setIsLeftoverSingleResourceBatch(resourceBatchId);
        } catch (SQLException ex) {
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnResourceBatch).build();
    }
}
