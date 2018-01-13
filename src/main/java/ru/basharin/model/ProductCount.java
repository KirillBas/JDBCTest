package ru.basharin.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by drbah on 07.07.2017.
 */
@Entity
@Table(name = "rack_product")
public class ProductCount implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(name = "product_name")
    private Product product;
    @Id
    @ManyToOne
    @JoinColumn(name = "rack_number")
    private Rack rack;
    @Column (name = "count", nullable = false)
    private int count;

    public ProductCount() {
    }

    public ProductCount(Product product, Rack rack, int count) {
        this.product = product;
        this.rack = rack;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCount that = (ProductCount) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(rack, that.rack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, rack);
    }

    @Override
    public String toString() {
        return "ProductCount{" +
                "product=" + product +
                ", count=" + count +
                '}';
    }
}
