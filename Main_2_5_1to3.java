import java.util.Arrays;

public class Main_2_5_1to3 {
    static class MyArraySizeException extends Exception {
        public MyArraySizeException() {
            super("Неверный размер массива! Требуется 4x4.");
        }
    }

    static class MyArrayDataException extends Exception {
        public MyArrayDataException(int row, int col) {
            super(String.format("Неверные данные в ячейке [%d][%d]", row+1, col+1));
        }
    }

    public static int sumArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4 || Arrays.stream(array).anyMatch(row -> row.length != 4)) {
            throw new MyArraySizeException();
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }

    private static void processArray(String[][] array, String description) {
        System.out.println("\nОбработка: " + description);
        try {
            int result = sumArray(array);
            System.out.println("Сумма элементов: " + result);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка данных: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String[][] Array1 = {
            {"1", "2", "3", "4"},
            {"5", "6", "7", "8"},
            {"9", "10", "11", "12"},
            {"13", "14", "15", "16"}
        };

        String[][] Array2 = {
            {"1", "2", "3"},
            {"4", "5", "6"},
            {"7", "8", "9"},
            {"10", "11", "12"}
        };
        String[][] Array3 = {
            {"1", "2", "3", "4"},
            {"5", "6", "X", "8"},
            {"9", "10", "11", "12"},
            {"13", "14", "15", "16"}
        };

        processArray(Array1, "Массив 1");
        processArray(Array2, "Массив 2");
        processArray(Array3, "Массив 3");
    }
}