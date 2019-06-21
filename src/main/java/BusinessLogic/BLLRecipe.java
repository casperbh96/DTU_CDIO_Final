package main.java.BusinessLogic;

import main.java.Core.*;
import main.java.DataAccess.dao.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BLLRecipe implements I_BLLRecipe {
    private I_DAL_Recipe daoRecipe = new DAO_Recipe();
    private BLLRecipeResource bllRecipeResource = new BLLRecipeResource();
    private BLLResource bllResource = new BLLResource();
    private BLLResourceBatch bllResourceBatch = new BLLResourceBatch();


    @Override
    public RecipeDTO createRecipeButNotIncludedResources(RecipeDTO recipe) throws SQLException {
        return daoRecipe.createSingleRecipe(recipe);
    }

    @Override
    public RecipeDTO createRecipe(RecipeDTO singleRecipe, List<Integer> listOfResourceIds, List<Double> resourceAmounts, List<Double> tolerances) throws SQLException {
        boolean resourceDoesNotExist = false;

        List<REL_RecipeResourceDTO> recipeResourceList = new ArrayList<>();
        if(listOfResourceIds != null){

            for(int i = 0; i < listOfResourceIds.size(); i++){
                REL_RecipeResourceDTO newRecipeResource = new REL_RecipeResourceDTO(listOfResourceIds.get(i), singleRecipe.getRecipeId(), singleRecipe.getRecipeEndDate(), resourceAmounts.get(i), tolerances.get(i));

                if(bllResource.readSingleResourcebyId(newRecipeResource.getResouceId()) != null) {
                    resourceDoesNotExist = true;
                }

                recipeResourceList.add(newRecipeResource);
            }

        }

        //returns null if the recipe composes of one or more non-existent resources
        if(resourceDoesNotExist) {
            return null;
        }
        RecipeDTO recipe = daoRecipe.createSingleRecipe(singleRecipe);
        bllRecipeResource.createMultipleRecipeResources(recipeResourceList);

        return recipe;
    }

    @Override
    public List<RecipeDTO> createRecipes(List<RecipeDTO> listOfRecipes, List<List<Integer>> listOfListOfResourceIds, List<List<Double>> listOfListOfResourceAmounts, List<List<Double>> listOfListOfTolerances) throws SQLException {
        boolean resourceDoesNotExist = false;

        List<REL_RecipeResourceDTO> recResList = new ArrayList<>();
        if(listOfListOfResourceIds != null && listOfListOfResourceAmounts != null && listOfListOfTolerances != null){

            for(int i = 0; i < listOfRecipes.size(); i++){

                //checks if the number of the connected values for the recipe elements are the same
                if(listOfListOfResourceAmounts.get(i).size() == listOfListOfResourceIds.get(i).size() &&
                        listOfListOfResourceIds.get(i).size() == listOfListOfTolerances.get(i).size()) {

                    for (int j = 0; j < listOfListOfResourceIds.get(i).size(); j++) {

                        REL_RecipeResourceDTO recRes = new REL_RecipeResourceDTO(listOfListOfResourceIds.get(i).get(j),
                                listOfRecipes.get(i).getRecipeId(),listOfRecipes.get(i).getRecipeEndDate(),
                                listOfListOfResourceAmounts.get(i).get(j), listOfListOfTolerances.get(i).get(j));

                        recResList.add(recRes);

                        if(bllResource.readSingleResourcebyId(recRes.getResouceId()) != null) {
                            resourceDoesNotExist = true;
                        }
                    }
                }
            }
        }

        //returns null if the recipe composes of one or more non-existent resources
        if(resourceDoesNotExist) {
            return null;
        }
        List<RecipeDTO> recipeList = daoRecipe.createMultipleRecipes(listOfRecipes);
        bllRecipeResource.createMultipleRecipeResources(recResList);

        return recipeList;
    }

    @Override
    public RecipeDTO getRecipeById(int recipeId, Date recipeEndDate) throws SQLException {
        return daoRecipe.readSingleRecipeById(recipeId,recipeEndDate);
    }

    public Object[] getAllResourcesAndResourceBatchesByRecipeId(int recipeId) throws SQLException {
        List<ResourceDTO> resList = new ArrayList<>();
        List<ResourceBatchDTO> listOfResBatLists = new ArrayList<>();
        List<REL_RecipeResourceDTO> recipeResourceList = null;
        List<Integer> listOfResourceIds = new ArrayList<>();

        double amount = 0.0;
        double tolerance = 0.0;

        recipeResourceList = bllRecipeResource.readAllRecipeResourcesByRecipeId(recipeId);
        for(REL_RecipeResourceDTO recRes : recipeResourceList){
            listOfResourceIds.add(recRes.getResouceId());
        }
        if(listOfResourceIds.size() > 0){
            resList = bllResource.readMultipleResourcesByList(listOfResourceIds);
        }

        List<ResourceBatchDTO> allResourceBatches = bllResourceBatch.readAllResourceBatchs();

        for(int i : listOfResourceIds){
            List<ResourceBatchDTO> newList = allResourceBatches.stream().filter(
                    ResourceBatchDTO -> ResourceBatchDTO.getResourceId() == i ).collect(Collectors.toList());

            for(REL_RecipeResourceDTO recRes : recipeResourceList){
                if(recRes.getResouceId() == i){
                    amount = recRes.getResourceAmount();
                    tolerance = recRes.getTolerance();
                }
            }
            int index = 0;
            ResourceBatchDTO resourceBatchToChoose = null;
            for(ResourceBatchDTO resBatch : newList){
                if(resBatch.getResourceBatchAmount() > amount - (amount * (tolerance/100))){
                    if(index == 0) resourceBatchToChoose = resBatch;
                    else if(resBatch.getResourceBatchAmount() > resourceBatchToChoose.getResourceBatchAmount()){
                        resourceBatchToChoose = resBatch;
                    }
                }
                index++;
            }
            listOfResBatLists.add(resourceBatchToChoose);
        }
        if(listOfResBatLists.size() > 0){
            return new Object[]{resList, listOfResBatLists};
        }
        return new Object[]{resList, new ArrayList<>()};
    }

    public Object[] getAllRelRecipeResourcesAndResourcesByRecipeId(int recipeId) throws SQLException {
        List<REL_RecipeResourceDTO> recResList = new ArrayList<>();
        List<ResourceDTO> resList = new ArrayList<>();
        List<Integer> listOfIds = new ArrayList<>();

        recResList = bllRecipeResource.readAllRecipeResourcesByRecipeId(recipeId);

        for(REL_RecipeResourceDTO recRes : recResList){
            listOfIds.add(recRes.getResouceId());
        }

        resList = bllResource.readMultipleResourcesByList(listOfIds);

        return new Object[]{recResList, resList};
    }

    @Override
    public RecipeDTO getActiveRecipeById(int recipeId) throws SQLException, ParseException {
        return daoRecipe.readSingleRecipeById(recipeId, new StringToSqlDateConverter().convertStringToDate("9999-12-31"));
    }

    @Override
    public List<RecipeDTO> getRecipesByList(List<Integer> listOfRecipeIds, List<Date> listOfRecipeEndDates) throws SQLException {
        return daoRecipe.readMultipleRecipesByList(listOfRecipeIds, listOfRecipeEndDates);
    }

    @Override
    public List<RecipeDTO> getRecipeBySearch(String keyword) throws SQLException {
        return daoRecipe.readRecipeBySearch(keyword);
    }

    @Override
    public List<RecipeDTO> getAllRecipes() throws SQLException {
        return daoRecipe.readAllRecipes();
    }

    @Override
    public List<RecipeDTO> getAllActiveRecipes() throws SQLException, ParseException {
        List<RecipeDTO> activeRecipes = new ArrayList<>();

        for (RecipeDTO rec : daoRecipe.readAllRecipes()) {
            if (rec.getRecipeEndDate() == new StringToSqlDateConverter().convertStringToDate("9999-12-31")) {
                activeRecipes.add(rec);
            }
        }

        return activeRecipes;
    }

    @Override
    public List<REL_RecipeResourceDTO> getAllResourcesForRecipe(int recipeId) throws SQLException, ParseException {
        return bllRecipeResource.readResourcesForRecipe(recipeId, new StringToSqlDateConverter().convertStringToDate("9999-12-31"));
    }

    @Override
    public RecipeDTO updateRecipeButNotResources(RecipeDTO recipe) throws SQLException {
        return daoRecipe.updateSingleRecipe(recipe);
    }

    @Override
    public RecipeDTO updateRecipe(RecipeDTO recipe, List<Integer> resourceIds, List<Double> resourceAmounts, List<Double> tolerances) throws SQLException {
        List<Integer> recIdHolder = new ArrayList<>();
        recIdHolder.add(recipe.getRecipeId());
        List<Date> recDateHolder = new ArrayList<>();
        recDateHolder.add(recipe.getRecipeEndDate());

        //stores the old recipe information in the db tables with the updated end date
        if (daoRecipe.readSingleRecipeById(recipe.getRecipeId(), recipe.getRecipeEndDate()) != null) {
            Date currDate = daoRecipe.setRecipeEndDateSingleRecipe(recipe.getRecipeId(), recipe.getRecipeEndDate()).getRecipeEndDate();
            List<REL_RecipeResourceDTO> recResToBeUpdated = bllRecipeResource.readMultipleRecipeResourcesByList(resourceIds, recIdHolder, recDateHolder);
            for (REL_RecipeResourceDTO recRes : recResToBeUpdated) {
                recRes.setRecipeEndDate(currDate);
            }
            bllRecipeResource.updateMultipleRecipeResources(recResToBeUpdated);
        }

        //creates the new (version of) recipe
        List<REL_RecipeResourceDTO> recipeResources = new ArrayList<>();
        for (int i = 0; i < resourceIds.size(); i++) {
            REL_RecipeResourceDTO recRes = new REL_RecipeResourceDTO(resourceIds.get(i),recipe.getRecipeId(),recipe.getRecipeEndDate(),resourceAmounts.get(i), tolerances.get(i));
            recipeResources.add(recRes);
        }
        bllRecipeResource.createMultipleRecipeResources(recipeResources);

        return daoRecipe.createSingleRecipe(recipe);
    }

//    @Override
//    public List<RecipeDTO> updateMultipleRecipes(List<RecipeDTO> listOfRecipes) throws SQLException {
//        return null;
//    }

    @Override
    public RecipeDTO setRecipeEndDateSingleRecipe(int recipeid, Date recipeEndDate) throws SQLException {
        return daoRecipe.setRecipeEndDateSingleRecipe(recipeid,recipeEndDate);
    }

    @Override
    public List<RecipeDTO> setRecipeEndDateMultipleRecipes(List<Integer> recipeIds, List<Date> recipeEndDateBeforeChangeList) throws SQLException {
        return daoRecipe.setRecipeEndDateMultipleRecipes(recipeIds, recipeEndDateBeforeChangeList);
    }
}
