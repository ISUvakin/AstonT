public class Main {

    public static void main(String[] args) {
        // 1
        System.out.println("Пункт 1");
        printThreeWords();

        // 2
        System.out.println("\nПункт 2");
        checkSumSign();

        // 3
        System.out.println("\nПункт 3");
        printColor();

        // 4
        System.out.println("\nПункт 4");
        compareNumbers();

        // 5
        System.out.println("\nПункт 5");
        System.out.println("Сумма в диапазоне: " + isSumInRange(12, 11));

        // 6
        System.out.println("\nПункт 6");
        printNumberSign(3);

        // 7
        System.out.println("\nПункт 7");
        System.out.println("Число отрицательное: " + isNegative(-3));

        // 8
        System.out.println("\nПункт 8");
        printStringMultipleTimes("Гениально", 3);

        // 9
        System.out.println("\nПункт 9");
        System.out.println("Год високосный: " + isLeapYear(2025));

        // 10
        System.out.println("\nПункт 10");
        int[] array10 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        invertArray(array10);
        System.out.print("Инвертированный массив: ");
        printArray(array10);

        // 11
        System.out.println("\nПункт 11");
        int[] array100 = createSequenceArray(100);
        System.out.print("Массив: ");
        printArray(array100);

        // 12
        System.out.println("\nПункт 12");
        int[] array12 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        multiplyLessThanSix(array12);
        System.out.print("Массив: ");
        printArray(array12);

        // 13
        System.out.println("\nПункт 13");
        int size = 5;
        int[][] squareArray = new int[size][size];
        fillDiagonals(squareArray);
        System.out.println("Массив:");
        print2DArray(squareArray);

        // 14
        System.out.println("\nПункт 14");
        int[] initialArray = createInitialArray(3, 7);
        System.out.print("Массив: ");
        printArray(initialArray);
    }
    // 1
    public static void printThreeWords() {
        System.out.println("Orange\nBanana\nApple");}

    // 2
    public static void checkSumSign() {
        int a = 2;
        int b = -4;
        int sum = a + b;
        System.out.println(sum >= 0 ? "Сумма положительная" : "Сумма отрицательная");
    }

    // 3
    public static void printColor() {
        int value = 36;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // 4
    public static void compareNumbers() {
        int a = 7;
        int b = 8;
        System.out.println(a >= b ? "a >= b" : "a < b");
    }

    // 5
    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // 6
    public static void printNumberSign(int number) {
        System.out.println(number >= 0 ? "Число положительное" : "Число отрицательное");
    }

    // 7
    public static boolean isNegative(int number) {
        return number < 0;
    }

    // 8
    public static void printStringMultipleTimes(String str, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

    // 9
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // 10
    public static void invertArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] == 0 ? 1 : 0;
        }
    }

    // 11
    public static int[] createSequenceArray(int len) {
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    // 12
    public static void multiplyLessThanSix(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
    }

    // 13
    public static void fillDiagonals(int[][] array) {
        int size = array.length;
        for (int i = 0; i < size; i++) {
            array[i][i] = 1;                 
            array[i][size - 1 - i] = 1;      
        }
    }

    // 14
    public static int[] createInitialArray(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }
        return array;
    }
    // Дополнительные методы
    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void print2DArray(int[][] array) {
        for (int[] row : array) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}