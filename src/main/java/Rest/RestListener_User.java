package main.java.Rest;

import main.java.BusinessLogic.BLLUser;
import main.java.Core.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;


@SuppressWarnings("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("users")
public class RestListener_User implements I_RestListener_User {
    @Path("create")
    @POST
    public Response createUser(UserDTO user) {
        System.out.println(user);

        UserDTO returnUser = null;
        try{
            returnUser = new BLLUser().createUser(user);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnUser).build();
    }

    @Path("get")
    @GET
    public Response getAllUsers() {
        List<UserDTO> user = null;
        try{
            user = new BLLUser().getAllUsers();
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(user).build();
    }

    @Path("get/{id}")
    @GET
    public Response getUserById(@PathParam("id") int userId) {
        System.out.println(userId);
        UserDTO user = null;
        try{
            user = new BLLUser().getUserById(userId);
        } catch (SQLException ex){
            // OOPS
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(user).build();
    }

    @Path("get/search/{keyword}")
    @GET
    public Response getUsersBySearch(@PathParam("keyword") String search) {
        System.out.println(search);
        List<UserDTO> user = null;
        try{
            user = new BLLUser().getUserBySearch(search);
        } catch (SQLException ex){
            // OOPS
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(user).build();
    }

    @Path("update")
    @PUT
    public Response updateUser(UserDTO user) {
        System.out.println(user);
        UserDTO returnUser = null;
        try{
            returnUser = new BLLUser().updateUser(user);
        } catch (SQLException ex){
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnUser).build();
    }

    @Path("delete")
    @DELETE
    public Response deleteUser(UserDTO user) {
        System.out.println(user);
        UserDTO returnUser = null;
        try{
            returnUser = new BLLUser().deleteUser(user.getUserId());
        } catch (SQLException ex) {
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnUser).build();
    }

}
