package DataAccess.dao;

import DataAccess.dto.ResourceDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static DataAccess.dao.Connector.static_commitTransAction;
import static DataAccess.dao.Connector.static_createConnection;
import static DataAccess.dao.Connector.static_startTransAction;

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
        List<Integer> idList = new ArrayList<>();

        try(Connection conn = static_createConnection()){
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO resources (resource_id, resource_name, reorder) VALUES (?,?,?)");

            int index = 0;
            for(ResourceDTO res : listOfResources){
                idList.add(res.getResourceId());

                pStmt.setInt(1, res.getResourceId());
                pStmt.setString(2, res.getResourceName());
                pStmt.setInt(3, res.getReorder());

                pStmt.addBatch();
                index++;
            }
            int[] numUpdates=pStmt.executeBatch();
            for (int i=0; i < numUpdates.length; i++) {
                if (numUpdates[i] == -2)
                    System.out.println("Execution " + i +
                            ": unknown number of rows updated");
                else
                    System.out.println("Execution " + i +
                            "successful: " + numUpdates[i] + " rows updated");
            }
            static_commitTransAction(conn);
        }
        catch(BatchUpdateException batchEx){
            throw new BatchUpdateException(batchEx);
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }

        return readMultipleResourcesByList(idList);
    }

    @Override
    public ResourceDTO readSingleResourcebyId(int ResourceId) throws SQLException {
        ResourceDTO res = null;

        try(Connection conn = static_createConnection()){
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM resources WHERE resource_id=?");

            pStmt.setInt(1,ResourceId);
            ResultSet resultset = pStmt.executeQuery();

            // Move pointer to first row before Id, then to row with Id (fix)
            resultset.beforeFirst();
            resultset.next();

            res = new ResourceDTO(resultset.getInt(1), resultset.getString(2), resultset.getInt(3));
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }
        return res;
    }

    @Override
    public List<ResourceDTO> readMultipleResourcesByList(List<Integer> listOfResourceIds) throws SQLException {
        ResourceDTO res;
        List<ResourceDTO> resList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        // Produce string with number of ? equal to size of listOfResourceIds
        for( int i = 0 ; i < listOfResourceIds.size(); i++ ) {
            if(i == listOfResourceIds.size()-1) {
                builder.append("?");
            }
            else{
                builder.append("?,");
            }
        }

        try(Connection conn = static_createConnection()){
            // Turns into SELECT * FROM resources WHERE resource_id IN () with the string builder in the parenthesis
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM resources WHERE resource_id IN (" + builder.toString() + ")");

            // Set each of the ? to the corresponding Id from listOfResourceIds
            int index = 1;
            for(int i : listOfResourceIds ) {
                pStmt.setInt(index++, i);
            }

            ResultSet resultset = pStmt.executeQuery();

            while(resultset.next()) {
                res = new ResourceDTO(resultset.getInt(1), resultset.getString(2), resultset.getInt(3));
                resList.add(res);
            }
        }
        catch(BatchUpdateException batchEx){
            throw new BatchUpdateException(batchEx);
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }

        return resList;
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
        List<ResourceDTO> resList = new LinkedList<>();
        ResourceDTO res = null;

        try(Connection connection = static_createConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM resources WHERE resource_id");
            ResultSet resultset = pStmt.executeQuery();

            while(resultset.next()) {
                res = new ResourceDTO(resultset.getInt(1), resultset.getString(2), resultset.getInt(3));
                resList.add(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
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
