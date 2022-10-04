import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //1.
        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(count);

        //2.
        List<String> warMens = persons.stream()
                .filter(person -> (person.getAge() >= 18 && person.getAge() <= 27))
                .filter(person -> person.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(warMens);

        //3.
        List<String> educatedPeoples = persons.stream()

                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> (person.getSex().equals(Sex.WOMAN) && person.getAge() < 60) | (person.getSex().equals(Sex.MAN) && person.getAge() < 65))
                .sorted(Comparator.comparing(Person::getFamily))
                .map(person1 -> "\n" + person1.toString())
                .collect(Collectors.toList());
        System.out.println(educatedPeoples);
    }
}