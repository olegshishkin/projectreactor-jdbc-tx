package com.github.olegshishkin.projectreactor.application.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.Data;

@Data
@Entity
public class Item {

    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Shop shop;

    @Version
    private int version;

    public Item() {
    }

    public Item(String name, Shop shop) {
        this.name = name;
        this.shop = shop;
    }
}
