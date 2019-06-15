package main.java.rest;

import main.java.BusinessLogic.BLLUser;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.LinkedList;
import java.util.List;


@Path("/data")
public class RestListener {

	@GET
//	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserDTO UserReturn(UserDTO dto){
	// List<RoleDTO> roleList, @QueryParam("action") String action, @QueryParam("aktive") boolean aktive, @QueryParam("in_aktive") boolean in_aktive


		UserDTO user1 = new UserDTO(1,"Marshmella Skufilidus","MS",false);

		return user1;
	}




}

