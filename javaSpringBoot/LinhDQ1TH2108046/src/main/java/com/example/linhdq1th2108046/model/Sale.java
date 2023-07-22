package com.example.linhdq1th2108046.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@Entity
@Data
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer saleNo;

    private Integer saleManId;

   // private Integer productId;

    private Date dateOfSale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
