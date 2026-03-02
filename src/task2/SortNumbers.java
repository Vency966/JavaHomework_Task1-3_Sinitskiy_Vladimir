package task2;

import java.util.Scanner;
import java.util.Arrays;

public class SortNumbers {
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
            
            int[] numbers = new int[n];
            
            // Читаем числа
            System.out.println("Введите " + n + " чисел (каждое на новой строке):");
            for (int i = 0; i < n; i++) {
                numbers[i] = scanner.nextInt();
            }
            
            // Сортируем
            Arrays.sort(numbers);
            
            // Выводим результат
            System.out.println("Вывод1 (отсортировано по возрастанию):");
            for (int num : numbers) {
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