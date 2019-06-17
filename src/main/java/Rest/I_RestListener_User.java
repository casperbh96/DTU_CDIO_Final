package main.java.Rest;

import com.google.gson.JsonObject;
import main.java.Core.UserDTO;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

public interface I_RestListener_User {
    // -- USER -- USER -- USER -- USER --
    public Response createUser(UserDTO user);//@QueryParam("userDTO") String userDTO, @QueryParam("roleDTOs") String roleDTOs);
    public Response getUser(int userId);
    public Response getUsersBySearch(String search);
    public UserDTO updateUser(@QueryParam("userDTO") String userDTO, @QueryParam("roleDTOs") String roleDTOs);
    public UserDTO deleteUser(@QueryParam("userDTO") String userDTO);
}

