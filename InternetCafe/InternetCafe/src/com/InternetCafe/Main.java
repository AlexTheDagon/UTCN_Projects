package com.InternetCafe;

import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //System.out.println("Hello World!"); // Display the string.
        //Product lol = new Product("League of Legends", "PC", 10, 50, 80);
        //System.out.println(lol.getName());
        FileWriter myFile = new FileWriter("text.txt");
        GUI GraphicalUserInterface = new GUI(myFile);
    }
}
