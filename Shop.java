package Joculet;

import java.util.ArrayList;
import java.util.Random;

public class Shop implements CellElement {
    Random rand = new Random();
    ArrayList<Potion> potiuni = new ArrayList<>();

    public Shop(){
        int i, n;
        n = rand.nextInt(3) + 2;
        for(i = 1; i <= n; i++)
        if(rand.nextBoolean() == true)
            potiuni.add(new HealthPotion());
        else
            potiuni.add(new ManaPotion());
    }

    @Override
    public char toCharacter()
    {
        return 'S';
    }

    @Override
    public String toString() {
        return "Shop: " +
                "" + potiuni +
                "\n";
    }
}
