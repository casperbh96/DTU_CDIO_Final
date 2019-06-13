package DataAccess.dao;

import DataAccess.dto.ProductBatchDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static DataAccess.dao.Connector.static_commitTransAction;
import static DataAccess.dao.Connector.static_createConnection;
import static DataAccess.dao.Connector.static_startTransAction;
import static DataAccess.dao.Connector.*;

public class DAO_ProductBatch implements I_DAL_ProductBatch {
    @Override
    public ProductBatchDTO createSingleProductBatch(ProductBatchDTO singleProductBatch) throws SQLException {
        return null;
    }

    @Override
    public List<ProductBatchDTO> createMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatchs) throws SQLException {
        return null;
    }

    @Override
    public ProductBatchDTO readSingleProductBatchbyId(int productBatchId) throws SQLException {
        return null;
    }

    @Override
    public List<ProductBatchDTO> readMultipleProductBatchsByList(List<Integer> listOfProductBatchIds) throws SQLException {
        return null;
    }

    @Override
    public List<ProductBatchDTO> readProductBatchbySearch(String keyword) throws SQLException {
        return null;
    }

    @Override
    public List<ProductBatchDTO> readAllProductBatchs() throws SQLException {
        return null;
    }

    @Override
    public ProductBatchDTO updateSingleProductBatch(ProductBatchDTO productBatch) throws SQLException {
        return null;
    }

    @Override
    public List<ProductBatchDTO> updateMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatches) throws SQLException {
        return null;
    }
}
