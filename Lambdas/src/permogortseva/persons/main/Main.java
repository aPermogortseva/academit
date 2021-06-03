package permogortseva.persons.main;

import permogortseva.persons.person.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Кирилл", 23),
                new Person("Наташа", 24),
                new Person("Пётр", 90),
                new Person("Максим", 13),
                new Person("Наташа", 60),
                new Person("Оксана", 56),
                new Person("Елена", 34),
                new Person("Пётр", 11)
        );

        String uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));

        System.out.println("Имена: " + uniqueNames);

        Double underageAverageAge = persons.stream()
                .filter(p -> p.getAge() < 18)
                .collect(Collectors.averagingInt(Person::getAge));

        System.out.println("Средний возраст людей младше 18 лет: " + underageAverageAge);

        Map<String, Double> namesWithAverageAge = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        namesWithAverageAge.forEach((name, averageAge) ->
                System.out.printf("имя: %s, средний возраст: %s%n", name, averageAge));

        List<String> names = persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() < 40)
                .map(Person::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("Люди в возрасте от 20 до 45: " + names);
    }
}

