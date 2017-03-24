package net.petclinic.pets;

import net.petclinic.enums.Gender;

/**
 * Created on 24.03.17
 *
 * @author alexey
 * @version 1.0
 */
public class Dog extends BaseAnimal {

    public Dog(int age, double weight, String name, Gender gender) {
        super(age, weight, name, gender);
    }

    @Override
    public String breed() {
        return "Собака";
    }
}
