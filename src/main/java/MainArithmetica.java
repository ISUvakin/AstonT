import java.util.Scanner;

public class MainArithmetica {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите первое число: ");
            double num1 = scanner.nextDouble();
            System.out.print("Введите оператор (+, -, *, /): ");
            char operator = scanner.next().charAt(0);
            System.out.print("Введите второе число: ");
            double num2 = scanner.nextDouble();
            
            double result;
                switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        System.out.println("Ошибка: деление на ноль");
                        return;
                    }
                    result = num1 / num2;
                    break;
                default:
                    System.out.println("Неподдерживаемая операция");
                    return;
            }
            
            System.out.printf("Результат: %.2f %c %.2f = %.2f\n", num1, operator, num2, result);
        }
    }
}