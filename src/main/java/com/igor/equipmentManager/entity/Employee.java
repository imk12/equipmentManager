package com.igor.equipmentManager.entity;

import com.igor.equipmentManager.enums.Sector;
import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated
    private Sector sector;

    //Getter and Setter


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Long getId() {
        return id;
    }

    //Constructor


    protected Employee() {}

    public Employee(Sector sector, String email, String name) {
        this.sector = sector;
        this.email = email;
        this.name = name;
    }
}
