package DataAccess.dto;

public class ResourceDTO {

    private int resourceId;
    private String resourceName;
    private boolean reorder;            //TODO kan måske være boolean???

    //simulated variable to hold value for relational table in db
    private double amount;

    public ResourceDTO(int resourceId, String resourceName, boolean reorder) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.reorder = reorder;
    }

    public ResourceDTO(int resourceId, double amount) {
        this.resourceId = resourceId;
        this.amount = amount;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public boolean getReorder() {
        return reorder;
    }

    public void setReorder(boolean reorder) {
        this.reorder = reorder;
    }

    @Override
    public String toString() {
        return "ResourceDTO{" +
                "resourceId=" + resourceId +
                ", resourceName='" + resourceName + '\'' +
                ", reorder=" + reorder +
                '}';
    }
}
