package com.learn.api.models.InventoryModel;

import java.util.Set;

import com.learn.api.models.SaleModel.InvoiceModel;
import com.learn.api.models.SaleModel.SaleOrderModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_Item_Inventory")
public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemID;

    @Column(name = "Name")
    private String name;

    @Column(name = "SKU")
    private String sku;

    @Column(name = "Type")
    private String type;

    @Column(name = "SalesDescription")
    private String salesDescription;

    @Column(name = "SalesUnitPrice")
    private Double salesUnitPrice;

    @OneToMany(mappedBy = "item_id", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<InvoiceModel> invoiceModels;

    @OneToMany(mappedBy = "item_id", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<SaleOrderModel> saleOrderModels;

    // Getters and Setters
    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "ItemModel{" +
                "itemID=" + itemID +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", type='" + type + '\'' +
                ", salesDescription='" + salesDescription + '\'' +
                ", salesUnitPrice=" + salesUnitPrice +
                '}';
    }
}
