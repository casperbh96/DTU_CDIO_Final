package main.java.DataAccess.dao;

import main.java.Core.REL_UserProductBatchDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static main.java.DataAccess.dao.Connector.*;

public class DAO_REL_UserProductBatch implements I_DAL_REL_UserProductBacth {

    private List<REL_UserProductBatchDTO> resultSetWhileLoop(ResultSet resultset) throws SQLException {
        REL_UserProductBatchDTO userProductBatch = null;
        List<REL_UserProductBatchDTO> userRoleList = new ArrayList<>();

        try{
            while (resultset.next()) {
                userProductBatch = new REL_UserProductBatchDTO(resultset.getInt(1), resultset.getInt(2));
                userRoleList.add(userProductBatch);
            }
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
        return userRoleList;
    }

    private PreparedStatement setCreatePreparedStatement(PreparedStatement pStmt, REL_UserProductBatchDTO userProductBatch) throws SQLException {
        try{
            pStmt.setInt(1, userProductBatch.getUserId());
            pStmt.setInt(2, userProductBatch.getProductBatchId());
        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    @Override
    public boolean assignSingleProductBatchUser(REL_UserProductBatchDTO singleUserProductBatch) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO rel_users_productBatches (user_id, product_batch_id) VALUES (?,?)");

            pStmt.setInt(1, singleUserProductBatch.getUserId());
            pStmt.setInt(2, singleUserProductBatch.getProductBatchId());

            pStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return doesUserHaveProductBatch(singleUserProductBatch.getUserId(),singleUserProductBatch.getProductBatchId());
    }

    @Override
    public boolean assignMultipleProductBatchUsers(List<REL_UserProductBatchDTO> listOfUserProductBatchs) throws SQLException {
        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO rel_users_productBatches (user_id, product_batch_id) VALUES (?,?)");

            for (REL_UserProductBatchDTO relUserProductBatch : listOfUserProductBatchs) {

                setCreatePreparedStatement(pStmt, relUserProductBatch);

                pStmt.addBatch();
            }
            pStmt.executeBatch();
            static_commitTransAction(conn);
        } catch (BatchUpdateException batchEx) {
            throw new BatchUpdateException(batchEx);
        }
        return readAllProductBatchUsers().containsAll(listOfUserProductBatchs);
    }

    public boolean doesUserHaveProductBatch(int userId, int productBatchId) throws SQLException {
        REL_UserProductBatchDTO relUserProductBatch;

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM rel_users_productBatches WHERE user_id = ? AND product_batch_id = ?");

            pStmt.setInt(1, userId);
            pStmt.setInt(2, productBatchId);
            ResultSet resultset = pStmt.executeQuery();

            // Move pointer to first row before Id, then to row with Id (fix)
            resultset.beforeFirst();
            resultset.next();

            relUserProductBatch = new REL_UserProductBatchDTO(resultset.getInt(1), resultset.getInt(2));
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return relUserProductBatch.getUserId() == userId && relUserProductBatch.getProductBatchId() == productBatchId;
    }


    @Override
    public List<REL_UserProductBatchDTO> readProductBatchUsers(int userProductBatchId) throws SQLException {
        List<REL_UserProductBatchDTO> productBatchUsers = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM rel_users_productBatches WHERE product_batch_id = ?");
            pStmt.setInt(1, userProductBatchId);

            ResultSet resultset = pStmt.executeQuery();

            productBatchUsers = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return productBatchUsers;
    }

    @Override
    public List<REL_UserProductBatchDTO> readProductBatchesByUserId(int userId) throws SQLException {
        List<REL_UserProductBatchDTO> productBatchUsers = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM rel_users_productBatches WHERE user_id = ?");
            pStmt.setInt(1, userId);

            ResultSet resultset = pStmt.executeQuery();

            productBatchUsers = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return productBatchUsers;
    }

    @Override
    public List<REL_UserProductBatchDTO> readAllProductBatchUsers() throws SQLException {
        List<REL_UserProductBatchDTO> relUserProductBatchList = new ArrayList<>();

        try (Connection connection = static_createConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM rel_users_productBatches");
            ResultSet resultset = pStmt.executeQuery();

            relUserProductBatchList = resultSetWhileLoop(resultset);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relUserProductBatchList;
    }

    @Override
    public void deleteSingleUserProductBatch(int userId, int productBatchId) throws SQLException {
        try(Connection connection = static_createConnection()){

            PreparedStatement pStmt = connection.prepareStatement("DELETE FROM rel_users_productBatches WHERE user_id = ? AND product_batch_id = ?");

            pStmt.setInt(1, userId);
            pStmt.setInt(2, productBatchId);
            pStmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMultipleUserProductBatchs(List<REL_UserProductBatchDTO> listOfUserProductBatchIds) throws SQLException {
        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("DELETE FROM rel_users_productBatches WHERE user_id = ? AND product_batch_id = ?");

            for (REL_UserProductBatchDTO productBatchUsers : listOfUserProductBatchIds) {

                pStmt.setInt(1, productBatchUsers.getUserId());
                pStmt.setInt(2, productBatchUsers.getProductBatchId());

                pStmt.addBatch();
            }
            pStmt.executeBatch();
            static_commitTransAction(conn);

        }  catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }
}
