package main.java.Core;

public class REL_UserProductBatchDTO {

    private int userId;
    private int productBatchId;

    public REL_UserProductBatchDTO(int userId, int productBatchId) {
        this.userId = userId;
        this.productBatchId = productBatchId;
    }
    public REL_UserProductBatchDTO(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductBatchId() {
        return productBatchId;
    }

    public void setProductBatchId(int productBatchId) {
        this.productBatchId = productBatchId;
    }

    @Override
    public String toString() {
        return "REL_UserProductBatchDTO{" +
                "userId=" + userId +
                ", productBatchId=" + productBatchId +
                '}';
    }
}
