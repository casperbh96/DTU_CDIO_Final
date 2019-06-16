package main.java.Rest;

import main.java.BusinessLogic.BLLRole;
import main.java.BusinessLogic.BLLUser;
import main.java.Core.ResourceDTO;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.management.relation.Role;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JsonHandler_unconnected{
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
    public UserDTO updateUser(UserDTO user, List<RoleDTO> roles) {

        UserDTO newDTO = new UserDTO();
        newDTO.setInactive(false);
        newDTO.setInitials("UpdateUserDTO");
        newDTO.setUsername("UpdateUserDTO");
        newDTO.setUserId(0);

        return newDTO;

    } // Update
    public UserDTO deleteUser(UserDTO user){

        UserDTO empty = new UserDTO();
        empty.setInactive(true);
        empty.setInitials("DeletedDTO");
        empty.setUsername("DeletedDTO");
        empty.setUserId(0);
        
        return empty;

    }                       // Delete
    //todo inaktive String - inclusive, exclusive


// -- Roles -- Roles -- Roles -- Roles -- Roles -- Roles -- Roles -- Roles --
    public List<RoleDTO> createRoles(List<RoleDTO> roles){

        List<RoleDTO> roleList = new LinkedList<>();

        RoleDTO roledto = new RoleDTO();
        roledto.setRolename("CreateRole_" + 1);
        roledto.setRoleId(1);
        roleList.add(roledto);

        return roleList;

        /*BLLRole blRole = new BLLRole();
        try {
            return blRole.createRoles(roles) ;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }*/
    }         //create
    public List<RoleDTO> getRoles(String searchMethod, String Id){

        List<RoleDTO> roleList = new LinkedList<>();
        String name;
        int q;
        if( searchMethod.equals("searchById") ){
            q = 1;
            name = "RoleSearchById_"+Id+"_";
        }else{
            q = 3;
            name = "RoleGetAll_";
        }

        for(int i = 0 ; i < q ; i++){
            RoleDTO roledto = new RoleDTO();
            roledto.setRolename(name + i);
            roledto.setRoleId(i);
            roleList.add(roledto);
        }

        return roleList;
    }                                       //Read
    public List<RoleDTO> searchRolesByKeyword(String keyword){

        List<RoleDTO> roleList = new LinkedList<>();
        for(int i = 0 ; i < 3 ; i++){
            RoleDTO roledto = new RoleDTO();
            roledto.setRolename("SearchByKeyword_"+keyword+"_" + i);
            roledto.setRoleId(i);
            roleList.add(roledto);
        }
        return roleList;

    }
    public List<RoleDTO> updateRoles(List<RoleDTO> roles){

        List<RoleDTO> roleList = new LinkedList<>();
        for(int i = 0 ; i < 2 ; i++){
            RoleDTO roledto = new RoleDTO();
            roledto.setRolename("UpdatedRole" + i);
            roledto.setRoleId(i);
            roleList.add(roledto);
        }
        return roleList;

    }         //Update
    public List<RoleDTO> deleteRoles(List<RoleDTO> roles){

        List<RoleDTO> roleList = new LinkedList<>();
        for(int i = 0 ; i < 2 ; i++){
            RoleDTO roledto = new RoleDTO();
            roledto.setRolename("DeletedRole_" + i);
            roledto.setRoleId(i);
            roleList.add(roledto);
        }
        return roleList;

    }         //Delete

// Ingredients
// -- Resources -- Resources -- Resources -- Resources -- Resources -- Resources --
    public List<ResourceDTO> createResource(List<ResourceDTO> resources){

        List<ResourceDTO> resourceList = new LinkedList<>();

        for(int i = 0; i < resources.size(); i++) {
            ResourceDTO resourceDto = new ResourceDTO(0,"",false,false);
            resourceDto.setResourceId(i);
            resourceDto.setResourceName("CreateResource_"+i);
            resourceDto.setReorder(false);
            resourceDto.setInactive(false);
            resourceList.add(resourceDto);
        }

        return resourceList;
    }         //create
    public List<ResourceDTO> getResource(String searchMethod, String Id){

        List<ResourceDTO> resourceList = new LinkedList<>();
        String name;
        int q;
        if( searchMethod.equals("searchById") ){
            q = 1;
            name = "ResourceSearchById_"+Id+"_";
        }else{
            q = 3;
            name = "ResourceGetAll_";
        }

        for(int i = 0 ; i < q ; i++){
            ResourceDTO resourceDto = new ResourceDTO(0,"",false,false);
            resourceDto.setResourceId(i);
            resourceDto.setResourceName(name+i);
            resourceDto.setReorder(false);
            resourceDto.setInactive(false);
            resourceList.add(resourceDto);
        }

        return resourceList;
    }                                       //Read
    public List<ResourceDTO> searchResource(String searchMethod,String keyword){

        String name = "resSearchByKey_";
        if( searchMethod.equals("searchByRow") ){
            name = "resSearchByRow_";
        }

        List<ResourceDTO> resourceList = new LinkedList<>();
        for(int i = 0 ; i < 3 ; i++){
            ResourceDTO resourceDto = new ResourceDTO(0,"",false,false);
            resourceDto.setResourceId(i);
            resourceDto.setResourceName(name+i);
            resourceDto.setReorder(false);
            resourceDto.setInactive(false);
            resourceList.add(resourceDto);
        }
        return resourceList;

    }
    public List<ResourceDTO> updateResource(List<ResourceDTO> resources){

        List<ResourceDTO> resourceList = new LinkedList<>();
        for(int i = 0 ; i < 2 ; i++){
            ResourceDTO resourceDto = new ResourceDTO(0,"",false,false);
            resourceDto.setResourceId(i);
            resourceDto.setResourceName("updateRes_"+i);
            resourceDto.setReorder(false);
            resourceDto.setInactive(false);
            resourceList.add(resourceDto);
        }
        return resourceList;

    }         //Update
    public List<ResourceDTO> deleteResource(List<ResourceDTO> resources){

        List<ResourceDTO> resourceList = new LinkedList<>();
        for(int i = 0 ; i < 2 ; i++){
            ResourceDTO resourceDto = new ResourceDTO(0,"",false,false);
            resourceDto.setResourceId(i);
            resourceDto.setResourceName("deleteResource_"+i);
            resourceDto.setReorder(false);
            resourceDto.setInactive(false);
            resourceList.add(resourceDto);
        }
        return resourceList;

    }         //Delete
    public List<ResourceDTO> ReOrdersResources(){

        List<ResourceDTO> resourceList = new LinkedList<>();
        for(int i = 0 ; i < 2 ; i++){
            ResourceDTO resourceDto = new ResourceDTO(0,"",false,false);
            resourceDto.setResourceId(i);
            resourceDto.setResourceName("ResourceReOrders_"+i);
            resourceDto.setReorder(false);
            resourceDto.setInactive(false);
            resourceList.add(resourceDto);
        }
        return resourceList;

    }
    //todo inaktive String - inclusive, exclusive




}