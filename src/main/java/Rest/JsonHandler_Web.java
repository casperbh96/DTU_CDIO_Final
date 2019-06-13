package rest;
/*
import main.java.BusinessLogic.BLLUser;
import main.java.BusinessLogic.I_BLLUser;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;
import rest.DTO.RestDTO_DataType_1;
import  main.java.rest.JsonHandlerStoredMethods;

public class JsonHandler_Web implements rest.I_JsonHandler {

    public RestDTO_DataType_1 handleJson(RestDTO_DataType_1 restDto){
        switch(restDto.getAction()){
            case "create":
                return optionCreate(restDto);
            case "read":
                return null;
            case "update":
                return null;
            case "delete":
                return null;
            default:
                return null;
        }
    }

    private RestDTO_DataType_1 optionCreate(RestDTO_DataType_1 restDto){

        switch (restDto.getValue_1()){
            case "users":
                UserDTO user = JsonHandlerStoredMethods.createUserFromRestDTO(restDto);// Antager at value2 er = columns Data.
               // RoleDTO roles = JsonHandlerStoredMethods.createRolesFromRestDTO(restDto);
                I_BLLUser bllUser = new BLLUser();

                return bllUser.createUser();;
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
                 return null;
        }
    }
}
*/