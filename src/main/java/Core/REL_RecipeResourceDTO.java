package main.java.Core;

import java.sql.Date;

public class REL_RecipeResourceDTO {
    private int resourceId;
    private int recipeId;
    private Date recipeEndDate;
    private double resourceAmount;
    private double tolerance;

    public REL_RecipeResourceDTO(int resourceId, int recipeId, Date recipeEndDate, double resourceAmount, double tolerance){
        this.resourceId = resourceId;
        this.recipeId = recipeId;
        this.recipeEndDate = recipeEndDate;
        this.resourceAmount = resourceAmount;
        this.tolerance = tolerance;
    }
    public REL_RecipeResourceDTO(){

    }

    public int getResouceId() {
        return resourceId;
    }

    public void setResouceId(int resouceId) {
        this.resourceId = resouceId;
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

    public double getResourceAmount() {
        return resourceAmount;
    }

    public void setResourceAmount(double resourceAmount) {
        this.resourceAmount = resourceAmount;
    }

    public double getTolerance() {
        return tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    @Override
    public String toString() {
        return "REL_RecipeResourceDTO{" +
                "resourceId=" + resourceId +
                ", recipeId=" + recipeId +
                ", recipeEndDate=" + recipeEndDate +
                ", resourceAmount=" + resourceAmount +
                ", tolerance=" + tolerance +
                '}';
    }
}
