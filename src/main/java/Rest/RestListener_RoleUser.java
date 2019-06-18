package main.java.Rest;

import main.java.BusinessLogic.BLLRoleUser;
import main.java.Core.REL_RoleUserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("roleuser")
public class RestListener_RoleUser implements I_RestListener_RoleUser {
    @Path("create")
    @POST
    public Response createRoleUser(REL_RoleUserDTO roleUser) {
        System.out.println(roleUser);

        try{
            new BLLRoleUser().assignUserRole(roleUser);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(roleUser).build();
    }

    @Path("get")
    @GET
    public Response getAllRoleUsers() {
        List<REL_RoleUserDTO> returnRoleUserList = null;
        try{
            returnRoleUserList = new BLLRoleUser().readAllUserRoles();
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnRoleUserList).build();
    }

    @Path("get/{id}")
    @GET
    public Response getRoleUserByUserId(@PathParam("id") int userId) {
        System.out.println(userId);

        List<REL_RoleUserDTO> roleUser = null;
        try{
            roleUser = new BLLRoleUser().readUsersRoles(userId);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(roleUser).build();
    }

    @Path("delete")
    @DELETE
    public Response deleteRoleUser(REL_RoleUserDTO roleUser) {
        System.out.println(roleUser);

        try{
            new BLLRoleUser().deleteUserRole(roleUser.getUserId(), roleUser.getRoleId());
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(roleUser).build();
    }
}
