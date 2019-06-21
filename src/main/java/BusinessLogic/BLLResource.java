package main.java.BusinessLogic;

import main.java.Core.ResourceDTO;
import main.java.DataAccess.dao.DAO_Resource;
import main.java.DataAccess.dao.I_DAL_Resource;

import java.sql.SQLException;
import java.util.List;

public class BLLResource implements I_BLLResource {
    private I_DAL_Resource daoRes = new DAO_Resource();

    @Override
    public ResourceDTO createSingleResource(ResourceDTO singleResource) throws SQLException {
        return daoRes.createSingleResource(singleResource);
    }

    @Override
    public List<ResourceDTO> createMultipleResources(List<ResourceDTO> listOfResources) throws SQLException {
        return daoRes.createMultipleResources(listOfResources);
    }

    @Override
    public ResourceDTO readSingleResourcebyId(int resourceId) throws SQLException {
        return daoRes.readSingleResourcebyId(resourceId);
    }

    @Override
    public List<ResourceDTO> readMultipleResourcesByList(List<Integer> listOfResourceIds) throws SQLException {
        return daoRes.readMultipleResourcesByList(listOfResourceIds);
    }

    @Override
    public List<ResourceDTO> readResourcebySearch(String keyword) throws SQLException {
        return daoRes.readResourcebySearch(keyword);
    }

    @Override
    public List<ResourceDTO> readAllResources() throws SQLException {
        return daoRes.readAllResources();
    }

    @Override
    public ResourceDTO updateSingleResource(ResourceDTO resource) throws SQLException {
        return daoRes.updateSingleResource(resource);
    }

    @Override
    public List<ResourceDTO> updateMultipleResources(List<ResourceDTO> listOfResources) throws SQLException {
        return daoRes.updateMultipleResources(listOfResources);
    }

    @Override
    public ResourceDTO setInactiveSingleResource(int resourceId) throws SQLException {
        return daoRes.setInactiveSingleResource(resourceId);
    }

    @Override
    public List<ResourceDTO> setInactiveMultipleResources(List<Integer> listOfResourceIds) throws SQLException {
        return daoRes.setInactiveMultipleResources(listOfResourceIds);
    }
}
