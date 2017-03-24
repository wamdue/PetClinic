package net.petclinic.customer;

import net.petclinic.pets.BaseAnimal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 24.03.17
 *
 * @Author Alexey Gorbunov
 * @Version 1.0
 */

public class Customer {
    private int age;
    private String name;
    private List<BaseAnimal> animals = new ArrayList<>();

    public Customer(int age, String name, List<BaseAnimal> animals) {
        this.age = age;
        this.name = name;
        this.animals = animals;
    }

    public List<BaseAnimal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<BaseAnimal> animals) {
        this.animals = animals;
    }

    public void addAnimal(BaseAnimal baseAnimal)
    {
        animals.add(baseAnimal);
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
