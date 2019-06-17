package main.java.Rest;

import main.java.Core.UserDTO;

import javax.ws.rs.QueryParam;
import java.util.List;

public interface I_RestListener_User {
    // -- USER -- USER -- USER -- USER --
    public UserDTO createUser(UserDTO user);//@QueryParam("userDTO") String userDTO, @QueryParam("roleDTOs") String roleDTOs);
    public List<UserDTO> getUser(String search);
    public UserDTO searchUsers(@QueryParam("searchMethod") String searchMethod, @QueryParam("KeyWord") String keyWord);
    public UserDTO updateUser(@QueryParam("userDTO") String userDTO, @QueryParam("roleDTOs") String roleDTOs);
    public UserDTO deleteUser(@QueryParam("userDTO") String userDTO);
}

