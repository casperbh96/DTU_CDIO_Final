package main.java.Rest;

import main.java.BusinessLogic.BLLRole;
import main.java.BusinessLogic.BLLUser;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.management.relation.Role;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JsonHandler_unconnected implements I_JsonHandler{
// -- User -- User -- User -- User -- User -- User -- User -- User -- User --
    public UserDTO createUserFromJSON(UserDTO user, List<RoleDTO> roles) {

        UserDTO newDTO = new UserDTO();
        newDTO.setInactive(false);
        newDTO.setInitials("newDTO");
        newDTO.setUsername("newDTO");
        newDTO.setUserId(0);

        return newDTO;

    } // Create
    public UserDTO getUsers(String searchMethod, String Id){
        UserDTO Users = new UserDTO();
        Users.setInactive(true);
        Users.setUserId(0);
        if(searchMethod.equals("searchById")){
            Users.setInitials("userById");
            Users.setUsername("userById");
        }else{
            Users.setInitials("allUser");
            Users.setUsername("allUser");
        }
        return Users;

    }               // Read
    public UserDTO searchUsersByRow(UserDTO searchParameters){

        UserDTO empty = new UserDTO();
        empty.setInactive(true);
        empty.setInitials("SearchResultByRow");
        empty.setUsername("SearchResultByRow");
        empty.setUserId(0);

        return empty;

    }
    public UserDTO searchUsersByKeyword(String keyword){

        UserDTO empty = new UserDTO();
        empty.setInactive(true);
        empty.setInitials("SearchResultByKeyword");
        empty.setUsername("SearchResultByKeyword");
        empty.setUserId(0);

        return empty;

    }
    public UserDTO updateUserFromJSON(UserDTO user, List<RoleDTO> roles) {

        UserDTO newDTO = new UserDTO();
        newDTO.setInactive(false);
        newDTO.setInitials("UpdateUserDTO");
        newDTO.setUsername("UpdateUserDTO");
        newDTO.setUserId(0);

        return newDTO;

    } // Update
    public UserDTO deleteUserFromJSON(UserDTO user){

        UserDTO empty = new UserDTO();
        empty.setInactive(true);
        empty.setInitials("DeletedDTO");
        empty.setUsername("DeletedDTO");
        empty.setUserId(0);
        
        return empty;

    }                       // Delete

// -- Roles -- Roles -- Roles -- Roles -- Roles -- Roles -- Roles -- Roles --
    public List<RoleDTO> createRolesFromJSON(List<RoleDTO> roles){

        List<RoleDTO> roleList = new LinkedList<>();
        for(int i = 0 ; i < roles.size() ; i++){
            RoleDTO roledto = new RoleDTO();
            roledto.setRolename("CreateRole_" + i);
            roledto.setRoleId(i);
        }
        return roleList;

        /*BLLRole blRole = new BLLRole();
        try {
            return blRole.createRoles(roles) ;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }*/
    }         //create
    public List<RoleDTO> getRoles(){

        List<RoleDTO> roleList = new LinkedList<>();
        for(int i = 0 ; i < 3 ; i++){
            RoleDTO roledto = new RoleDTO();
            roledto.setRolename("GetRoles_" + i);
            roledto.setRoleId(i);
        }
        return roleList;

    }                                       //Read
    public List<RoleDTO> searchRolesByKeyword(String keyword){

        List<RoleDTO> roleList = new LinkedList<>();
        for(int i = 0 ; i < 2 ; i++){
            RoleDTO roledto = new RoleDTO();
            roledto.setRolename("RolesBySearchKeyword_" + i);
            roledto.setRoleId(i);
        }
        return roleList;

    }
    public List<RoleDTO> updateRolesFromJSON(List<RoleDTO> roles){

        List<RoleDTO> roleList = new LinkedList<>();
        for(int i = 0 ; i < 2 ; i++){
            RoleDTO roledto = new RoleDTO();
            roledto.setRolename("UpdatedRole" + i);
            roledto.setRoleId(i);
        }
        return roleList;

    }         //Update
    public List<RoleDTO> deleteRolesFromJSON(List<RoleDTO> roles){

        List<RoleDTO> roleList = new LinkedList<>();
        for(int i = 0 ; i < 2 ; i++){
            RoleDTO roledto = new RoleDTO();
            roledto.setRolename("DeletedRole" + i);
            roledto.setRoleId(i);
        }
        return roleList;

    }         //Delete


/*


       for ( RoleDTO role : roles) {
            role.setRoleId();
            role.setRolename();
        }
 */

}