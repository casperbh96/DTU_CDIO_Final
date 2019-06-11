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
            List<ResourceDTO> allRes = dao.readAllResources();
            if(allRes != null){
                for(ResourceDTO i : allRes){
                    System.out.println(i);
                }
            }
            else{
                System.out.println("Result was null");
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
            if(res != null){
                for(ResourceDTO resI : res){
                    System.out.println(resI.getResourceId());
                }
            }
            else{
                System.out.println("Result was null");
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
            ResourceDTO res1 = new ResourceDTO(990,"test90",0);
            ResourceDTO res2 = new ResourceDTO(991,"test91",0);

            resList.add(res1);
            resList.add(res2);

            List<ResourceDTO> allRes = dao.createMultipleResources(resList);
            if(allRes != null){
                for(ResourceDTO i : allRes){
                    System.out.println(i);
                }
            }
            else{
                System.out.println("Result was null");
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
            System.out.println(res);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
