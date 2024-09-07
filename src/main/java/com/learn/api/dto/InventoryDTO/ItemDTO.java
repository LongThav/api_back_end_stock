package com.learn.api.dto.InventoryDTO;

public class ItemDTO {
    private Long itemID;
    private String type;
    private String sku;
    private String name;
    private String salesDescription;
    private Double salesUnitPrice;

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalesDescription() {
        return salesDescription;
    }

    public void setSalesDescription(String salesDescription) {
        this.salesDescription = salesDescription;
    }

    public Double getSalesUnitPrice() {
        return salesUnitPrice;
    }

    public void setSalesUnitPrice(Double salesUnitPrice) {
        this.salesUnitPrice = salesUnitPrice;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemID=" + itemID +
                ", type='" + type + '\'' +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", salesDescription='" + salesDescription + '\'' +
                ", salesUnitPrice=" + salesUnitPrice +
                '}';
    }
}
