package ru.basharin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by drbah on 21.04.2017.
 */
@Entity
@Table(name = "rack")
public class Rack {
    @Id
    @Column(name = "number", nullable = false, updatable = false)
    private int number;

    @ManyToOne
    @JoinColumn(name = "storage_number", nullable = false, updatable = false)
    private Storage storage;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "rack")
    private List<ProductCount> products = new ArrayList<>();

    public Rack() {
    }

    public Rack(int number, Storage storage) {
        this.number = number;
        this.storage = storage;
    }

    public int getNumber() {
        return number;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public List<ProductCount> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCount> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Rack{"+ ", number=" + number +
                ", storage=" + storage +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rack rack = (Rack) o;
        return number == rack.number &&
                Objects.equals(storage, rack.storage) &&
                Objects.equals(products, rack.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, storage, products);
    }
}
