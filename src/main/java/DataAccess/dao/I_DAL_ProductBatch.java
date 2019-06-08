package main.java.dal;

import DataAccess.dto.ProductBatchDTO;

import java.io.Serializable;
import java.util.List;

public interface I_DAL_ProductBatch extends Serializable {

    ProductBatchDTO createSingleProductBatch( ProductBatchDTO singleProductBatch );
    List<ProductBatchDTO> createMultipleProductBatchs( List<ProductBatchDTO> listOfProductBatchs );

    ProductBatchDTO readSingleProductBatchbyId(int ProductBatchId );
    List<ProductBatchDTO> readMultipleProductBatchsByList (List<Integer> listOfProductBatchIds);
    List<ProductBatchDTO> readProductBatchbySearch(String keyword);
    List<ProductBatchDTO> readAllProductBatchs();

    ProductBatchDTO updateSingleProductBatch(ProductBatchDTO ProductBatch);
    List<ProductBatchDTO> updateMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatchs);

    ProductBatchDTO deleteSingleProductBatch(ProductBatchDTO ProductBatch);
    List<ProductBatchDTO> deleteMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatchs);

}
