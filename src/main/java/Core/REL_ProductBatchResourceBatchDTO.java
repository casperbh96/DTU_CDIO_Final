package main.java.Core;

public class REL_ProductBatchResourceBatchDTO {
    private int resourceBatchId;
    private int productBatchId;
    private double netAmount;
    private double tara;

    public REL_ProductBatchResourceBatchDTO(int resourceBatchId, int productBatchId, double netAmount, double tara){
        this.resourceBatchId = resourceBatchId;
        this.productBatchId = productBatchId;
        this.netAmount = netAmount;
        this.tara = tara;
    }
    public REL_ProductBatchResourceBatchDTO(){}

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

    @Override
    public String toString() {
        return "REL_ProductBatchResourceBatchDTO{" +
                "resourceBatchId=" + resourceBatchId +
                ", productBatchId=" + productBatchId +
                ", netAmount=" + netAmount +
                ", tara=" + tara +
                '}';
    }
}
