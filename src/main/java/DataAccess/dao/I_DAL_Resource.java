package DataAccess.dao;

import DataAccess.dto.ResourceDTO;

import java.sql.SQLException;
import java.util.List;

public interface I_DAL_Resource {

    ResourceDTO createSingleResource( ResourceDTO singleResource ) throws SQLException;
    List<ResourceDTO> createMultipleResources( List<ResourceDTO> listOfResources );

    ResourceDTO readSingleResourcebyId(int ResourceId );
    List<ResourceDTO> readMultipleResourcesByList (List<Integer> listOfResourceIds);
    List<ResourceDTO> readResourcebySearch(String keyword) throws SQLException;
    List<ResourceDTO> readAllResources() throws SQLException;

    ResourceDTO updateSingleResource(ResourceDTO Resource);
    List<ResourceDTO> updateMultipleResources(List<ResourceDTO> listOfResources);

    ResourceDTO deleteSingleResource(ResourceDTO Resource);
    List<ResourceDTO> deleteMultipleResources(List<ResourceDTO> listOfResources);

}
