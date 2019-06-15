package main.java.rest;

import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import java.util.List;

public interface I_JsonHandler {
//--- USERS --- USERS --- USERS --- USERS --- USERS --- USERS --- USERS ---
//Create
    public UserDTO createUserFromJSON(UserDTO user, List<RoleDTO> roles);
//Read
    public UserDTO getUsers(String searchMethod, String Id);
    public UserDTO searchUsersByRow(UserDTO searchParameters);
    public UserDTO searchUsersByKeyword(String keyword);
//Update
    public UserDTO updateUserFromJSON(UserDTO user, List<RoleDTO> roles);
//Delete
    public UserDTO deleteUserFromJSON(UserDTO user);


//--- ROLES --- ROLES --- ROLES --- ROLES --- ROLES --- ROLES --- ROLES ---
//Create
    public UserDTO createRolesFromJSON(UserDTO user, List<RoleDTO> roles);
//Read
    public UserDTO getRoles(String searchMethod, String Id);
    public UserDTO searchRolesByKeyword(String keyword);
//Update
    public UserDTO updateRolesFromJSON(UserDTO user, List<RoleDTO> roles);
//Delete
    public UserDTO deleteRolesFromJSON(UserDTO user);





}
