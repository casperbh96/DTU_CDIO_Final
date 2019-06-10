package DataAccess.dto;

public class ResourceDTO {
    int resource_id;
    String resource_name;
    // reorder?

    public int getIngredientId() {
        return resource_id;
    }
    public void setIngredientId(int ingredientId) {
        this.resource_id = ingredientId;
    }

    public String getName() {
        return resource_name;
    }
    public void setName(String name) {
        this.resource_name = name;
    }

    //TODO write toString
}
