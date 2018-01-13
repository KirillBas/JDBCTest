package ru.basharin.util;

import java.io.Serializable;
import java.util.*;

public class MyLinkedList<E> implements List<E>, Serializable {
    int size = 0;
    private Node<E> first;
    private Node<E> last;

    MyLinkedList() {
    }

    public void addFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    public void addLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    void addBefore(E e, Node<E> next) {
        final Node<E> previous = next.prev;
        final Node<E> newNode = new Node<>(previous, e, next);
        next.prev = newNode;
        if (previous == null) {
            first = newNode;
        } else {
            previous.next = newNode;
        }
        size++;
    }

    private E deletedFIrst(Node<E> f) {
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return element;
    }

    private E unlink(Node<E> x) {
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev==null) {
            first=next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next==null) {
            last=prev;
        } else {
            next.prev= prev;
            x.next=null;
        }
        x.item=null;
        size--;
        return element;
    }

    public E removeFirst() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return deletedFIrst(f);
    }

    Node<E> getNode(int index) {
        if (index < (size / 2)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i < index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    public int getIndex(Object o) throws Exception {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        private Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    // TODO: 05.12.2017 написать indexOf
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o==null) {
            for (Node<E> x=first; x!=null; x=x.next) {
                if (x.item==null) {
                   unlink(x);
                   return true;
                }
            }
        } else {
            for (Node<E> x = first; x!=null; x=x.next) {
                if (x.item.equals(o)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    private boolean elementIndex(int index) {
        return index > 0 && index <= size;
    }

    @Override
    public E get(int index) {
        if (!elementIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
        return getNode(index).item;
    }

    @Override
    public E set(int index, E element) {
        Node<E> x = getNode(index);
        E oldElem = x.item;
        x.item = element;
        return oldElem;
    }

    @Override
    public void add(int index, E element) {
        if (index <= 0 && index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == size) {
            addLast(element);
        } else {
            addBefore(element, getNode(index));
        }
    }

    @Override
    public E remove(int index) {
        return null;
    }

    // стр 89
    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
