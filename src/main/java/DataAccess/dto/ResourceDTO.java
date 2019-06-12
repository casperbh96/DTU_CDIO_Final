package DataAccess.dto;

public class ResourceDTO {

    private int resourceId;
    private String resourceName;
    private int reorder;            //TODO kan måske være boolean???
    //private double amount;          //TODO Hvad gør vi her????

    public ResourceDTO(int resourceId, String resourceName, int reorder) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.reorder = reorder;
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

    public int getReorder() {
        return reorder;
    }

    public void setReorder(int reorder) {
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
