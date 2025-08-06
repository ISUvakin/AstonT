public class Main_2_5_4{

    public static void main(String[] args) {
        generateArrayIndexException();
    }

    private static void generateArrayIndexException() {
        System.out.println("Генерация ArrayIndexOutOfBoundsException:");
        int[] arr = new int[5];
        
        try {
            for (int i = 0; i <= arr.length; i++) {
                arr[i] = i;
                System.out.println("Присвоено значение индексу " + i);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано исключение: " + e);
            System.out.println("Сообщение: " + e.getMessage());
        }
    }
}