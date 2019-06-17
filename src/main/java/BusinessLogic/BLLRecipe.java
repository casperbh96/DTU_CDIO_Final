package main.java.BusinessLogic;

import main.java.Core.REL_RecipeResourceDTO;
import main.java.Core.RecipeDTO;
import main.java.DataAccess.dao.DAO_REL_RecipeResource;
import main.java.DataAccess.dao.DAO_Recipe;
import main.java.DataAccess.dao.I_DAL_REL_UserProductBacth;
import main.java.DataAccess.dao.I_DAL_Recipe;
import org.junit.internal.runners.statements.FailOnTimeout;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BLLRecipe implements I_BLLRecipe {
    private DAO_Recipe daoRecipe = new DAO_Recipe();
    private DAO_REL_RecipeResource daoRecipeResource= new DAO_REL_RecipeResource();


    @Override
    public RecipeDTO createSingleRecipe(RecipeDTO singleRecipe, List<Integer> listOfResourceIds, List<Double> resourceAmounts, List<Double> tolerances) throws SQLException {
        RecipeDTO user = daoRecipe.createSingleRecipe(singleRecipe);
        boolean shouldReturnNull = false;

        if(listOfResourceIds != null){

            List<REL_RecipeResourceDTO> recipeResourceList = new ArrayList<>();
            for(int i = 0; i < listOfResourceIds.size(); i++){
                REL_RecipeResourceDTO newRecipeResource = new REL_RecipeResourceDTO(listOfResourceIds.get(i), singleRecipe.getRecipeId(), singleRecipe.getRecipeEndDate(), resourceAmounts.get(i), tolerances.get(i));


                recipeResourceList.add(newRecipeResource);

//                boolean addedRole = DAL_roleUser.assignUserRole(newRecipeResource);
//
//                if(!addedRole){
//                    shouldReturnNull = true;
//                }
            }
            daoRecipeResource.createMultipleRecipeResources(recipeResourceList);


        }

        if(shouldReturnNull) return null;

        return user;
    }

    @Override
    public List<RecipeDTO> createMultipleRecipes(List<RecipeDTO> listOfRecipes) throws SQLException {
        return null;
    }

    @Override
    public RecipeDTO readSingleRecipeById(int recipeId, Date recipeEndDate) throws SQLException {
        return null;
    }

    @Override
    public List<RecipeDTO> readMultipleRecipesByList(List<Integer> listOfRecipeIds, List<Date> listOfRecipeEndDates) throws SQLException {
        return null;
    }

    @Override
    public List<RecipeDTO> readRecipeBySearch(String keyword) throws SQLException {
        return null;
    }

    @Override
    public List<RecipeDTO> readAllRecipes() throws SQLException {
        return null;
    }

    @Override
    public RecipeDTO updateSingleRecipe(RecipeDTO recipe) throws SQLException {
        return null;
    }

    @Override
    public List<RecipeDTO> updateMultipleRecipes(List<RecipeDTO> listOfRecipes) throws SQLException {
        return null;
    }

    @Override
    public RecipeDTO setRecipeEndDateSingleRecipe(int recipeid, Date recipeEndDate) throws SQLException {
        return null;
    }

    @Override
    public List<RecipeDTO> setRecipeEndDateMultipleRecipes(List<Integer> recipeIds, List<Date> recipeEndDateBeforeChangeList) throws SQLException {
        return null;
    }
}
