package task1;

import java.util.Random;

class Node {
    int data;
    Node next;
    
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;
    int size;
    
    public LinkedList() {
        head = null;
        size = 0;
    }
    
    // Добавление элемента в конец списка
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    // Удаление первого элемента
    public void removeFirst() {
        if (head != null) {
            head = head.next;
            size--;
        }
    }
    
    // Удаление элемента по индексу
    public void removeAt(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        
        if (index == 0) {
            removeFirst();
            return;
        }
        
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        
        current.next = current.next.next;
        size--;
    }
    
    // Удаление всех отрицательных элементов
    public void removeNegatives() {
        Node current = head;
        Node prev = null;
        int index = 0;
        
        while (current != null) {
            if (current.data < 0) {
                if (prev == null) {
                    // Удаляем голову
                    head = current.next;
                    current = head;
                } else {
                    prev.next = current.next;
                    current = current.next;
                }
                size--;
            } else {
                prev = current;
                current = current.next;
            }
        }
    }
    
    // Проверка наличия отрицательных элементов
    public boolean hasNegatives() {
        Node current = head;
        while (current != null) {
            if (current.data < 0) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    // Получение элемента по индексу
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    // Вывод списка
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    
    public int size() {
        return size;
    }
}

public class LinkedListTask {
    public static void main(String[] args) {
        // Создаем список из 23 случайных чисел
        LinkedList list = new LinkedList();
        Random random = new Random();
        
        System.out.println("Исходный список (23 случайных числа от -100 до 100):");
        for (int i = 0; i < 23; i++) {
            int num = random.nextInt(201) - 100; // от -100 до 100
            list.add(num);
            System.out.print(num + " ");
        }
        System.out.println("\n");
        
        // Проверяем наличие отрицательных элементов
        if (list.hasNegatives()) {
            System.out.println("Удаляем все отрицательные элементы:");
            list.removeNegatives();
        } else {
            System.out.println("Отрицательных элементов нет. Удаляем 10-й элемент:");
            if (list.size() >= 10) {
                list.removeAt(9); // индекс 9 = 10-й элемент
            }
        }
        
        // Выводим результат
        System.out.println("Результат:");
        list.printList();
        System.out.println("Размер списка: " + list.size());
    }
}