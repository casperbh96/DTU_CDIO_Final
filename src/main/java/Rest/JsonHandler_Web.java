package main.java.Rest;

import main.java.BusinessLogic.*;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;
import main.java.Rest.DTO.RestDTO_DataType_1;
import  main.java.Rest.JsonHandlerStoredMethods;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JsonHandler_Web{

    public <Any> Any handleJson(RestDTO_DataType_1 restDto){
        switch (restDto.getValue_1()){
            case "users":
                return (Any) optionUser(restDto);
            case "recipes":
                return null;
            case "product_batches":
                return null;
            case "roles":
                return null;
            case "resources":
                return null;
            case "resource_batches":
                return null;
            default:
                // todo Indsæt forklarende fejl besked.
                return null;
        }
    }

    /* OPTIONS METHODS */
    private List<UserDTO> optionUser(RestDTO_DataType_1 restDTO){
        BLLUser bll_User = new BLLUser();
        UserDTO user;
        List<RoleDTO> roles;

        user = JsonHandlerStoredMethods.createUserFromRestDTO(restDTO); // Asuming User data is in Value 2.
        roles = JsonHandlerStoredMethods.createRolesFromRestDTO(restDTO); // Asuming Role data is in Value 3, syntax( roleid = "name"; roleid = "name";)

        List<UserDTO> freeList = new LinkedList<>();
        switch (restDTO.getAction()){
            case "create":
                try {
                    freeList.add(bll_User.createUser(user, roles));
                    return freeList;
                }catch (SQLException e){
                    e.printStackTrace();
                }
            case "read":
                try {
                    freeList.add(bll_User.getUserById(user.getUserId()));
                    return freeList;
                }catch (SQLException e){
                    e.printStackTrace();
                }
            case "update":
                try {
                    freeList.add(bll_User.updateUser(user));
                    return freeList;
                }catch (SQLException e){
                    e.printStackTrace();
                }
            case "delete":
                bll_User.deleteUser(user.getUserId());
                return null;
            default:
                // todo Indsæt forklarende fejl besked.
                return null;
        }
    }
    private List<RoleDTO> optionroles(RestDTO_DataType_1 restDTO){

        BLLRole bll_role = new BLLRole();
        List<RoleDTO> roles = new LinkedList<>();
        roles = JsonHandlerStoredMethods.createRolesFromRestDTO(restDTO); // Asuming Role data is in Value 3, syntax ( roleid = "name"; roleid = "name"; )

        switch (restDTO.getAction()){
            case "create":
                try{
                    return bll_role.createRoles(roles);
                }catch (SQLException e){
                    e.printStackTrace();
                }
            case "read":
                return null;
            case "update":
                return null;
            case "delete":
                return null;
            default:
                // todo Indsæt forklarende fejl besked.
                return null;
        }
    }


}

