package Joculet;

public class ManaPotion implements Potion{
    private int pret, greutate, valoareregenerare;
    public ManaPotion(){
        pret = 40;
        greutate = 8;
        valoareregenerare = 50;
    }

    @Override
    public int pret() {
        return pret;
    }

    @Override
    public int valoarereg() {
        return valoareregenerare;
    }

    @Override
    public int greutate() {
        return greutate;
    }

    @Override
    public String toString() {
        return "ManaPotion: " +
                " pret = " + pret +
                ", greutate = " + greutate +
                ", valoareregenerare = " + valoareregenerare +
                "\n";
    }
}
