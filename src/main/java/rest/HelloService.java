package main.java.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("hello")
public class HelloService {
	
	@GET
	public String getHello(){
		return "Hello";
	}
	
	@POST
	@Path("{id}")
	public String postHello(@PathParam("id") String name){
		return "Hello " + name;
		
	}

}
