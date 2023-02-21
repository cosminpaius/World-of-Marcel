package Joculet;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Entity implements CellElement{
    Random rand = new Random();
    public Enemy() {
        abilitati = new ArrayList<>();
        viatamaxima = rand.nextInt(201) + 100;
        manamaxima = rand.nextInt(251) + 150;
        viatacurenta = viatamaxima;
        manacurenta = manamaxima;
        ice = rand.nextBoolean();
        fire = rand.nextBoolean();
        earth = rand.nextBoolean();
        if (viatamaxima <= 200) {
            abilitati.add(new Earth());
            abilitati.add(new Fire());
            abilitati.add(new Ice());
        }
        if (viatamaxima > 200 && viatamaxima <= 250) {
            abilitati.add(new Earth());
            abilitati.add(new Fire());
        }
        if (viatamaxima > 250 && viatamaxima <= 280) {
            abilitati.add(new Earth());
            abilitati.add(new Ice());
        }
        if (viatamaxima > 280){
            abilitati.add(new Fire());
            abilitati.add(new Ice());
        }
    }
    @Override
    public char toCharacter() {
        return 'E';
    }

    @Override
    void receiveDamage(int damage) {
        if(rand.nextBoolean() == true) //sansa de 50%
            viatacurenta = viatacurenta - damage;
    }

    @Override
    int getDamage() {
        int damage = rand.nextInt(26);  //valoare intr-un interval
        if(rand.nextBoolean() == true)  //sansa de 50%
            damage = damage * 2;
        return damage;
    }

    @Override
    void upgrade() {}

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
