package permogortseva.lambdas.persons.main;

import permogortseva.lambdas.persons.person.Person;

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
                new Person("Елена", 21),
                new Person("Пётр", 11)
        );

        List<String> uniqueNamesList = persons.stream()
                .map(Person::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(uniqueNamesList);

        String uniqueNames = uniqueNamesList.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(uniqueNames);

        Double underageAverageAge = persons.stream()
                .filter(p -> p.getAge() < 18)
                .collect(Collectors.averagingDouble(Person::getAge));

        System.out.println("Средний возраст людей младше 18 лет: " + underageAverageAge);

        Map<String, Double> namesWithAverageAge = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));

        namesWithAverageAge.forEach((name, averageAge) ->
                System.out.printf("имя: %s, средний возраст: %s%n", name, averageAge));

        List<String> names = persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println("Люди в возрасте от 20 до 45: " + names);
    }
}

