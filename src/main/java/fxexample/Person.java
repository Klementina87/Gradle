package fxexample;

import java.util.*;

public class Person {

    private int id;
    private String fName;
    private String lName;
    private int age;

    public Person(int id, String fName,String lName, int age) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getLName() {
        return lName;
    }

    public String getFName() {
        return fName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + fName + " " + lName + '\'' +
                ", age=" + age +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id == person.id && age == person.age && Objects.equals(fName, person.fName) && Objects.equals(lName, person.lName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fName, lName, age);
    }

    public static List<Person> createPersonList() {
        return Arrays.asList(
                new Person(12, "John","Smith", 25),
                new Person(15, "Mary","Gomez", 92),
                new Person(22, "Lionel","Wang", 45),
                new Person(23, "Susan","Williams", 50),
                new Person(18, "Manar", "George",10)
        );
    }


    public static Map<PersonAge, List<Person>> getPeopleMap() {
        Map<PersonAge, List<Person>> grouped = new HashMap<>();
        for (Person p : createPersonList()) {
                if (p.getAge() <= PersonAge.YOUTH.getMax())
                addToMap(PersonAge.YOUTH, p, grouped);
                else if (p.getAge() <= PersonAge.YOUNG_ADULT.getMax())
                    addToMap(PersonAge.YOUNG_ADULT, p, grouped);
                else if (p.getAge() <= PersonAge.ADULT.getMax())
                    addToMap(PersonAge.ADULT, p, grouped);
                else addToMap(PersonAge.SENIOR, p, grouped);


        }
        return grouped;
    }

    private static void addToMap(PersonAge key,Person person,Map<PersonAge, List<Person>>map){
        if(!map.containsKey(key))
            map.put(key,new ArrayList<>(Arrays.asList(person)));
        else
            map.get(key).add(person);
    }
}