package DataAccess.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class RecipeDTO {

    private int recipeId;
    private Date recipeEndDate;
    private String recipeName;
    private double productAmount;

    public RecipeDTO(int recipeId, Date recipeEndDate, String recipeName, double productAmount) {
        this.recipeId = recipeId;
        this.recipeEndDate = recipeEndDate;
        this.recipeName = recipeName;
        this.productAmount = productAmount;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public Date getRecipeEndDate() {
        return recipeEndDate;
    }

    public void setRecipeEndDate(Date recipeEndDate) {
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

    @Override
    public String toString() {
        return "RecipeDTO{" +
                "recipeId=" + recipeId +
                ", recipeEndDate=" + recipeEndDate +
                ", recipeName='" + recipeName + '\'' +
                ", productAmount=" + productAmount +
                '}';
    }
    //mulighed 2
//    List<ResourceDTO> resourceAmounts = new LinkedList<>();

    //mulighed 2
//    public RecipeDTO(int recipeId, Timestamp recipeEndDate, String recipeName, double productAmount, int authorId) {
//        this.recipeId = recipeId;
//        this.recipeEndDate = recipeEndDate;
//        this.recipeName = recipeName;
//        this.productAmount = productAmount;
//        this.authorId = authorId;
//    }
}
