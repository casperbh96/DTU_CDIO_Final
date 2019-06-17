package main.java.Rest;

import main.java.Core.REL_RoleUserDTO;
import main.java.Core.UserDTO;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

public interface I_RestListener_User {
    Response createUser(UserDTO user);
    Response getAllUsers();
    Response getUserById(int userId);
    Response getUsersBySearch(String search);
    Response updateUser(UserDTO user);
//    Response updateUserRoles(UserDTO user, ArrayList<REL_RoleUserDTO> roleUserList);
    Response deleteUser(UserDTO user);
//    Response deleteUserRoles(UserDTO user, ArrayList<REL_RoleUserDTO> roleUserList);
}

