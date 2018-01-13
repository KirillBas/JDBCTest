package ru.basharin.util;

import org.junit.Assert;
import org.junit.Test;

public class MyLinkedListTest {
    @Test
    public void getNode1() throws Exception {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        String a = "One";
        String b = "Two";
        String c = "Tree";
        myLinkedList.addFirst(a);
        myLinkedList.addFirst(b);
        myLinkedList.addFirst(c);
//        Assert.assertEquals(c, myLinkedList.getIndex());
    }

    @Test
    public void addFirst() throws Exception {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        String a = "One";
        String b = "Two";
        String c = "Tree";
        myLinkedList.addFirst(a);
        myLinkedList.addFirst(b);
        myLinkedList.addFirst(c);
        Assert.assertEquals(0, myLinkedList.getIndex(c));
    }

    @Test
    public void addLast() throws Exception {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        String a = "One";
        String b = "Two";
        String c = "Tree";
        myLinkedList.addLast(a);
        myLinkedList.addLast(b);
        myLinkedList.addLast(c);
        Assert.assertEquals(2, myLinkedList.getIndex(c));
    }

    @Test
    public void deletedFIrst() throws Exception {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        String a = "One";
        String b = "Two";
        String c = "Tree";
        myLinkedList.addFirst(a);
        myLinkedList.addFirst(b);
        myLinkedList.addFirst(c);
        Assert.assertEquals(c, myLinkedList.removeFirst());


    }

    @Test
    public void getNode() throws Exception {
    }

    @Test
    public void getIndex() throws Exception {
    }

    @Test
    public void size() throws Exception {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        String a = "One";
        String b = "Two";
        String c = "Tree";
        myLinkedList.addFirst(a);
        myLinkedList.addFirst(b);
        myLinkedList.addFirst(c);
        Assert.assertEquals(3, myLinkedList.size );
    }
}