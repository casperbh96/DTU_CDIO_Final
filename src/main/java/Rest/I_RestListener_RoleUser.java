package main.java.Rest;

import main.java.Core.REL_RoleUserDTO;

import javax.ws.rs.core.Response;

public interface I_RestListener_RoleUser {
    Response createRoleUser(REL_RoleUserDTO roleUser);
    Response getAllRoleUsers();
    Response getRoleUserByUserId(int userId);
    Response deleteRoleUser(REL_RoleUserDTO roleUser);
}
