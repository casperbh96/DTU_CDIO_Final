package main.java.Rest;

import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("users")
public class RestListener_User implements I_RestListener_User {

    JsonHandler_unconnected jsonHandler = new JsonHandler_unconnected();
    //JsonHandler jsonHandler = new JsonHandler();

    @Path("create")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO createUser(@QueryParam("userDTO") String userDTO, @QueryParam("roleDTOs") String roleDTOs) {

        UserDTO user = JsonDTOassembler.assembleRestUserDTO(userDTO);
        List<RoleDTO> roles = JsonDTOassembler.assembleRestRoleDTO(roleDTOs);
        return jsonHandler.createUserFromJSON(user, roles);

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUser(@QueryParam("searchMethod") String searchMethod, @QueryParam("Id") String Id) {

        return jsonHandler.getUsers(searchMethod, Id);

    }
    @Path("search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO searchUsers(@QueryParam("searchMethod") String searchMethod, @QueryParam("keyword") String keyWord) {
        if (searchMethod.equals("searchUsersByRow")) {

            UserDTO userParameters = JsonDTOassembler.assembleRestUserDTO(keyWord);
            return jsonHandler.searchUsersByRow(userParameters);

        } else if(searchMethod.equals("searchUsersById")){

            return jsonHandler.searchUsersByKeyword(keyWord);

        }else{

            return null;

        }
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
