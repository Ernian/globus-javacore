package org.example.collection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMyLinkedList {

    @Test
    @DisplayName("Нулевая длина при создании списка")
    void test1() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("Добавление нескольких элементов")
    void test2() {
        int size = 10;
        MyLinkedList<Integer> list = new MyLinkedList<>();

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        assertEquals(size, list.size());
    }

    @Test
    @DisplayName("Получение элемента по индексу")
    void test3() {
        int size = 10;
        MyLinkedList<Integer> list = new MyLinkedList<>();

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        assertEquals(0, list.get(0));
        assertEquals(size / 2, list.get(size / 2));
        assertEquals(size - 1, list.get(size - 1));
    }

    @Test
    @DisplayName("Добавление первого элемента")
    void test4() {
        int size = 10;
        Integer firstElement = -1;
        MyLinkedList<Integer> list = new MyLinkedList<>();

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        list.addFirst(firstElement);

        assertEquals(size + 1, list.size());
        assertEquals(firstElement, list.get(0));
    }

    @Test
    @DisplayName("Ошибка при выходе за пределы списка")
    void test5() {
        int size = 10;
        MyLinkedList<Integer> list = new MyLinkedList<>();

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(size));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(size + 1));
    }

    @Test
    @DisplayName("Удаление первого элемента")
    void test6() {
        int size = 10;
        MyLinkedList<Integer> list = new MyLinkedList<>();

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        list.removeFirst();

        assertEquals(size - 1, list.size());
        assertNotEquals(0, list.get(0));
    }

    @Test
    @DisplayName("Удаление по индексу")
    void test7() {
        int size = 10;
        MyLinkedList<Integer> list = new MyLinkedList<>();
        int index = size / 2;

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        list.remove(index);

        assertEquals(size - 1, list.size());
        assertNotEquals(size / 2, list.get(index));
    }

    @Test
    @DisplayName("Удаление по значению элемента")
    void test8() {
        int size = 10;
        Integer removedElement = size / 2;
        MyLinkedList<Integer> list = new MyLinkedList<>();

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        list.remove(removedElement);

        assertEquals(size - 1, list.size());
        assertNotEquals(removedElement, list.get(size / 2));
    }

    @Test
    @DisplayName("Добавление элемента в середину списка")
    void test9() {
        int size = 10;
        int index = size / 2;
        Integer element = -1;
        MyLinkedList<Integer> list = new MyLinkedList<>();

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        Integer previous = list.get(index);
        list.set(index, element);

        assertEquals(size + 1, list.size());
        assertEquals(element, list.get(index));
        assertEquals(previous, list.get(index + 1));
    }

    @Test
    @DisplayName("Очистка списка")
    void test10() {
        int size = 10;
        MyLinkedList<Integer> list = new MyLinkedList<>();

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        list.clear();

        assertEquals(0, list.size());
    }
}