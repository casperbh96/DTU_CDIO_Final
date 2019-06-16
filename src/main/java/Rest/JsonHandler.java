package main.java.Rest;

import main.java.BusinessLogic.BLLUser;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import javax.management.relation.Role;
import java.sql.SQLException;
import java.util.List;

public class JsonHandler {

   public UserDTO createUserFromJSON(UserDTO user, List<RoleDTO> roles)throws SQLException {
/*
      try {

         BLLUser bluser = new BLLUser();
         return bluser.createUser(user, roles);

      }catch (SQLException e){

         UserDTO errorDTO = new UserDTO();
         errorDTO.setInactive(false);
         errorDTO.setInitials("error");
         errorDTO.setUsername("error");
         errorDTO.setUserId(0);
         return errorDTO;

      }
*/ return null;
   }
   public UserDTO deleteUserFromJSON(UserDTO user)throws SQLException{

      BLLUser bluser = new BLLUser();
      bluser.deleteUser(user.getUserId());

      UserDTO empty = new UserDTO();
      empty.setInactive(true);
      empty.setInitials("deleted");
      empty.setUsername("deleted");
      empty.setUserId(0);
      return empty;

   }




}
