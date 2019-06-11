package DataAccess.dto;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class RecipeDTO {

    private int recipeId;
    private Timestamp recipeEndDate;
    private String recipeName;
    private double productAmount;
    private int authorId;
    private List<ResourceDTO> resourceAmounts;

    //mulighed 2
//    List<ResourceDTO> resourceAmounts = new LinkedList<>();

    public RecipeDTO(int recipeId, Timestamp recipeEndDate, String recipeName, double productAmount, int authorId, List<ResourceDTO> resourceAmounts) {
        this.recipeId = recipeId;
        this.recipeEndDate = recipeEndDate;
        this.recipeName = recipeName;
        this.productAmount = productAmount;
        this.authorId = authorId;
        this.resourceAmounts = resourceAmounts;
    }

    //mulighed 2
//    public RecipeDTO(int recipeId, Timestamp recipeEndDate, String recipeName, double productAmount, int authorId) {
//        this.recipeId = recipeId;
//        this.recipeEndDate = recipeEndDate;
//        this.recipeName = recipeName;
//        this.productAmount = productAmount;
//        this.authorId = authorId;
//    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public Timestamp getRecipeEndDate() {
        return recipeEndDate;
    }

    public void setRecipeEndDate(Timestamp recipeEndDate) {
        this.recipeEndDate = recipeEndDate;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public double getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(double productAmount) {
        this.productAmount = productAmount;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }


    public List<ResourceDTO> getResourceAmounts() {
        return resourceAmounts;
    }

    public void setResourceAmounts(List<ResourceDTO> resourceAmounts) {
        this.resourceAmounts = resourceAmounts;
    }

    @Override
    public String toString() {
        return "RecipeDTO{" +
                "recipeId=" + recipeId +
                ", recipeEndDate=" + recipeEndDate +
                ", recipeName='" + recipeName + '\'' +
                ", productAmount=" + productAmount +
                ", authorId=" + authorId +
                ", resourceAmounts=" + resourceAmounts +
                '}';
    }
}
