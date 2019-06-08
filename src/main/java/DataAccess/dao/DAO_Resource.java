package DataAccess.dao;

import DataAccess.dto.ResourceDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static DataAccess.dao.Connector.static_createConnection;

public class DAO_Resource implements main.java.dal.I_DAL_Resource {
    @Override
    public ResourceDTO createSingleResource(ResourceDTO singleResource) throws SQLException {
        try{
            try(Connection conn = static_createConnection()){
                // Code
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
    public List<ResourceDTO> readResourcebySearch(String keyword) {
        return null;
    }

    @Override
    public List<ResourceDTO> readAllResources() throws SQLException {
        try (Connection conn = static_createConnection()) {
            List<ResourceDTO> resourceList = new LinkedList<>();

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Raavare");

            while (resultSet.next()) {
                ResourceDTO ress = new ResourceDTO();
                ress.setIngredientId(resultSet.getInt("idRaavare"));
                ress.setName(resultSet.getString("name"));
                resourceList.add(ress);
            }

            return resourceList;

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
