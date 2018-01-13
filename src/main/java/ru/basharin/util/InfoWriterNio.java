package ru.basharin.util;

import au.com.bytecode.opencsv.CSVWriter;
import ru.basharin.model.ProductCount;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class InfoWriterNio {
    public static void writeProductsToFile(List<ProductCount> products, Path path) throws Exception {
        try (Writer writer = new OutputStreamWriter(Files.newOutputStream(path))) {
            writer.append("Name,Count,Coast,Rack").append("\n");
            for (ProductCount productCount : products) {
                writer.append(productCount.getProduct().getName()).append(",")
                        .append(Integer.toString(productCount.getCount())).append(",")
                        .append(Integer.toString(productCount.getProduct().getCoast())).append(",")
                        .append(Integer.toString(productCount.getRack().getNumber())).append("\n");
            }
        }
    }

    // TODO: 25.11.2017 автоматически обертывает строки в "",
    public static void writeProductsToFileWithCSVWriter(List<ProductCount> products, Path path) throws Exception {
        List<String> test = new LinkedList<>();
        test.size();
        try (CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(Files.newOutputStream(path)),CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER)) {
            csvWriter.writeNext(new String[]{
                    "Name,Count,Coast,Rack"
            });
            for (ProductCount productCount : products) {
                csvWriter.writeNext(new String[]{
                        productCount.getProduct().getName(),
                        Integer.toString(productCount.getCount()),
                        Integer.toString(productCount.getProduct().getCoast()),
                        Integer.toString(productCount.getRack().getNumber())
                });
            }
        }
    }
}
