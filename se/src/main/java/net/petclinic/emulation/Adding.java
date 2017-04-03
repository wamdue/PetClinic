package net.petclinic.emulation;

import net.petclinic.enums.Gender;
import net.petclinic.pets.BaseAnimal;
import net.petclinic.pets.Cat;
import net.petclinic.pets.Dog;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Wamdue
 * @version 1.0
 * @since 31.03.2017
 */
public class Adding implements Runnable {
    private List<BaseAnimal> animals;
    private static int id = 0;

    public Adding(List<BaseAnimal> animals) {
        this.animals = animals;
    }

    @Override
    public void run() {

        synchronized (this.animals) {
            while (true) {
                try {
                    while (animals.size() >= 1) {
                        wait(3000);
                    }
                    int r = ThreadLocalRandom.current().nextInt();
                    int age = ThreadLocalRandom.current().nextInt(14) + 1;
                    double weight = ThreadLocalRandom.current().nextDouble(10);
                    BaseAnimal animal = null;

                    r = r % 2 == 0 ? 0 : 1;

                    switch (r) {
                        case (0):
                            animal = new Cat(age, weight, "Cat " + id++, Gender.FEMALE);
                            break;
                        case (1):
                            animal = new Dog(age, weight, "Dog " + id++, Gender.FEMALE);
                            break;
                    }
                    System.out.println(animal + " в очереди на лечение!");
                    animals.add(animal);
                    notifyAll();

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }
}
