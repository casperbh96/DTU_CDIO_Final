package main.java.BusinessLogic;

import main.java.Core.ProductBatchDTO;
import main.java.Core.REL_ProductBatchResourceBatchDTO;
import main.java.DataAccess.dao.DAO_ProductBatch;
import main.java.DataAccess.dao.I_DAL_ProductBatch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BLLProductBatch implements I_BLLProductBatch {
    private I_DAL_ProductBatch daoProductBatch = new DAO_ProductBatch();
    BLLUserProductBatch bllUserProductBatch = new BLLUserProductBatch();
    BLLProductBatchResourceBatch bllProductBatchResourceBatch = new BLLProductBatchResourceBatch();
    BLLResourceBatch bllResourceBatch = new BLLResourceBatch();

    @Override
    public ProductBatchDTO createProductBatchButNotIncludedResourceBatches(ProductBatchDTO productBatchDTO) throws SQLException {
        return daoProductBatch.createSingleProductBatch(productBatchDTO);
    }

    @Override
    public ProductBatchDTO createProductBatch(ProductBatchDTO singleProductBatch, List<Integer> listOfResourceBatchIds, List<Double> listOfNetAmounts, List<Integer> listOfTaras) throws SQLException {
        boolean resourceBatchDouesNotExist = false;

        List<REL_ProductBatchResourceBatchDTO> productBatchResourceBatchList = new ArrayList<>();
        if (listOfResourceBatchIds != null) {

            for (int i = 0; i < listOfResourceBatchIds.size(); i++) {
                REL_ProductBatchResourceBatchDTO newProductBatchResourceBatch = new REL_ProductBatchResourceBatchDTO(listOfResourceBatchIds.get(i), singleProductBatch.getProductBatchId(),listOfNetAmounts.get(i), listOfTaras.get(i));

                if (bllResourceBatch.readSingleResourceBatchById(newProductBatchResourceBatch.getResourceBatchId()) != null) {
                    resourceBatchDouesNotExist = true;
                }

                productBatchResourceBatchList.add(newProductBatchResourceBatch);
            }
        }

        //returns null if the productBatch composes of one or more non-existent resourceBatches
        if(resourceBatchDouesNotExist) {
            return null;
        }
        ProductBatchDTO productBatch = daoProductBatch.createSingleProductBatch(singleProductBatch);
        bllProductBatchResourceBatch.createMultipleProductBatchResourceBatchs(productBatchResourceBatchList);

        return productBatch;
    }

//    @Override
//    public List<ProductBatchDTO> createMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatches) throws SQLException {
//        return null;
//    }

    @Override
    public ProductBatchDTO getProductBatchById(int productBatchId) throws SQLException {
        return daoProductBatch.readSingleProductBatchById(productBatchId);
    }

    @Override
    public List<ProductBatchDTO> getProductBatchesByList(List<Integer> listOfProductBatchIds) throws SQLException {
        return daoProductBatch.readMultipleProductBatchsByList(listOfProductBatchIds);
    }

    @Override
    public List<ProductBatchDTO> getProductBatchBySearch(String keyword) throws SQLException {
        return daoProductBatch.readProductBatchBySearch(keyword);
    }

    @Override
    public List<ProductBatchDTO> getAllProductBatchs() throws SQLException {
        return daoProductBatch.readAllProductBatchs();
    }

    @Override
    public List<ProductBatchDTO> getAllProductBatchesUnderProduction() throws SQLException {
        List<ProductBatchDTO> productBatchesUnderProduction = new ArrayList<>();

        for (ProductBatchDTO prodB : daoProductBatch.readAllProductBatchs()) {
            if (prodB.getProductionStatus().toLowerCase() == "under produktion".toLowerCase()) {
                productBatchesUnderProduction.add(prodB);
            }
        }

        return productBatchesUnderProduction;
    }

    @Override
    public List<ProductBatchDTO> getALlProductBatchesFinished() throws SQLException {
        List<ProductBatchDTO> productBatchesUnderProduction = new ArrayList<>();

        for (ProductBatchDTO prodB : daoProductBatch.readAllProductBatchs()) {
            if (prodB.getProductionStatus().toLowerCase() == "afsluttet".toLowerCase()) {
                productBatchesUnderProduction.add(prodB);
            }
        }

        return productBatchesUnderProduction;
    }

    @Override
    public ProductBatchDTO updateProductBatchButNotResourceBatches(ProductBatchDTO productBatchDTO) throws SQLException {
        return daoProductBatch.updateSingleProductBatch(productBatchDTO);
    }

    @Override
    public ProductBatchDTO updateSingleProductBatch(ProductBatchDTO productBatch, List<Integer> resourceIds, List<Double> netAmounts, List<Double> taras) throws SQLException {
        List<Integer> prodIdBatchHolder = new ArrayList<>();
        prodIdBatchHolder.add(productBatch.getProductBatchId());

        //gets the prodBatchResBatch elements that will get updated
        List<REL_ProductBatchResourceBatchDTO> prodBatchResBatchToBeUpdated =
                bllProductBatchResourceBatch.readMultipleProductBatchResourceBatchsByList(resourceIds, prodIdBatchHolder);


        boolean prodBatchExists = daoProductBatch.readSingleProductBatchById(productBatch.getProductBatchId()) != null;
        boolean connectedValuesHaveMatchingNumberOfElements = resourceIds.size() == netAmounts.size() && resourceIds.size() == taras.size() &&
                resourceIds.size() == prodBatchResBatchToBeUpdated.size();

        if (prodBatchExists && connectedValuesHaveMatchingNumberOfElements) {
            for (int i = 0; i < prodBatchResBatchToBeUpdated.size(); i++) {
                prodBatchResBatchToBeUpdated.get(i).setNetAmount(netAmounts.get(i));
                prodBatchResBatchToBeUpdated.get(i).setTara(taras.get(i));
            }
            bllProductBatchResourceBatch.updateMultipleProductBatchResourceBatchs(prodBatchResBatchToBeUpdated);
            return daoProductBatch.updateSingleProductBatch(productBatch);
        }

        //returns null, if problem with update data. In this case no modifying methods have been executed
        return null;
    }

//    @Override
//    public List<ProductBatchDTO> updateMultipleProductBatchs(List<ProductBatchDTO> listOfProductBatches) throws SQLException {
//        return null;
//    }

    @Override
    public ProductBatchDTO setInactiveSingleProductBatch(int productBatchId) throws SQLException {
        return daoProductBatch.setInactiveSingleProductBatch(productBatchId);
    }

    @Override
    public List<ProductBatchDTO> setInactiveMultipleProductBatchs(List<Integer> listOfProductBatchIds) throws SQLException {
        return daoProductBatch.setInactiveMultipleProductBatchs(listOfProductBatchIds);
    }
}
