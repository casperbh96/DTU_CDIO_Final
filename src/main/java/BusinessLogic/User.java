package main.java.BusinessLogic;

import main.java.DataAccess.dao.DAO_User;
import main.java.DataAccess.dao.I_DAL_User;

public class User {
    I_DAL_User user = new DAO_User();

    private int userId;
    private String username;
    private String initials;
    private boolean inactive;


}
