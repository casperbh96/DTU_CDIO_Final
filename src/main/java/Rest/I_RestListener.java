package main.java.rest;

import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

public interface I_RestListener {
// -- USER -- USER -- USER -- USER --
    public UserDTO createUser(@QueryParam("userDTO") String userDTO, @QueryParam("roleDTOs") String roleDTOs);
    public UserDTO getUser(@QueryParam("searchMethod") String searchMethod ,@QueryParam("Id") String Id );
    public UserDTO searchUsers(@QueryParam("searchMethod") String searchMethod ,@QueryParam("KeyWord") String keyWord );
    public UserDTO updateUser(@QueryParam("userDTO") String userDTO,@QueryParam("roleDTOs") String roleDTOs);
    public UserDTO deleteUser(@QueryParam("userDTO") String userDTO );




}
