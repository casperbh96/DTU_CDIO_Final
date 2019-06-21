package main.java.DataAccess.arrList;

import main.java.Core.RoleDTO;
import main.java.DataAccess.dao.I_DAL_Role;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ARR_Role implements I_DAL_Role {
    ArrayList<RoleDTO> roleList;

    public ARR_Role(){
        roleList = new ArrayList<>();
        roleList.add(new RoleDTO(1, "admin"));
        roleList.add(new RoleDTO(2, "laborant technician"));
        roleList.add(new RoleDTO(3, "pharmacist"));
        roleList.add(new RoleDTO(4, "productionleader"));
    }

    @Override
    public RoleDTO createSingleRole(RoleDTO singleRole) throws SQLException {
        roleList.add(singleRole);
        return readSingleRoleById(singleRole.getRoleId());
    }

    @Override
    public List<RoleDTO> createMultipleRoles(List<RoleDTO> listOfRoles) throws SQLException {
        List<Integer> listOfIds = new ArrayList<>();
        for(RoleDTO role : listOfRoles){
            roleList.add(role);
            listOfIds.add(role.getRoleId());
        }
        return readMultipleRolesByList(listOfIds);
    }

    @Override
    public RoleDTO readSingleRoleById(int roleId) throws SQLException {
        RoleDTO r = null;
        for(RoleDTO role : roleList){
            if(role.getRoleId() == roleId){
                r = role;
            }
        }
        return r;
    }

    @Override
    public List<RoleDTO> readMultipleRolesByList(List<Integer> listOfRoleIds) throws SQLException {
        List<RoleDTO> returnList = new ArrayList<>();
        for(RoleDTO role : roleList){
            for(Integer i : listOfRoleIds){
                if(role.getRoleId() == i){
                    returnList.add(role);
                }
            }
        }
        return returnList;
    }

    @Override
    public List<RoleDTO> readRoleBySearch(String keyword) throws SQLException {
        List<RoleDTO> returnList = new ArrayList<>();

        for(RoleDTO role : roleList){
            if(role.toString().contains(keyword)){
                returnList.add(role);
            }
        }

        return returnList;
    }

    @Override
    public List<RoleDTO> readAllRoles() throws SQLException {
        return roleList;
    }

    @Override
    public RoleDTO updateSingleRole(RoleDTO role) throws SQLException {
        for(RoleDTO r : roleList){
            if(r.getRoleId() == role.getRoleId()){
                return r;
            }
        }
        return null;
    }

    @Override
    public List<RoleDTO> updateMultipleRoles(List<RoleDTO> listOfRoles) throws SQLException {
        List<RoleDTO> returnList = new ArrayList<>();

        for(RoleDTO role : roleList){
            for(RoleDTO r : listOfRoles){
                if(r.getRoleId() == role.getRoleId()){
                    returnList.add(role);
                }
            }
        }
        return returnList;
    }
}
