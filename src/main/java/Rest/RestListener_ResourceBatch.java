package main.java.Rest;

import main.java.BusinessLogic.BLLResourceBatch;
import main.java.BusinessLogic.I_BLLResourceBatch;
import main.java.Core.ResourceBatchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@SuppressWarnings("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("resourcebatch")
public class RestListener_ResourceBatch implements I_RestListener_ResourceBatch {
    I_BLLResourceBatch resourceBatchBLL = new BLLResourceBatch();

    @Path("create")
    @POST
    public Response createResourceBatch(ResourceBatchDTO recipe) {
        return null;
    }

    @Path("get")
    @GET
    public Response getAllResourceBatches() {
        return null;
    }

    @Path("get/{resourceid}")
    @GET
    public Response getResourceBatchById(@PathParam("resourceid") int resourceBatchId) {
        return null;
    }

    @Path("get/search/{keyword}")
    @GET
    public Response getResourceBatchesBySearch(String search) {
        return null;
    }

    @Path("update")
    @PUT
    public Response updateResourceBatch(ResourceBatchDTO user) {
        return null;
    }

    @Path("delete/{resourceid}")
    @DELETE
    public Response deleteResourceBatch(@PathParam("resourceid") int resourceBatchId) {
        return null;
    }
}
