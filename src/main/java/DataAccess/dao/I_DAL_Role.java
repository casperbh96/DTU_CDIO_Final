package DataAccess.dao;

import DataAccess.dto.RoleDTO;

import java.beans.IntrospectionException;
import java.io.Serializable;
import java.util.List;

public interface I_DAL_Role extends Serializable{


    RoleDTO createSingleRole( RoleDTO singleRole );
    List<RoleDTO> createMultipleRoles( List<RoleDTO> listOfRoles );

    RoleDTO readSingleRolebyId(int roleId );
    List<RoleDTO> readMultipleRolesByList (List<Integer> listOfRoleIds);
    List<RoleDTO> readRolebySearch(String keyword);
    List<RoleDTO> readAllRoles();

    RoleDTO updateSingleRole(RoleDTO role);
    List<RoleDTO> updateMultipleRoles(List<RoleDTO> listOfRoles);

    RoleDTO deleteSingleRole(RoleDTO role);
    List<RoleDTO> deleteMultipleRoles(List<RoleDTO> listOfRoles );


}
