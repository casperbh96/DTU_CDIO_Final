package DataAccess.test;

import DataAccess.dao.DAO_Resource;
import DataAccess.dto.ResourceDTO;
import org.junit.Test;

import java.sql.SQLException;
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
            System.out.println(dao.readResourcebySearch("1"));
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
