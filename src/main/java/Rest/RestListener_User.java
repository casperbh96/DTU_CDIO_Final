package main.java.Rest;

import com.google.gson.JsonObject;
import main.java.BusinessLogic.BLLUser;
import main.java.BusinessLogic.I_BLLUser;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("users")
public class RestListener_User implements I_RestListener_User {

    JsonHandler_unconnected jsonHandler = new JsonHandler_unconnected();
    //JsonHandler jsonHandler = new JsonHandler();

    @Path("create")
    @POST
    public Response createUser(UserDTO user) {
        System.out.println(user);
        return Response.ok(user).build();
    }

    @Path("get/{id}")
    @GET
    public Response getUser(@PathParam("id") int userId) {
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
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO updateUser(@QueryParam("userDTO") String userDTO, @QueryParam("roleDTOs") String roleDTOs) {

        UserDTO user = JsonDTOassembler.assembleRestUserDTO(userDTO);
        List<RoleDTO> roles = JsonDTOassembler.assembleRestRoleDTO(roleDTOs);
        return jsonHandler.updateUser(user, roles);

    }
    @Path("delete")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO deleteUser(@QueryParam("userDTO") String userDTO) {

        UserDTO user = JsonDTOassembler.assembleRestUserDTO(userDTO);
        return jsonHandler.deleteUser(user);

    }
}
