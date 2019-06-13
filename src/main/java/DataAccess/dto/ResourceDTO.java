package DataAccess.dto;

public class ResourceDTO {

    private int resourceId;
    private String resourceName;
    private boolean reorder;
    private boolean inactive;

    public ResourceDTO(int resourceId, String resourceName, boolean reorder, boolean inactive) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.reorder = reorder;
        this.inactive = inactive;
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

    public boolean getInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
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
