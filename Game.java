package Joculet;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.google.gson.*;


public class Game {
    ArrayList<Account> conturi = new ArrayList<>();
    public static HashMap<Cell.tip, List<String>> dictionar = new HashMap<>();
    Grid map = null;

    private static Game obj = null;
    private Game() {
        System.out.println("Incepe jocul...");
    }
    public static Game getInstance() {
        if (obj == null)
            obj = new Game();
        return obj;
    }

    void run(String citire) throws Exception {
        Scanner sc = new Scanner(System.in);
        if (citire.equals("Text")) {
            Cell celula = new Cell(0,0, new CellElement() {
                @Override
                public char toCharacter() {
                    return 'N';
                }
            });
            parsareconturi();
            System.out.println("Introduceti numele de utilizator: ");
            String numecont = sc.nextLine();
            System.out.println("Introduceti parola: ");
            String parolacont = sc.nextLine();
            int oknume = -1, okparola = -1;
            for(int i = 0 ; i < conturi.size(); i ++) {
                if (conturi.get(i).info.getCredentiale().getEmail().equals(numecont))
                    oknume = i;
            }
            if (conturi.get(oknume).info.getCredentiale().getParola().equals(parolacont))
                    okparola = 1;
            if(oknume == -1 || okparola == -1){
                throw new InvalidCommentException();
            }
            System.out.println("Ati ales contul cu urmatoarele caractere: ");
            for(int i = 0 ; i < conturi.get(oknume).personaje.size(); i++)
                System.out.println(i + "." + conturi.get(oknume).personaje.get(i).toString1());
            System.out.println("\nIntroduceti indexul caracterului pentru a incepe jocul: ");
            String caracter = sc.nextLine();
            int okcaracter = Integer.parseInt(caracter);
            map = Grid.generareint(CharacterFactory.factory(conturi.get(oknume).personaje.get(okcaracter).getClass().getSimpleName(), conturi.get(oknume).personaje.get(okcaracter).nume, conturi.get(oknume).personaje.get(okcaracter).nivel, conturi.get(oknume).personaje.get(okcaracter).experienta), celula);
            for(int i = 0; i < map.lungime; i++ )
                for(int j = 0 ; j < map.latime; j++)
                    if(map.get(i).get(j).element.toCharacter() == 'P')
                        map.celulacrt = map.get(i).get(j);
            conturi.get(oknume).personaje.get(okcaracter).upgrade();
            map.caractercrt.upgrade();
            System.out.println("Felicitari! Ati ales caracterul: " + conturi.get(oknume).personaje.get(okcaracter));
            map.afisare();
            while(map.celulacrt.element.toCharacter() != 'F' && map.caractercrt.viatacurenta > 0){
                System.out.println("Introduceti o comanda: ");
                String comanda = sc.nextLine();
                if(comanda.equals("stop"))
                    break;
                if(comanda.equals("Nord")) {
                    map.goNorth();
                }
                else
                    if(comanda.equals("Sud")) {
                        map.goSouth();
                    }
                    else
                        if(comanda.equals("Est")) {
                            map.goEast();
                        }
                        else
                            if(comanda.equals("Vest")) {
                                map.goWest();
                            }
                            else
                                System.out.println("Ati introdus o comanda gresita! Incercati Nord/Sud/Est/Vest");
                if (map.celulacrt.vizitat == false) {
                    String poveste = parsarepovesti(map.celulacrt);
                    System.out.println(poveste);
                    Random rand = new Random();
                    if (rand.nextInt(5) == 1)
                        map.caractercrt.inventar.NrMonede += rand.nextInt(50);
                }
                if(map.celulacrt.element.toCharacter() == 'S') {
                    Shop shop = (Shop) map.celulacrt.element;
                    int terminat = 0;
                    while (terminat != 1) {
                        System.out.println("NrMonede = " + map.caractercrt.inventar.NrMonede + ", GreutateRamasa = " + map.caractercrt.inventar.calculgreutate());
                        System.out.print("Potiuni curente: ");
                        for(int i = 0 ; i < map.caractercrt.inventar.potiuni.size(); i++)
                            System.out.print(map.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName() + "; ");
                        System.out.println("\nAti ajuns la un shop. Aveti posibilitatea de a cumpara: ");
                        for (int i = 0; i < shop.potiuni.size(); i++)
                            System.out.print(i + ". " + shop.potiuni.get(i));
                        System.out.println("Introduceti indicele potiunii pe care doriti sa o cumparati: ");
                        String indice = sc.nextLine();
                        if (indice.equals("0")) {
                            boolean ok = map.caractercrt.inventar.adaugpotiune(shop.potiuni.get(0));
                            if(ok == true)
                                shop.potiuni.remove(0);
                        }
                        if (indice.equals("1")) {
                            boolean ok = map.caractercrt.inventar.adaugpotiune(shop.potiuni.get(1));
                            if(ok == true)
                                shop.potiuni.remove(1);
                        }
                        if (indice.equals("2")) {
                            boolean ok = map.caractercrt.inventar.adaugpotiune(shop.potiuni.get(2));
                            if(ok == true)
                                shop.potiuni.remove(2);
                        }
                        if (indice.equals("3")) {
                            boolean ok = map.caractercrt.inventar.adaugpotiune(shop.potiuni.get(3));
                            if(ok == true)
                                shop.potiuni.remove(3);
                        }
                        if (indice.equals("nu")) {
                            System.out.println("Am inteles ca nu mai doriti sa cumparati.");
                            terminat = 1;
                        }
                    }
                }
                if(map.celulacrt.element.toCharacter() == 'E' && map.celulacrt.vizitat == false) {
                    Enemy inamic = (Enemy) map.celulacrt.element;
                    while (map.caractercrt.viatacurenta > 0 && inamic.viatacurenta > 0) {
                        System.out.print("Detalii caracter: " + " V: " + map.caractercrt.viatacurenta + ", M: " + map.caractercrt.manacurenta + ", " + "A: " + map.caractercrt.abilitati + ", P: " + map.caractercrt.inventar.potiuni + ";\n");
                        System.out.println("Detalii inamic: " + " V: " + inamic.viatacurenta + ", M: " + inamic.manacurenta + ", A: " + inamic.abilitati + ";");
                        System.out.println("Introduceti ce tip de abilitate/potiune doriti: ");
                        String abilitate = sc.nextLine();
                        if (abilitate.equals("Atac")) {
                            inamic.receiveDamage(map.caractercrt.getDamage());
                        }
                        if (abilitate.equals("Fire")) {
                            int ok = -1;
                            if (map.caractercrt.abilitati == null) {
                                System.out.println("Nu aveti aceasta abilitate.");
                                continue;
                            }
                            for (int i = 0; i < map.caractercrt.abilitati.size(); i++)
                                if (map.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Fire"))
                                    ok = i;
                                if (ok == -1)
                                    System.out.println("Nu aveti aceasta abilitate.");
                                else {
                                    map.caractercrt.abilitati.get(ok).set();
                                    if(map.caractercrt.manacurenta >= map.caractercrt.abilitati.get(ok).CostMana) {
                                        //map.caractercrt.abilitati.get(ok).visit(inamic);
                                        inamic.accept(map.caractercrt.abilitati.get(ok));
                                        map.caractercrt.manacurenta -= map.caractercrt.abilitati.get(ok).CostMana;
                                        map.caractercrt.abilitati.remove(ok);
                                    }
                                    else{
                                        System.out.println("Nu aveti destula mana!");
                                    }
                                }
                        }
                        if (abilitate.equals("Earth")) {
                            int ok = -1;
                            if (map.caractercrt.abilitati == null) {
                                System.out.println("Nu aveti aceasta abilitate.");
                                continue;
                            }
                            for (int i = 0; i < map.caractercrt.abilitati.size(); i++)
                                if (map.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Earth"))
                                    ok = i;
                                if (ok == -1)
                                    System.out.println("Nu aveti aceasta abilitate.");
                                else {
                                    map.caractercrt.abilitati.get(ok).set();
                                    if(map.caractercrt.manacurenta >= map.caractercrt.abilitati.get(ok).CostMana) {
                                        inamic.accept(map.caractercrt.abilitati.get(ok));
                                        map.caractercrt.manacurenta -= map.caractercrt.abilitati.get(ok).CostMana;
                                        map.caractercrt.abilitati.remove(ok);
                                    }
                                    else{
                                        System.out.println("Nu aveti destula mana!");
                                    }
                                }
                        }
                        if (abilitate.equals("Ice")) {
                            int ok = -1;
                            if (map.caractercrt.abilitati == null) {
                                System.out.println("Nu aveti aceasta abilitate.");
                                continue;
                            }
                            for (int i = 0; i < map.caractercrt.abilitati.size(); i++)
                                if (map.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Ice"))
                                    ok = i;
                                if (ok == -1)
                                    System.out.println("Nu aveti aceasta abilitate.");
                                else {
                                    map.caractercrt.abilitati.get(ok).set();
                                    if(map.caractercrt.manacurenta >= map.caractercrt.abilitati.get(ok).CostMana) {
                                        //map.caractercrt.abilitati.get(ok).visit(inamic);
                                        inamic.accept(map.caractercrt.abilitati.get(ok));
                                        map.caractercrt.manacurenta -= map.caractercrt.abilitati.get(ok).CostMana;
                                        map.caractercrt.abilitati.remove(ok);
                                    }
                                    else{
                                        System.out.println("Nu aveti destula mana!");
                                    }
                                }
                        }
                        if (abilitate.equals("Viata")) {
                            int ok = -1;
                            if (map.caractercrt.inventar.potiuni.isEmpty()) {
                                System.out.println("Nu aveti aceasta potiune.");
                                continue;
                            }
                            for (int i = 0; i < map.caractercrt.inventar.potiuni.size(); i++)
                                if (map.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion"))
                                    ok = i;
                                if (ok == -1)
                                    System.out.println("Nu aveti destule potiuni.");
                                else
                                    map.caractercrt.folosirepotiune(map.caractercrt.inventar.potiuni.get(ok));
                        }
                        if (abilitate.equals("Mana")) {
                            int ok = -1;
                            if (map.caractercrt.inventar.potiuni.isEmpty()) {
                                System.out.println("Nu aveti aceasta potiune.");
                                continue;
                            }
                            for (int i = 0; i < map.caractercrt.inventar.potiuni.size(); i++)
                                if (map.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("ManaPotion"))
                                    ok = i;
                                if (ok == -1)
                                    System.out.println("Nu aveti destule potiuni.");
                                else
                                    map.caractercrt.folosirepotiune(map.caractercrt.inventar.potiuni.get(ok));
                        }
                        Random rand = new Random();
                        int abilitate_inamic = rand.nextInt(2);
                        if (inamic.abilitati.isEmpty()) {
                            abilitate_inamic = 0;
                        }
                        if (abilitate_inamic == 0) {
                            map.caractercrt.receiveDamage(inamic.getDamage());
                        } else {
                            int nr = inamic.abilitati.size();
                            Random newrand = new Random();
                            int abilitate_random = newrand.nextInt(nr);
                            inamic.abilitati.get(abilitate_random).set();
                            if(inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                //inamic.abilitati.get(abilitate_random).visit(map.caractercrt);
                                map.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                inamic.abilitati.remove(abilitate_random);
                            }
                            else
                            {
                                System.out.println("Inamicul nu are suficienta mana.");
                            }
                        }
                    }
                    if (map.caractercrt.viatacurenta <= 0)
                        System.out.println("Ai pierdut! Aduna monede si incearca mai tarziu.");
                    else {
                        Random randmonede = new Random();
                        Random randsansa = new Random();
                        Random experienta = new Random();
                        int sansa = randsansa.nextInt(5);
                        int monede = randmonede.nextInt(100);
                        if(sansa != 0 && monede != 0) {
                            System.out.println("Felicitari, ai infrant inamicul si ai primit " + monede + " monede!");
                            map.caractercrt.inventar.NrMonede += monede;
                        }
                        else{
                            System.out.println("Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.");
                        }
                        map.caractercrt.experienta += experienta.nextInt(70);
                        if(map.caractercrt.experienta >= 100) {
                            map.caractercrt.nivel ++;
                            map.caractercrt.experienta -= 100;
                            map.caractercrt.upgrade();
                        }
                    }
                    System.out.println("\nCaracterul tau dupa ultima lupta:\n" + map.caractercrt);
                }
                else
                    if(map.celulacrt.element.toCharacter() == 'E' && map.celulacrt.vizitat == true){
                        System.out.println("Ai mai luptat deja cu acest inamic.");
                    }
                map.celulacrt.vizitat = true;
                map.afisare();
            }
        } else if (citire.equals("Interfata grafica")) {
            parsareconturi();
            new Logare(conturi);
        }
    }

    void hardcodare() throws Exception {
        Scanner sc = new Scanner(System.in);
        Cell celulacrt = new Cell(0, 0, new CellElement() {
            @Override
            public char toCharacter() {
                return 'N';
            }
        });
        parsareconturi();
        System.out.println("Introduceti numele de utilizator: ");
        String numecont = sc.nextLine();
        System.out.println("Introduceti parola: ");
        String parolacont = sc.nextLine();
        int oknume = -1, okparola = -1;
        for (int i = 0; i < conturi.size(); i++) {
            if (conturi.get(i).info.getCredentiale().getEmail().equals(numecont))
                oknume = i;
        }
        if (conturi.get(oknume).info.getCredentiale().getParola().equals(parolacont))
            okparola = 1;
        if (oknume == -1 || okparola == -1) {
            throw new InvalidCommentException();

        }
        System.out.println("Ati ales contul cu urmatoarele caractere: ");
        for (int i = 0; i < conturi.get(oknume).personaje.size(); i++)
            System.out.println(i + "." + conturi.get(oknume).personaje.get(i).toString1());
        System.out.println("\nIntroduceti indexul caracterului pentru a incepe jocul: ");
        String caracter = sc.nextLine();
        int okcaracter = Integer.parseInt(caracter);
        map = Grid.generaretext(CharacterFactory.factory(conturi.get(oknume).personaje.get(okcaracter).getClass().getSimpleName(), conturi.get(oknume).personaje.get(okcaracter).nume, conturi.get(oknume).personaje.get(okcaracter).nivel, conturi.get(oknume).personaje.get(okcaracter).experienta), celulacrt);
        conturi.get(oknume).personaje.get(okcaracter).upgrade();
        map.caractercrt.upgrade();
        System.out.println("Felicitari! Ati ales caracterul: " + conturi.get(oknume).personaje.get(okcaracter));
        map.afisare();
        map.caractercrt.inventar.NrMonede += 60;
        System.out.println("Introduceti o comanda: ");
        String comanda = sc.nextLine();
        if(comanda.equals("P")) {
            map.goEast();
            map.celulacrt.vizitat = true;
            map.goEast();
            map.celulacrt.vizitat = true;
            map.goEast();
            map.celulacrt.vizitat = true;
        }
        while (map.celulacrt.element.toCharacter() != 'F' && map.caractercrt.viatacurenta > 0) {
            if (comanda.equals("stop"))
                break;
            if (map.celulacrt.vizitat == false) {
                String poveste = parsarepovesti(map.celulacrt);
                System.out.println(poveste);
                Random rand = new Random();
                if (rand.nextInt(5) == 1)
                    map.caractercrt.inventar.NrMonede += rand.nextInt(50);
            }
            if(map.celulacrt.element.toCharacter() == 'S') {
                Shop shop = (Shop) map.celulacrt.element;
                int terminat = 0;
                shop.potiuni.add(new ManaPotion());
                shop.potiuni.add(new HealthPotion());
                while (terminat != 1) {
                    System.out.println("NrMonede = " + map.caractercrt.inventar.NrMonede + ", GreutateRamasa = " + map.caractercrt.inventar.calculgreutate());
                    System.out.print("Potiuni curente: ");
                    for(int i = 0 ; i < map.caractercrt.inventar.potiuni.size(); i++)
                        System.out.print(map.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName() + "; ");
                    System.out.println("\nAti ajuns la un shop. Aveti posibilitatea de a cumpara: ");
                    for (int i = 0; i < shop.potiuni.size(); i++)
                        System.out.print(i + ". " + shop.potiuni.get(i));
                    System.out.println("Introduceti indicele potiunii pe care doriti sa o cumparati: ");
                    comanda = sc.nextLine();
                    if(comanda.equals("P"))
                        while(shop.potiuni.isEmpty() == false){
                            map.caractercrt.inventar.adaugpotiune(shop.potiuni.get(0));
                            shop.potiuni.remove(0);
                            terminat = 1;
                        }
                }
            }
            if(map.celulacrt.element.toCharacter() == 'E' && map.celulacrt.vizitat == false) {
                Enemy inamic = (Enemy) map.celulacrt.element;
                map.celulacrt.vizitat = true;
                while (map.caractercrt.viatacurenta > 0 && inamic.viatacurenta > 0) {
                    System.out.print("Detalii caracter: " + " V: " + map.caractercrt.viatacurenta + ", M: " + map.caractercrt.manacurenta + ", " + "A: " + map.caractercrt.abilitati + ", P: " + map.caractercrt.inventar.potiuni + ";\n");
                    System.out.println("Detalii inamic: " + " V: " + inamic.viatacurenta + ", M: " + inamic.manacurenta + ", A: " + inamic.abilitati + ";");
                    System.out.println("Introduceti ce tip de abilitate/potiune doriti: ");
                    String abilitate = sc.nextLine();
                    if (abilitate.equals("P")) {
                        while (map.caractercrt.abilitati.isEmpty() == false) {
                            inamic.accept(map.caractercrt.abilitati.get(0));
                            map.caractercrt.abilitati.remove(0);
                            Random rand = new Random();
                            int abilitate_inamic = rand.nextInt(2);
                            if (inamic.abilitati.isEmpty()) {
                                abilitate_inamic = 0;
                            }
                            if (abilitate_inamic == 0) {
                                map.caractercrt.receiveDamage(inamic.getDamage());
                            } else {
                                int nr = inamic.abilitati.size();
                                Random newrand = new Random();
                                int abilitate_random = newrand.nextInt(nr);
                                inamic.abilitati.get(abilitate_random).set();
                                if(inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                    //inamic.abilitati.get(abilitate_random).visit(map.caractercrt);
                                    map.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                    inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                    inamic.abilitati.remove(abilitate_random);
                                }
                                else
                                {
                                    System.out.println("Inamicul nu are suficienta mana.");
                                }
                            }
                        }
                        inamic.receiveDamage(map.caractercrt.getDamage());
                        Random rand = new Random();
                        int abilitate_inamic = rand.nextInt(2);
                        if (inamic.abilitati.isEmpty()) {
                            abilitate_inamic = 0;
                        }
                        if (abilitate_inamic == 0) {
                            map.caractercrt.receiveDamage(inamic.getDamage());
                        } else {
                            int nr = inamic.abilitati.size();
                            Random newrand = new Random();
                            int abilitate_random = newrand.nextInt(nr);
                            inamic.abilitati.get(abilitate_random).set();
                            if(inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                //inamic.abilitati.get(abilitate_random).visit(map.caractercrt);
                                map.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                inamic.abilitati.remove(abilitate_random);
                            }
                            else
                            {
                                System.out.println("Inamicul nu are suficienta mana.");
                            }
                        }
                        if (map.caractercrt.inventar.potiuni.isEmpty()) {
                            System.out.println("Nu aveti potiuni.");
                            continue;
                        }
                        int ok = -1;
                        for (int i = 0; i < map.caractercrt.inventar.potiuni.size(); i++)
                            if (map.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion"))
                                ok = i;
                        if (ok == -1)
                            System.out.println("Nu aveti destule potiuni.");
                        else {
                            map.caractercrt.folosirepotiune(map.caractercrt.inventar.potiuni.get(ok));
                            System.out.println("Ati folosit o potiune pentru viata.");
                        }

                        ok = -1;
                        for (int i = 0; i < map.caractercrt.inventar.potiuni.size(); i++)
                            if (map.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("ManaPotion"))
                                ok = i;
                        if (ok == -1)
                            System.out.println("Nu aveti destule potiuni.");
                        else {
                            map.caractercrt.folosirepotiune(map.caractercrt.inventar.potiuni.get(ok));
                            System.out.println("Ati folosit o potiune pentru mana.");
                        }
                    }
                    }
                if (map.caractercrt.viatacurenta <= 0)
                    System.out.println("Ai pierdut! Aduna monede si incearca mai tarziu.");
                else {
                    Random randmonede = new Random();
                    Random randsansa = new Random();
                    Random experienta = new Random();
                    int sansa = randsansa.nextInt(5);
                    int monede = randmonede.nextInt(100);
                    if(sansa != 0 && monede != 0) {
                        System.out.println("Felicitari, ai infrant inamicul si ai primit " + monede + " monede!");
                        map.caractercrt.inventar.NrMonede += monede;
                    }
                    else{
                        System.out.println("Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.");
                    }
                    map.caractercrt.experienta += experienta.nextInt(70);
                    if(map.caractercrt.experienta >= 100) {
                        map.caractercrt.nivel ++;
                        map.caractercrt.experienta -= 100;
                        map.caractercrt.upgrade();
                    }
                    map.goSouth();
                }
                System.out.println("\nCaracterul tau dupa ultima lupta:\n" + map.caractercrt);
            }
            else
            if(map.celulacrt.element.toCharacter() == 'E' && map.celulacrt.vizitat == true){
                System.out.println("Ai mai luptat deja cu acest inamic.");
            }
            map.celulacrt.vizitat = true;
            map.afisare();
            if(map.celulacrt.element.toCharacter() != 'F' && map.caractercrt.viatacurenta > 0) {
                System.out.println("Introduceti o comanda: ");
                comanda = sc.nextLine();
                if (comanda.equals("P")) {
                    map.goEast();
                    map.celulacrt.vizitat = true;
                    map.goSouth();
                    map.celulacrt.vizitat = true;
                    map.goSouth();
                    map.celulacrt.vizitat = true;
                    map.goSouth();
                }
            }
        }
    }

    void parsareconturi() {
        File input = new File("C:\\Facultate\\An2sem1\\POO\\Tema\\src\\Joculet\\accounts.json");
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonArray jsonArrayofAccounts = fileObject.get("accounts").getAsJsonArray();
            for (JsonElement AccountElem : jsonArrayofAccounts) {
                String email, parola;
                JsonObject accelm = AccountElem.getAsJsonObject();
                String name = accelm.get("name").getAsString();
                String country = accelm.get("country").getAsString();
                int maps_completed = accelm.get("maps_completed").getAsInt();
                JsonObject jsonOBjectofCredentials = accelm.get("credentials").getAsJsonObject();
                email = jsonOBjectofCredentials.get("email").getAsString();
                parola = jsonOBjectofCredentials.get("password").getAsString();
                JsonArray favorite_games = accelm.get("favorite_games").getAsJsonArray();
                TreeSet<String> namefv = new TreeSet<>();
                for (JsonElement fvgames : favorite_games) {
                    //JsonObject fvgamesobj = fvgames.getAsJsonObject();
                    namefv.add(fvgames.toString());
                }
                JsonArray characters = accelm.get("characters").getAsJsonArray();
                ArrayList<Character> caractere = new ArrayList<>();
                for (JsonElement chiterator : characters) {
                    JsonObject ch = chiterator.getAsJsonObject();
                    String numecaract = ch.get("name").getAsString();
                    String profesie = ch.get("profession").getAsString();
                    int level = ch.get("level").getAsInt();
                    int experience = ch.get("experience").getAsInt();
                    caractere.add(CharacterFactory.factory(profesie, numecaract, level, experience));
                }
                Credentials credentialecurente = new Credentials(email, parola);
                Account.Information.InformationBuilder bldcurent = new Account.Information.InformationBuilder(credentialecurente, name);
                bldcurent.settara(country);
                bldcurent.setjocuripreferate(namefv);
                Account.Information infocurent = bldcurent.bld();
                Account contcurent = new Account(infocurent, caractere, maps_completed);
                conturi.add(contcurent);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String parsarepovesti(Cell cell) {
        File input = new File("C:\\Facultate\\An2sem1\\POO\\Tema\\src\\Joculet\\stories.json");
        ArrayList<String> empty = new ArrayList<>();
        ArrayList<String> shops = new ArrayList<>();
        ArrayList<String> enemy = new ArrayList<>();
        ArrayList<String> finish = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonArray jsonArrayofStories = fileObject.get("stories").getAsJsonArray();
            for (JsonElement AccountElem : jsonArrayofStories){
                String type, value;
                JsonObject storieselem = AccountElem.getAsJsonObject();
                type = storieselem.get("type").getAsString();
                value = storieselem.get("value").getAsString();
                if(type.equals("EMPTY"))
                    empty.add(value);
                if(type.equals("SHOP"))
                    shops.add(value);
                if(type.equals("ENEMY"))
                    enemy.add(value);
                if(type.equals("FINISH"))
                    finish.add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        dictionar.put(Cell.tip.EMPTY, empty);
        dictionar.put(Cell.tip.ENEMY, enemy);
        dictionar.put(Cell.tip.SHOP, shops);
        dictionar.put(Cell.tip.FINISH, finish);
        if(cell.element.toCharacter() == 'S'){
            Random rand = new Random();
            return dictionar.get(Cell.tip.SHOP).get(rand.nextInt(dictionar.get(Cell.tip.SHOP).size()));
        }
        if(cell.element.toCharacter() == 'N'){
            Random rand = new Random();
            return dictionar.get(Cell.tip.EMPTY).get(rand.nextInt(dictionar.get(Cell.tip.EMPTY).size()));
        }
        if( cell.element.toCharacter() == 'E'){
            Random rand = new Random();
            return dictionar.get(Cell.tip.ENEMY).get(rand.nextInt(dictionar.get(Cell.tip.ENEMY).size()));
        }
        if( cell.element.toCharacter() == 'F'){
            Random rand = new Random();
            return dictionar.get(Cell.tip.FINISH).get(rand.nextInt(dictionar.get(Cell.tip.FINISH).size()));
        }
        return "Celula este vizitata, deci a fost transmisa o poveste.";
    }
}

