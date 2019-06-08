package DataAccess.dto;

public class ResourceDTO {
    int idRaavare;
    String name;
//      boolean active;
//      boolean reOrder;
//      boolean expired;
//      int amount;
//
//    public int getAmount() {
//        return amount;
//    }
//    public void setAmount(int ammount) {
//        this.amount = ammount;
//    }

    public int getIngredientId() {
        return idRaavare;
    }
    public void setIngredientId(int ingredientId) {
        this.idRaavare = ingredientId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
//
//    public boolean isActive() {
//        return active;
//    }
//    public void setActive(boolean active) {
//        this.active = active;
//    }
//
//    public boolean isReOrder() {
//        return reOrder;
//    }
//    public void setReOrder(boolean reOrder) {
//        this.reOrder = reOrder;
//    }
//
//    public boolean isExpired() {
//        return expired;
//    }
//    public void setExpired(boolean expired){
//        this.expired=expired;
//    }

    //TODO write toString
}
