package main.java.Rest;

import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;


public class RestListener{
/*
    @Path("users")
    public class RestListener_User implements I_RestListener_User {
        JsonHandler_unconnected jsonHandler = new JsonHandler_unconnected();

        //JsonHandler jsonHandler = new JsonHandler();
        // -- USERS --
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
        public UserDTO searchUsers(@QueryParam("searchMethod") String searchMethod, @QueryParam("KeyWord") String keyWord) {
            if (searchMethod.equals("searchUsersByRow")) {

                UserDTO userParameters = JsonDTOassembler.assembleRestUserDTO(keyWord);
                return jsonHandler.searchUsersByRow(userParameters);

            } else {

                return jsonHandler.searchUsersByKeyword(keyWord);

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
    @Path("roles")
    public class RestListener_Roles implements I_RestListener_Roles {

        @Path("role/create")
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

    }*/
}