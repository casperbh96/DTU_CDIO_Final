package main.java.DataAccess.dao;

import main.java.Core.ResourceBatchDTO;
import main.java.Core.RoleDTO;
import main.java.Core.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static main.java.DataAccess.dao.Connector.static_commitTransAction;
import static main.java.DataAccess.dao.Connector.static_createConnection;
import static main.java.DataAccess.dao.Connector.static_startTransAction;
import static main.java.DataAccess.dao.SQL_Manipulation.static_parameterBuilder;
import static main.java.DataAccess.dao.SQL_Manipulation.static_setIntPreparedStatements;

public class DAO_ResourceBatch implements I_DAL_ResourceBatch {


    private List<ResourceBatchDTO> resultSetWhileLoop(ResultSet resultset) throws SQLException {
        ResourceBatchDTO resourceBatch = null;
        List<ResourceBatchDTO> resourceBatchList = new ArrayList<>();

        try{
            while (resultset.next()) {
                resourceBatch = new ResourceBatchDTO(resultset.getInt(1), resultset.getDouble(2), resultset.getString(3), resultset.getBoolean(4),resultset.getInt(5));
                resourceBatchList.add(resourceBatch);
            }
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
        return resourceBatchList;
    }

    private PreparedStatement setCreatePreparedStatement(PreparedStatement pStmt, ResourceBatchDTO resourceBatch) throws SQLException {
        try{
            pStmt.setInt(1, resourceBatch.getResourceBatchId());
            pStmt.setDouble(2, resourceBatch.getResourceBatchAmount());
            pStmt.setString(3, resourceBatch.getSupplierName());
            pStmt.setBoolean(4, resourceBatch.getIsLeftover());
            pStmt.setInt(5, resourceBatch.getResourceId());

        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    private PreparedStatement setUpdatePreparedStatement(PreparedStatement pStmt, ResourceBatchDTO resourceBatch) throws SQLException {
        try{
            pStmt.setDouble(1, resourceBatch.getResourceBatchAmount());
            pStmt.setString(2, resourceBatch.getSupplierName());
            pStmt.setBoolean(3, resourceBatch.getIsLeftover());
            pStmt.setInt(4, resourceBatch.getResourceId());
            pStmt.setInt(5, resourceBatch.getResourceBatchId());
        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    @Override
    public ResourceBatchDTO createSingleResourceBatch(ResourceBatchDTO singleResourceBatch) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO resource_batches (resource_batch_id, resource_batch_amount, supplier_name, is_leftover, resource_id) VALUES (?,?,?,?,?)");

            setCreatePreparedStatement(pStmt, singleResourceBatch);

            pStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readSingleResourceBatchById(singleResourceBatch.getResourceBatchId());
    }

    @Override
    public List<ResourceBatchDTO> createMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatchs) throws SQLException {
        List<Integer> idList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO resource_batches (resource_batch_id, resource_batch_amount, supplier_name, is_leftover, resource_id) VALUES (?,?,?,?,?)");

            for (ResourceBatchDTO resourceBatch : listOfResourceBatchs) {
                idList.add(resourceBatch.getResourceBatchId());

                setCreatePreparedStatement(pStmt, resourceBatch);

                pStmt.addBatch();
            }
            pStmt.executeBatch();
            static_commitTransAction(conn);
        } catch (BatchUpdateException batchEx) {
            throw new BatchUpdateException(batchEx);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readMultipleResourceBatchsByList(idList);
    }

    @Override
    public ResourceBatchDTO readSingleResourceBatchById(int resourceBatchId) throws SQLException {
        ResourceBatchDTO resourceBatch = null;

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM resource_batches WHERE resource_batch_id = ?");

            pStmt.setInt(1, resourceBatchId);
            ResultSet resultset = pStmt.executeQuery();

            // Move pointer to first row before Id, then to row with Id (fix)
            resultset.beforeFirst();
            resultset.next();

            resourceBatch = new ResourceBatchDTO(resultset.getInt(1), resultset.getDouble(2), resultset.getString(3), resultset.getBoolean(4),resultset.getInt(5));
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return resourceBatch;
    }

    @Override
    public List<ResourceBatchDTO> readMultipleResourceBatchsByList(List<Integer> listOfResourceBatchIds) throws SQLException {
        List<ResourceBatchDTO> resourceBatchList = new ArrayList<>();

        String parameters = static_parameterBuilder(listOfResourceBatchIds);

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM resource_batches WHERE resource_batch_id IN (" + parameters + ")");

            static_setIntPreparedStatements(pStmt,listOfResourceBatchIds);

            ResultSet resultset = pStmt.executeQuery();

            resourceBatchList = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return resourceBatchList;
    }

    @Override
    public List<ResourceBatchDTO> readResourceBatchBySearch(String keyword) throws SQLException {
        List<ResourceBatchDTO> resourceBatchList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("select * from resource_batches " +
                    "WHERE resource_batch_id LIKE ? OR resource_batch_amount LIKE ? supplier_name LIKE ? is_leftover LIKE ? resource_id LIKE ?");
            pStmt.setString(1, "%" + keyword + "%");
            pStmt.setString(2, "%" + keyword + "%");
            pStmt.setString(3, "%" + keyword + "%");
            pStmt.setString(4, "%" + keyword + "%");
            pStmt.setString(5, "%" + keyword + "%");

            ResultSet resultset = pStmt.executeQuery();

            resourceBatchList = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return resourceBatchList;
    }

    @Override
    public List<ResourceBatchDTO> readAllResourceBatchs() throws SQLException {
        List<ResourceBatchDTO> resourceBatchList = new ArrayList<>();

        try (Connection connection = static_createConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM resource_batches");
            ResultSet resultset = pStmt.executeQuery();

            resourceBatchList = resultSetWhileLoop(resultset);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resourceBatchList;
    }

    @Override
    public ResourceBatchDTO updateSingleResourceBatch(ResourceBatchDTO resourceBatch) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("UPDATE resource_batches SET resource_batch_amount = ?, supplier_name = ?, is_leftover = ?, resource_id = ? WHERE resource_batch_id = ?");

            setUpdatePreparedStatement(pStmt, resourceBatch);

            pStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readSingleResourceBatchById(resourceBatch.getResourceBatchId());
    }

    @Override
    public List<ResourceBatchDTO> updateMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatchs) throws SQLException {
        List<Integer> idList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("UPDATE resource_batches SET resource_batch_amount = ?, supplier_name = ?, is_leftover = ?, resource_id = ? WHERE resource_batch_id = ?");

            for (ResourceBatchDTO resourceBatch : listOfResourceBatchs) {
                idList.add(resourceBatch.getResourceBatchId());

                setUpdatePreparedStatement(pStmt, resourceBatch);
                pStmt.addBatch();
            }

            pStmt.executeBatch();
            static_commitTransAction(conn);

        } catch (BatchUpdateException batchEx){
            throw new BatchUpdateException(batchEx);
        }
        return readMultipleResourceBatchsByList(idList);
    }

    @Override
    public ResourceBatchDTO setIsLeftoverSingleResourceBatch(int resourceBatchId) throws SQLException {
        try(Connection connection = static_createConnection()){

            PreparedStatement pStmt = connection.prepareStatement("UPDATE resource_batches SET is_leftover = ? WHERE resource_batch_id = ?");

            pStmt.setBoolean(1, true);
            pStmt.setInt(2, resourceBatchId);
            pStmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return readSingleResourceBatchById(resourceBatchId);
    }

    @Override
    public List<ResourceBatchDTO> setIsLeftoverMultipleResourceBatchs(List<Integer> listOfResourceBatchIds) throws SQLException {
        List<Integer> idList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("UPDATE resource_batches SET is_leftover = ? WHERE resource_batch_id = ?");

            for (int id : listOfResourceBatchIds) {
                idList.add(id);

                pStmt.setBoolean(1, true);
                pStmt.setInt(2, id);

                pStmt.addBatch();
            }
            pStmt.executeBatch();
            static_commitTransAction(conn);

        } catch (BatchUpdateException batchEx) {
            throw new BatchUpdateException(batchEx);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readMultipleResourceBatchsByList(idList);
    }
}
