package Joculet;

public class Earth extends Spell{

    @Override
    void set() {
        damage = 25;
        CostMana = 60;
    }

    @Override
    public String toString() {
        return " Earth ";
    }

    @Override
    public void visit(Entity entity){
        if(entity.earth == false){
            entity.receiveDamage(damage);
        }
        else{
            System.out.println("Imun la abilitatea earth.");
        }
    }

}
