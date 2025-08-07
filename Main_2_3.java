public class Main_2_3 {
    public static void main(String[] args) {
        Product[] productsArray = new Product[5];
        productsArray[0] = new Product("Samsung S25 Ultra", "07.02.2025", "Samsung Corp.", "Korea", 5699, true);
        productsArray[1] = new Product("Samsung Galaxy S24", "11.07.2024", "Samsung Corp.", "Korea", 4899, false);
        productsArray[2] = new Product("Xiaomi 14", "10.03.2025", "Xiaomi Corporation", "China", 3299, true);
        productsArray[3] = new Product("HONOR 200 Pro", "05.02.2025", "HONOR", "China", 4199, false);
        productsArray[4] = new Product("OnePlus 12", "20.01.2025", "OnePlus Technology", "China", 3999, true);
        
        System.out.println("\nИНФОРМАЦИЯ О ТОВАРАХ\n");
        for (Product product : productsArray) {
            product.displayInfo();
        }

        Park disneyland = new Park("Диснейленд");

        Park.Attraction[] attractions = {
            disneyland.new Attraction("Большая Гремучая Гора", "10:00-20:00", 24.9),
            disneyland.new Attraction("Пираты Карибского моря", "09:00-20:00", 19.9),
            disneyland.new Attraction("Индиана Джонс и Храм опасности", "10:00-23:00", 14.9)
        };
        disneyland.setAttractions(attractions);
        
        System.out.println("\nИНФОРМАЦИЯ О ПАРКЕ\n");
        disneyland.displayParkInfo();
    }
}

class Product {
    private String name;
    private String productionDate;
    private String manufacturer;
    private String country;
    private double price;
    private boolean isReserved;

    public Product(String name, String productionDate, String manufacturer, String country, double price, boolean isReserved) 
    {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.isReserved = isReserved;
    }

    public void displayInfo() {
        System.out.println("Товар: " + name);
        System.out.println("Дата производства: " + productionDate);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна: " + country);
        System.out.println("Цена: $" + price);
        System.out.println("Бронь: " + (isReserved ? "забронирован" : "свободен"));
        System.out.println("----------------------------");
    }
}

class Park {
    private String parkName;
    private Attraction[] attractions;

    // Внутренний класс аттракциона
    class Attraction {
        private String name;
        private String workingHours;
        private double price;

        public Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Аттракцион: " + name + "\nВремя работы: " + workingHours + "\nСтоимость: $" + price + "\n";
        }
    }

    // Конструктор только с именем парка
    public Park(String parkName) {
        this.parkName = parkName;
    }

    // Установка массива аттракционов
    public void setAttractions(Attraction[] attractions) {
        this.attractions = attractions;
    }

    public void displayParkInfo() {
        System.out.println("Парк: " + parkName);
        System.out.println("Аттракционы:");
        for (Attraction attraction : attractions) {
            System.out.println(attraction);
        }
    }
}