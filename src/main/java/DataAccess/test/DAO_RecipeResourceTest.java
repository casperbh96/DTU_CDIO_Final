package main.java.DataAccess.test;

import main.java.Core.REL_RecipeResourceDTO;
import main.java.Core.RecipeDTO;
import main.java.Core.ResourceDTO;
import main.java.DataAccess.dao.DAO_REL_RecipeResource;
import main.java.DataAccess.dao.DAO_Recipe;
import main.java.DataAccess.dao.DAO_Resource;
import org.junit.jupiter.api.Assertions;

import java.sql.BatchUpdateException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DAO_RecipeResourceTest {
    DAO_REL_RecipeResource dao = new DAO_REL_RecipeResource();
    DAO_Recipe daoRec = new DAO_Recipe();
    DAO_Resource daoRes = new DAO_Resource();

    // region CREATE

    // Single create

    @org.junit.Test
    public void test(){
        try {

            List<REL_RecipeResourceDTO> recResList = new ArrayList<>();

            recResList.add(new REL_RecipeResourceDTO(60, 60, Date.valueOf("9999-12-31"), 222, 2));
            recResList.add(new REL_RecipeResourceDTO(61, 60, Date.valueOf("9999-12-31"), 222, 2));
            recResList.add(new REL_RecipeResourceDTO(64, 60, Date.valueOf("9999-12-31"), 112, 1));
            recResList.add(new REL_RecipeResourceDTO(65, 60, Date.valueOf("9999-12-31"), 222, 2));



            dao.createMultipleRecipeResources(recResList);
            System.out.println(recResList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void createMultipleRecipeResourceTest() {
        try {
            List<REL_RecipeResourceDTO> recResDTO = new ArrayList<>();

            List<RecipeDTO> recList = new ArrayList<>();
            RecipeDTO rec1 = new RecipeDTO(70, Date.valueOf("9999-12-31"), "recipe3", 123,61);
            RecipeDTO rec2 = new RecipeDTO(60, Date.valueOf("9999-12-31"), "recipe4", 321, 61);
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
                        recList.get(i).getRecipeEndDate(), 25.22, 1.2));
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
    // endregion

    //region READ
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
    public void readRecipeResourceBySearchTest() {
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
    public void readSingleRecipeResourceByIdTest() {
        try {
            REL_RecipeResourceDTO res = dao.readSingleRecipeResourcebyId(280, 180, Date.valueOf("9999-12-31"));
            Assertions.assertNotNull(res);

            System.out.println(res);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readMultipleRecipeResourceByListTest() {
        try {
            List<REL_RecipeResourceDTO> userList = dao.readMultipleRecipeResourcesByList(
                    new ArrayList<>(Arrays.asList(280, 281)), new ArrayList<>(Arrays.asList(180, 181)),
                    new ArrayList<>(Arrays.asList(Date.valueOf("9999-12-31"), Date.valueOf("9999-12-31"))) );

            Assertions.assertNotNull(userList);
            Assertions.assertNotEquals(userList.isEmpty(), userList);

            for (REL_RecipeResourceDTO i : userList) {
                System.out.println(i);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //endregion

    // region UPDATE
    @org.junit.Test
    public void updateSingleRecipeResourceTest(){
        try{
            REL_RecipeResourceDTO resRec = dao.updateSingleRecipeResource(
                    new REL_RecipeResourceDTO(280, 180,
                            Date.valueOf("9999-12-31"), 33.33, 2.5));
            Assertions.assertNotNull(resRec);

            System.out.println(resRec);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void updateMultipleRecipeResourceTest(){
        try{
            List<REL_RecipeResourceDTO> recResList = new ArrayList<>();
            REL_RecipeResourceDTO recRes1 = new REL_RecipeResourceDTO(280, 180, Date.valueOf("9999-12-31"), 55.55, 3.5);
            REL_RecipeResourceDTO recRes2 = new REL_RecipeResourceDTO(281, 181, Date.valueOf("9999-12-31"), 56.56, 4.5);
            recResList.add(recRes1);
            recResList.add(recRes2);

            List<REL_RecipeResourceDTO> allRes = dao.updateMultipleRecipeResources(recResList);
            Assertions.assertNotNull(allRes);
            Assertions.assertNotEquals(recResList.isEmpty(), recResList);

            for (REL_RecipeResourceDTO i : allRes) {
                System.out.println(i);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    // endregion

    // region DELETE
    @org.junit.Test
    public void deleteSingleRecipeResourceTest(){
        try{
            dao.deleteSingleRecipeResource(270, 170, Date.valueOf("9999-12-31"));

        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void deleteMultipleRecipeResourceTest(){
        try{
            List<Integer> resourcesIds = new ArrayList<>(Arrays.asList(280, 281));
            List<Integer> recipeIds = new ArrayList<>(Arrays.asList(180, 181));
            List<Date> dateList = new ArrayList<>(Arrays.asList(Date.valueOf("9999-12-31"),Date.valueOf("9999-12-31")));

            dao.deleteMultipleRecipeResources(resourcesIds, recipeIds, dateList);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    // endregion
}
