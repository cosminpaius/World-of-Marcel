package Joculet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        Game joc = Game.getInstance();
        System.out.println("Text / Interfata grafica / Hardcodare : ");
        Scanner sc = new Scanner(System.in);
        String citire = sc.nextLine();
        if(citire.equals("Hardcodare"))
            joc.hardcodare();
        else
            joc.run(citire);
    }


}
