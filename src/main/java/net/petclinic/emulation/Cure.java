package net.petclinic.emulation;

import net.petclinic.pets.BaseAnimal;

import java.util.List;

/**
 * @author Wamdue
 * @version 1.0
 * @since 31.03.2017
 */
public class Cure implements Runnable{

    private List<BaseAnimal> animals;

    public Cure(List<BaseAnimal> animals) {
        this.animals = animals;
    }

    @Override
    public void run() {
        synchronized (this.animals)
        {
            try {
                while(true)
                {
                    while(animals.size() == 0)
                    {
                        wait();
//                        notifyAll();
                    }

                    BaseAnimal animal = animals.remove(0);
                    System.out.println(animal.getClass().getSimpleName() + " по имени " + animal.getName() + " вылечена!");
                }
            }catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
