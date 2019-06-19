package main.java.Rest;

import main.java.Core.ProductBatchDTO;

import javax.ws.rs.core.Response;

public interface I_RestListener_ProductBatch {
    Response createProductBatch(ProductBatchDTO recipe);
    Response getAllProductBatches();
    Response getProductBatchById(int productBatchId);
    Response getProductBatchesBySearch(String search);
    Response updateProductBatch(ProductBatchDTO user);
    Response deleteProductBatch(int productBatchId);
}
