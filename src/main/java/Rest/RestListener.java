package main.java.Rest;

import main.java.BusinessLogic.BLLUser;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;
import main.java.Rest.DTO.RestDTO_DataType_1;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Path("/data")
public class RestListener {

	@GET
	@Path("/users")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserDTO> UserReturn(UserDTO dto,List<RoleDTO> roleList, @QueryParam("action") String action, @QueryParam("aktive") boolean aktive, @QueryParam("in_aktive") boolean in_aktive ){

		UserDTO user0 = new UserDTO(0,action,String.valueOf(aktive),in_aktive);
		UserDTO user1 = new UserDTO(1,"Marshmella Skufilidus","MS",false);
		UserDTO user2 = new UserDTO(2,"Anastacia Dumskij Hatjer Dfeil","ADHD",false);
		UserDTO user3 = new UserDTO(3,"Pjil Ikkah Iskritjethar ","PIK",false);

		List<UserDTO> list = new LinkedList<>();

		list.add(user0);
		list.add(user1);
		list.add(user2);
		list.add(user3);

		return list;
	}

	@GET
	@Path("/Roles")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<RoleDTO> RolesReturn(RestDTO_DataType_1 dto,@QueryParam("action") String action, @QueryParam("aktive") boolean aktive, @QueryParam("in_aktive") boolean in_aktive ){

		RoleDTO role1 = new RoleDTO(1,"NyRolle");
		RoleDTO role2 = new RoleDTO(2,"NyRolle2");
		RoleDTO role3 = new RoleDTO(3,"NyRolle3");;

		List<RoleDTO> list = new LinkedList<>();

		list.add(role1);
		list.add(role2);
		list.add(role3);

		return list;
	}
}

