package com.learn.api.models.SaleModel;

import java.time.LocalDate;

import com.learn.api.models.InventoryModel.ItemModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_package")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id")
    private Integer packageId;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(name = "sale_order_id", nullable = false)
    private Integer saleOrderId;

    @Column(name = "package_slip", nullable = false, length = 255)
    private String packageSlip;

    @Column(name = "package_date", nullable = false)
    private LocalDate packageDate;

    @Column(name = "order_number", nullable = false, unique = true)
    private Integer orderNumber;

    @Column(name = "packed_number", nullable = false, unique = true)
    private Integer packedNumber;

    @Column(name = "quantity_to_pay", nullable = false)
    private Integer quantityToPay;

    @Column(name = "internal_notes", nullable = true, length = 400)
    private String internalNotes;

    @Column(name = "item_id", nullable = false)
    private Integer itemId;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "sale_order_id", insertable = false, updatable = false)
    private SaleOrderModel saleOrder;

    @ManyToOne
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private ItemModel itemInventory;
}
