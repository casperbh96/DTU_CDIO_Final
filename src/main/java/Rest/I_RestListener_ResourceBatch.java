package main.java.Rest;

import main.java.Core.ResourceBatchDTO;

import javax.ws.rs.core.Response;

public interface I_RestListener_ResourceBatch {
    Response createResourceBatch(ResourceBatchDTO recipe);
    Response getAllResourceBatches();
    Response getResourceBatchById(int resourceBatchId);
    Response getResourceBatchesBySearch(String search);
    Response updateResourceBatch(ResourceBatchDTO user);
    Response deleteResourceBatch(int resourceBatchId);
}
