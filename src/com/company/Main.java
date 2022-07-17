package com.company;

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

        long underage = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + underage);

        List<String> recruts = persons.stream()
                .filter(x -> x.getSex().equals(Sex.MAN))
                .filter(x -> x.getAge() >= 18 && x.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Количество призывников: " + recruts.size());
        System.out.println("Фамилии 15 призывников из списка: ");
        recruts.stream().limit(15).forEach(System.out::println);

        List<Person> ableToWork = persons.stream()
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 65)
                .filter(x -> !(x.getSex().equals(Sex.WOMAN) && x.getAge() > 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());;
        System.out.println("Количество работоспособных людей с высшим образованием: " + ableToWork.size());
        System.out.println("Первые 15 работоспособных людей с высшим образованием из списка: ");
        ableToWork.stream().limit(15).forEach(System.out::println);
    }
}
