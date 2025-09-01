package com.skylar.banking.models;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cat")
    private Category category;
}
