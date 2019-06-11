package DataAccess.dao;

import DataAccess.dto.ResourceDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static DataAccess.dao.Connector.static_createConnection;

public class DAO_Resource implements I_DAL_Resource {
    @Override
    public ResourceDTO createSingleResource(ResourceDTO singleResource) throws SQLException {
        try{
            try(Connection conn = static_createConnection()){
                // Code
                return null;
            }
        }
        catch(SQLException ex){
            // Code
        }
        return null;
    }

    @Override
    public List<ResourceDTO> createMultipleResources(List<ResourceDTO> listOfResources) {
        return null;
    }

    @Override
    public ResourceDTO readSingleResourcebyId(int ResourceId) {
        return null;
    }

    @Override
    public List<ResourceDTO> readMultipleResourcesByList(List<Integer> listOfResourceIds) {
        return null;
    }

    @Override
    public List<ResourceDTO> readResourcebySearch(String keyword) throws SQLException{
        try (Connection conn = static_createConnection()) {
            return null;

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public List<ResourceDTO> readAllResources() throws SQLException {
        try (Connection conn = static_createConnection()) {
            return null;

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public ResourceDTO updateSingleResource(ResourceDTO Resource) {
        return null;
    }

    @Override
    public List<ResourceDTO> updateMultipleResources(List<ResourceDTO> listOfResources) {
        return null;
    }

    @Override
    public ResourceDTO deleteSingleResource(ResourceDTO Resource) {
        return null;
    }

    @Override
    public List<ResourceDTO> deleteMultipleResources(List<ResourceDTO> listOfResources) {
        return null;
    }
}
