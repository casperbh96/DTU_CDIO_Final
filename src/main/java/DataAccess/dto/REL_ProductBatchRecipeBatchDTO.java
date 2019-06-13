package main.java.DataAccess.dto;

import java.sql.Date;

public class REL_ProductBatchRecipeBatchDTO {
    private int resourceId;
    private int recipeId;
    private Date recipeEndDate;
    private double resourceAmount;
    private double tolerance;

    REL_ProductBatchRecipeBatchDTO(int resourceId, int recipeId, Date recipeEndDate, double resourceAmount, double tolerance){
        this.resourceId = resourceId;
        this.recipeId = recipeId;
        this.recipeEndDate = recipeEndDate;
        this.resourceAmount = resourceAmount;
        this.tolerance = tolerance;
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
}
