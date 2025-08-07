import java.util.InputMismatchException;
import java.util.Scanner;

public class MainTriangleArea {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите длины сторон треугольника:");
            
            double a = readValidDouble(scanner, "Сторона a: ");
            double b = readValidDouble(scanner, "Сторона b: ");
            double c = readValidDouble(scanner, "Сторона c: ");
            
            if (a <= 0 || b <= 0 || c <= 0) {
                System.out.println("Длины сторон должны быть положительными числами");
            } else if (a + b <= c || a + c <= b || b + c <= a) {
                System.out.println("Треугольник с такими сторонами не существует");
            } else {
                double p = (a + b + c) / 2;
                double area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
                System.out.printf("Площадь треугольника: %.2f\n", area);
            }
        }
    }
    private static double readValidDouble(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
                }
            catch (InputMismatchException e) {
                System.out.println("Ошибка: нужно ввести число");
                scanner.nextLine();
                }
            }
        }
    }