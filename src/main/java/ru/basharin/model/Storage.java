package ru.basharin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by drbah on 21.04.2017.
 */
@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @Column(name = "number", nullable = false, updatable = false)
    private int number;
    @Enumerated(EnumType.STRING)
    @Column(name = "starage_type", nullable = false, updatable = false)
    private StorageType storageType;
    @Column(name = "adress", nullable = false, updatable = false)
    private String adress;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "storage")
    private List<Rack> racks = new ArrayList<>();

    public Storage() {
    }

    public Storage(StorageType storageType, String adress, int number) {
        this.storageType = storageType;
        this.adress = adress;
        this.number = number;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    public String getAdress() {
        return adress;
    }

    public int getNumber() {
        return number;
    }

    public void setStorageType(StorageType storageType) {
        this.storageType = storageType;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<Rack> getRacks() {
        return racks;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "number=" + number +
                ", storageType=" + storageType +
                ", adress='" + adress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return number == storage.number &&
                storageType == storage.storageType &&
                Objects.equals(adress, storage.adress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, storageType, adress);
    }
}
