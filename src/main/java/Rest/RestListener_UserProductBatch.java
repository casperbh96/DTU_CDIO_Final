package main.java.Rest;

import main.java.BusinessLogic.BLLUserProductBatch;
import main.java.BusinessLogic.I_BLLUserProductBatch;
import main.java.Core.REL_UserProductBatchDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@SuppressWarnings("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("userproductbatch")
public class RestListener_UserProductBatch implements I_RestListener_UserProductBatch {
    I_BLLUserProductBatch userProductBatchBLL = new BLLUserProductBatch();
    @Path("create")
    @POST
    public Response createRoleUser(REL_UserProductBatchDTO roleUser) {
        return null;
    }

    @Path("get")
    @GET
    public Response getAllRoleUsers() {
        return null;
    }

    @Path("get/{id}")
    @GET
    public Response getRoleUserByUserId(@PathParam("id") int userId) {
        return null;
    }

    @Path("delete")
    @DELETE
    public Response deleteRoleUser(REL_UserProductBatchDTO roleUser) {
        return null;
    }
}
