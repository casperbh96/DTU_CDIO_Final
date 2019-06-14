package main.java.DataAccess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Connector {
    public static Connection static_createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185131"
                + "?user=s185131&password=f641omiIhm3Ly1oQR5khj");
    }
    public static void static_startTransAction(Connection con) throws SQLException{
        con.setAutoCommit(false);
    }
    public static void static_commitTransAction(Connection con) throws SQLException{
        con.commit();
    }
    public static void static_rollBack(Connection con)throws SQLException{
        con.rollback();
    }
}
