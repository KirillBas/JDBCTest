package ru.basharin.util;

import org.junit.Assert;
import org.junit.Test;
import ru.basharin.model.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InfoWriterNioTest {
    @Test
    public void writeProductsToFile() throws Exception {
        Path path = Files.createTempFile("products", "csv");
        Storage storage = new Storage(StorageType.CHEMICAL, "DOP", 1);
        Rack rack1 = new Rack(1, storage);
        Rack rack2 = new Rack(2, storage);
        List<ProductCount> productCountList = new ArrayList<>();
        productCountList.add(new ProductCount(new Product("Banan", 100), rack1, 500));
        productCountList.add(new ProductCount(new Product("Tomat", 200), rack2, 600));
        productCountList.add(new ProductCount(new Product("Orange", 300), rack2, 700));
        productCountList.add(new ProductCount(new Product("Apple", 450), rack1, 6464));
        productCountList.add(new ProductCount(new Product("Garlic", 550), rack2, 4250));
        InfoWriterNio.writeProductsToFile(productCountList, path);
        List<String> strings = new ArrayList<>();
        strings.add("Name,Count,Coast,Rack");
        strings.add("Banan,500,100,1");
        strings.add("Tomat,600,200,2");
        strings.add("Orange,700,300,2");
        strings.add("Apple,6464,450,1");
        strings.add("Garlic,4250,550,2");
        Assert.assertEquals(Files.readAllLines(path), strings);
        Files.delete(path);
    }

    @Test
    public void writeProductsToFileWithCSVWriter() throws Exception {
        Path path = Files.createTempFile("products", ".csv");
        Storage storage = new Storage(StorageType.CHEMICAL, "DOP", 1);
        Rack rack1 = new Rack(1, storage);
        Rack rack2 = new Rack(2, storage);
        List<ProductCount> productCountList = new ArrayList<>();
        productCountList.add(new ProductCount(new Product("Banan", 100), rack1, 500));
        productCountList.add(new ProductCount(new Product("Tomat", 200), rack2, 600));
        productCountList.add(new ProductCount(new Product("Orange", 300), rack2, 700));
        productCountList.add(new ProductCount(new Product("Apple", 450), rack1, 6464));
        productCountList.add(new ProductCount(new Product("Garlic", 550), rack2, 4250));
        InfoWriterNio.writeProductsToFileWithCSVWriter(productCountList, path);
        List<String> strings = new ArrayList<>();
        strings.add("Name,Count,Coast,Rack");
        strings.add("Banan,500,100,1");
        strings.add("Tomat,600,200,2");
        strings.add("Orange,700,300,2");
        strings.add("Apple,6464,450,1");
        strings.add("Garlic,4250,550,2");
        Assert.assertEquals(Files.readAllLines(path), strings);
        Files.delete(path);
    }
}