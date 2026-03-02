package task3;

import java.util.Scanner;

// Узел стека
class StackNode<T> {
    T data;
    StackNode<T> next;
    
    StackNode(T data) {
        this.data = data;
        this.next = null;
    }
}

// Реализация стека через связанный список
class Stack<T> {
    private StackNode<T> top;
    private int size;
    
    public Stack() {
        top = null;
        size = 0;
    }
    
    // Добавление в начало (push)
    public void push(T data) {
        StackNode<T> newNode = new StackNode<>(data);
        newNode.next = top;
        top = newNode;
        size++;
    }
    
    // Удаление первого элемента (pop)
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }
    
    // Просмотр верхнего элемента
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return top.data;
    }
    
    public boolean isEmpty() {
        return top == null;
    }
    
    public int size() {
        return size;
    }
}

public class ExpressionCalculator {
    
    // Проверка, является ли символ оператором
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    
    // Получение приоритета оператора
    private static int getPriority(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }
    
    // Преобразование инфиксной записи в постфиксную (обратная польская нотация)
    private static String infixToPostfix(String expression) {
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (Character.isDigit(c)) {
                // Собираем число
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    output.append(expression.charAt(i));
                    i++;
                }
                output.append(' ');
                i--; // возвращаемся на один символ назад
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop()).append(' ');
                }
                stack.pop(); // удаляем '('
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(c)) {
                    output.append(stack.pop()).append(' ');
                }
                stack.push(c);
            }
        }
        
        // Выталкиваем оставшиеся операторы
        while (!stack.isEmpty()) {
            output.append(stack.pop()).append(' ');
        }
        
        return output.toString().trim();
    }
    
    // Вычисление постфиксного выражения
    private static int evaluatePostfix(String postfix) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = postfix.split(" ");
        
        for (String token : tokens) {
            if (token.isEmpty()) continue;
            
            if (token.matches("\\d+")) { // если число
                stack.push(Integer.parseInt(token));
            } else { // если оператор
                int b = stack.pop();
                int a = stack.pop();
                
                switch (token.charAt(0)) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        if (b == 0) {
                            throw new ArithmeticException("Деление на ноль");
                        }
                        stack.push(a / b);
                        break;
                }
            }
        }
        
        return stack.pop();
    }
    
    // Проверка корректности скобок
    private static boolean checkParentheses(String expression) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.println("Введите арифметическое выражение (целые положительные числа и операции +, -, *, /):");
            String expression = scanner.nextLine().replaceAll("\\s+", ""); // удаляем пробелы
            
            // Проверка на пустое выражение
            if (expression.isEmpty()) {
                System.out.println("Выражение не может быть пустым");
                return;
            }
            
            // Проверка скобок
            if (!checkParentheses(expression)) {
                System.out.println("Ошибка: некорректная расстановка скобок");
                return;
            }
            
            // Преобразуем в постфиксную запись и вычисляем
            String postfix = infixToPostfix(expression);
            System.out.println("Постфиксная запись: " + postfix);
            
            int result = evaluatePostfix(postfix);
            System.out.println("Результат: " + result);
            
        } catch (ArithmeticException e) {
            System.out.println("Ошибка вычисления: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка в выражении: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}