package DataAccess.test;

import DataAccess.dao.DAO_Resource;
import DataAccess.dto.ResourceDTO;
import org.junit.Test;

import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DAO_ResourceTest {
    DAO_Resource dao = new DAO_Resource();

    @org.junit.Test
    public void getIngredientsAll() {
        try{
            System.out.println(dao.readAllResources());
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void getResourcesBySearchString(){
        try{
            System.out.println(dao.readResourcebySearch("998"));
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void insertMultipleResources(){
        try{
            List<ResourceDTO> resList = new ArrayList<>();
            ResourceDTO res1 = new ResourceDTO(998,"test1",0);
            ResourceDTO res2 = new ResourceDTO(999,"test2",0);

            resList.add(res1);
            resList.add(res2);

            System.out.println(dao.createMultipleResources(resList));
        }
        catch(BatchUpdateException batchEx){
            batchEx.printStackTrace();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
