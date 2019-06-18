package main.java.Rest;

import main.java.BusinessLogic.BLLProductBatch;
import main.java.BusinessLogic.I_BLLProductBatch;
import main.java.Core.ProductBatchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@SuppressWarnings("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("productbatch")
public class RestListener_ProductBatch implements I_RestListener_ProductBatch {
    I_BLLProductBatch productBatchBLL = new BLLProductBatch();

    @Path("create")
    @POST
    public Response createProductBatch(ProductBatchDTO recipe) {
        return null;
    }

    @Path("get")
    @GET
    public Response getAllProductBatches() {
        return null;
    }

    @Path("get/{id}")
    @GET
    public Response getProductBatchById(@PathParam("id") int productBatchId) {
        return null;
    }

    @Path("get/search/{keyword}")
    @GET
    public Response getProductBatchesBySearch(@PathParam("keyword") String search) {
        return null;
    }

    @Path("update")
    @PUT
    public Response updateProductBatch(ProductBatchDTO user) {
        return null;
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteProductBatch(@PathParam("id") int productBatchId) {
        return null;
    }
}
