package ru.basharin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Created by drbah on 21.04.2017.
 */
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "name", nullable = false, updatable = false)
    private String name;
    @Column(name = "coast", nullable = false)
    private int coast;
    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public Product() {

    }

    public Product(String name, int coast) {
        this.name = name;
        this.coast = coast;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getName() {
        return name;
    }

    public int getCoast() {
        return coast;
    }

    public void setCoast(int coast) {
        this.coast = coast;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", coast=" + coast +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return coast == product.coast &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coast);
    }
}
