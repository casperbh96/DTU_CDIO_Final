package DataAccess.dto;

public class ResourceBatchDTO2 {

    private int resourceBatchId;
    private String name;
    private int resourceId;
    private int amount;
    private boolean expired;
    private boolean active;

//    public int getReservedAmount() {
//        return reservedAmount;
//    }
//    public void setReservedAmount(int reservedAmount) {
//        this.reservedAmount = reservedAmount;
//    }
//
//    private int reservedAmount = 0;


    public ResourceBatchDTO2(int batchId, String name, int resourceId, int quantity, boolean expired, boolean active){
        this.resourceBatchId = batchId;
        this.name = name;
        this.resourceId = resourceId;
        this.amount = quantity;
        this.expired = expired;
        this.active = active;
    }


    public int getResourceBatchId() {
        return resourceBatchId;
    }
    public void setResourceBatchId(int resourceBatchId) {
        this.resourceBatchId = resourceBatchId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getResourceId() {
        return resourceId;
    }
    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isExpired() {
        return expired;
    }
    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "ResourceBatchDTO [resourceBatchId= " + resourceBatchId + ", materialName= " + name + ", resourceId= " + resourceId + ", amount= " + amount + ", isExpired= " + expired + ", isActive= " + active + "]";
    }
}
