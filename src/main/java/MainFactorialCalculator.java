import java.util.Scanner;

public class MainFactorialCalculator {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите число для вычисления факториала: ");
            int n = scanner.nextInt();
            
            if (n < 0) {
                System.out.println("Факториал определен только для неотрицательных чисел");
            }
            else {
                long factorial = 1;
                for (int i = 1; i <= n; i++) {
                    factorial *= i;
                }
                System.out.println(n + "! = " + factorial);
            }
        }
    }
}