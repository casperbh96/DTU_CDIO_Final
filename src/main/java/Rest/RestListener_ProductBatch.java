package main.java.Rest;

import main.java.BusinessLogic.BLLProductBatch;
import main.java.BusinessLogic.I_BLLProductBatch;
import main.java.Core.ProductBatchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("productbatch")
public class RestListener_ProductBatch implements I_RestListener_ProductBatch {
    I_BLLProductBatch productBatchBLL = new BLLProductBatch();

    @Path("create")
    @POST
    public Response createProductBatch(ProductBatchDTO productBatch) {
        System.out.println(productBatch);

        ProductBatchDTO returnProductBatch = null;
        try{
            returnProductBatch = productBatchBLL.createProductBatchButNotIncludedResourceBatches(productBatch);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnProductBatch).build();
    }

    @Path("get")
    @GET
    public Response getAllProductBatches() {
        List<ProductBatchDTO> productBatchList = null;
        try{
            productBatchList = productBatchBLL.getAllProductBatchs();
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(productBatchList).build();
    }

    @Path("get/{id}")
    @GET
    public Response getProductBatchById(@PathParam("id") int productBatchId) {
        System.out.println(productBatchId);
        ProductBatchDTO productBatch = null;
        try{
            productBatch = productBatchBLL.getProductBatchById(productBatchId);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(productBatch).build();
    }

    @Path("get/search/{keyword}")
    @GET
    public Response getProductBatchesBySearch(@PathParam("keyword") String search) {
        System.out.println(search);
        List<ProductBatchDTO> productBatchList = null;
        try{
            productBatchList = productBatchBLL.getProductBatchBySearch(search);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(productBatchList).build();
    }

    @Path("update")
    @PUT
    public Response updateProductBatch(ProductBatchDTO productBatch) {
        System.out.println(productBatch);
        ProductBatchDTO returnProductBatch = null;
        try{
            returnProductBatch = productBatchBLL.updateProductBatchButNotResourceBatches(productBatch);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnProductBatch).build();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteProductBatch(@PathParam("id") int productBatchId) {
        System.out.println(productBatchId);
        ProductBatchDTO returnProductBatch = null;
        try{
            returnProductBatch = productBatchBLL.setInactiveSingleProductBatch(productBatchId);
        } catch (SQLException ex) {
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnProductBatch).build();
    }
}
