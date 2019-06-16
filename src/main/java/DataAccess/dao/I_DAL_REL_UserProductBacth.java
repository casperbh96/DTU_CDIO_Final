package main.java.DataAccess.dao;

import main.java.Core.REL_UserProductBatchDTO;

import java.sql.SQLException;
import java.util.List;

public interface I_DAL_REL_UserProductBacth {

    REL_UserProductBatchDTO createSingleUserProductBatch(REL_UserProductBatchDTO singleUserProductBatch ) throws SQLException;
    List<REL_UserProductBatchDTO> createMultipleUserProductBatchs(List<REL_UserProductBatchDTO> listOfUserProductBatchs ) throws SQLException;

    REL_UserProductBatchDTO readSingleUserProductBatchbyId(int userProductBatchId ) throws SQLException;
    List<REL_UserProductBatchDTO> readMultipleUserProductBatchsByList (List<Integer> listOfUserProductBatchIds) throws SQLException;
    List<REL_UserProductBatchDTO> readUserProductBatchbySearch(String keyword) throws SQLException;
    List<REL_UserProductBatchDTO> readAllUserProductBatchs() throws SQLException;

    REL_UserProductBatchDTO updateSingleUserProductBatch(REL_UserProductBatchDTO userProductBatch) throws SQLException;
    List<REL_UserProductBatchDTO> updateMultipleUserProductBatchs(List<REL_UserProductBatchDTO> listOfUserProductBatchs) throws SQLException;

    REL_UserProductBatchDTO deleteSingleUserProductBatch(int userProductBatchId) throws SQLException;
    REL_UserProductBatchDTO deleteMultipleUserProductBatchs(List<Integer> listOfUserProductBatchIds) throws SQLException;
}
