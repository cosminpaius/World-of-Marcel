package Joculet;

public class Cell{
    int Ox ;
    int Oy ;
    boolean vizitat = false;
    CellElement element;
    enum tip
    {
        EMPTY, ENEMY, SHOP, FINISH
    }
    public Cell(int Ox, int Oy, CellElement element){
        this.Ox = Ox;
        this.Oy = Oy;
        this.element = element;
    }
}
