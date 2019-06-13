package main.java.DataAccess.dao;

import main.java.Core.REL_UserProductBatchDTO;

import java.sql.SQLException;
import java.util.List;

public class DAO_REL_UserProductBatch implements I_DAL_REL_UserProductBacth {
    @Override
    public REL_UserProductBatchDTO createSingleUserProductBatch(REL_UserProductBatchDTO singleUserProductBatch) throws SQLException {
        return null;
    }

    @Override
    public List<REL_UserProductBatchDTO> createMultipleUserProductBatchs(List<REL_UserProductBatchDTO> listOfUserProductBatchs) throws SQLException {
        return null;
    }

    @Override
    public REL_UserProductBatchDTO readSingleUserProductBatchbyId(int userProductBatchId) throws SQLException {
        return null;
    }

    @Override
    public List<REL_UserProductBatchDTO> readMultipleUserProductBatchsByList(List<Integer> listOfUserProductBatchIds) throws SQLException {
        return null;
    }

    @Override
    public List<REL_UserProductBatchDTO> readUserProductBatchbySearch(String keyword) throws SQLException {
        return null;
    }

    @Override
    public List<REL_UserProductBatchDTO> readAllUserProductBatchs() throws SQLException {
        return null;
    }

    @Override
    public REL_UserProductBatchDTO updateSingleUserProductBatch(REL_UserProductBatchDTO userProductBatch) throws SQLException {
        return null;
    }

    @Override
    public List<REL_UserProductBatchDTO> updateMultipleUserProductBatchs(List<REL_UserProductBatchDTO> listOfUserProductBatchs) throws SQLException {
        return null;
    }

    @Override
    public REL_UserProductBatchDTO deleteSingleUserProductBatch(int userProductBatchId) throws SQLException {
        return null;
    }

    @Override
    public REL_UserProductBatchDTO deleteMultipleUserProductBatchs(List<Integer> listOfUserProductBatchIds) throws SQLException {
        return null;
    }
}
