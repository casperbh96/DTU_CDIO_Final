package main.java.Rest;

import main.java.Core.UserDTO;

import javax.ws.rs.core.Response;

public interface I_RestListener_User {
    Response createUser(UserDTO user);
    Response getAllUsers();
    Response getUserById(int userId);
    Response getUsersBySearch(String search);
    Response updateUser(UserDTO user);
    Response deleteUser(UserDTO user);
}

