package task2;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SortUniqueNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Читаем количество чисел
            System.out.println("Введите количество чисел:");
            int n = scanner.nextInt();
            scanner.nextLine(); // очищаем буфер
            
            if (n <= 0) {
                System.out.println("Количество должно быть положительным");
                return;
            }
            
            // Используем TreeSet для автоматической сортировки и удаления дубликатов
            Set<Integer> uniqueNumbers = new TreeSet<>();
            
            // Читаем числа
            System.out.println("Введите " + n + " чисел (каждое на новой строке):");
            for (int i = 0; i < n; i++) {
                uniqueNumbers.add(scanner.nextInt());
            }
            
            // Выводим результат
            System.out.println("Вывод2 (отсортировано без повторений):");
            for (int num : uniqueNumbers) {
                System.out.print(num + " ");
            }
            System.out.println();
            
        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}