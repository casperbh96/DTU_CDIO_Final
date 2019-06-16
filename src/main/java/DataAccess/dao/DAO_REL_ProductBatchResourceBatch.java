package main.java.DataAccess.dao;

import main.java.Core.REL_ProductBatchResourceBatchDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static main.java.DataAccess.dao.Connector.*;
import static main.java.DataAccess.dao.SQL_Manipulation.static_parameterBuilder;

public class DAO_REL_ProductBatchResourceBatch implements I_DAL_REL_ProductBatchResourceBatch {

    private List<REL_ProductBatchResourceBatchDTO> resultSetWhileLoop(ResultSet resultset) throws SQLException {
        REL_ProductBatchResourceBatchDTO relProductBatchResourceBatch = null;
        List<REL_ProductBatchResourceBatchDTO> relRecipeResourceList = new ArrayList<>();

        try{
            while (resultset.next()) {
                relProductBatchResourceBatch = new REL_ProductBatchResourceBatchDTO(resultset.getInt(1), resultset.getInt(2), resultset.getDouble(3), resultset.getDouble(4));
                relRecipeResourceList.add(relProductBatchResourceBatch);
            }
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
        return relRecipeResourceList;
    }

    private PreparedStatement setCreatePreparedStatement(PreparedStatement pStmt, REL_ProductBatchResourceBatchDTO relProductBatchResourceBatch) throws SQLException {
        try{
            pStmt.setInt(1, relProductBatchResourceBatch.getResourceBatchId());
            pStmt.setInt(2, relProductBatchResourceBatch.getProductBatchId());
            pStmt.setDouble(3, relProductBatchResourceBatch.getNetAmount());
            pStmt.setDouble(4, relProductBatchResourceBatch.getTara());

        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    private PreparedStatement setUpdatePreparedStatement(PreparedStatement pStmt, REL_ProductBatchResourceBatchDTO relProductBatchResourceBatch) throws SQLException {
        try{
            pStmt.setDouble(1, relProductBatchResourceBatch.getNetAmount());
            pStmt.setDouble(2, relProductBatchResourceBatch.getTara());
            pStmt.setInt(3, relProductBatchResourceBatch.getResourceBatchId());
            pStmt.setInt(4, relProductBatchResourceBatch.getProductBatchId());
        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    @Override
    public REL_ProductBatchResourceBatchDTO createSingleProductBatchResourceBatch(REL_ProductBatchResourceBatchDTO singleProductBatchResourceBatch) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO rel_productBatches_resourceBatches (resource_batch_id, product_batch_id, net_amount, tara) VALUES (?,?,?,?)");

            setCreatePreparedStatement(pStmt, singleProductBatchResourceBatch);

            pStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readSingleProductBatchResourceBatchbyId(singleProductBatchResourceBatch.getResourceBatchId(),singleProductBatchResourceBatch.getProductBatchId());
    }

    @Override
    public List<REL_ProductBatchResourceBatchDTO> createMultipleProductBatchResourceBatchs(List<REL_ProductBatchResourceBatchDTO> listOfProductBatchResourceBatchs ) throws SQLException {
        List<Integer> resBatchIdList = new ArrayList<>();
        List<Integer> prodBatchIdList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO rel_productBatches_resourceBatches (resource_batch_id, product_batch_id, net_amount, tara) VALUES (?,?,?,?)");

            for (REL_ProductBatchResourceBatchDTO relRecipeResource : listOfProductBatchResourceBatchs) {
                resBatchIdList.add(relRecipeResource.getResourceBatchId());
                prodBatchIdList.add(relRecipeResource.getProductBatchId());

                setCreatePreparedStatement(pStmt, relRecipeResource);

                pStmt.addBatch();
            }
            pStmt.executeBatch();
            static_commitTransAction(conn);
        } catch (BatchUpdateException batchEx) {
            throw new BatchUpdateException(batchEx);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readMultipleProductBatchResourceBatchsByList(resBatchIdList,prodBatchIdList);
    }

    @Override
    public REL_ProductBatchResourceBatchDTO readSingleProductBatchResourceBatchbyId(int resourceBatchId, int productBatchId) throws SQLException {
        REL_ProductBatchResourceBatchDTO relProductBatchResourceBatch = null;

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM rel_productBatches_resourceBatches WHERE resource_batch_id = ? AND product_batch_id = ?");

            pStmt.setInt(1, resourceBatchId);
            pStmt.setInt(2, productBatchId);
            ResultSet resultset = pStmt.executeQuery();

            // Move pointer to first row before Id, then to row with Id (fix)
            resultset.beforeFirst();
            resultset.next();

            relProductBatchResourceBatch = new REL_ProductBatchResourceBatchDTO(resultset.getInt(1), resultset.getInt(2), resultset.getDouble(3), resultset.getDouble(4));
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return relProductBatchResourceBatch;
    }

    @Override
    public List<REL_ProductBatchResourceBatchDTO> readMultipleProductBatchResourceBatchsByList(List<Integer> listOfResourceBatchIds, List<Integer> listOfProductBatchIds) throws SQLException {
        List<REL_ProductBatchResourceBatchDTO> relProductBatchResourceBatchList = new ArrayList<>();

        String resourceIdsParameters = static_parameterBuilder(listOfResourceBatchIds);

        String productBatchIdsParameters = static_parameterBuilder(listOfProductBatchIds);

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM rel_resourceBatches_resourceBatches WHERE resource_id IN (" + resourceIdsParameters + "product_batch_id IN (" + productBatchIdsParameters + ")");

            for (int i = 0; i < listOfResourceBatchIds.size(); i++) {
                pStmt.setInt(i+1, listOfResourceBatchIds.get(i));
                pStmt.setInt(i+1+listOfResourceBatchIds.size(), listOfProductBatchIds.get(i));
            }

            ResultSet resultset = pStmt.executeQuery();

            relProductBatchResourceBatchList = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return relProductBatchResourceBatchList;
    }

    @Override
    public List<REL_ProductBatchResourceBatchDTO> readProductBatchResourceBatchbySearch(String keyword) throws SQLException {
        List<REL_ProductBatchResourceBatchDTO> relProductBatchResourceBatchList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("select * from rel_productBatches_resourceBatches " +
                    "WHERE resource_batch_id LIKE ? OR product_batch_id LIKE ? OR net_amount LIKE ? OR tara LIKE ?");
            pStmt.setString(1, "%" + keyword + "%");
            pStmt.setString(2, "%" + keyword + "%");
            pStmt.setString(3, "%" + keyword + "%");
            pStmt.setString(4, "%" + keyword + "%");

            ResultSet resultset = pStmt.executeQuery();

            relProductBatchResourceBatchList = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return relProductBatchResourceBatchList;
    }

    @Override
    public List<REL_ProductBatchResourceBatchDTO> readAllProductBatchResourceBatchs() throws SQLException {
        List<REL_ProductBatchResourceBatchDTO> relProductBatchResourceBatchList = new ArrayList<>();

        try (Connection connection = static_createConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM rel_productBatches_resourceBatches");
            ResultSet resultset = pStmt.executeQuery();

            relProductBatchResourceBatchList = resultSetWhileLoop(resultset);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relProductBatchResourceBatchList;
    }

    @Override
    public REL_ProductBatchResourceBatchDTO updateSingleProductBatchResourceBatch(REL_ProductBatchResourceBatchDTO productBatchResourceBatch) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("UPDATE rel_productBatches_resourceBatches SET net_amount = ?, tara = ? WHERE resource_batch_id = ? AND product_batch_id = ?");

            setUpdatePreparedStatement(pStmt, productBatchResourceBatch);

            pStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readSingleProductBatchResourceBatchbyId(productBatchResourceBatch.getResourceBatchId(),productBatchResourceBatch.getProductBatchId());
    }

    @Override
    public List<REL_ProductBatchResourceBatchDTO> updateMultipleProductBatchResourceBatchs(List<REL_ProductBatchResourceBatchDTO> listOfProductBatchResourceBatchs) throws SQLException {
        List<Integer> resBatchIdList = new ArrayList<>();
        List<Integer> prodBatchIdList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("UPDATE rel_productBatches_resourceBatches SET net_amount = ?, tara = ? WHERE resource_batch_id = ? AND product_batch_id = ?");

            for (REL_ProductBatchResourceBatchDTO relRecipeResource : listOfProductBatchResourceBatchs) {
                resBatchIdList.add(relRecipeResource.getResourceBatchId());
                prodBatchIdList.add(relRecipeResource.getProductBatchId());

                setUpdatePreparedStatement(pStmt, relRecipeResource);
                pStmt.addBatch();
            }

            pStmt.executeBatch();
            static_commitTransAction(conn);

        } catch (BatchUpdateException batchEx){
            throw new BatchUpdateException(batchEx);
        }
        return readMultipleProductBatchResourceBatchsByList(resBatchIdList,prodBatchIdList);
    }

    @Override
    public void deleteSingleProductBatchResourceBatch(int resourceBatchId, int productBatchId) throws SQLException {
        try(Connection connection = static_createConnection()){

            PreparedStatement pStmt = connection.prepareStatement("DELETE FROM rel_productBatches_resourceBatches WHERE resource_batch_id = ? AND product_batch_id = ?");

            pStmt.setInt(1, resourceBatchId);
            pStmt.setInt(2, productBatchId);
            pStmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMultipleProductBatchResourceBatchs(List<Integer> resourceBatchIds, List<Integer> productBatchIds) throws SQLException {
        try (Connection connection = static_createConnection()) {
            static_startTransAction(connection);
            PreparedStatement pStmt = connection.prepareStatement("DELETE FROM rel_productBatches_resourceBatches WHERE resource_batch_id = ? AND product_batch_id = ?");

            for (int i = 0; i < resourceBatchIds.size(); i++) {

                pStmt.setInt(1, resourceBatchIds.get(i));
                pStmt.setInt(2, productBatchIds.get(i));

                pStmt.addBatch();
            }
            pStmt.executeBatch();
            static_commitTransAction(connection);

        }  catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }
}
