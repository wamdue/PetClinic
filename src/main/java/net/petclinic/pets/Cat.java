package net.petclinic.pets;

import net.petclinic.enums.Gender;

public class Cat extends BaseAnimal {

    public Cat(int age, double weight, String name, Gender gender) {
        super(age, weight, name, gender);
    }

    @Override
    public String breed() {
        return "Кошка";
    }
}
