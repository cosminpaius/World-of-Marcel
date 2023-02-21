package Joculet;

import java.util.ArrayList;

public abstract class Entity implements Element {
    ArrayList<Spell> abilitati = new ArrayList<>();
    int viatacurenta, viatamaxima;
    int manacurenta, manamaxima;
    boolean fire, ice, earth;
    void regenerareviata(int valoare){
        if(viatacurenta + valoare > viatamaxima)
            viatacurenta = viatamaxima;
        else
            viatacurenta += valoare;
    }
    void regeneraremana(int valoare){
        if(manacurenta + valoare > manamaxima)
            manacurenta = manamaxima;
        else
            manacurenta += valoare;
    }

    abstract void receiveDamage(int damage);
    abstract int getDamage();
    abstract void upgrade();
}
