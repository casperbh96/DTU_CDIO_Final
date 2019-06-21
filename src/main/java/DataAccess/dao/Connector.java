package main.java.DataAccess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Connector {
    public static Connection static_createConnection() throws SQLException {
        try{
            return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s190344"
                    + "?user=s190344&password=SvqYIs9WD7cfJsIVax6EW");
        }
        catch(SQLException ex){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s190344"
                    + "?user=s190344&password=SvqYIs9WD7cfJsIVax6EW");
        }
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
