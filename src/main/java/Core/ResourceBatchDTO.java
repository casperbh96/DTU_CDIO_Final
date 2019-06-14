package main.java.Core;

public class ResourceBatchDTO {

    private int resourceBatchId;
    private double resourceBatchAmount;
    private String supplierName;
    private boolean isLeftover;
    private int resourceId;

    public ResourceBatchDTO(int resourceBatchId, double resourceBatchAmount, String supplierName, boolean isLeftover, int resourceId) {
        this.resourceBatchId = resourceBatchId;
        this.resourceBatchAmount = resourceBatchAmount;
        this.supplierName = supplierName;
        this.isLeftover = isLeftover;
        this.resourceId = resourceId;
    }

    public int getResourceBatchId() {
        return resourceBatchId;
    }

    public void setResourceBatchId(int resourceBatchId) {
        this.resourceBatchId = resourceBatchId;
    }

    public double getResourceBatchAmount() {
        return resourceBatchAmount;
    }

    public void setResourceBatchAmount(double resourceBatchAmount) {
        this.resourceBatchAmount = resourceBatchAmount;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public boolean getIsLeftover() {
        return isLeftover;
    }

    public void setIsLeftover(boolean isLeftover) {
        this.isLeftover = isLeftover;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "ResourceBatchDTO{" +
                "resourceBatchId=" + resourceBatchId +
                ", resourceBatchAmount=" + resourceBatchAmount +
                ", supplierName='" + supplierName + '\'' +
                ", isLeftover=" + isLeftover +
                ", resourceId=" + resourceId +
                '}';
    }
}
