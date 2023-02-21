package Joculet;

import java.util.ArrayList;
import java.util.Random;

public class Warrior extends Character{
    public Warrior(String nume, int nivel, int experienta) {
        Random rand = new Random();
        int n = rand.nextInt(10);
        for(int i = 0 ; i < n ; i++ ){
            int x = rand.nextInt(3);
            if(x == 0)
                abilitati.add(new Fire());
            if(x == 1)
                abilitati.add(new Earth());
            if(x == 2)
                abilitati.add(new Ice());
        }
        inventar = new Inventory(50);
        this.nume = nume;
        this.experienta = experienta;
        this.nivel = nivel;
        putere = 10;
        dexteritate = 6;
        carisma = 4 ;
        earth = false;
        fire = true;
        ice = false;
    }

    void upgrade(){
        putere += nivel * 3;
        dexteritate += nivel * 2;
        carisma += nivel;
    }

    @Override
    void receiveDamage(int damage) {
        Random rand = new Random();
        int temp = dexteritate + carisma;
        if(rand.nextInt(25) < temp)
            damage = damage / 2;
        viatacurenta -= damage;
    }

    @Override
    int getDamage() {
        int damage = 2 * putere;
        Random rand = new Random();
        if(putere > rand.nextInt(16))
            damage = damage * 2;
        return damage;
    }

    @Override
    public String toString() {
        return "\nWarrior cu " +
                "Numele = '" + nume + '\'' +
                "" + inventar +
                "\nExperienta = " + experienta +
                "\nNivel = " + nivel +
                "\nPutere = " + putere +
                "\nCarisma = " + carisma +
                "\nDexteritate = " + dexteritate +
                "\nAbilitati : " + abilitati +
                "\nViataCurenta = " + viatacurenta +
                "\nViataMaxima = " + viatamaxima +
                "\nManaCurenta = " + manacurenta +
                "\nManaMaxima = " + manamaxima +
                "\nImun->Fire = " + fire +
                "\nImun->Ice = " + ice +
                "\nImun->Earth = " + earth +
                "\n";
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
