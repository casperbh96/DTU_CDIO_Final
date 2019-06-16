package main.java.rest;

import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.management.relation.Role;
import java.util.LinkedList;
import java.util.List;

public class JsonDTOassembler {
    public static UserDTO assembleRestUserDTO(String userDTO){

        UserDTO user = new UserDTO();
        String[] temp = userDTO.split(";");
        String[][] userDTOStats = new String[temp.length][2];

        for(int i = 0; i < temp.length; i++){
            userDTOStats[i] = temp[i].split("=");
        }

        for(int i = 0; i < userDTOStats.length; i++){
            switch ( userDTOStats[i][0] ){
                case "userId":
                    user.setUserId( Integer.parseInt( userDTOStats[i][1] ) );
                    break;
                case "username":
                    user.setUsername( userDTOStats[i][1] );
                    break;
                case "initials":
                    user.setInitials( userDTOStats[i][1] );
                    break;
                case "inactive":
                    user.setInactive( Boolean.parseBoolean( userDTOStats[i][1] ) );
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



}
