package DataAccess.dto;

import java.sql.Date;

public class ProductBatchDTO {

    private int productBatchId;
    private Date creationDate;
    private int productionStatus;
    private Date productionEndDate;
    private boolean inactive;

    public ProductBatchDTO(int productBatchId, Date creationDate, int productionStatus, Date productionEndDate, boolean inactive) {
        this.productBatchId = productBatchId;
        this.creationDate = creationDate;
        this.productionStatus = productionStatus;
        this.productionEndDate = productionEndDate;
        this.inactive = inactive;
    }

    public int getProductBatchId() {
        return productBatchId;
    }

    public void setProductBatchId(int productBatchId) {
        this.productBatchId = productBatchId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getProductionStatus() {
        return productionStatus;
    }

    public void setProductionStatus(int productionStatus) {
        this.productionStatus = productionStatus;
    }

    public Date getProductionEndDate() {
        return productionEndDate;
    }

    public void setProductionEndDate(Date productionEndDate) {
        this.productionEndDate = productionEndDate;
    }

    public boolean getInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    @Override
    public String toString(){
        return "ProductBatchDTO{" +
                "productBatchId=" + productBatchId +
                ", creationDate=" + creationDate +
                ", productionStatus=" + productionStatus +
                ", productionEndDate=" + productionEndDate +
                ", inactive=" + inactive +
                '}';
    }
    //mulighed 2
//    public ProductBatchDTO(int productBatchId, Timestamp creationDate, int orderStatus, int recipeId, Timestamp recipeEndDate, int productionLeaderId) {
//        this.productBatchId = productBatchId;
//        this.creationDate = creationDate;
//        this.orderStatus = orderStatus;
//        this.recipeId = recipeId;
//        this.recipeEndDate = recipeEndDate;
//        this.productionLeaderId = productionLeaderId;
//    }
    //    private int recipeId;
//    private Date recipeEndDate;
//    private int productionLeaderId;
//    private List<ResourceBatchDTO> resourceBatchAmounts;

    //mulighed 2
//    private List<ResourceBatchDTO> resourceBatchAmounts = new LinkedList<>();
}
