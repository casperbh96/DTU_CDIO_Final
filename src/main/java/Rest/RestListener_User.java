package main.java.Rest;

import main.java.BusinessLogic.BLLUser;
import main.java.BusinessLogic.I_BLLUser;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public UserDTO createUser(UserDTO user) {
        System.out.println(user);
        return user;
    }

    @Path("get")
    @POST
    public List<UserDTO> getUser(String searchField) {
        I_BLLUser u = new BLLUser();
        System.out.println(searchField);
        try{
            List<UserDTO> userList = new ArrayList<>();
            userList = u.getUserBySearch(searchField);
            for(UserDTO use : userList){
                System.out.println(use);
            }
            System.out.println("user list finished");
            return userList;
        } catch(SQLException ex){
            // WHAT TO DO
            System.out.println("there was an error");
        }
        return null;
    }

    @Path("search")
    @GET
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
