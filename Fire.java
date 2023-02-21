package Joculet;

public class Fire extends Spell{
    @Override
    void set() {
        damage = 15;
        CostMana = 55;
    }

    @Override
    public String toString() {
        return " Fire ";
    }

    @Override
    public void visit(Entity entity){
        if(entity.fire == false){
            entity.receiveDamage(damage);
        }
        else{
            System.out.println("Imun la abilitatea fire.");
        }

    }
}
