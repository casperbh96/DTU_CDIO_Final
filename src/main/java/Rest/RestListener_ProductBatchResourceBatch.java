package main.java.Rest;

import main.java.BusinessLogic.BLLProductBatchResourceBatch;
import main.java.BusinessLogic.I_BLLProductBatchResourceBatch;
import main.java.Core.REL_ProductBatchResourceBatchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@SuppressWarnings("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("productbatchresourcebatch")
public class RestListener_ProductBatchResourceBatch implements I_RestListener_ProductBatchResourceBatch {
    I_BLLProductBatchResourceBatch productBatchResourceBatchBLL = new BLLProductBatchResourceBatch();

    @Path("create")
    @POST
    public Response createProductBatchResourceBatch(REL_ProductBatchResourceBatchDTO recipe) {
        return null;
    }

    @Path("get")
    @GET
    public Response getAllProductBatchResourceBatches() {
        return null;
    }

    @Path("get/{resourceid}/{productbatchid}")
    @GET
    public Response getProductBatchResourceBatchById(@PathParam("resourceid") int resourceBatchId,@PathParam("productbatchid") int productBatchId) {
        return null;
    }

    @Path("get/search/{keyword}")
    @GET
    public Response getProductBatchResourceBatchesBySearch(String search) {
        return null;
    }

    @Path("update")
    @PUT
    public Response updateProductBatchResourceBatch(REL_ProductBatchResourceBatchDTO user) {
        return null;
    }

    @Path("delete/{resourceid}/{productbatchid}")
    @DELETE
    public Response deleteProductBatchResourceBatch(@PathParam("resourceid") int resourceBatchId,@PathParam("productbatchid") int productBatchId) {
        return null;
    }
}
