package main.java.rest;

import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.management.relation.Role;
import java.util.LinkedList;
import java.util.List;

public class JsonDTOassembler {

    public static UserDTO assembleRestUserDTO(String categories, String values){
        UserDTO user = new UserDTO();
        String tCategories[] = categories.split(";");
        String tValues[]= values.split(";");

        for(int i = 0; i< tCategories.length; i++){
            switch (tCategories[i]){
                case "userId":
                    user.setUserId(Integer.parseInt(tValues[i]));
                    break;
                case "username":
                    user.setUsername(tValues[i]);
                    break;
                case "initials":
                    user.setInitials(tValues[i]);
                    break;
                case "inactive":
                    user.setInactive(Boolean.parseBoolean(tValues[i]));
                    break;
            }
        }
        return user;
    }
    public static List<RoleDTO> assembleRestRoleDTO(String roleNames, String roleIds){
        UserDTO user = new UserDTO();
        String tRoleNames[] = roleNames.split(";");
        String tRoleIds[]= roleIds.split(";");

        List<RoleDTO> roles = new LinkedList<>();
        roles.add(new RoleDTO(1,"hek"));
        return roles;
    }



}
