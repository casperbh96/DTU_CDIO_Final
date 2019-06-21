package main.java.Rest;

import main.java.BusinessLogic.BLLProductBatchResourceBatch;
import main.java.BusinessLogic.I_BLLProductBatchResourceBatch;
import main.java.Core.REL_ProductBatchResourceBatchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("productbatchresourcebatch")
public class RestListener_ProductBatchResourceBatch implements I_RestListener_ProductBatchResourceBatch {
    I_BLLProductBatchResourceBatch productBatchResourceBatchBLL = new BLLProductBatchResourceBatch();

    @Path("create")
    @POST
    public Response createProductBatchResourceBatch(REL_ProductBatchResourceBatchDTO pbrb) {
        System.out.println(pbrb);

        REL_ProductBatchResourceBatchDTO returnProductBatch = null;
        try{
            returnProductBatch = productBatchResourceBatchBLL.createSingleProductBatchResourceBatch(pbrb);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnProductBatch).build();
    }

    @Path("get")
    @GET
    public Response getAllProductBatchResourceBatches() {
        List<REL_ProductBatchResourceBatchDTO> productBatchList = null;
        try{
            productBatchList = productBatchResourceBatchBLL.readAllProductBatchResourceBatchs();
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(productBatchList).build();
    }

    @Path("get/{resourceid}/{productbatchid}")
    @GET
    public Response getProductBatchResourceBatchById(@PathParam("resourceid") int resourceBatchId,@PathParam("productbatchid") int productBatchId) {
        System.out.println(productBatchId);
        REL_ProductBatchResourceBatchDTO productBatch = null;
        try{
            productBatch = productBatchResourceBatchBLL.readSingleProductBatchResourceBatchbyId(resourceBatchId,productBatchId);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(productBatch).build();
    }

    @Path("get/search/{keyword}")
    @GET
    public Response getProductBatchResourceBatchesBySearch(String search) {
        System.out.println(search);
        List<REL_ProductBatchResourceBatchDTO> productBatchList = null;
        try{
            productBatchList = productBatchResourceBatchBLL.readProductBatchResourceBatchbySearch(search);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(productBatchList).build();
    }

    @Path("update")
    @PUT
    public Response updateProductBatchResourceBatch(REL_ProductBatchResourceBatchDTO user) {
        System.out.println(user);
        REL_ProductBatchResourceBatchDTO returnProductBatch = null;
        try{
            returnProductBatch = productBatchResourceBatchBLL.updateSingleProductBatchResourceBatch(user);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnProductBatch).build();
    }

    @Path("delete/{resourceid}/{productbatchid}")
    @DELETE
    public Response deleteProductBatchResourceBatch(@PathParam("resourceid") int resourceBatchId,@PathParam("productbatchid") int productBatchId) {
        System.out.println(productBatchId);
        try{
            productBatchResourceBatchBLL.deleteSingleProductBatchResourceBatch(resourceBatchId, productBatchId);
        } catch (SQLException ex) {
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok().build();
    }
}
