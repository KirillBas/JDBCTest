package ru.basharin.util;

import ru.basharin.model.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.String.format;

public final class InfoWriter {

    private InfoWriter() {}

    public static void writeInFile(Rack rack) throws IOException {
        File file = new File(format("rack_№%s.csv", rack.getNumber()));
        if (!file.exists()) {
            file.createNewFile();
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for(ProductCount productCount : rack.getProducts()) {
                bufferedWriter
                        .append(wrap(productCount.getProduct().getName())).append(", ")
                        .append(wrap(Integer.toString(productCount.getProduct().getCoast()))).append(", ")
                        .append(wrap(Integer.toString(productCount.getCount())))
                        .append("\n");
            }
        }
    }

    public static void writeStorageInFile(Storage storage) throws IOException {
        File file = new File(format("storage_№%s.csv", storage.getNumber()));
        if(!file.exists()) {
            file.createNewFile();
        }
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter
                    .append(Integer.toString(storage.getNumber())).append(", ")
                    .append(String.valueOf(storage.getStorageType())).append(", ")
                    .append(storage.getAdress());
        }
    }

    private static String wrap(String string) {
        return "\"" + string + "\"";
    }
}
