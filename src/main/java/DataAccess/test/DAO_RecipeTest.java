package main.java.DataAccess.test;

import main.java.Core.RecipeDTO;
import main.java.Core.ResourceBatchDTO;
import main.java.DataAccess.dao.DAO_Recipe;

import java.sql.BatchUpdateException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;

public class DAO_RecipeTest {
    DAO_Recipe dao = new DAO_Recipe();

    @org.junit.Test
    public void readAllRecipesTest() {
        try {
            List<RecipeDTO> dtoList = dao.readAllRecipes();
            assertNotNull(dtoList);
            assertNotEquals(dtoList.isEmpty(), dtoList);

            for (RecipeDTO i : dtoList) {
                System.out.println(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void readRecipeBySearchTest() {
        try {
            List<RecipeDTO> dtoList = dao.readRecipeBySearch("61");
            assertNotNull(dtoList);
            assertNotEquals(dtoList.isEmpty(), dtoList);

            for (RecipeDTO dto : dtoList) {
                System.out.println(dto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void createMultipleRecipeTest() {
        try {
            List<RecipeDTO> dtoList = new ArrayList<>();
            RecipeDTO dto1 = new RecipeDTO(70, Date.valueOf("9999-12-31"), "rec1", 123,61);
            RecipeDTO dto2 = new RecipeDTO(71, Date.valueOf("9999-12-31"), "rec2", 321, 61);

            dtoList.add(dto1);
            dtoList.add(dto2);

            List<RecipeDTO> allObjs = dao.createMultipleRecipes(dtoList);
            assertNotNull(allObjs);
            assertNotEquals(dtoList.isEmpty(), dtoList);

            for (RecipeDTO i : allObjs) {
                System.out.println(i);
            }
        } catch (BatchUpdateException batchEx) {
            batchEx.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readSingleRecipeByIdTest() {
        try {
            RecipeDTO dto = dao.readSingleRecipeById(70, Date.valueOf("9999-12-31"));
            assertNotNull(dto);

            System.out.println(dto);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void readMultipleRecipeByListTest() {
        try {
            ArrayList<Integer> idList = new ArrayList<>();
            idList.add(60);
            idList.add(61);

            ArrayList<Date> dateList = new ArrayList<>();
            dateList.add(Date.valueOf("9999-12-31"));
            dateList.add(Date.valueOf("9999-12-31"));

            List<RecipeDTO> dtoList = dao.readMultipleRecipesByList(idList,dateList);
            assertNotNull(dtoList);
            assertNotEquals(dtoList.isEmpty(), dtoList);

            for (RecipeDTO i : dtoList) {
                System.out.println(i);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void updateSingleRecipeTest(){
        try{
            RecipeDTO dto = dao.updateSingleRecipe(new RecipeDTO(70, Date.valueOf("9999-12-31"), "updatedRec1", 999,61));
            assertNotNull(dto);

            System.out.println(dto);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    @org.junit.Test
    public void updateMultipleResourceBatchesTest(){
        try{
            List<RecipeDTO> dtoList = new ArrayList<>();
            RecipeDTO obj1 = new RecipeDTO(70, Date.valueOf("9999-12-31"), "multUpdRec1", 567,61);
            RecipeDTO obj2 = new RecipeDTO(71, Date.valueOf("9999-12-31"), "multUpdRec1", 789, 61);

            dtoList.add(obj1);
            dtoList.add(obj2);

            List<RecipeDTO> allDTOs = dao.updateMultipleRecipes(dtoList);
            assertNotNull(allDTOs);
            assertNotEquals(dtoList.isEmpty(), dtoList);

            for (RecipeDTO i : allDTOs) {
                System.out.println(i);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }




}
