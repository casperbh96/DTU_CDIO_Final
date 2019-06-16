package main.java.Rest;

import com.sun.org.apache.xpath.internal.operations.Bool;
import main.java.Core.ResourceDTO;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.annotation.Resource;
import javax.management.relation.Role;
import java.util.LinkedList;
import java.util.List;

public class JsonDTOassembler {
    public static UserDTO assembleRestUserDTO(String userDto){
            UserDTO user = new UserDTO();
            String[] temp = userDto.split(";");
            String[][] userDTOStats = new String[temp.length][2];

            for (int i = 0; i < temp.length; i++) {
                userDTOStats[i] = temp[i].split("=");
            }

            for (int i = 0; i < userDTOStats.length; i++) {
                switch (userDTOStats[i][0]) {
                    case "userId":
                        user.setUserId(Integer.parseInt(userDTOStats[i][1]));
                        break;
                    case "username":
                        user.setUsername(userDTOStats[i][1]);
                        break;
                    case "initials":
                        user.setInitials(userDTOStats[i][1]);
                        break;
                    case "inactive":
                        user.setInactive(Boolean.parseBoolean(userDTOStats[i][1]));
                        break;
                }
            }
            return user;
    }
    public static List<RoleDTO> assembleRestRoleDTO(String roleDTO){

        List<RoleDTO> roles = new LinkedList<>();
        String temp[] = roleDTO.split(";");
        String roleDTOStats[][] = new String[temp.length][2];

        for(int i = 0; i< roleDTOStats.length; i++) {
            roleDTOStats[i] = temp[i].split("=");
        }

        for(int i = 0; i< roleDTOStats.length; i++){
            RoleDTO role = new RoleDTO();
            role.setRoleId(Integer.parseInt(roleDTOStats[i][0]));
            role.setRolename(roleDTOStats[i][1]);
            roles.add(role);
        }

        return roles;
    }
    public static ResourceDTO assembleRestResourceDTO(String resources){

        String[] temporary = resources.split(";");
        String[][] resourceDTOStats = new String[temporary.length][2];

        for (int i = 0; i < temporary.length; i++) {
            resourceDTOStats[i] = temporary[i].split("=");
        }

        ResourceDTO resource = new ResourceDTO(0,"",false,false);

        for (int i = 0; i < resourceDTOStats.length; i++) {
            switch (resourceDTOStats[i][0]) {
                case "resourceId":
                    resource.setResourceId(Integer.parseInt(resourceDTOStats[i][1]));
                    break;
                case "resourceName":
                    resource.setResourceName(resourceDTOStats[i][1]);
                    break;
                case "reorder":
                    resource.setReorder(Boolean.parseBoolean(resourceDTOStats[i][1]));
                    break;
                case "inactive":
                    resource.setInactive(Boolean.parseBoolean(resourceDTOStats[i][1]));
                    break;
            }
        }
        return resource;
    }


}
