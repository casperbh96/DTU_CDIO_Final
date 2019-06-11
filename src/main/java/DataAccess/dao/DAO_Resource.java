package DataAccess.dao;

import DataAccess.dto.ResourceDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static DataAccess.dao.Connector.static_createConnection;

public class DAO_Resource implements I_DAL_Resource {
    @Override
    public ResourceDTO createSingleResource(ResourceDTO singleResource) throws SQLException {
        try(Connection conn = static_createConnection()){
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO users (user_id, username, initials) VALUES (?,?,?)");

            pStmt.setInt(1, singleResource.getResourceId());
            pStmt.setString(2, singleResource.getResourceName());
            pStmt.setInt(3, singleResource.getReorder());

            pStmt.executeUpdate();
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
        return readSingleResourcebyId(singleResource.getResourceId());
    }

    @Override
    public List<ResourceDTO> createMultipleResources(List<ResourceDTO> listOfResources) throws SQLException{
        try(Connection conn = static_createConnection()){
            return null;
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
    }

    @Override
    public ResourceDTO readSingleResourcebyId(int ResourceId) throws SQLException {
        try(Connection conn = static_createConnection()){
            return null;
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
    }

    @Override
    public List<ResourceDTO> readMultipleResourcesByList(List<Integer> listOfResourceIds) throws SQLException {
        try(Connection conn = static_createConnection()){
            return null;
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
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
    public ResourceDTO updateSingleResource(ResourceDTO Resource) throws SQLException {
        try(Connection conn = static_createConnection()){
            return null;
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
    }

    @Override
    public List<ResourceDTO> updateMultipleResources(List<ResourceDTO> listOfResources) throws SQLException {
        try(Connection conn = static_createConnection()){
            return null;
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
    }

    @Override
    public ResourceDTO deleteSingleResource(ResourceDTO Resource) throws SQLException {
        try(Connection conn = static_createConnection()){
            return null;
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
    }

    @Override
    public List<ResourceDTO> deleteMultipleResources(List<ResourceDTO> listOfResources) throws SQLException {
        try(Connection conn = static_createConnection()){
            return null;
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
    }
}
