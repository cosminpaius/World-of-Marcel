package Joculet;

import java.util.Random;

public abstract class Character extends Entity {
    String nume;
    int Ox, Oy;
    Inventory inventar;
    int experienta;
    int nivel;
    int putere, carisma, dexteritate;

    public Character() {
        Random rand = new Random();
        viatamaxima = rand.nextInt(201) + 100;
        manamaxima = rand.nextInt(251) + 150;
        viatacurenta = viatamaxima;
        manacurenta = manamaxima;
    }

    boolean posibilbuy(Potion potiune) {
        if (inventar.calculgreutate() >= potiune.greutate() && inventar.NrMonede >= potiune.pret()) {
            inventar.adaugpotiune(potiune);
            inventar.NrMonede = inventar.NrMonede - potiune.pret();
            return true;
        }
        return false;
    }

    void folosirepotiune(Potion potiune) {
        if (potiune.getClass().getSimpleName().equals("HealthPotion")) {
            regenerareviata(potiune.valoarereg());
            inventar.eliminpotiune(potiune);
        }
        else{
            regeneraremana(potiune.valoarereg());
            inventar.eliminpotiune(potiune);
        }
    }
    public String toString1(){
        return "" +
                "Nume: '" + nume + '\'' +
                ";   Experienta = " + experienta +
                ";    Nivel = " + nivel +
                "";
    }
    @Override
    public String toString() {
        return "\n" +
                "nume = '" + nume + '\'' +
                ", inventar = " + inventar +
                ", experienta = " + experienta +
                ", nivel = " + nivel +
                ", putere = " + putere +
                ", carisma = " + carisma +
                ", dexteritate = " + dexteritate +
                " }\n";
    }
}

class CharacterFactory {
    public static Character factory (String ChName, String nume, int experienta, int nivel) {
        if (ChName.equals("Warrior"))
            return new Warrior(nume, experienta, nivel);
        if (ChName.equals("Mage"))
            return new Mage(nume, experienta, nivel);
        if (ChName.equals("Rogue"))
            return new Rogue(nume, experienta, nivel);
        return null;
    }

}