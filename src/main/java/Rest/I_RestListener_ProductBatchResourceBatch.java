package main.java.Rest;

import main.java.Core.REL_ProductBatchResourceBatchDTO;

import javax.ws.rs.core.Response;

public interface I_RestListener_ProductBatchResourceBatch {
    Response createProductBatchResourceBatch(REL_ProductBatchResourceBatchDTO recipe);
    Response getAllProductBatchResourceBatches();
    Response getProductBatchResourceBatchById(int resourceBatchId, int productBatchId);
    Response getProductBatchResourceBatchesBySearch(String search);
    Response updateProductBatchResourceBatch(REL_ProductBatchResourceBatchDTO user);
    Response deleteProductBatchResourceBatch(int resourceBatchId, int productBatchId);
}
