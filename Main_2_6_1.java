import java.util.*;

public class Main_2_6_1{
    static class Student {
        private String name;
        private String group;
        private int course;
        private List<Integer> grades;

        public Student(String name, String group, int course, List<Integer> grades) {
            this.name = name;
            this.group = group;
            this.course = course;
            this.grades = grades;
        }

        public String getName() { return name; }
        public String getGroup() { return group; }
        public int getCourse() { return course; }
        public List<Integer> getGrades() { return grades; }
        
        public void setCourse(int course) { this.course = course; }

        public double getAverageGrade() {
            return grades.stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0.0);
        }

        @Override
        public String toString() {
            return name + " (Группа: " + group + ", Курс: " + course + ", Средний балл: " + String.format("%.2f", getAverageGrade()) + ")";
        }
    }

    public static void main(String[] args) {
        Set<Student> students = new LinkedHashSet<>(Arrays.asList(
            new Student("Иван Иванов", "2081", 2, Arrays.asList(4, 5, 3, 4)),
            new Student("Егор Соколов", "3081", 3, Arrays.asList(5, 5, 5, 5)),
            new Student("Алексей Сидоров", "2081", 2, Arrays.asList(3, 2, 2, 3)),
            new Student("Дарья Васильева", "3081", 3, Arrays.asList(4, 4, 3, 5))
        ));

        System.out.println("Студенты:");
        students.forEach(System.out::println);

        removeUnderperformingStudents(students);
        System.out.println("\nУспевающие студенты:");
        students.forEach(System.out::println);

        promoteStudents(students);
        System.out.println("\nСтуденты, перешедшие на следующий курс:");
        students.forEach(System.out::println);

        System.out.println("\nСтуденты 3 курса:");
        printStudents(students, 3);
    }

    public static void removeUnderperformingStudents(Set<Student> students) {
        students.removeIf(student -> student.getAverageGrade() < 3.0);
    }

    public static void promoteStudents(Set<Student> students) {
        students.forEach(student -> {
            if (student.getAverageGrade() >= 3.0) {
                student.setCourse(student.getCourse() + 1);
            }
        });
    }

    public static void printStudents(Set<Student> students, int course) {
        students.stream()
            .filter(student -> student.getCourse() == course)
            .forEach(student -> System.out.println(student.getName()));
    }
}