package org.example.collection;

import java.util.Objects;

public class MyLinkedList<T> implements MyList<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;


    // O(1)
    @Override
    public int size() {
        return size;
    }

    // O(1), время добавления элемента в конец связанного списка не зависит от длины списка
    @Override
    public boolean add(T e) {
        int startSize = size;
        Node<T> node = new Node<>(e);

        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;

        size++;

        return startSize < size;
    }

    // O(1), время добавления элемента в начало связанного списка не зависит от длины списка
    @Override
    public boolean addFirst(T e) {
        int startSize = size;

        if (head == null) {
            add(e);
        } else {
            Node<T> node = new Node<>(e);
            node.next = head;
            head = node;
        }

        size++;

        return startSize < size;
    }

    // O(n), время поиска элемента линейно зависит от длины списка
    private Node<T> getNode(int index) {
        int i = 0;
        Node<T> current = head;

        while (i < index) {
            current = current.next;
            i++;
        }

        return current;
    }

    // O(n), т.к. внутри есть вызов getNode
    @Override
    public T get(int index) {
        checkIndex(index);
        Node<T> node = getNode(index);

        return node.value;
    }

    // O(1) если index == 0, иначе O(n)
    @Override
    public boolean set(int index, T e) {
        checkIndex(index);

        if (index == 0) {
            return addFirst(e);
        }

        Node<T> node = new Node<>(e);
        Node<T> prevNode = getNode(index - 1);
        node.next = prevNode.next;
        prevNode.next = node;

        int startSize = size;
        size++;

        return startSize < size;
    }

    // O(1) если size == 0 || head.equals(o), иначе O(n)
    @Override
    public boolean remove(T o) {
        if (size == 0) {
            return false;
        }

        int startSize = size;

        if (head.equals(o)) {
            return removeFirst();
        }

        Node<T> current = head;

        while (current.next != null) {
            if (current.next.value.equals(o)) {
                current.next = current.next.next;
                size--;
                break;
            }

            current = current.next;
        }

        return startSize > size;
    }

    // O(n)
    @Override
    public boolean remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        int startSize = size;
        Node<T> prev = getNode(index - 1);
        prev.next = prev.next.next;
        size--;

        return startSize > size;
    }

    //O(1)
    public boolean removeFirst() {
        if (size == 0) {
            return false;
        }

        int startSize = size;

        if (size == 1) {
            clear();
        } else {
            head = head.next;
            size--;
        }

        return startSize > size;
    }

    //O(1)
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    //O(1)
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private static class Node<E> {
        public E value;
        public Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }
    }
}