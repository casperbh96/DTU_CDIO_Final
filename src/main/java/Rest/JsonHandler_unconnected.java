package main.java.rest;

import main.java.BusinessLogic.BLLUser;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.management.relation.Role;
import java.sql.SQLException;
import java.util.List;

public class JsonHandler_unconnected implements I_JsonHandler{

// -- User -- User -- User -- User -- User -- User -- User -- User -- User --
//create
    public UserDTO createUserFromJSON(UserDTO user, List<RoleDTO> roles) {

        UserDTO newDTO = new UserDTO();
        newDTO.setInactive(false);
        newDTO.setInitials("newDTO");
        newDTO.setUsername("newDTO");
        newDTO.setUserId(0);

        return newDTO;

    }
//read
    public UserDTO getUsers(String searchMethod, String Id){

        UserDTO empty = new UserDTO();
        empty.setInactive(true);
        empty.setInitials("deleted");
        empty.setUsername("deleted");
        empty.setUserId(0);

        return empty;

    }
    public UserDTO searchUsersByRow(UserDTO searchParameters){

        UserDTO empty = new UserDTO();
        empty.setInactive(true);
        empty.setInitials("deleted");
        empty.setUsername("deleted");
        empty.setUserId(0);

        return empty;

    }
    public UserDTO searchUsersByKeyword(String keyword){

        UserDTO empty = new UserDTO();
        empty.setInactive(true);
        empty.setInitials("deleted");
        empty.setUsername("deleted");
        empty.setUserId(0);

        return empty;

    }
//update
    public UserDTO updateUserFromJSON(UserDTO user, List<RoleDTO> roles) {

        UserDTO newDTO = new UserDTO();
        newDTO.setInactive(false);
        newDTO.setInitials("newDTO");
        newDTO.setUsername("newDTO");
        newDTO.setUserId(0);

        return newDTO;

    }
//delete
    public UserDTO deleteUserFromJSON(UserDTO user){

        UserDTO empty = new UserDTO();
        empty.setInactive(true);
        empty.setInitials("deleted");
        empty.setUsername("deleted");
        empty.setUserId(0);
        
        return empty;

    }


// -- Roles -- Roles -- Roles -- Roles -- Roles -- Roles -- Roles -- Roles --
//create
    public UserDTO createRolesFromJSON(UserDTO user, List<RoleDTO> roles){
        return null;
    }
//Read
    public UserDTO getRoles(String searchMethod, String Id){
        return null;
    }
    public UserDTO searchRolesByKeyword(String keyword){
        return null;
    }
//Update
    public UserDTO updateRolesFromJSON(UserDTO user, List<RoleDTO> roles){
        return null;
    }
//Delete
    public UserDTO deleteRolesFromJSON(UserDTO user){
        return null;
    }




}