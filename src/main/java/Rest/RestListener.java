package main.java.rest;

import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Path("users")
public class RestListener implements I_RestListener{

    JsonHandler_unconnected jsonHandler = new JsonHandler_unconnected();
	//JsonHandler jsonHandler = new JsonHandler();

// -- Create ---- Create ---- Create ---- Create ---- Create ---- Create ---- Create --

	@Path("create")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserDTO createUser(@QueryParam("userDTO") String userDTO, @QueryParam("roleDTOs") String roleDTOs){

		UserDTO user = JsonDTOassembler.assembleRestUserDTO(userDTO);
		List<RoleDTO> roles = JsonDTOassembler.assembleRestRoleDTO(roleDTOs);
		return jsonHandler.createUserFromJSON(user, roles);

	}

//-- read ---- read ---- read ---- read ---- read ---- read ---- read ---- read ---- read --

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUser(@QueryParam("searchMethod") String searchMethod ,@QueryParam("Id") String Id ) {

        return jsonHandler.getUsers(searchMethod, Id );

	}

    @Path("search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO searchUsers(@QueryParam("searchMethod") String searchMethod ,@QueryParam("KeyWord") String keyWord ){
        if(searchMethod.equals("searchUsersByRow")){

            UserDTO userParameters = JsonDTOassembler.assembleRestUserDTO(keyWord);
            return jsonHandler.searchUsersByRow(userParameters);

        }else{

            return jsonHandler.searchUsersByKeyword(keyWord);

        }
    }

//-- update ---- update ---- update ---- update ---- update ---- update ---- update ---- update --

    @Path("update")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO updateUser(@QueryParam("userDTO") String userDTO,@QueryParam("roleDTOs") String roleDTOs){

        UserDTO user = JsonDTOassembler.assembleRestUserDTO(userDTO);
        List<RoleDTO> roles = JsonDTOassembler.assembleRestRoleDTO(roleDTOs);
        return jsonHandler.updateUserFromJSON(user, roles);

    }

//-- delete ---- delete ---- delete ---- delete ---- delete ---- delete ---- delete ---- delete --

    @Path("delete")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO deleteUser(@QueryParam("userDTO") String userDTO ){

        UserDTO user = JsonDTOassembler.assembleRestUserDTO(userDTO);
        return jsonHandler.deleteUserFromJSON(user);

    }

}