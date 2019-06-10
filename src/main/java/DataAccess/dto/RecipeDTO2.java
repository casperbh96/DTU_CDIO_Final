package DataAccess.dto;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class RecipeDTO2 {

    private int recipeId;
    private Timestamp endDate;
    private String name;
    private int authorId;
    private int quantity;
    List<ResourceDTO> ingredients = new LinkedList<ResourceDTO>();

    public RecipeDTO2 (int recipeId, String name, int authorId, int quantity) {
        this.recipeId = recipeId;
        this.name = name;
        this.authorId = authorId;
        this.quantity = quantity;
    }

    public int getRecipeId() {
        return recipeId;
    }
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public Timestamp getEndDate() {
        return endDate;
    }
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public List<ResourceDTO> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<ResourceDTO> ingredients) {
        this.ingredients = ingredients;
    }
    public void addIngredient(ResourceDTO ingredient){
        this.ingredients.add(ingredient);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAuthorId() {
        return authorId;
    }
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "RecipeDTO [recipeId= " + recipeId + ", recipeName= " + name + ", authorID= " + authorId + ", quantity= " + quantity + "]";
    }
}
