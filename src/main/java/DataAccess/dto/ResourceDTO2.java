package DataAccess.dto;

public class ResourceDTO2 {

    int resourceId;
    String name;
    boolean reOrder;

    public ResourceDTO2(int resourceId, String name, boolean reOrder) {
        this.resourceId = resourceId;
        this.name = name;
        this.reOrder = reOrder;
    }

    public int getResourceId() {
        return resourceId;
    }
    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isReOrder() {
        return reOrder;
    }
    public void setReOrder(boolean reOrder) {
        this.reOrder = reOrder;
    }

    @Override
    public String toString() {
        return "ResourceDTO [resourceId= " + resourceId + ", resourceName= " + name + ", reOrderStatus= " + reOrder + "]";
    }
}
