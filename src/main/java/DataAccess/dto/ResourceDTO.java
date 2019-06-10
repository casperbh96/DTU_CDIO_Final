package DataAccess.dto;

public class ResourceDTO {
    int resource_id;
    String resource_name;
    boolean reorder;

    public int getResourceId() {
        return resource_id;
    }
    public void setResourceId(int ingredientId) {
        this.resource_id = ingredientId;
    }

    public String getResourceName() {
        return resource_name;
    }
    public void setResourceName(String name) {
        this.resource_name = name;
    }

    public boolean getResourceReorder() {
        return reorder;
    }
    public void setResourceReorder(boolean reorder) {
        this.reorder = reorder;
    }

    //TODO write toString
}
