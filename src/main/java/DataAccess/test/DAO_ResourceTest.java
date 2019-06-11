package DataAccess.test;

import java.util.*;
import DataAccess.dao.DAO_Resource;
import DataAccess.dto.ResourceDTO;
import org.junit.Test;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DAO_ResourceTest {
    DAO_Resource dao = new DAO_Resource();

    @org.junit.Test
    public void getIngredientsAll() {
        try{
            List<ResourceDTO> allRes = dao.readAllResources();
            assertNotNull(allRes);

            for(ResourceDTO i : allRes){
                System.out.println(i);
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void getResourcesBySearchString(){
        try{
            List<ResourceDTO> res = dao.readResourcebySearch("998");
            assertNotNull(res);

            for(ResourceDTO resI : res){
                System.out.println(resI.getResourceId());
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void insertMultipleResources(){
        try{
            List<ResourceDTO> resList = new ArrayList<>();
            ResourceDTO res1 = new ResourceDTO(60,"123",0);
            ResourceDTO res2 = new ResourceDTO(61,"1234",0);

            resList.add(res1);
            resList.add(res2);

            List<ResourceDTO> allRes = dao.createMultipleResources(resList);
            assertNotNull(allRes);

            for(ResourceDTO i : allRes){
                System.out.println(i);
            }
        }
        catch(BatchUpdateException batchEx){
            batchEx.printStackTrace();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void getSingleResource() {
        try{
            ResourceDTO res = dao.readSingleResourcebyId(998);
            assertNotNull(res);

            System.out.println(res);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readMultipleResources() {
        try {
            List<ResourceDTO> allRes = dao.readMultipleResourcesByList(new ArrayList<>(Arrays.asList(1, 998)));
            assertNotNull(allRes);

            for (ResourceDTO i : allRes) {
                System.out.println(i);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
