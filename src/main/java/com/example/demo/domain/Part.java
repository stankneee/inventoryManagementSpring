package com.example.demo.domain;

import com.example.demo.validators.ValidDeletePart;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 *
 *
 */
@Entity
@ValidDeletePart
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="part_type",discriminatorType = DiscriminatorType.INTEGER)
@Table(name="Parts")
public class Part implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    @Min(value = 0, message = "Price value must be positive")
    double price;
    @Min(value = 0, message = "Inventory value must be positive")
    @Min(value = 0, message = "Inventory cannot be negative")
    @Max(value = 1000, message = "Inventory exceeds the maximum")
    int inv;

    @Column(name = "min_inventory") // replace "min_inventory" with the actual column name in the database
    @Min(value = 0, message = "Maximum inventory cannot be negative")
    @Max(value = 100, message = "Maximum inventory value must be less than or equal to 100")
    private int minInventory;

    @Column(name = "max_inventory") // replace "max_inventory" with the actual column name in the database
    @Min(value = 0, message = "Maximum inventory cannot be negative")
    @Max(value = 100, message = "Maximum inventory value must be less than or equal to 100")
    private int maxInventory;
    @ManyToMany
    @JoinTable(name="product_part", joinColumns = @JoinColumn(name="part_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    Set<Product> products= new HashSet<>();

    public Part() {
    }

    public Part(String name, double price, int inv, int minInventory, int maxInventory) {
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.minInventory = minInventory;
        this.maxInventory = maxInventory;
    }

    public Part(long id, String name, double price, int inv, int minInventory, int maxInventory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.minInventory = minInventory;
        this.maxInventory = maxInventory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        this.inv = inv;
        }


    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    public int getMinInventory() {
        return minInventory;
    }

    public int getMaxInventory() {
        return maxInventory;
    }

    public void setMinInventory(int minInventory) {
        if (minInventory < 0) {
            throw new IllegalArgumentException("Minimum inventory cannot be negative.");
        }

        if (minInventory > inv) {
            throw new IllegalArgumentException("Minimum inventory cannot be greater than the current inventory.");
        }

        this.minInventory = minInventory;
    }
    public void setMaxInventory(int maxInventory) {
        if (maxInventory < 0) {
            throw new IllegalArgumentException("Maximum inventory cannot be negative.");
        }

        if (maxInventory < inv) {
            throw new IllegalArgumentException("Maximum inventory cannot be less than the current inventory.");
        }

        this.maxInventory = maxInventory;
    }
    public String toString(){
        return this.name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        return id == part.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}