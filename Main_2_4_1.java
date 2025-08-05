import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Animal {
    private String name;
    private static int count = 0;
    
    public Animal(String name) {
        this.name = name;
        count++;
    }
    public void run(int distance) {
        System.out.println(name + " пробежал(а) " + distance + " м.");
    }
    public void swim(int distance) {
        System.out.println(name + " проплыл(а) " + distance + " м.");
    }
    public String getName() {
        return name;
    }
    public static int getCount() {
        return count;
    }
}

class Dog extends Animal {
    private static int count = 0;
    private static List<Dog> allDogs = new ArrayList<>();
    
    private final int MAX_RUN = 500;
    private final int MAX_SWIM = 10;
    
    public Dog(String name) {
        super(name);
        count++;
        allDogs.add(this);
    }
    
    @Override
    public void run(int distance) {
        if (distance <= MAX_RUN) {
            super.run(distance);
        } else {
            System.out.println(getName() + " не может пробежать " + distance + " м. Максимум - " + MAX_RUN + " м.");
        }
    }
    
    @Override
    public void swim(int distance) {
        if (distance <= MAX_SWIM) {
            super.swim(distance);
        } else {
            System.out.println(getName() + " не может проплыть " + distance + " м. Максимум - " + MAX_SWIM + " м.");
        }
    }
    
    public static int getCount() {
        return count;
    }
    public static List<Dog> getAllDogs() {
        return allDogs;
    }
}

class Cat extends Animal {
    private static int count = 0;
    private static List<Cat> allCats = new ArrayList<>();
    private static final Random random = new Random();
    
    private final int MAX_RUN = 200;
    private boolean satiety;
    
    public Cat(String name) {
        super(name);
        satiety = false;
        count++;
        allCats.add(this);
    }
    
    @Override
    public void run(int distance) {
        if (distance <= MAX_RUN) {
            super.run(distance);
        } else {
            System.out.println(getName() + " не может пробежать " + distance + " м. Максимум - " + MAX_RUN + " м.");
        }
    }
    
    @Override
    public void swim(int distance) {
        System.out.println(getName() + " не умеет плавать.");
    }
    
    public void eat(Bowl bowl) {
        int neededFood = 15 + random.nextInt(6);
        if (bowl.getFoodAmount() >= neededFood) {
            bowl.decreaseFood(neededFood);
            satiety = true;
            System.out.println(getName() + " сыт(а)");
        } else {
            System.out.println(getName() + " хочет есть");
        }
    }
    
    public boolean isSatiety() {
        return satiety;
    }
    
    public static int getCount() {
        return count;
    }
    
    public static List<Cat> getAllCats() {
        return allCats;
    }
    
    public static List<Cat> getHungryCats() {
        List<Cat> hungryCats = new ArrayList<>();
        for (Cat cat : allCats) {
            if (!cat.isSatiety()) {
                hungryCats.add(cat);
            }
        }
        return hungryCats;
    }
}

class Bowl {
    private int foodAmount;
    public static final int MAX_CAPACITY = 50;
    
    public void addFood(int amount) {
        
        int newAmount = foodAmount + amount;
        if (newAmount > MAX_CAPACITY) {
            int added = MAX_CAPACITY - foodAmount;
            foodAmount = MAX_CAPACITY;
            System.out.println("\nЕда добавлена. Миска полная");
        } else {
            foodAmount = newAmount;
            System.out.println("\nДобавлено " + amount + " еды. Всего еды: " + foodAmount);
        }
    }
    
    public void decreaseFood(int amount) {
        if (amount <= foodAmount) {
            foodAmount -= amount;
        }
    }
    
    public int getFoodAmount() {
        return foodAmount;
    }
}

public class Main_2_4_1 {
    public static void main(String[] args) {
        Animal dog1 = new Dog("Шарик");
        Animal dog2 = new Dog("Дружок");
        Animal cat1 = new Cat("Кузя");
        Animal cat2 = new Cat("Рыжик");
        
        dog1.run(20);
        dog1.swim(5);
        dog2.run(60);
        dog2.swim(20);
        cat1.run(200);
        cat1.swim(1);
        cat2.run(1);
        cat2.swim(200);
        
        Cat[] newCats = {
            new Cat("Вася"),
            new Cat("Мурзик"),
            new Cat("Пушок"),
        };

        System.out.println("\nВсего котов: " + Cat.getCount());
        System.out.println("Всего собак: " + Dog.getCount());
        System.out.println("Всего животных: " + Animal.getCount());
        
        Bowl bowl = new Bowl();
        System.out.println("\nМаксимальная вместимость миски: " + Bowl.MAX_CAPACITY + " еды.");
        bowl.addFood(Bowl.MAX_CAPACITY);
        
        List<Cat> allCats = Cat.getAllCats();
        
        for (Cat cat : allCats) {
            cat.eat(bowl);
        }
        
        
        List<Cat> hungryCats = Cat.getHungryCats();
        while (!hungryCats.isEmpty()) {
            
            int neededFood = hungryCats.size() * 20;
            System.out.println("В миску добавляется " + neededFood + " еды.");
            bowl.addFood(neededFood);
            
            for (Cat cat : hungryCats) {
                cat.eat(bowl);
            }
            hungryCats = Cat.getHungryCats();
        }
        

        System.out.println("\nИтого:");
        for (Cat cat : allCats) {
            System.out.println(cat.getName() + ": " + (cat.isSatiety() ? "сыт(а)" : "голоден(на)"));
        }
        
        System.out.println("Остаток в миске: " + bowl.getFoodAmount() + ".");
    }
}