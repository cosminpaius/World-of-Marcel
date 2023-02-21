package Joculet;

public abstract class Spell implements Visitor{
    int damage;
    int CostMana;
    abstract void set();

    @Override
    public void visit(Entity entity){

    }
}
