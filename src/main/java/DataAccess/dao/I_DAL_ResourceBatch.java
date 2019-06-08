package main.java.dal;

import DataAccess.dto.ResourceBatchDTO;

import java.io.Serializable;
import java.util.List;

public interface I_DAL_ResourceBatch extends Serializable {

    ResourceBatchDTO createSingleResourceBatch( ResourceBatchDTO singleResourceBatch );
    List<ResourceBatchDTO> createMultipleResourceBatchs( List<ResourceBatchDTO> listOfResourceBatchs );

    ResourceBatchDTO readSingleResourceBatchbyId(int ResourceBatchId );
    List<ResourceBatchDTO> readMultipleResourceBatchsByList (List<Integer> listOfResourceBatchIds);
    List<ResourceBatchDTO> readResourceBatchbySearch(String keyword);
    List<ResourceBatchDTO> readAllResourceBatchs();

    ResourceBatchDTO updateSingleResourceBatch(ResourceBatchDTO ResourceBatch);
    List<ResourceBatchDTO> updateMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatchs);

    ResourceBatchDTO deleteSingleResourceBatch(ResourceBatchDTO ResourceBatch);
    List<ResourceBatchDTO> deleteMultipleResourceBatchs(List<ResourceBatchDTO> listOfResourceBatchs);

}
