package main.java.Rest;

import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.ws.rs.QueryParam;
import java.util.List;

public interface I_RestListener_Roles {

    // -- ROLE -- ROLE -- ROLE -- ROLE --
    List<RoleDTO> createRole(@QueryParam("roleDTOs") String roleDTOs);
    List<RoleDTO> getRole(@QueryParam("searchMethod") String searchMethod, @QueryParam("Id") String Id);
    List<RoleDTO> searchRoles(@QueryParam("searchMethod") String searchMethod, @QueryParam("KeyWord") String keyWord);
    List<RoleDTO> updateRole(@QueryParam("userDTO") String userDTO, @QueryParam("roleDTOs") String roleDTOs);
    List<RoleDTO> deleteRole(@QueryParam("userDTO") String userDTO);

}
