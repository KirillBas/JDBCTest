package ru.basharin.model;

import java.util.Objects;

public class Rectangle {
    private String side1;
    private String side2;
    private int side3;
    private int side4;

    public Rectangle(String side1, String side2, int side3, int side4) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.side4 = side4;
    }

    public String getSide1() {
        return side1;
    }

    public void setSide1(String side1) {
        this.side1 = side1;
    }

    public String getSide2() {
        return side2;
    }

    public void setSide2(String side2) {
        this.side2 = side2;
    }

    public int getSide3() {
        return side3;
    }

    public void setSide3(int side3) {
        this.side3 = side3;
    }

    public int getSide4() {
        return side4;
    }

    public void setSide4(int side4) {
        this.side4 = side4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return side3 == rectangle.side3 &&
                side4 == rectangle.side4 &&
                Objects.equals(side1, rectangle.side1) &&
                Objects.equals(side2, rectangle.side2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(side1, side2, side3, side4);
    }
}
