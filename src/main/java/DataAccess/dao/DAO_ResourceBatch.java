package DataAccess.dao;

import DataAccess.dto.ResourceBatchDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static DataAccess.dao.Connector.static_commitTransAction;
import static DataAccess.dao.Connector.static_createConnection;
import static DataAccess.dao.Connector.static_startTransAction;
import static DataAccess.dao.Connector.*;

public class DAO_ResourceBatch implements I_DAL_ResourceBatch {
    @Override
    public ResourceBatchDTO createSingleResourceBatch(ResourceBatchDTO singleResourceBatch) {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> createMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatches) throws SQLException {
        List<Integer> idList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO resource_batches (resource_batch_id, resource_batch_name, resource_batch_amount, supplier_name, is_leftover, resource_id) VALUES (?,?,?,?,?,?)");

            int index = 0;
            for (ResourceBatchDTO resBatch : listOfResourceBatches) {
                idList.add(resBatch.getResourceId());

                pStmt.setInt(1, resBatch.getResourceId());
                pStmt.setString(2, resBatch.getResourceBatchName());
                pStmt.setDouble(3, resBatch.getResourceBatchAmount());
                pStmt.setString(4, resBatch.getSupplierName());
                pStmt.setBoolean(5, resBatch.getIsLeftover());      //TODO ændre til boolean i sql-db
                pStmt.setInt(6, resBatch.getResourceId());

                pStmt.addBatch();
                index++;
            }

            //TODO skal dette samles til en metode??
            int[] numUpdates = pStmt.executeBatch();
            for (int i = 0; i < numUpdates.length; i++) {
                if (numUpdates[i] == -2)
                    System.out.println("Execution " + i +
                            ": unknown number of rows updated");
                else
                    System.out.println("Execution " + i +
                            "successful: " + numUpdates[i] + " rows updated");
            }
            static_commitTransAction(conn);
        } catch (BatchUpdateException batchEx) {
            throw new BatchUpdateException(batchEx);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return readMultipleResourceBatchsByList(idList);
    }

    @Override
    public ResourceBatchDTO readSingleResourceBatchbyId(int resourceBatchId) {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> readMultipleResourceBatchsByList(List<Integer> listOfResourceBatchIds) throws SQLException {
        ResourceBatchDTO res;
        List<ResourceBatchDTO> resBatchList = new ArrayList<>();

        // Produce string with number of ? equal to size of listOfResourceBatchIds
        String parameters = static_parameterBuilder(listOfResourceBatchIds);


        try (Connection conn = static_createConnection()) {
            // Turns into SELECT * FROM resources WHERE resource_batch_id IN () with the string builder in the parenthesis
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM resource_batches WHERE resource_batch_id IN (" + parameters + ")");

            // Set each of the ? to the corresponding Id from listOfResourceBatchIds
            int index = 1;
            for (int i : listOfResourceBatchIds) {
                pStmt.setInt(index++, i);
            }

            ResultSet resultset = pStmt.executeQuery();

            while (resultset.next()) {
                //TODO is_leftover skal i sql-databasen ændres fra int til boolean
                res = new ResourceBatchDTO(resultset.getInt(1), resultset.getString(2), resultset.getDouble(3),
                        resultset.getString(4),resultset.getBoolean(5),resultset.getInt(6));
                resBatchList.add(res);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return resBatchList;
    }

    @Override
    public List<ResourceBatchDTO> readResourceBatchbySearch(String keyword) {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> readAllResourceBatchs() {
        return null;
    }

    @Override
    public ResourceBatchDTO updateSingleResourceBatch(ResourceBatchDTO resourceBatch) {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> updateMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatches) {
        return null;
    }

    @Override
    public ResourceBatchDTO deleteSingleResourceBatch(ResourceBatchDTO resourceBatch) {
        return null;
    }

    @Override
    public List<ResourceBatchDTO> deleteMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatchs) {
        return null;
    }

}
