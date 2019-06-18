package main.java.Rest;

import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

public interface I_RestListener_Roles {

    // -- ROLE -- ROLE -- ROLE -- ROLE --
    Response createRole(RoleDTO role);
    Response getAllRoles();
    Response getRoleById(int roleId);
    Response getRolesBySearch(String keyWord);
    Response updateRole(RoleDTO role);

}
