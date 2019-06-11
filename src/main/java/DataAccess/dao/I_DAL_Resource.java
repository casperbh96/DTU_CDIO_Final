package DataAccess.dao;

import DataAccess.dto.ResourceDTO;

import java.sql.SQLException;
import java.util.List;

public interface I_DAL_Resource {

    ResourceDTO createSingleResource( ResourceDTO singleResource ) throws SQLException;
    List<ResourceDTO> createMultipleResources( List<ResourceDTO> listOfResources ) throws SQLException;

    ResourceDTO readSingleResourcebyId(int ResourceId ) throws SQLException;
    List<ResourceDTO> readMultipleResourcesByList (List<Integer> listOfResourceIds) throws SQLException;
    List<ResourceDTO> readResourcebySearch(String keyword) throws SQLException;
    List<ResourceDTO> readAllResources() throws SQLException;

    ResourceDTO updateSingleResource(ResourceDTO Resource) throws SQLException;
    List<ResourceDTO> updateMultipleResources(List<ResourceDTO> listOfResources) throws SQLException;

    ResourceDTO deleteSingleResource(ResourceDTO Resource) throws SQLException;
    List<ResourceDTO> deleteMultipleResources(List<ResourceDTO> listOfResources) throws SQLException;

}