package DataAccess.dto;

public class ResourceBatchDTO {

    private int resourceBatchId;
    private String resourceBatchName;
    private double resourceBatchAmount;
    private String supplierName;
    private int isLeftover;     //TODO kan måske være boolean???
    private int resourceId;

    public ResourceBatchDTO(int resourceBatchId, String resourceBatchName, double resourceBatchAmount, String supplierName, int isLeftover, int resourceId) {
        this.resourceBatchId = resourceBatchId;
        this.resourceBatchName = resourceBatchName;
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

    public String getResourceBatchName() {
        return resourceBatchName;
    }

    public void setResourceBatchName(String resourceBatchName) {
        this.resourceBatchName = resourceBatchName;
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

    public int getIsLeftover() {
        return isLeftover;
    }

    public void setIsLeftover(int isLeftover) {
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
                ", resourceBatchName='" + resourceBatchName + '\'' +
                ", resourceBatchAmount=" + resourceBatchAmount +
                ", supplierName='" + supplierName + '\'' +
                ", isLeftover=" + isLeftover +
                ", resourceId=" + resourceId +
                '}';
    }
}
