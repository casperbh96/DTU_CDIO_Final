package main.java.Rest;

import main.java.Core.UserDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("roles")
public class RestListener_Roles implements I_RestListener_Roles {

    @Path("create")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO createRole(@QueryParam("userDTO") String userDTO, @QueryParam("roleDTOs") String roleDTOs) {
        return null;
    }
    public UserDTO getRole(@QueryParam("searchMethod") String searchMethod, @QueryParam("Id") String Id) {
        return null;
    }
    public UserDTO searchRoles(@QueryParam("searchMethod") String searchMethod, @QueryParam("KeyWord") String keyWord) {
        return null;
    }
    public UserDTO updateRole(@QueryParam("userDTO") String userDTO, @QueryParam("roleDTOs") String roleDTOs) {
        return null;
    }
    public UserDTO deleteRole(@QueryParam("userDTO") String userDTO) {
        return null;
    }

}