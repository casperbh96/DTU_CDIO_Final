package DataAccess.dao;

import DataAccess.dto.RecipeDTO;

import java.io.Serializable;
import java.util.List;

public interface I_DAL_Recipe extends Serializable {

    RecipeDTO createSingleRecipe( RecipeDTO singleRecipe );
    List<RecipeDTO> createMultipleRecipes( List<RecipeDTO> listOfRecipes );

    RecipeDTO readSingleRecipebyId(int RecipeId );
    List<RecipeDTO> readMultipleRecipesByList (List<Integer> listOfRecipeIds);
    List<RecipeDTO> readRecipebySearch(String keyword);
    List<RecipeDTO> readAllRecipes();

    RecipeDTO updateSingleRecipe(RecipeDTO Recipe);
    List<RecipeDTO> updateMultipleRecipes(List<RecipeDTO> listOfRecipes);

    RecipeDTO deleteSingleRecipe(RecipeDTO Recipe);
    List<RecipeDTO> deleteMultipleRecipes(List<RecipeDTO> listOfRecipes);


}
