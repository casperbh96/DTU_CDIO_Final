package main.java.DataAccess.dto;

public class REL_RecipeResourceDTO {
    private int resourceBatchId;
    private int productBatchId;
    private double netAmount;
    private double tara;

    REL_RecipeResourceDTO(int resourceBatchId, int productBatchId, double netAmount, double tara){
        this.resourceBatchId = resourceBatchId;
        this.productBatchId = productBatchId;
        this.netAmount = netAmount;
        this.tara = tara;
    }

    public int getResourceBatchId() {
        return resourceBatchId;
    }

    public void setResourceBatchId(int resourceBatchId) {
        this.resourceBatchId = resourceBatchId;
    }

    public int getProductBatchId() {
        return productBatchId;
    }

    public void setProductBatchId(int productBatchId) {
        this.productBatchId = productBatchId;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public double getTara() {
        return tara;
    }

    public void setTara(double tara) {
        this.tara = tara;
    }
}
