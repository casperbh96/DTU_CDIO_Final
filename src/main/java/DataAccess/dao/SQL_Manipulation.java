package main.java.DataAccess.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SQL_Manipulation {

    public static String static_parameterBuilder(List<Integer> listOfIds) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < listOfIds.size(); i++) {
            if (i == listOfIds.size() - 1) {
                builder.append("?");
            } else {
                builder.append("?,");
            }
        }
        return builder.toString();
    }

    public static void static_setIntPreparedStatements(PreparedStatement pStmt, List<Integer> listOfIds) throws SQLException {
        int index = 1;
        for (int i : listOfIds) {
            pStmt.setInt(index++, i);
        }
    }


}
