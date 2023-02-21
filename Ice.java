package Joculet;

public class Ice extends Spell{

    @Override
    void set() {
        damage = 10;
        CostMana = 50;
    }

    @Override
    public String toString() {
        return " Ice ";
    }

    @Override
    public void visit(Entity entity){
        if(entity.ice == false){
            entity.receiveDamage(damage);
        }
        else{
            System.out.println("Imun la abilitatea ice.");
        }
    }
}
