package DataAccess.dao;

import DataAccess.dto.ResourceDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static DataAccess.dao.Connector.*;

public class DAO_Resource implements I_DAL_Resource {
    @Override
    public ResourceDTO createSingleResource(ResourceDTO singleResource) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("INSERT INTO resources (resource_id, resource_name, reorder, inactive) VALUES (?,?,?,?)");

            pStmt.setInt(1, singleResource.getResourceId());
            pStmt.setString(2, singleResource.getResourceName());
            pStmt.setBoolean(3, singleResource.getReorder());
            pStmt.setBoolean(4, singleResource.getInactive());

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

                pStmt.setInt(1, res.getResourceId());
                pStmt.setString(2, res.getResourceName());
                pStmt.setBoolean(3, res.getReorder());
                pStmt.setBoolean(4, res.getInactive());

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
        ResourceDTO res;
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

            while (resultset.next()) {
                res = new ResourceDTO(resultset.getInt(1), resultset.getString(2), resultset.getBoolean(3), resultset.getBoolean(4));
                resList.add(res);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return resList;
    }

    @Override
    public List<ResourceDTO> readResourcebySearch(String keyword) throws SQLException {
        ResourceDTO res;
        List<ResourceDTO> resourceList = new ArrayList<>();

        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("select * from resources " +
                    "WHERE resource_id LIKE ? OR resource_name LIKE ? OR reorder LIKE ? OR inactive LIKE ?");
            pStmt.setString(1, "%" + keyword + "%");
            pStmt.setString(2, "%" + keyword + "%");
            pStmt.setString(3, "%" + keyword + "%");
            pStmt.setString(4, "%" + keyword + "%");
            ResultSet resultSet = pStmt.executeQuery();

            while (resultSet.next()) {
                res = new ResourceDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(3), resultSet.getBoolean(4));
                resourceList.add(res);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

        return resourceList;
    }

    @Override
    public List<ResourceDTO> readAllResources() throws SQLException {
        List<ResourceDTO> resList = new ArrayList<>();
        ResourceDTO res = null;

        try (Connection connection = static_createConnection()) {
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM resources WHERE resource_id");
            ResultSet resultset = pStmt.executeQuery();

            while (resultset.next()) {
                res = new ResourceDTO(resultset.getInt(1), resultset.getString(2), resultset.getBoolean(3), resultset.getBoolean(4));
                resList.add(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    @Override
    public ResourceDTO updateSingleResource(ResourceDTO resource) throws SQLException {
        try (Connection conn = static_createConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("UPDATE resources SET resource_name = ?, reorder = ?, inactive = ? WHERE resource_id = ?");

            pStmt.setString(1, resource.getResourceName());
            pStmt.setBoolean(2, resource.getReorder());
            pStmt.setBoolean(3, resource.getInactive());
            pStmt.setInt(4, resource.getResourceId());

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

                pStmt.setString(1, res.getResourceName());
                pStmt.setBoolean(2, res.getReorder());
                pStmt.setBoolean(3, res.getInactive());
                pStmt.setInt(4, res.getResourceId());

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
