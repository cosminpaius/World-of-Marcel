package Joculet;

public class HealthPotion implements Potion{
    private int pret, greutate, valoareregenerare;
    public HealthPotion(){
        pret = 20;
        greutate = 10;
        valoareregenerare = 25;
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
        return "HealthPotion: " +
                "pret = " + pret +
                ", greutate = " + greutate +
                ", valoareregenerare = " + valoareregenerare +
                "\n";
    }
}
