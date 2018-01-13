//package ru.basharin.api;
//
//import org.hibernate.Session;
//import ru.basharin.dao.implhibernate.ProductDAO;
//import ru.basharin.util.InfoWriter;
//import ru.basharin.dao.implhibernate.RackDAO;
//import ru.basharin.dao.implhibernate.StorageDAO;
//import ru.basharin.model.*;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Scanner;
//
///**
// * Created by drbah on 31.05.2017.
// */
//public class CmdApi {
//    private Session session;
//    private final Scanner scanner;
//    private final ProductDAO productDAO;
//    private final RackDAO rackDAO;
//    private final StorageDAO storageDAO;
//
//    public CmdApi(Scanner scanner, ProductDAO productDAO, RackDAO rackDAO, StorageDAO storageDAO, Session session) {
//        this.scanner = scanner;
//        this.productDAO = productDAO;
//        this.rackDAO = rackDAO;
//        this.storageDAO = storageDAO;
//        this.session = session;
//    }
//
//    public void printGreatingMenu() throws Exception {
//        String input;
//        while (true) {
//            System.out.println("Приветствуем Вас в cупер мега приложении <<Склад>>");
//            System.out.println("Для работы со складом нажмите 1");
//            System.out.println("Для работы со стелажами нажмите 2");
//            System.out.println("Для работы с товаром нажмите 3");
//            System.out.println("Для выхода нажмите #");
//            input = scanner.next();
//            switch (input) {
//                case "1":
//                    worckWithStorage();
//                    break;
//                case "2":
//                    workWithRacks();
//                    break;
//                case "3":
//                    workWithProduct();
//                    break;
//                case "#":
//                    return;
//            }
//        }
//
//    }
//
//    public void workWithProduct() throws Exception {
//        String input;
//        while (true) {
//            System.out.println("Нажмите 1 для создания продукта");
//            System.out.println("Нажмите 2 для обновления продукта");
//            System.out.println("Нажмите 3 для получения информации по продукту");
//            System.out.println("Нажмите 4 для удаления продукта");
//            System.out.println("Нажмите # для выхода");
//            input = scanner.next();
//            switch (input) {
//                case "1":
//                    createNewProduct();
//                    break;
//                case "2":
//                    updateProduct();
//                    break;
//                case "3":
//                    while (true) {
//                        System.out.println("Введите название продукта. (Для выхода нажмите #)");
//                        System.out.println("************************************************");
//                        String name;
//                        try {
//                            name = scanner.next();
////                            Должен автоматически подтягивать Rack
////                            System.out.println(readProduct(name, ));
//                            if (name.equals("#")) {
//                                return;
//                            }
//                        } catch (Exception e) {
//                            System.out.println("Неправильное имя.");
//                        }
//                    }
//                case "4":
//                    System.out.println("Введите имя продукта который хотите удалить");
//                    System.out.println("*******************************************");
//                    try {
//                        String name = scanner.next();
////                        Должен автоматически подтягивать Rack
////                        productDAO.delete(readProduct(name));
//                    } catch (Exception e) {
//                        System.out.println("Удалить не удалось.");
//                    }
//                    break;
//                case "#":
//                    return;
//            }
//        }
//    }
//
//    public void createNewProduct() throws Exception {
//        System.out.println("Создание нового продукта");
//        System.out.println("************************");
//        String name;
//        while (true) {
//            try {
//                System.out.println("Введите название продукта. (Нажмите # для выхода)");
//                name = scanner.next();
//                if (name.equals("#")) {
//                    return;
//                }
//                break;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        int coast;
//        String input;
//        while (true) {
//            try {
//                System.out.println("Введите стоимость продукта. (Нажмите # для выхода)");
//                input = scanner.next();
//                if (input.equals("#")) {
//                    return;
//                }
//                coast = Integer.parseInt(input);
//                break;
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//            }
//        }
//        Product product = new Product();
//        product.setName(name);
//        product.setCoast(coast);
//        productDAO.create(product);
//        System.out.println("Продукт: " + product.getName() + " создан");
//    }
//
//    public void updateProduct() throws Exception {
//        System.out.println("Выберите продукт которому необходимо изменить цену. (Для выхода нажмите #)");
//        System.out.println("**************************************************************************");
////        проинициализирова null что бы убрать ошибку на строке 154
//        Product product = null;
//        while (true) {
//            System.out.println("Введите название продукта.");
//            String name = scanner.next();
////            Должен автоматически подтягивать Rack
////            product = readProduct(name);
//            if (name.equals("#")) {
//                return;
//            }
//            if (product != null) {
//                System.out.println("Выбранный продукт:");
//                System.out.println(product);
//                break;
//            } else {
//                System.out.println("Продукт не найден.");
//            }
//        }
//
//        int coast;
//        String input = null;
//        while (true) {
//            try {
//                System.out.println("Введите новую цену продукта. (Для выхода нажмите #)");
//                input = scanner.next();
//                if (input.equals("#")) {
//                    return;
//                }
//                coast = Integer.parseInt(input);
//                break;
//            } catch (NumberFormatException e) {
//                System.out.println(input + " - не число.");
//            }
//        }
//        product.setCoast(coast);
//        productDAO.update(product);
////        System.out.println("Цена продукта: " + product.getName() + " изменена. (Для выхода нажмите #)");
//    }
//
////    public String readProduct(String message) {
////        System.out.println(message);
////        System.out.println("**************************************************");
////        String name = scanner.next();
////        if (name.equals("#")) {
////            return null;
////        }
////        return name;
////    }
//
//    public List<Product> readProduct(String name, Rack rack) throws Exception {
//        System.out.println("Информация о продукте.");
//        System.out.println("**********************");
//        return productDAO.readProduct(name, rack.getNumber());
//    }
//
//    public void workWithRacks() throws Exception {
//        String input;
//        while (true) {
//            System.out.println("Нажмите 1 для создания стеллажа");
//            System.out.println("Нажмите 2 для обновления стеллажа");
//            System.out.println("Нажмите 3 для получения информации по стеллажу");
//            System.out.println("Нажмите 4 для удаления стеллажа");
//            System.out.println("Нажмите 5 для добавления продукта на стеллаж.");
//            System.out.println("Нажмите # для выхода");
//            input = scanner.next();
//            switch (input) {
//                case "1":
//                    createNewRack();
//                    break;
//                case "2":
//                    updateRack();
//                    break;
//                case "3":
//                    while (true) {
//                        System.out.println("Введите номер стеллажа. (Для выхода нажмите #)");
//                        System.out.println("*********************************************");
//                        int number;
//                        try {
//                            String name;
//                            name = scanner.next();
//                            if (name.equals("#")) {
//                                return;
//                            }
//                            number = Integer.parseInt(name);
//                            System.out.println(readRack(number));
//                        } catch (Exception e) {
//                            System.out.println("Неправильное имя.");
//                        }
//                    }
//                case "4":
//                    System.out.println("Введите номер стеллажа для удаления. (Нажмите # для выхода.)");
//                    System.out.println("***********************************");
//                    try {
//                        int number;
//                        String name = scanner.next();
//                        if (name.equals("#")) {
//                            return;
//                        }
//                        number = Integer.parseInt(name);
//                        rackDAO.delete(rackDAO.readProduct(number));
//                    } catch (Exception e) {
//                        System.out.println("Стеллаж не удалось удалить.");
//                    }
//                    break;
//                case "5":
//                    addProductToRack();
//                    break;
//                case "#":
//                    return;
//            }
//        }
//    }
//
//    public void createNewRack() throws Exception {
//        System.out.println("Создание нового стеллажа");
//        System.out.println("************************");
//        int storageNumber;
//        String input;
//        while (true) {
//            System.out.println("Выберите склад в котором необходимо создать стеллаж. (Нажмите # для выхода)");
//            input = scanner.next();
//            if (input.equals("#")) {
//                return;
//            }
//            storageNumber = Integer.parseInt(input);
//            break;
//        }
//
//        int number;
//        while (true) {
//            System.out.println("Введите номер создаваемого стеллажа. (Нажмите # для выхода)");
//            input = scanner.next();
//            if (input.equals("#")) {
//                return;
//            }
//            number = Integer.parseInt(input);
//            break;
//        }
//        Rack rack = new Rack();
//        rack.setStorage(storageDAO.readProduct(storageNumber));
//        rack.setNumber(number);
//        rackDAO.create(rack);
//        System.out.println("Стеллаж " + rack.getNumber() + " создан на складе №" + rack.getStorage());
//        System.out.println("******************************");
//    }
//
//    public void updateRack() throws Exception {
//        System.out.println("Выберите стеллаж который необходимо изменить. (Для выхода нажмите #)");
//        System.out.println("*******************************************************************");
//        Rack rack;
//        int number;
//        while (true) {
//            System.out.println("Введите номер стеллажа.");
//            String name = scanner.next();
//            if (name.equals("#")) {
//                return;
//            }
//            number = Integer.parseInt(name);
//            rack = readRack(number);
//            if (rack != null) {
//                System.out.println("Выбранный стеллаж:");
//                System.out.println(rack);
//                break;
//            } else {
//                System.out.println("Стеллаж не найден.");
//            }
//        }
//
//        int storageNumber;
//        String input = null;
//        while (true) {
//            try {
//                System.out.println("Введите новую цену продукта. (Для выхода нажмите #)");
//                input = scanner.next();
//                if (input.equals("#")) {
//                    return;
//                }
//                storageNumber = Integer.parseInt(input);
//                break;
//            } catch (NumberFormatException e) {
//                System.out.println(input + " - не число.");
//            }
//        }
//        rack.setNumber(number);
//        rack.setStorage(storageDAO.readProduct(storageNumber));
//        rackDAO.update(rack);
//    }
//
//    public Rack readRack(int number) throws Exception {
//        System.out.println("Информация по стеллажу");
//        System.out.println("**********************");
//        try {
//            InfoWriter.writeInFile(rackDAO.readProduct(number));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return rackDAO.readProduct(number);
//    }
//
//    public void addProductToRack() throws Exception {
//        Product product;
//        Rack rack;
//        String name;
//        while (true) {
//            System.out.println("Выберите продукт который хотите добавить на стеллаж");
//            System.out.println("***************************************************");
//            name = scanner.next();
//            if (name.equals("#")) {
//                return;
//            }
//            product = readProduct(name, rack.getNumber());
//            if (product != null) {
//                System.out.println(product.toString());
//                break;
//            }
//            System.out.println("Продукт: " + name + " не найден. Введите другое название.");
//        }
//
//        int number;
//        while (true) {
//            System.out.println("Выберите стеллаж на который хотите добавить продукт");
//            System.out.println("***************************************************");
//            name = scanner.next();
//            if (name.equals("#")) {
//                return;
//            }
//            try {
//                number = Integer.parseInt(name);
//            } catch (NumberFormatException e) {
//                System.out.println("Введите число.");
//                continue;
//            }
//            rack = rackDAO.readProduct(number);
//            if (rack != null) {
//                System.out.println("Выбранный стеллаж " + rack.toString());
//                break;
//            }
//            System.out.println("Стеллаж с таким номером не найден.");
//        }
//
//        int count;
//        System.out.println("Введите количество добавляемого товара.");
//        System.out.println("***************************************");
//        while (true) {
//            name = scanner.next();
//            if (name.equals("#")) {
//                return;
//            }
//            try {
//                count = Integer.parseInt(name);
//                break;
//            } catch (NumberFormatException e) {
//                System.out.println("Введите число.");
//            }
//        }
//
//        rack.getProducts().add(new ProductCount(product, rack, count));
//        rackDAO.update(rack);
//        System.out.println("Продукт " + product.getName() + " добавлен на стеллаж № " + rack.getNumber());
//    }
//
//    public void worckWithStorage() throws Exception {
//        String input;
//        while (true) {
//            System.out.println("Нажмите 1 для создания склада");
//            System.out.println("Нажмите 2 для обновления склада");
//            System.out.println("Нажмите 3 для получения информации по складу");
//            System.out.println("Нажмите 4 для удаления склада");
//            System.out.println("Нажмите # для выхода");
//            input = scanner.next();
//            switch (input) {
//                case "1":
//                    createNewStorage();
//                    break;
//                case "2":
//                    updateStorage();
//                    break;
//                case "3":
//                    while (true) {
//                        System.out.println("Введите номер склада. (Для выхода нажмите #)");
//                        System.out.println("*******************************************");
//                        int number;
//                        try {
//                            input = scanner.next();
//                            if (input.equals("#")) {
//                                return;
//                            }
//                            number = Integer.parseInt(input);
//                            InfoWriter.writeStorageInFile(storageDAO.readProduct(number));
//                            System.out.println(storageDAO.readProduct(number));
//                        } catch (Exception e) {
//                            System.out.println("Неверный номер склада.");
//                        }
//                        break;
//                    }
//                    break;
//                case "4":
//                    System.out.println("Введите номер склада для удаления.");
//                    System.out.println("**********************************");
//                    try {
//                        int number;
//                        String name = scanner.next();
//                        if (name.equals("#")) {
//                            return;
//                        }
//                        number = Integer.parseInt(name);
//                        storageDAO.delete(number);
//                    } catch (Exception e) {
//                        System.out.println("Неудалось удалить склад!");
//                    }
//                    break;
//                case "#":
//                    return;
//            }
//        }
//    }
//
//    public void createNewStorage() throws Exception {
//        System.out.println("Создание нового склада");
//        System.out.println("**********************");
//        String input;
//        int number;
//        while (true) {
//            System.out.println("Введите номер нового склада. (Для выхода нажмите #)");
//            input = scanner.next();
//            if (input.equals("#")) {
//                return;
//            }
//            number = Integer.parseInt(input);
//            break;
//        }
//
//        String name;
//        while (true) {
//            System.out.println("Введите адресс склада. (Для выхода нажмите #)");
//            name = scanner.next();
//            if (name.equals("#")) {
//                return;
//            }
//            break;
//        }
//
//        StorageType storageType;
//        while (true) {
//            System.out.println("Введите тип склада. (Для выхода нажмите #)");
//            input = scanner.next();
//            if (input.equals("#")) {
//                return;
//            }
//            storageType = StorageType.valueOf(input);
//            break;
//        }
//        Storage storage = new Storage();
//        storage.setNumber(number);
//        storage.setAdress(name);
//        storage.setStorageType(storageType);
//        storageDAO.create(storage);
//        System.out.println("Создан новый склад №: " + storage.getNumber() + " по улице: " + storage.getAdress());
//    }
//
//    public void updateStorage() throws Exception {
//        System.out.println("Выберите склад для изменения. (Для выхода нажмите #)");
//        System.out.println("***************************************************");
//        Storage storage;
//        String input;
//        while (true) {
//            int number;
//            input = scanner.next();
//            if (input.equals("#")) {
//                return;
//            }
//            try {
//                number = Integer.parseInt(input);
//            } catch (Exception e) {
//                System.out.println("Введите число.");
//                continue;
//            }
//            storage = storageDAO.readProduct(number);
//            System.out.println(storage);
//            break;
//        }
//
//        int number;
//        while (true) {
//            System.out.println("Введите число.");
//            input = scanner.next();
//            if (input.equals("#")) {
//                return;
//            }
//            try {
//                number = Integer.parseInt(input);
//            } catch (Exception e) {
//                System.out.println("Введите число.");
//                continue;
//            }
//            break;
//        }
//
//        String adress;
//        while (true) {
//            System.out.println("Введите адрес.");
//            adress = scanner.next();
//            if (adress.equals("#")) {
//                return;
//            }
//            break;
//        }
//
//        StorageType storageType;
//        while (true) {
//            System.out.println("Введите тип склада.");
//            input = scanner.next();
//            if (input.equals("#")) {
//                return;
//            }
//            storageType = StorageType.valueOf(input);
//            break;
//        }
//
//        storage.setNumber(number);
//        storage.setAdress(adress);
//        storage.setStorageType(storageType);
//        storageDAO.update(storage);
//    }
//
//    public Storage readStorage(int number) throws Exception {
//        System.out.println("Информация о складе.");
//        System.out.println("*******************");
//        return storageDAO.readProduct(number);
//    }
//}
