package main.java.Rest;

import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import java.util.List;

public interface I_JsonHandler {
//--- USERS --- USERS --- USERS --- USERS --- USERS --- USERS --- USERS ---
    UserDTO createUserFromJSON(UserDTO user, List<RoleDTO> roles);//Create
    UserDTO getUsers(String searchMethod, String Id);//Read
    UserDTO searchUsersByRow(UserDTO searchParameters);
    UserDTO searchUsersByKeyword(String keyword);
    UserDTO updateUserFromJSON(UserDTO user, List<RoleDTO> roles);//Update
    UserDTO deleteUserFromJSON(UserDTO user);//Delete


//--- ROLES --- ROLES --- ROLES --- ROLES --- ROLES --- ROLES --- ROLES ---
    List<RoleDTO> createRolesFromJSON(List<RoleDTO> roles);//Create
    List<RoleDTO> getRoles();//Read
    List<RoleDTO> searchRolesByKeyword(String keyword);
    List<RoleDTO> updateRolesFromJSON(List<RoleDTO> roles);//Update
    List<RoleDTO> deleteRolesFromJSON(List<RoleDTO> roles);//Delete





}
