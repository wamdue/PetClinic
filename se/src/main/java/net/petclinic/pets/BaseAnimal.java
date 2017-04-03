package net.petclinic.pets;

import net.petclinic.enums.Gender;
import net.petclinic.history.Desease;
import org.jsoup.Connection;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class for animals
 *
 * @author Wamdue
 * @version 1.0
 */
public abstract class BaseAnimal implements Animal {
    private int age;
    private double weight;
    private String name;
    private Gender gender;
    private Map<LocalDateTime, Desease> deseaseMap = new HashMap<>();

    public BaseAnimal(int age, double weight, String name, Gender gender) {
        this.age = age;
        this.weight = weight;
        this.name = name;
        this.gender = gender;
    }

    public BaseAnimal(String name){
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Map<LocalDateTime, Desease> getDeseaseMap() {
        return deseaseMap;
    }

    public void setDeseaseMap(Map<LocalDateTime, Desease> deseaseMap) {
        this.deseaseMap = deseaseMap;
    }

    public void addDeasease(Desease desease)
    {
        if(desease != null)
            deseaseMap.put(LocalDateTime.now(), desease);
    }

    public void printHistory()
    {
        deseaseMap.entrySet().forEach((k) -> System.out.println(k + ": " + deseaseMap.get(k)));
    }

    public abstract String breed();

    @Override
    public String toString() {
        return breed() + "{"+"gender="+ gender +
                ", age=" + age + " years" +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                '}';
    }
}
