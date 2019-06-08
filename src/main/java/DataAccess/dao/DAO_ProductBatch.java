package DataAccess.dao;

import DataAccess.dto.ProductBatchDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static DataAccess.dao.Connector.static_commitTransAction;
import static DataAccess.dao.Connector.static_createConnection;
import static DataAccess.dao.Connector.static_startTransAction;
import static DataAccess.dao.Connector.*;

public class DAO_ProductBatch implements main.java.dal.I_DAL_ProductBatch {
    @Override
    public ProductBatchDTO createSingleProductBatch(ProductBatchDTO singleProductBatch) {
        return null;
    }

    @Override
    public List<ProductBatchDTO> createMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatchs) {
        return null;
    }

    @Override
    public ProductBatchDTO readSingleProductBatchbyId(int ProductBatchId) {
        return null;
    }

    @Override
    public List<ProductBatchDTO> readMultipleProductBatchsByList(List<Integer> listOfProductBatchIds) {
        return null;
    }

    @Override
    public List<ProductBatchDTO> readProductBatchbySearch(String keyword) {
        return null;
    }

    @Override
    public List<ProductBatchDTO> readAllProductBatchs() {
        return null;
    }

    @Override
    public ProductBatchDTO updateSingleProductBatch(ProductBatchDTO ProductBatch) {
        return null;
    }

    @Override
    public List<ProductBatchDTO> updateMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatchs) {
        return null;
    }

    @Override
    public ProductBatchDTO deleteSingleProductBatch(ProductBatchDTO ProductBatch) {
        return null;
    }

    @Override
    public List<ProductBatchDTO> deleteMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatchs) {
        return null;
    }
}
