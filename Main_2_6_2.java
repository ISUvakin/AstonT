import java.util.*;

public class Main_2_6_2 {
    private final Map<String, List<String>> directory = new HashMap<>();
    
    public void add(String surname, String phoneNumber) {
        directory.computeIfAbsent(surname, k -> new ArrayList<>());
        directory.get(surname).add(phoneNumber);
    }
    
    public List<String> get(String surname) {
        return directory.getOrDefault(surname, Collections.emptyList());
    }
    
    public void printDirectory() {
        System.out.println("\nТелефонный справочник:");
        for (Map.Entry<String, List<String>> entry : directory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Main_2_6_2 directory = new Main_2_6_2();
        
        directory.add("Иванов", "123456");
        directory.add("Петров", "789012");
        directory.add("Сидорова", "345778");
        directory.add("Козлова", "901234");
        directory.add("Соколов", "567890");
        
        directory.printDirectory();
        
        System.out.println("\nПоиск по фамилии:");
        System.out.println("Иванов: " + directory.get("Иванов"));
        System.out.println("Петров: " + directory.get("Петров"));
        System.out.println("Кузнецова: " + directory.get("Кузнецова"));
    }
}