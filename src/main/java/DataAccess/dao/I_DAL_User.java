package main.java.dal;

import DataAccess.dto.UserDTO;

import java.io.Serializable;
import java.util.List;

public interface I_DAL_User extends Serializable {

    UserDTO createSingleUser( UserDTO singleUser );
    List<UserDTO> createMultipleUsers( List<UserDTO> listOfUsers);

    UserDTO readSingleUserbyId(int UserId );
    List<UserDTO> readMultipleUsersByList (List<Integer> listOfUserIds);
    List<UserDTO> readUserbySearch(String keyword);
    List<UserDTO> readAllUsers();

    UserDTO updateSingleUser(UserDTO User);
    List<UserDTO> updateMultipleUsers(List<UserDTO> listOfUsers);

    UserDTO deleteSingleUser(UserDTO User);
    List<UserDTO> deleteMultipleUsers(List<UserDTO> listOfUsers);

}
