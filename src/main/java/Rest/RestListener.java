package main.java.rest;

import main.java.BusinessLogic.BLLUser;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.management.relation.Role;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.LinkedList;
import java.util.List;

@Path("/data")
public class RestListener {
	@GET
//	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserDTO UserReturn(
		@QueryParam("action") String action,
		@QueryParam("tableCategories") String tableCategories, @QueryParam("tableValues") String tableValues,
		@QueryParam("userRolesNames")  String rolenames,@QueryParam("userRolesNames")  String roleIds,
		@QueryParam("range") String range,
		@QueryParam("aktive") boolean aktive,@QueryParam("in_aktive") boolean in_aktive

		){
		UserDTO user = new UserDTO(1,"bob","b",false);
		//List<RoleDTO> roles = JsonDTOassembler.assembleRestRoleDTO(rolenames,roleIds);

		return user;


	}
}

