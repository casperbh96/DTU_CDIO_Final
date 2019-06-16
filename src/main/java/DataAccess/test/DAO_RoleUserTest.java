package main.java.DataAccess.test;

import junit.framework.TestCase;
import main.java.Core.REL_RoleUserDTO;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;
import main.java.DataAccess.dao.DAO_REL_RoleUser;
import main.java.DataAccess.dao.DAO_Role;
import main.java.DataAccess.dao.DAO_User;
import org.junit.Assert;

import java.sql.BatchUpdateException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DAO_RoleUserTest {
    DAO_REL_RoleUser dao = new DAO_REL_RoleUser();
    DAO_User daoUser = new DAO_User();
    DAO_Role daoRole = new DAO_Role();

    @org.junit.Test
    public void readAllRoleUserTest() {
        try {
            List<REL_RoleUserDTO> roleUserList = dao.readAllUserRoles();
            assertNotNull(roleUserList);
            assertNotEquals(roleUserList.isEmpty(), roleUserList);

            for (REL_RoleUserDTO i : roleUserList) {
                System.out.println(i);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void createMultipleRoleUserTest() {
        try {
            List<REL_RoleUserDTO> roleUserDTO = new ArrayList<>();

            List<RoleDTO> roleList = new ArrayList<>();
            RoleDTO role1 = new RoleDTO(61, "test123");
            RoleDTO role2 = new RoleDTO(62, "test1234");
            roleList.add(role1);
            roleList.add(role2);

            UserDTO user = new UserDTO(556, "CapTest2", "CT1", false);

            for(int i = 0; i < roleList.size(); i++){
                roleUserDTO.add(new REL_RoleUserDTO(
                        user.getUserId(), roleList.get(i).getRoleId()));
            }

            daoUser.createSingleUser(user);

            boolean returnValue = dao.assignUserMultipleRoles(roleUserDTO);
            TestCase.assertEquals(true, returnValue);

            System.out.println(dao.readUsersRoles(556));

        }
        catch (BatchUpdateException batchEx) {
            batchEx.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
