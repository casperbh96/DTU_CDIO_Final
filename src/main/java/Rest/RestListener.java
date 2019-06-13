package rest;

import rest.DTO.RestDTO_DataType_1;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("DataType1")
public class RestListener {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String postHello(RestDTO_DataType_1 dto){

		I_JsonConverter jsonConv = new JsonConverter();
		jsonConv.httpGET_web_Data1(dto);
		return "hej";
	}
}

