package net.petclinic.pets;

import net.petclinic.enums.Gender;

/**
 * Created by alexey on 24.03.17.
 */
public class Cat extends BaseAnimal {

    public Cat(int age, double weight, String name, Gender gender) {
        super(age, weight, name, gender);
    }

    @Override
    public String breed() {
        return "Кошка";
    }
}
