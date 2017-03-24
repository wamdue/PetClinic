package net.petclinic;

import net.petclinic.customer.Customer;
import net.petclinic.enums.Commands;
import net.petclinic.pets.BaseAnimal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 24.03.17
 *
 * @Author alexey
 * @Version 1.0
 */

public class Clinic {
    private List<Customer> customerList = new ArrayList<>();
    private List<BaseAnimal> baseAnimals = new ArrayList<>();


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
            String line = "";
            while (!line.equals("7"))
            {
                 mainMenu();
                line = readFromConsole();
            }
    }

    private void addAnimal(Customer customer, BaseAnimal animal)
    {
        customer.getAnimals().add(animal);
        baseAnimals.add(animal);
    }

    private void addCustomer(Customer customer, BaseAnimal animal)
    {
        for(Customer cust: customerList)
            if(cust.getName().equals(customer.getName())) {
                System.out.println("Клиент с таким именем уже присутствует");
                return;
            }
        customerList.add(customer);
        System.out.println("Клиент " + customer.getName() + "Успешно добавлен");

        customer.addAnimal(animal);
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

    private String readFromConsole()
    {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            return reader.readLine();
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    private int readInt()
    {
        return (int) readDouble();
    }

    private double readDouble()
    {
        while (true)
        {
            try{
                double d = Double.parseDouble(readFromConsole());
                return d;
            }catch (Exception ex)
            {
                System.out.println("Необходимо ввессти цифру, дробная часть указывается через \".\"");
            }
        }
    }

}
