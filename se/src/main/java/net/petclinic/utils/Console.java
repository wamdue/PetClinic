package net.petclinic.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created on 24.03.17
 *
 * @author Wamdue
 * @version 1.0
 */
public class Console {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static String readFromConsole()
    {
        try{
            String line = reader.readLine();
            return line == null || line == "" ? " " : line;
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static int readInt()
    {
        return (int) readDouble();
    }

    public static double readDouble()
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
