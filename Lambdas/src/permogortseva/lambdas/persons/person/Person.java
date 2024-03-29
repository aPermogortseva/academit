package permogortseva.lambdas.persons.person;

public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст должен быть >= 0. Текущее значение: " + age);
        }

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
