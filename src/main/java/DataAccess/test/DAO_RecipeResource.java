package main.java.DataAccess.test;

import main.java.Core.REL_RecipeResourceDTO;
import main.java.Core.RecipeDTO;
import main.java.Core.ResourceDTO;
import main.java.DataAccess.dao.DAO_REL_RecipeResource;
import main.java.DataAccess.dao.DAO_Recipe;
import main.java.DataAccess.dao.DAO_Resource;

import java.sql.BatchUpdateException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DAO_RecipeResource {
    DAO_REL_RecipeResource dao = new DAO_REL_RecipeResource();
    DAO_Recipe daoRec = new DAO_Recipe();
    DAO_Resource daoRes = new DAO_Resource();

    @org.junit.Test
    public void readAllRecipesResourcesTest() {
        try {
            List<REL_RecipeResourceDTO> recResList = dao.readAllRecipeResources();
            assertNotNull(recResList);
            assertNotEquals(recResList.isEmpty(), recResList);

            for(REL_RecipeResourceDTO recRes : recResList){
                System.out.println(recRes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void readRecipeBySearchTest() {
        try {
            List<REL_RecipeResourceDTO> dtoList = dao.readRecipeResourcebySearch("1");
            assertNotNull(dtoList);
            assertNotEquals(dtoList.isEmpty(), dtoList);

            for (REL_RecipeResourceDTO dto : dtoList) {
                System.out.println(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void createMultipleRecipeTest() {
        try {
            List<REL_RecipeResourceDTO> recResDTO = new ArrayList<>();

            List<RecipeDTO> recList = new ArrayList<>();
            RecipeDTO rec1 = new RecipeDTO(180, Date.valueOf("9999-12-31"), "recipe3", 123,61);
            RecipeDTO rec2 = new RecipeDTO(181, Date.valueOf("9999-12-31"), "recipe4", 321, 61);
            recList.add(rec1);
            recList.add(rec2);

            List<ResourceDTO> resList = new ArrayList<>();
            ResourceDTO res1 = new ResourceDTO(280, "test123", false, false);
            ResourceDTO res2 = new ResourceDTO(281, "test1234", false, false);
            resList.add(res1);
            resList.add(res2);

            assertEquals(recList.size(), resList.size());

            for(int i = 0; i < recList.size() && i < resList.size(); i++){
                recResDTO.add(new REL_RecipeResourceDTO(
                        resList.get(i).getResourceId(), recList.get(i).getRecipeId(),
                        recList.get(i).getRecipeEndDate(), 22.22, 1.2));
            }

            daoRec.createMultipleRecipes(recList);
            daoRes.createMultipleResources(resList);

            List<REL_RecipeResourceDTO> returnList = dao.createMultipleRecipeResources(recResDTO);
            assertNotNull(returnList);
            assertNotEquals(returnList.isEmpty(), returnList);

            for(REL_RecipeResourceDTO i : returnList){
                System.out.println(i);
            }
        }
        catch (BatchUpdateException batchEx) {
            batchEx.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
