package net.wamdue.servlets;

import net.petclinic.enums.Gender;
import net.petclinic.pets.BaseAnimal;
import net.petclinic.pets.Cat;
import net.petclinic.pets.Dog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClinicServlet extends HttpServlet {
    final List<BaseAnimal> pets = new CopyOnWriteArrayList<>();
    private final Random rand = new Random();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.append(
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "     <title>Clinic Pets</title>" +
                        "</head>" +
                        "<body>" +
                        "     <form action='"+req.getContextPath()+"/' method='post'>" +
                        "         Name : <input type='text' name='name'>"+
                        "         Age : <input type='text' name='age'><br>"+
                        "         weight : <input type='text' name='weight'>"+
                        "         Gender : <input type='text' name='gender'><br>"+
                        "         <input type='submit' value='Submit'>"+
                        "     <form><br>"+
                        " <br>" +
                        "     <form action='" + req.getContextPath() +"/' method='post'>"+
                        "        Search By Name: <input type='text' name='search'>"+
                        "        <input type='submit' value='Search'>"+
                        "     </form><br>" +
                        this.viewPets() +
                        "</body>" +
                        "</html>"
        );
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println(req.getParameter("submit"));
        System.out.println(req.getParameter("search"));
        int age = 0;
        double weight =1.0;
        Gender gender = Gender.MALE;
        String name = "noname";
        try{
            age = Integer.valueOf(req.getParameter("age"));
            weight = Double.valueOf(req.getParameter("weight"));
            gender = Gender.valueOf(req.getParameter("gender").toUpperCase());
            name = req.getParameter("name");
        }catch (Exception ex){
        }

        int r = rand.nextInt(2);
        BaseAnimal animal = null;

        switch(r){
            case 0: animal = new Dog(age, weight, name, gender); break;
            default : animal = new Cat(age, weight, name, gender);
        }
        this.pets.add(animal);
        doGet(req, resp);
    }

    private String viewPets() {
        StringBuilder sb = new StringBuilder();
        sb.append("<p>Pets</p>");
        for (BaseAnimal pet : this.pets) {
            sb.append("<tr><td style='border : 1px solid black'>").append(pet.toString()).append("</td></tr><br>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    private String searchPet(String name){
        StringBuilder sb =new StringBuilder();

        sb.append("<p>Search result:</p>");
        sb.append("<table style='border : 1px solid black'>");
        for(BaseAnimal pet : this.pets){
            if(name.equalsIgnoreCase(pet.getName()))
                sb.append("<tr><td style='border : 1px solid black'>").append(pet.toString()).append("</td></tr>");
        }
        sb.append("</table>");

        return sb.toString();

    }
}