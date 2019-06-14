package main.java.Rest;

import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;
import main.java.Rest.DTO.RestDTO_DataType_1;

import javax.management.relation.Role;
import java.util.LinkedList;
import java.util.List;

public class JsonHandlerStoredMethods {
    public JsonHandlerStoredMethods(){}

    public static UserDTO createUserFromRestDTO(RestDTO_DataType_1 rDTO){
        String tempStr[] = rDTO.getValue_2().split(";");
        String columnsData[][] = new String[tempStr.length][2];
        UserDTO user = new UserDTO(0,"","",false);

        for(int i = 0; i < tempStr.length; i++){
            columnsData[i] = tempStr[i].split("=");
        }
        for(int i =0; i< tempStr.length; i++){
            switch (columnsData[i][0]){
                case "userId":
                    user.setUserId(Integer.parseInt(columnsData[i][1]));
                    break;
                case "username":
                    user.setUsername(columnsData[i][1]);
                    break;
                case "initials":
                    user.setInitials(columnsData[i][1]);
                    break;
                case "inactive":
                    user.setInactive(Boolean.parseBoolean(columnsData[i][1]));
                    break;
            }
        }
        return user;
    }


    public static List<RoleDTO> createRolesFromRestDTO(RestDTO_DataType_1 rDTO){
        try {
            String tempStr[] = rDTO.getValue_3().split(";");
            String columnsData[][] = new String[tempStr.length][2];

            for(int i = 0; i < tempStr.length; i++){
                columnsData[i] = tempStr[i].split("=");
            }

            List<RoleDTO> roleList = new LinkedList<>();
            for (int i = 0; i < columnsData.length; i++) {
                RoleDTO newRole = new RoleDTO(Integer.parseInt(columnsData[i][0]), "knoldeSparker");
            }

            return roleList;
        }
        catch (NullPointerException e){
            return null;
        }
    }
}
