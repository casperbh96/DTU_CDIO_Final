package main.java.DataAccess.dao;

import main.java.DataAccess.dto.ResourceDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static main.java.DataAccess.dao.Connector.*;

public class DAO_Resource implements I_DAL_Resource {
    private List<ResourceDTO> resultSetWhileLoop(ResultSet resultset) throws SQLException {
        ResourceDTO res = null;
        List<ResourceDTO> resList = new ArrayList<>();

        try{
            while (resultset.next()) {
                res = new ResourceDTO(resultset.getInt(1), resultset.getString(2), resultset.getBoolean(3), resultset.getBoolean(4));
                resList.add(res);
            }
        }
        catch(SQLException ex){
            throw new SQLException(ex);
        }

        return resList;
    }

    private PreparedStatement setCreatePreparedStatement(PreparedStatement pStmt, ResourceDTO res) throws SQLException {
        try{
            pStmt.setInt(1, res.getResourceId());
            pStmt.setString(2, res.getResourceName());
            pStmt.setBoolean(3, res.getReorder());
            pStmt.setBoolean(4, res.getInactive());
        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    private PreparedStatement setUpdatePreparedStatement(PreparedStatement pStmt, ResourceDTO res) throws SQLException {
        try{
            pStmt.setString(1, res.getResourceName());
            pStmt.setBoolean(2, res.getReorder());
            pStmt.setBoolean(3, res.getInactive());
            pStmt.setInt(4, res.getResourceId());
        } catch (SQLException ex){
            throw new SQLException(ex);
        }
        return pStmt;
    }

    @Override
    public ResourceDTO createSingleResource(ResourceDTO singleResource) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO resources (resource_id, resource_name, reorder, inactive) VALUES (?,?,?,?)");

            pStmt = setCreatePreparedStatement(pStmt, singleResource);

            pStmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return readSingleResourcebyId(singleResource.getResourceId());
    }

    @Override
    public List<ResourceDTO> createMultipleResources(List<ResourceDTO> listOfResources) throws SQLException {
        List<Integer> idList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO resources (resource_id, resource_name, reorder, inactive) VALUES (?,?,?,?)");

            int index = 0;
            for (ResourceDTO res : listOfResources) {
                idList.add(res.getResourceId());

                pStmt = setCreatePreparedStatement(pStmt, res);

                pStmt.addBatch();
                index++;
            }
            pStmt.executeBatch();
            static_commitTransAction(conn);
        } catch (BatchUpdateException batchEx) {
            throw new BatchUpdateException(batchEx);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return readMultipleResourcesByList(idList);
    }

    @Override
    public ResourceDTO readSingleResourcebyId(int resourceId) throws SQLException {
        ResourceDTO res = null;

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM resources WHERE resource_id=?");

            pStmt.setInt(1, resourceId);
            ResultSet resultset = pStmt.executeQuery();

            // Move pointer to first row before Id, then to row with Id (fix)
            resultset.beforeFirst();
            resultset.next();

            res = new ResourceDTO(resultset.getInt(1), resultset.getString(2), resultset.getBoolean(3), resultset.getBoolean(4));
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return res;
    }

    @Override
    public List<ResourceDTO> readMultipleResourcesByList(List<Integer> listOfResourceIds) throws SQLException {
        List<ResourceDTO> resList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        // Produce string with number of ? equal to size of listOfResourceIds
        for (int i = 0; i < listOfResourceIds.size(); i++) {
            if (i == listOfResourceIds.size() - 1) {
                builder.append("?");
            } else {
                builder.append("?,");
            }
        }

        try (Connection conn = static_createConnection()) {
            // Turns into SELECT * FROM resources WHERE resource_id IN () with the string builder in the parenthesis
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM resources WHERE resource_id IN (" + builder.toString() + ")");

            // Set each of the ? to the corresponding Id from listOfResourceIds
            int index = 1;
            for (int i : listOfResourceIds) {
                pStmt.setInt(index++, i);
            }

            ResultSet resultset = pStmt.executeQuery();

            resList = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return resList;
    }

    @Override
    public List<ResourceDTO> readResourcebySearch(String keyword) throws SQLException {
        List<ResourceDTO> resList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("select * from resources " +
                    "WHERE resource_id LIKE ? OR resource_name LIKE ? OR reorder LIKE ? OR inactive LIKE ?");
            pStmt.setString(1, "%" + keyword + "%");
            pStmt.setString(2, "%" + keyword + "%");
            pStmt.setString(3, "%" + keyword + "%");
            pStmt.setString(4, "%" + keyword + "%");
            ResultSet resultset = pStmt.executeQuery();

            resList = resultSetWhileLoop(resultset);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return resList;
    }

    @Override
    public List<ResourceDTO> readAllResources() throws SQLException {
        List<ResourceDTO> resList = new ArrayList<>();
        ResourceDTO res = null;

        try (Connection connection = static_createConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM resources WHERE resource_id");
            ResultSet resultset = pStmt.executeQuery();

            resList = resultSetWhileLoop(resultset);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    @Override
    public ResourceDTO updateSingleResource(ResourceDTO resource) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("UPDATE resources SET resource_name = ?, reorder = ?, inactive = ? WHERE resource_id = ?");

            pStmt = setUpdatePreparedStatement(pStmt, resource);

            pStmt.executeUpdate();

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return readSingleResourcebyId(resource.getResourceId());
    }

    @Override
    public List<ResourceDTO> updateMultipleResources(List<ResourceDTO> listOfResources) throws SQLException {
        List<Integer> idList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            static_startTransAction(conn);
            PreparedStatement pStmt = conn.prepareStatement("UPDATE resources SET resource_name = ?, reorder = ?, inactive = ? WHERE resource_id = ?");

            int index = 0;
            for (ResourceDTO res : listOfResources) {
                idList.add(res.getResourceId());

                pStmt = setUpdatePreparedStatement(pStmt, res);

                pStmt.addBatch();
                index++;
            }

            pStmt.executeBatch();
            static_commitTransAction(conn);

        } catch (BatchUpdateException batchEx){
            throw new BatchUpdateException(batchEx);
        }
        catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return readMultipleResourcesByList(idList);
    }

//    @Override
//    public void deleteSingleResource(int resourceId) throws SQLException {
//        try(Connection connection = static_createConnection()){
//
//            PreparedStatement pStmt = connection.prepareStatement("DELETE FROM resources WHERE resource_id = ?");
//
//            pStmt.setInt(1, resourceId);
//            pStmt.executeUpdate();
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void deleteMultipleResources(List<Integer> listOfResourceIds) throws SQLException {
//        try (Connection conn = static_createConnection()) {
//            static_startTransAction(conn);
//            PreparedStatement pStmt = conn.prepareStatement("DELETE FROM resources WHERE resource_id = ?");
//
//            int index = 0;
//            for (int id : listOfResourceIds) {
//                pStmt.setInt(1, id);
//
//                pStmt.addBatch();
//                index++;
//            }
//            pStmt.executeBatch();
//            static_commitTransAction(conn);
//
//        } catch (BatchUpdateException batchEx) {
//            throw new BatchUpdateException(batchEx);
//        } catch (SQLException ex) {
//            throw new SQLException(ex);
//        }
//    }
}
