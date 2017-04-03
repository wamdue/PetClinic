package net.petclinic;

import net.petclinic.customer.*;
import net.petclinic.emulation.Adding;
import net.petclinic.emulation.Cure;
import net.petclinic.enums.Gender;
import net.petclinic.pets.BaseAnimal;
import net.petclinic.pets.Cat;
import net.petclinic.pets.Dog;
import net.petclinic.utils.Console;
import net.petclinic.utils.CustomList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 24.03.17
 *
 * @author alexey
 * @version 1.0
 */

public class Clinic {
    private List<Customer> customerList = new CustomList<>();
    private List<BaseAnimal> baseAnimals = new CustomList<>();
    private List<BaseAnimal> queue = new ArrayList<>();


    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<BaseAnimal> getBaseAnimals() {
        return baseAnimals;
    }

    public void setBaseAnimals(List<BaseAnimal> baseAnimals) {
        this.baseAnimals = baseAnimals;
    }

    public void work()
    {
     /*       String line;
            while (true)
            {
                mainMenu();
                System.out.println("Выберите меню: ");
                line = Console.readFromConsole();
                switch (line)
                {
                    case "1" : addCustomer(); break;
                    case "2" : addPetToExistingCustomer(); break;
                    case "3" : System.out.println("Введите имя для поиска:");
                        findCustomer(Console.readFromConsole()); break;
                    case "4" : System.out.println("Введите кличку питомца:");
                        findAnimal(Console.readFromConsole()); break;
                    case "7" : System.out.println("Bye bye!");
                        System.exit(0);
                }
            }*/

        Thread add = new Thread(new Adding(baseAnimals));
        Thread cure = new Thread(new Cure(baseAnimals));


        add.start();
        cure.start();
    }

    private void findAnimal(String name) {
        if(name != null && name != "")
        {
            baseAnimals.forEach(v -> {
                if(v.getName().equalsIgnoreCase(name)) {
                    System.out.println(v);
                    return;
                }
            });
            System.out.printf("Питомец %s не найден!\n", name);

        }
    }

//    private void addAnimal(Customer customer, BaseAnimal animal)
//    {
//        customer.getAnimals().add(animal);
//        baseAnimals.add(animal);
//    }

//    private void addCustomer(Customer customer, BaseAnimal animal)
//    {
//        for(Customer cust: customerList)
//            if(cust.getName().equals(customer.getName())) {
//                System.out.println("Клиент с таким именем уже присутствует");
//                return;
//            }
//        customerList.add(customer);
//        System.out.println("Клиент " + customer.getName() + "Успешно добавлен");
//
//        customer.addAnimal(animal);
//    }

    private void addPetToExistingCustomer()
    {
        System.out.println("Добавляем питомца для клиента: ");
        String name = Console.readFromConsole();

        if(name != null && name != "") {
            for (Customer cust : customerList) {
                if (cust.getName().equalsIgnoreCase(name)) {
                    cust.addAnimal(addAnimal());
                    System.out.printf("Питомец добавлен клиенту %s", name);
                    break;
                }
            }
            System.out.println("Клиент не найден");
        }else
        {
            System.out.println("Необходимо ввести имя клиента!");
        }
    }

    private void mainMenu()
    {
        System.out.println("1 - Добавить клиента");
        System.out.println("2 - Добавить животное");
        System.out.println("3 - Найти клиента");
        System.out.println("4 - Найти животное");
        System.out.println("5 - Поставить диагноз");
        System.out.println("6 - История болезни");
        System.out.println("7 - Выход");
    }

    private void addCustomer()
    {
        System.out.println("Введите ФИО Клиента:");
        String name = Console.readFromConsole();
        System.out.println("Возраст:");
        int age = Console.readInt();
        BaseAnimal animal = addAnimal();
        List<BaseAnimal> animals = new ArrayList<>();
        animals.add(animal);
        Customer customer = new Customer(age, name, animals);
        customerList.add(customer);
        baseAnimals.add(animal);
        System.out.println("Клиент добавлен!");
    }

    private BaseAnimal addAnimal()
    {
        System.out.println("Введите род животного cat/dog:");
        String type = Console.readFromConsole();
        System.out.println("Как зовут?");
        String name = Console.readFromConsole();
        System.out.println("Введите пол животного 1 - мальчик, 2 девочка: ");
        Gender gender = Console.readInt() == 1? Gender.MALE : Gender.FEMALE;
        System.out.println("Введите возраст животного: ");
        int animalAge = Console.readInt();
        System.out.println("Введите вес животного:");
        int weight = Console.readInt();

        BaseAnimal animal = type.equalsIgnoreCase("cat") ? new Cat(animalAge, weight, name, gender)
                : new Dog(animalAge, weight, name, gender);
        System.out.println("Питомец добавлен!");
        return animal;
    }

    private void findCustomer(String name)
    {
        if(name != null && name != "") {
            customerList.forEach(v -> {
                if (v.getName().equalsIgnoreCase(name)) {
                    System.out.println(v);
                }
            });
        }
        System.out.printf("Клиент %s не найден!\n", name);
    }
}
