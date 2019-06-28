package main.java.DataAccess.dao;

import main.java.Core.ProductBatchDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static main.java.DataAccess.dao.Connector.*;
import static main.java.DataAccess.dao.SQL_Manipulation.static_parameterBuilder;
import static main.java.DataAccess.dao.SQL_Manipulation.static_setIntPreparedStatements;

public class DAO_ProductBatch implements I_DAL_ProductBatch {

    private List<ProductBatchDTO> resultSetWhileLoop(ResultSet resultset) throws SQLException {
        ProductBatchDTO productBatch = null;
        List<ProductBatchDTO> resourceBatchList = new ArrayList<>();

        try{
            while (resultset.next()) {
                productBatch = new ProductBatchDTO(resultset.getInt(1), resultset.getDate(2), resultset.getString(3), resultset.getDate(4),resultset.getBoolean(5),resultset.getInt(6),resultset.getDate(7),resultset.getInt(8));
                resourceBatchList.add(productBatch);
            }
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
        return resourceBatchList;
    }

    private PreparedStatement setCreatePreparedStatement(PreparedStatement pStmt, ProductBatchDTO productBatch) throws SQLException {
        try{
            pStmt.setInt(1, productBatch.getProductBatchId());
            pStmt.setDate(2, productBatch.getCreationDate());
            pStmt.setString(3, productBatch.getProductionStatus());
            pStmt.setDate(4, productBatch.getProductionEndDate());
            pStmt.setBoolean(5, productBatch.isInactive());
            pStmt.setInt(6, productBatch.getRecipeId());
            pStmt.setDate(7, productBatch.getRecipeEndDate());
            pStmt.setInt(8, productBatch.getProductionLeaderUserId());

        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    private PreparedStatement setUpdatePreparedStatement(PreparedStatement pStmt, ProductBatchDTO productBatch) throws SQLException {
        try{
            pStmt.setDate(1, productBatch.getCreationDate());
            pStmt.setString(2, productBatch.getProductionStatus());
            pStmt.setDate(3, productBatch.getProductionEndDate());
            pStmt.setBoolean(4, productBatch.isInactive());
            pStmt.setInt(5, productBatch.getRecipeId());
            pStmt.setDate(6, productBatch.getRecipeEndDate());
            pStmt.setInt(7, productBatch.getProductionLeaderUserId());
            pStmt.setInt(8, productBatch.getProductBatchId());
        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    @Override
    public ProductBatchDTO createSingleProductBatch(ProductBatchDTO singleProductBatch) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO product_batches (product_batch_id, creation_date, production_status, production_end_date, inactive, recipe_id, recipe_end_date, productionleader_id_user_id) VALUES (?,?,?,?,?,?,?,?)");

            setCreatePreparedStatement(pStmt, singleProductBatch);

            pStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readSingleProductBatchById(singleProductBatch.getProductBatchId());
    }

    @Override
    public List<ProductBatchDTO> createMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatches) throws SQLException {
        List<Integer> idList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO product_batches (product_batch_id, creation_date, production_status, production_end_date, inactive, recipe_id, recipe_end_date, productionleader_id_user_id) VALUES (?,?,?,?,?,?,?,?)");

            for (ProductBatchDTO productBatch : listOfProductBatches) {
                idList.add(productBatch.getProductBatchId());

                setCreatePreparedStatement(pStmt, productBatch);

                pStmt.addBatch();
            }
            pStmt.executeBatch();
            static_commitTransAction(conn);
        } catch (BatchUpdateException batchEx) {
            throw new BatchUpdateException(batchEx);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readMultipleProductBatchsByList(idList);
    }

    @Override
    public ProductBatchDTO readSingleProductBatchById(int productBatchId) throws SQLException {
        ProductBatchDTO productBatch = null;

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM product_batches WHERE product_batch_id = ?");

            pStmt.setInt(1, productBatchId);
            ResultSet resultset = pStmt.executeQuery();

            // Move pointer to first row before Id, then to row with Id (fix)
            resultset.beforeFirst();
            resultset.next();

            productBatch = new ProductBatchDTO(resultset.getInt(1), resultset.getDate(2), resultset.getString(3), resultset.getDate(4),resultset.getBoolean(5),resultset.getInt(6),resultset.getDate(7),resultset.getInt(8));        }
        catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return productBatch;
    }

    @Override
    public List<ProductBatchDTO> readMultipleProductBatchsByList(List<Integer> listOfProductBatchIds) throws SQLException {
        List<ProductBatchDTO> productBatchList = new ArrayList<>();

        String parameters = static_parameterBuilder(listOfProductBatchIds);

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM product_batches WHERE product_batch_id IN (" + parameters + ")");

            static_setIntPreparedStatements(pStmt,listOfProductBatchIds);

            ResultSet resultset = pStmt.executeQuery();

            productBatchList = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return productBatchList;
    }

    @Override
    public List<ProductBatchDTO> readProductBatchBySearch(String keyword) throws SQLException {
        List<ProductBatchDTO> productBatchList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("select * from product_batches " +
                    "WHERE product_batch_id LIKE ? OR creation_date LIKE ? OR production_status LIKE ? OR production_end_date LIKE ? OR inactive LIKE ? OR recipe_id LIKE ? OR recipe_end_date LIKE ? OR productionleader_id_user_id LIKE ?");
            pStmt.setString(1, "%" + keyword + "%");
            pStmt.setString(2, "%" + keyword + "%");
            pStmt.setString(3, "%" + keyword + "%");
            pStmt.setString(4, "%" + keyword + "%");
            pStmt.setString(5, "%" + keyword + "%");
            pStmt.setString(6, "%" + keyword + "%");
            pStmt.setString(7, "%" + keyword + "%");
            pStmt.setString(8, "%" + keyword + "%");

            ResultSet resultset = pStmt.executeQuery();

            productBatchList = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return productBatchList;
    }

    @Override
    public List<ProductBatchDTO> readAllProductBatchs() throws SQLException {
        List<ProductBatchDTO> productBatchList = new ArrayList<>();

        try (Connection connection = static_createConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM product_batches");
            ResultSet resultset = pStmt.executeQuery();

            productBatchList = resultSetWhileLoop(resultset);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productBatchList;
    }

    @Override
    public ProductBatchDTO updateSingleProductBatch(ProductBatchDTO productBatch) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("UPDATE product_batches SET creation_date = ?, order_status = ?, production_EndDate = ?, inactive = ?, recipe_id = ?, recipe_end_date = ?, productionleader_id_user_id = ? WHERE product_batch_id = ?");

            pStmt = setUpdatePreparedStatement(pStmt, productBatch);

            pStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readSingleProductBatchById(productBatch.getProductBatchId());
    }

    @Override
    public List<ProductBatchDTO> updateMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatches) throws SQLException {
        List<Integer> idList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("UPDATE product_batches SET creation_date = ?, production_status = ?, production_end_date = ?, inactive = ?, recipe_id = ?, recipe_end_date = ?, productionleader_id_user_id = ? WHERE product_batch_id = ?");

            for (ProductBatchDTO resourceBatch : listOfProductBatches) {
                idList.add(resourceBatch.getProductBatchId());

                setUpdatePreparedStatement(pStmt, resourceBatch);
                pStmt.addBatch();
            }

            pStmt.executeBatch();
            static_commitTransAction(conn);

        } catch (BatchUpdateException batchEx) {
            throw new BatchUpdateException(batchEx);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readMultipleProductBatchsByList(idList);
    }

    @Override
    public ProductBatchDTO setInactiveSingleProductBatch(int productBatchId) throws SQLException {
        try(Connection connection = static_createConnection()){

            PreparedStatement pStmt = connection.prepareStatement("UPDATE product_batches SET inactive = ? WHERE product_batch_id = ?");

            pStmt.setBoolean(1, true);
            pStmt.setInt(2, productBatchId);
            pStmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return readSingleProductBatchById(productBatchId);
    }

    @Override
    public List<ProductBatchDTO> setInactiveMultipleProductBatchs(List<Integer> listOfProductBatchIds) throws SQLException {
        List<Integer> idList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("UPDATE product_batches SET inactive = ? WHERE product_batch_id = ?");

            for (int id : listOfProductBatchIds) {
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
        return readMultipleProductBatchsByList(idList);
    }
}
