package main.java.Rest;

import main.java.Core.UserDTO;

import javax.ws.rs.QueryParam;

public interface I_RestListener {
// -- USER -- USER -- USER -- USER --
UserDTO createUser(@QueryParam("userDTO") String userDTO, @QueryParam("roleDTOs") String roleDTOs);
    UserDTO getUser(@QueryParam("searchMethod") String searchMethod, @QueryParam("Id") String Id);
    UserDTO searchUsers(@QueryParam("searchMethod") String searchMethod, @QueryParam("KeyWord") String keyWord);
    UserDTO updateUser(@QueryParam("userDTO") String userDTO, @QueryParam("roleDTOs") String roleDTOs);
    UserDTO deleteUser(@QueryParam("userDTO") String userDTO);




}
