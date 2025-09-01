package com.skylar.banking.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
//@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(
            name = "prenom",
            length = 1024,
            updatable = false,
            insertable = false
    )
    private LocalDateTime creationDate = LocalDateTime.now();


    private String firstname;
}
