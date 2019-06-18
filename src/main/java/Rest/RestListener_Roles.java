package main.java.Rest;

import main.java.BusinessLogic.BLLRole;
import main.java.Core.RoleDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("roles")
public class RestListener_Roles implements I_RestListener_Roles{

    @Path("create")
    @POST
    public Response createRole(RoleDTO role) {
        RoleDTO returnRole = null;
        try{
            returnRole = new BLLRole().createRole(role);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnRole).build();
    }

    @Path("get")
    @GET
    public Response getAllRoles() {
        List<RoleDTO> returnRoleList = null;
        try{
            returnRoleList = new BLLRole().getAllRoles();
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnRoleList).build();
    }

    @Path("get/{id}")
    @GET
    public Response getRoleById(@PathParam("id") int roleId) {
        RoleDTO returnRole = null;
        try{
            returnRole = new BLLRole().getRoleById(roleId);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnRole).build();
    }

    @Path("get/search/{keyword}")
    @GET
    public Response getRolesBySearch(@PathParam("keyword") String keyWord) {
        List<RoleDTO> returnRoleList = null;
        try{
            returnRoleList = new BLLRole().getRolebySearch(keyWord);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnRoleList).build();
    }

    @Path("update")
    @PUT
    public Response updateRole(RoleDTO role) {
        RoleDTO returnRole = null;
        try{
            returnRole = new BLLRole().updateRole(role);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnRole).build();
    }
}