package main.java.Core;

public class REST_DTO_Recipe_withRelResources {
    private RecipeDTO recipeDTO;
    private REL_RecipeResourceDTO[] relationsResource;

    public REST_DTO_Recipe_withRelResources(){

    }

    public RecipeDTO getRecipeDTO() {
        return recipeDTO;
    }

    public void setRecipeDTO(RecipeDTO recipeDTO) {

        this.recipeDTO = recipeDTO;
    }

    public REL_RecipeResourceDTO[] getRelationsResource() {
        return relationsResource;
    }

    public void setRelationsResource(REL_RecipeResourceDTO[] relationsResource) {
        this.relationsResource = relationsResource;
    }
}
