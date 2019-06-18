package main.java.BusinessLogic;

import main.java.Core.ResourceDTO;

import java.sql.SQLException;
import java.util.List;

public class BLLResource implements I_BLLResource {
    @Override
    public ResourceDTO createSingleResource(ResourceDTO singleResource) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceDTO> createMultipleResources(List<ResourceDTO> listOfResources) throws SQLException {
        return null;
    }

    @Override
    public ResourceDTO readSingleResourcebyId(int resourceId) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceDTO> readMultipleResourcesByList(List<Integer> listOfResourceIds) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceDTO> readResourcebySearch(String keyword) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceDTO> readAllResources() throws SQLException {
        return null;
    }

    @Override
    public ResourceDTO updateSingleResource(ResourceDTO resource) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceDTO> updateMultipleResources(List<ResourceDTO> listOfResources) throws SQLException {
        return null;
    }

    @Override
    public ResourceDTO setInactiveSingleResource(int resourceId) throws SQLException {
        return null;
    }

    @Override
    public List<ResourceDTO> setInactiveMultipleResources(List<Integer> listOfResourceIds) throws SQLException {
        return null;
    }
}
