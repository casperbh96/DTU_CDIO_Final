package main.java.Rest;

import main.java.BusinessLogic.BLLUserProductBatch;
import main.java.BusinessLogic.I_BLLUserProductBatch;
import main.java.Core.REL_UserProductBatchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("userproductbatch")
public class RestListener_UserProductBatch implements I_RestListener_UserProductBatch {
    I_BLLUserProductBatch userProductBatchBLL = new BLLUserProductBatch();
    @Path("create")
    @POST
    public Response createRoleUser(REL_UserProductBatchDTO userProductBatch) {
        System.out.println(userProductBatch);

        try{
            userProductBatchBLL.assignSingleProductBatchUser(userProductBatch);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(userProductBatch).build();
    }

    @Path("get")
    @GET
    public Response getAllRoleUsers() {
        List<REL_UserProductBatchDTO> returnUserProductBatchList = null;
        try{
            returnUserProductBatchList = userProductBatchBLL.readAllProductBatchUsers();
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnUserProductBatchList).build();
    }

    @Path("get/{id}")
    @GET
    public Response getRoleUserByUserId(@PathParam("id") int userId) {
        System.out.println(userId);

        List<REL_UserProductBatchDTO> returnUserProductBatchList = null;
        try{
            returnUserProductBatchList = userProductBatchBLL.readProductBatchesByUserId(userId);

        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnUserProductBatchList).build();
    }

    @Path("delete")
    @DELETE
    public Response deleteRoleUser(REL_UserProductBatchDTO userProductBatch) {
        System.out.println("deleting users roles: " + userProductBatch.toString());

        try{
            userProductBatchBLL.deleteSingleUserProductBatch(userProductBatch.getUserId(), userProductBatch.getProductBatchId());
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok().build();
    }
}
