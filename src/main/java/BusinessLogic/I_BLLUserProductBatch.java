package main.java.BusinessLogic;

import main.java.Core.REL_UserProductBatchDTO;

import java.sql.SQLException;
import java.util.List;

public interface I_BLLUserProductBatch {

    boolean assignSingleProductBatchUser(REL_UserProductBatchDTO singleUserProductBatch) throws SQLException;
    boolean assignMultipleProductBatchUsers(List<REL_UserProductBatchDTO> listOfUserProductBatchs) throws SQLException;

    boolean doesUserHaveProductBatch(int userId, int productBatchId) throws SQLException;
    List<REL_UserProductBatchDTO> readProductBatchUsers(int userProductBatchId) throws SQLException;
    List<REL_UserProductBatchDTO> readProductBatchesByUserId(int userId) throws SQLException;
    List<REL_UserProductBatchDTO> readAllProductBatchUsers() throws SQLException;

    void deleteSingleUserProductBatch(int userId, int productBatchId) throws SQLException;
    void deleteMultipleUserProductBatchs(List<REL_UserProductBatchDTO> listOfUserProductBatchIds) throws SQLException;

}
