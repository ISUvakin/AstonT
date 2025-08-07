import java.util.Scanner;

public class MainComparator {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите первое число: ");
            int num1 = scanner.nextInt();
            System.out.print("Введите второе число: ");
            int num2 = scanner.nextInt();
            
            if (num1 > num2) {
                System.out.printf("%d > %d\n", num1, num2);
            } else if (num1 < num2) {
                System.out.printf("%d < %d\n", num1, num2);
            } else {
                System.out.printf("%d = %d\n", num1, num2);
            }
        }
    }
}