package Joculet;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Grid extends ArrayList<ArrayList<Cell>> {
    int lungime;
    int latime;
    Character caractercrt;
    Cell celulacrt;
    private Grid(int lungime, int latime, Character caractercrt, Cell celulacrt){
        this.lungime = lungime;
        this.latime = latime;
        this.caractercrt = caractercrt;
        this.celulacrt = celulacrt;
    }
    static Grid map = null;
    public static Grid generaretext(Character caractercrt, Cell celulacrt) {
        celulacrt.vizitat = true;
        map = new Grid(5, 5, caractercrt, celulacrt);
        int i = 0, j = 0;
        for(i = 0; i < map.lungime; i++) {
            map.add(new ArrayList<Cell>());
        }
        for(i = 0 ; i < map.lungime; i++)
            for(j = 0 ; j < map.latime; j++)
                map.get(i).add(j, new Cell(j, i, new CellElement() {
                    @Override
                    public char toCharacter() {
                        return 'N';
                    }
                }));
        map.get(0).set(0, celulacrt);
        map.get(0).set(3, new Cell(3,0, new Shop()));
        map.get(1).set(3, new Cell(3,1, new Shop()));
        map.get(2).set(0, new Cell(0,2, new Shop()));
        map.get(3).set(4, new Cell(4,3, new Enemy()));
        map.get(4).set(4, new Cell(4, 4, new CellElement() {
            @Override
            public char toCharacter() {
                return 'F';
            }
        }));
        return map;
    }

    public static Grid generareint(Character caractercrt, Cell celulacrt){
        Random rand = new Random();
        int lungime = rand.nextInt(6) + 5;
        int latime = rand.nextInt(6) + 5;
        map = new Grid(lungime, latime, caractercrt, celulacrt);
        int i, j, nr = (lungime * latime) / 3;
        int nrshop = nr/2 - rand.nextInt(nr/3), nrinamici = nr - nrshop;
        int poz1[] = new int[nr];
        int poz2[] = new int[nr];
        for(i = 0 ; i < nr ; i ++) {
            poz1[i] = rand.nextInt(lungime - 1) + 1;
            poz2[i] = rand.nextInt(latime - 1) + 1;
        }
        for(i = 0; i < map.lungime; i++) {
            map.add(new ArrayList<Cell>());
        }
        for (i = 0 ; i < lungime ; i ++)
            for(j = 0 ; j < latime ; j ++)
                map.get(i).add(j, new Cell(j, i, new CellElement() {
                    @Override
                    public char toCharacter() {
                                return 'N';
                            }
                }));
        for(i = 0 ; i < lungime; i ++)
            for(j = 0 ; j < latime; j++)
                for (int k = 0; k < nr; k++) {
                    if (poz1[k] == i && poz2[k] == j) {
                        if (nrshop != 0) {
                            map.get(i).set(j, new Cell(j, i, new Shop()));
                            nrshop--;
                        } else
                            map.get(i).set(j, new Cell(j, i, new Enemy()));
                    }
                }
        int starti = rand.nextInt(lungime);
        int startj = rand.nextInt(latime);
        int stopi = rand.nextInt(lungime);
        int stopj = rand.nextInt(latime);
        map.get(starti).set(startj, new Cell(startj, starti, new CellElement() {
            @Override
            public char toCharacter() {
                return 'P';
            }
        }));
        map.get(starti).get(startj).vizitat = true;
        celulacrt = map.get(starti).get(startj);
        map.celulacrt = map.get(starti).get(startj);
        map.get(stopi).set(stopj, new Cell(stopj, stopi, new CellElement() {
            @Override
            public char toCharacter() {
                return 'F';
            }
        }));
        map.get(stopi).get(stopj).vizitat = true;
                return map;
    }

    void afisare() {
        int i, j;
        for (i = 0; i < lungime; i++) {
            for (j = 0; j < latime; j++) {
                if (map.get(i).get(j) == celulacrt)
                    System.out.print("P");
                if (map.get(i).get(j).vizitat == true)
                    System.out.print(map.get(i).get(j).element.toCharacter() + " ");
                else
                    System.out.print("? ");
            }
                System.out.println();
        }
    }

    void goNorth(){
        if(get(celulacrt.Oy).get(celulacrt.Ox).Oy - 1 < 0)
            System.out.println("Nu se poate deplasa spre nord.");
        else {
            celulacrt = get(celulacrt.Oy - 1).get(celulacrt.Ox);
            caractercrt.Ox = celulacrt.Ox;
            caractercrt.Oy = celulacrt.Oy;
        }
    }
    void goSouth(){
        if(get(celulacrt.Oy).get(celulacrt.Ox).Oy + 1 >= lungime)
            System.out.println("Nu se poate deplasa spre sud.");
        else {
            celulacrt = get(celulacrt.Oy + 1).get(celulacrt.Ox);
            caractercrt.Ox = celulacrt.Ox;
            caractercrt.Oy = celulacrt.Oy;
        }
    }
    void goWest(){
        if(get(celulacrt.Oy).get(celulacrt.Ox).Ox - 1 < 0)
            System.out.println("Nu se poate deplasa spre vest.");
        else {
            celulacrt = get(celulacrt.Oy).get(celulacrt.Ox - 1);
            caractercrt.Ox = celulacrt.Ox;
            caractercrt.Oy = celulacrt.Oy;
        }
    }
    void goEast() {
        if (get(celulacrt.Oy).get(celulacrt.Ox).Ox + 1 >= latime)
            System.out.println("Nu se poate deplasa spre est.");
        else {
            celulacrt = get(celulacrt.Oy).get(celulacrt.Ox + 1);
            caractercrt.Ox = celulacrt.Ox;
            caractercrt.Oy = celulacrt.Oy;
        }
    }
}
