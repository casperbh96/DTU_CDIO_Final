package rest;

import main.java.BusinessLogic.BLLUser;
import main.java.Core.UserDTO;
import rest.DTO.RestDTO_DataType_1;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import rest.I_JsonConverter;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


@Path("/DataType1")
public class RestListener {

	@GET
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserDTO> postHello(RestDTO_DataType_1 dto)throws Exception{

		//I_JsonConverter jsonConv = new JsonConverter();
		//jsonConv.httpGET_Data1(dto);
			BLLUser blUser = new BLLUser();
			List<UserDTO> users = blUser.getAllUsers();
			return users;
			//StringBuilder strBldr = new StringBuilder();
			//String Json = " hej ";
			/*for(int i = 0; i < users.size(); i++){
				Json = Json + "{'userId':'"+ users.get(i).getUserId() +"','username':'"+users.get(i).getUsername() +"','initials':'"+users.get(i).getInitials() +"','inactive':'"+users.get(i).getInactive() +"'}";
			}*/

			/*UserDTO user1 = new UserDTO(123,"Shanaynay klamydine","KR", false);
			UserDTO user2 = new UserDTO(124,"Marshmella Skumfilados","MS", false);
			UserDTO user3 = new UserDTO(125,"Priscillia von blob","PB", false);
			List<UserDTO> listUsers = new LinkedList<>();
			listUsers.add(user1);
			listUsers.add(user2);
			listUsers.add(user3);*/


	}
}

