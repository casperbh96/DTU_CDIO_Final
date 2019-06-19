package main.java.Rest;

import main.java.BusinessLogic.BLLUser;
import main.java.BusinessLogic.I_BLLUser;
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
    I_BLLUser userBLL = new BLLUser();

    @Path("create")
    @POST
    public Response createUser(UserDTO user) {
        System.out.println(user);

        UserDTO returnUser = null;
        try{
            returnUser = userBLL.createUser(user);
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
            user = userBLL.getAllUsers();
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
            user = userBLL.getUserById(userId);
        } catch (SQLException ex){
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
            user = userBLL.getUserBySearch(search);
        } catch (SQLException ex){
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
            returnUser = userBLL.updateUser(user);
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
            returnUser = userBLL.deleteUser(user.getUserId());
        } catch (SQLException ex) {
            return Response.status(400).entity("SQLException: " + ex.getMessage()).build();
        }

        return Response.ok(returnUser).build();
    }
}
