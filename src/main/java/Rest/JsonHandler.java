package main.java.rest;

import main.java.BusinessLogic.BLLUser;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.management.relation.Role;
import java.sql.SQLException;
import java.util.List;

public class JsonHandler {

   public static List<UserDTO> handleGetUserJSON(String action,UserDTO userDto,List<RoleDTO> roles,String range,boolean aktive,boolean in_aktive) throws SQLException{
      BLLUser blUser = new BLLUser();
      switch (action){
         case "create":
           // return blUser.createUser(userDto, roles);
         case "read":
            return blUser.getAllUsers();
         case "readbyId":
            //return blUser.getUserById(userDto.getUserId());
         case "searchByRow":
            return null;
         case "searchByKeyWord":
           // return blUser.getUserBySearch();
         case "update":
           // return blUser.updateUser();
         case "delete":
           // return blUser.deleteUser();
         default:
            return null;
      }
   }

}
