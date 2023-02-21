package Joculet;

import java.util.ArrayList;

public class Inventory {
    ArrayList<Potion> potiuni = new ArrayList<>();
    int GreutateMaxima;
    int NrMonede;
    public Inventory(int GreutateMaxima){
        this.GreutateMaxima = GreutateMaxima;
        NrMonede = 0;
    }
    boolean adaugpotiune(Potion potiune) {
        int sum = 0, i;
        for (i = 0; i < potiuni.size(); i++)
            sum = sum + (potiuni.get(i)).greutate();
        if (GreutateMaxima >= sum + potiune.greutate() && NrMonede >= potiune.pret()) {
            potiuni.add(potiune);
            NrMonede -= potiune.pret();
        }
        else if (GreutateMaxima < sum + potiune.greutate()) {
            System.out.println("Nu mai aveti loc in inventar.");
            return false;
        } else if (NrMonede < potiune.pret()) {
            System.out.println("Nu aveti destui bani.");
            return false;
        }
        return true;
    }

    void eliminpotiune(Potion potiune){
        if(potiuni != null)
            potiuni.remove(potiune);
    }

    int calculgreutate(){
        int sum = 0, i;
        for( i = 0 ; i < potiuni.size(); i ++)
            sum = sum + (potiuni.get(i)).greutate();
        return (GreutateMaxima - sum);
    }

    @Override
    public String toString() {
        return "\n" +
                "potiuni = " + potiuni +
                ", GreutateMaxima = " + GreutateMaxima +
                ", NrMonede = " + NrMonede;
    }
}
