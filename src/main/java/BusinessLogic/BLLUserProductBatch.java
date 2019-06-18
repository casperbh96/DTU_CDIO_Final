package main.java.BusinessLogic;

import main.java.Core.REL_UserProductBatchDTO;
import main.java.DataAccess.dao.DAO_REL_UserProductBatch;

import java.sql.SQLException;
import java.util.List;

public class BLLUserProductBatch implements I_BLLUserProductBatch {
    DAO_REL_UserProductBatch daoUserProductBatch = new DAO_REL_UserProductBatch();

    @Override
    public boolean assignSingleProductBatchUser(REL_UserProductBatchDTO singleUserProductBatch) throws SQLException {
        return daoUserProductBatch.assignSingleProductBatchUser(singleUserProductBatch);
    }

    @Override
    public boolean assignMultipleProductBatchUsers(List<REL_UserProductBatchDTO> listOfUserProductBatchs) throws SQLException {
        return daoUserProductBatch.assignMultipleProductBatchUsers(listOfUserProductBatchs);
    }

    @Override
    public boolean doesUserHaveProductBatch(int userId, int productBatchId) throws SQLException {
        return daoUserProductBatch.doesUserHaveProductBatch(userId, productBatchId);
    }

    @Override
    public List<REL_UserProductBatchDTO> readProductBatchUsers(int userProductBatchId) throws SQLException {
        return daoUserProductBatch.readProductBatchUsers(userProductBatchId);
    }

    @Override
    public List<REL_UserProductBatchDTO> readProductBatchesByUserId(int userId) throws SQLException {
        return daoUserProductBatch.readProductBatchesByUserId(userId);
    }

    @Override
    public List<REL_UserProductBatchDTO> readAllProductBatchUsers() throws SQLException {
        return daoUserProductBatch.readAllProductBatchUsers();
    }

    @Override
    public void deleteSingleUserProductBatch(int userId, int productBatchId) throws SQLException {
        daoUserProductBatch.deleteSingleUserProductBatch(userId, productBatchId);
    }

    @Override
    public void deleteMultipleUserProductBatchs(List<REL_UserProductBatchDTO> listOfUserProductBatchIds) throws SQLException {
        daoUserProductBatch.deleteMultipleUserProductBatchs(listOfUserProductBatchIds);
    }
}
