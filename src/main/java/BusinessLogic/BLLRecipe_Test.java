package main.java.BusinessLogic;

import main.java.Core.RecipeDTO;
import main.java.DataAccess.dao.DAO_Recipe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;

public class BLLRecipe_Test {
    BLLRecipe bll = new BLLRecipe();

    @org.junit.Test
    public void readAllRecipesTest() {
        try {
            Object[] objects = bll.getAllResourcesAndResourceBatchesByRecipeId(2);
            ArrayList<Object> list = new ArrayList(Arrays.asList(objects));
            for(Object o : list){
                System.out.println(o.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
