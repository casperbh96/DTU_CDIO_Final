package DataAccess.dto;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class ProductBatchDTO {

    private int productBatchId;
    private Timestamp creationDate;     //TODO kan også være sql.Date
    private int orderStatus;            //TODO kan måske være boolean???
    private int recipeId;
    private Timestamp recipeEndDate;
    private int productionLeaderId;
    private List<ResourceBatchDTO> resourceBatchAmounts;            //TODO svarer dette til rel_table???

    //mulighed 2
//    private List<ResourceBatchDTO> resourceBatchAmounts = new LinkedList<>();


    public ProductBatchDTO(int productBatchId, Timestamp creationDate, int orderStatus, int recipeId, Timestamp recipeEndDate, int productionLeaderId, List<ResourceBatchDTO> resourceBatchAmounts) {
        this.productBatchId = productBatchId;
        this.creationDate = creationDate;
        this.orderStatus = orderStatus;
        this.recipeId = recipeId;
        this.recipeEndDate = recipeEndDate;
        this.productionLeaderId = productionLeaderId;
        this.resourceBatchAmounts = resourceBatchAmounts;
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

    public int getProductBatchId() {
        return productBatchId;
    }

    public void setProductBatchId(int productBatchId) {
        this.productBatchId = productBatchId;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public Timestamp getRecipeEndDate() {
        return recipeEndDate;
    }

    public void setRecipeEndDate(Timestamp recipeEndDate) {
        this.recipeEndDate = recipeEndDate;
    }

    public int getProductionLeaderId() {
        return productionLeaderId;
    }

    public void setProductionLeaderId(int productionLeaderId) {
        this.productionLeaderId = productionLeaderId;
    }

    public List<ResourceBatchDTO> getResourceBatchAmounts() {
        return resourceBatchAmounts;
    }

    public void setResourceBatchAmounts(List<ResourceBatchDTO> resourceBatchAmounts) {
        this.resourceBatchAmounts = resourceBatchAmounts;
    }

    @Override
    public String toString() {
        return "ProductBatchDTO{" +
                "productBatchId=" + productBatchId +
                ", creationDate=" + creationDate +
                ", orderStatus=" + orderStatus +
                ", recipeId=" + recipeId +
                ", recipeEndDate=" + recipeEndDate +
                ", productionLeaderId=" + productionLeaderId +
                ", resourceBatchAmounts=" + resourceBatchAmounts +
                '}';
    }
}
