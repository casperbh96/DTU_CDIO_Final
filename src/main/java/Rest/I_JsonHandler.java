package main.java.Rest;

import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import java.util.List;

public interface I_JsonHandler {
//--- USERS --- USERS --- USERS --- USERS --- USERS --- USERS --- USERS ---
//Create
UserDTO createUserFromJSON(UserDTO user, List<RoleDTO> roles);
//Read
UserDTO getUsers(String searchMethod, String Id);
    UserDTO searchUsersByRow(UserDTO searchParameters);
    UserDTO searchUsersByKeyword(String keyword);
//Update
UserDTO updateUserFromJSON(UserDTO user, List<RoleDTO> roles);
//Delete
UserDTO deleteUserFromJSON(UserDTO user);


//--- ROLES --- ROLES --- ROLES --- ROLES --- ROLES --- ROLES --- ROLES ---
//Create
UserDTO createRolesFromJSON(UserDTO user, List<RoleDTO> roles);
//Read
UserDTO getRoles(String searchMethod, String Id);
    UserDTO searchRolesByKeyword(String keyword);
//Update
UserDTO updateRolesFromJSON(UserDTO user, List<RoleDTO> roles);
//Delete
UserDTO deleteRolesFromJSON(UserDTO user);





}
