package Joculet;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Joc {
    int nrinamici = 0;

    public Joc(int indice, Grid mapa) {
        JFrame frame = new JFrame("World of Marcel");
        //frame.getContentPane().setBackground((new Color(0, 45, 106)));
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        panel.setLayout(null);
        panel.setBackground((new Color(0, 117, 126)));


        java.net.URL imgUrl = getClass().getResource("world of marcel.png");
        ImageIcon icon = new ImageIcon(imgUrl);
        Image Image = icon.getImage();
        frame.setIconImage(Image);

        mapa.caractercrt.upgrade();
        for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
            mapa.caractercrt.abilitati.get(i).set();
        JPanel panel2 = new JPanel();
        JTextField nume = new JTextField(15);
        nume.setText("NUME: " + mapa.caractercrt.nume);
        nume.setForeground(Color.BLACK);
        nume.setEditable(false);
        nume.setBackground(new Color(145, 140, 145));
        panel2.add(nume);
        JTextField viata = new JTextField(15);
        viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
        viata.setForeground(Color.RED);
        viata.setEditable(false);
        viata.setBackground(new Color(145, 140, 145));
        panel2.add(viata);
        JTextField exp = new JTextField(15);
        exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
        exp.setForeground(Color.YELLOW);
        exp.setEditable(false);
        exp.setBackground(new Color(145, 140, 145));
        panel2.add(exp);
        JTextField nivel = new JTextField(15);
        nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
        nivel.setForeground(Color.green);
        nivel.setEditable(false);
        nivel.setBackground(new Color(145, 140, 145));
        panel2.add(nivel);
        JTextField mana = new JTextField(15);
        mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
        mana.setForeground(Color.BLUE);
        mana.setEditable(false);
        mana.setBackground(new Color(145, 140, 145));
        panel2.add(mana);
        JTextField putere = new JTextField(15);
        putere.setText("\nPUTERE: " + mapa.caractercrt.putere);
        putere.setForeground(Color.BLACK);
        putere.setEditable(false);
        putere.setBackground(new Color(145, 140, 145));
        panel2.add(putere);
        JTextField car = new JTextField(15);
        car.setText("\nCARISMA: " + mapa.caractercrt.carisma);
        car.setForeground(Color.BLACK);
        car.setEditable(false);
        car.setBackground(new Color(145, 140, 145));
        panel2.add(car);
        JTextField dex = new JTextField(15);
        dex.setText("\nDEXTERITATE: " + mapa.caractercrt.dexteritate);
        dex.setForeground(Color.BLACK);
        dex.setBackground(new Color(145, 140, 145));
        panel2.add(dex);
        dex.setEditable(false);
        JTextField monede = new JTextField(15);
        monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
        monede.setForeground(Color.BLACK);
        monede.setEditable(false);
        monede.setBackground(new Color(145, 140, 145));
        panel2.add(monede);
        JTextField greutater = new JTextField(15);
        greutater.setText("\nGreutateR: " + mapa.caractercrt.inventar.calculgreutate());
        greutater.setForeground(Color.BLACK);
        greutater.setEditable(false);
        greutater.setBackground(new Color(145, 140, 145));
        panel2.add(greutater);
        JTextField imune = new JTextField(15);
        imune.setText("\nIMUN->EARTH: " + mapa.caractercrt.earth);
        imune.setForeground(Color.BLACK);
        imune.setEditable(false);
        imune.setBackground(new Color(145, 140, 145));
        panel2.add(imune);
        JTextField imunf = new JTextField(15);
        imunf.setText("\nIMUN->FIRE: " + mapa.caractercrt.fire);
        imunf.setForeground(Color.BLACK);
        imunf.setEditable(false);
        panel2.add(imunf);
        imunf.setBackground(new Color(145, 140, 145));
        JTextField imuni = new JTextField(15);
        imuni.setText("\nIMUN->ICE: " + mapa.caractercrt.ice);
        imuni.setForeground(Color.BLACK);
        imuni.setEditable(false);
        imuni.setBackground(new Color(145, 140, 145));
        panel2.add(imuni);

        panel2.setBackground(new Color(145, 140, 145));
        panel2.setLayout(new GridLayout(13, 1));

        Object[][] map = new Object[mapa.lungime][mapa.latime];
        String[] coloane = new String[mapa.latime];
        for (int i = 0; i < mapa.latime; i++)
            coloane[i] = "";
        for (int i = 0; i < mapa.lungime; i++)
            for (int j = 0; j < mapa.latime; j++) {
                if (mapa.get(i).get(j).vizitat == false) {
                    map[i][j] = new ImageIcon("src/Joculet/question-mark.png");
                } else {
                    if (mapa.get(i).get(j).element.toCharacter() == 'F')
                        map[i][j] = new ImageIcon("src/Joculet/finish.png");
                    else if (mapa.get(i).get(j).element.toCharacter() == 'S')
                        map[i][j] = new ImageIcon("src/Joculet/shopp.png");
                    else if (mapa.get(i).get(j).element.toCharacter() == 'E')
                        map[i][j] = new ImageIcon("src/Joculet/poison.png");
                    else if (mapa.get(i).get(j).element.toCharacter() == 'N')
                        map[i][j] = new ImageIcon("src/Joculet/n.png");
                    else {
                        mapa.celulacrt = mapa.get(i).get(j);
                        map[i][j] = new ImageIcon("src/Joculet/man.png");
                    }
                }
            }
        DefaultTableModel tableModel = new DefaultTableModel(map, coloane);
        JTable table = new JTable(tableModel) {
            public Class getColumnClass(int coloana) {
                return Icon.class;
            }
        };
        table.setLayout(null);
        table.setBounds(0, 0, 430, 900);
        table.setRowHeight(60);
        table.setMinimumSize(new Dimension(430, 900));
        table.setBackground(new Color(195, 195, 195));
        frame.add(table);

        JButton nord = new JButton();
        nord.setIcon(new ImageIcon("src/Joculet/north.png"));
        nord.setBounds(520, 5, 36, 58);
        nord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox).Oy - 1 < 0)
                    JOptionPane.showMessageDialog(frame, "Nu te poti deplasa spre nord!",
                            "EROARE", JOptionPane.ERROR_MESSAGE);
                else {
                    if (mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox).element.toCharacter() == 'F')
                        table.setValueAt(new ImageIcon("src/Joculet/finish.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    else if (mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox).element.toCharacter() == 'S')
                        table.setValueAt(new ImageIcon("src/Joculet/shopp.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    else if (mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox).element.toCharacter() == 'E')
                        table.setValueAt(new ImageIcon("src/Joculet/poison.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    else
                        table.setValueAt(new ImageIcon("src/Joculet/n.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    mapa.celulacrt = mapa.get(mapa.celulacrt.Oy - 1).get(mapa.celulacrt.Ox);
                    table.setValueAt(new ImageIcon("src/Joculet/man.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    mapa.caractercrt.Ox = mapa.celulacrt.Ox;
                    mapa.caractercrt.Oy = mapa.celulacrt.Oy;
                    if (mapa.celulacrt.vizitat == false) {
                        JOptionPane.showMessageDialog(frame, Game.parsarepovesti(mapa.celulacrt),
                                "POVESTE", JOptionPane.INFORMATION_MESSAGE);
                        Random rand = new Random();
                        int mon;
                        if (rand.nextInt(5) == 1) {
                            mon = rand.nextInt(50);
                            mapa.caractercrt.inventar.NrMonede += mon;
                            JOptionPane.showMessageDialog(frame, "Felicitari! Ai gasit o comoara cu : " + mon
                                            + " monede.",
                                    "COMOARA NEDESCOPERITA", JOptionPane.INFORMATION_MESSAGE);
                            monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                        }
                    }
                    if (mapa.celulacrt.element.toCharacter() == 'S') {
                        JFrame fr = new JFrame("Magazin");
                        Shop sh = (Shop) mapa.celulacrt.element;
                        fr.setMinimumSize(new Dimension(600, 400));
                        fr.getContentPane().setBackground(new Color(153, 132, 111));
                        ImageIcon login = new ImageIcon(getClass().getResource("shopare.png"));
                        Image img = login.getImage();
                        Image imgscale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        login = new ImageIcon(imgscale);
                        JLabel image = new JLabel();
                        image.setBounds(5, 60, 120, 120);
                        image.setIcon(login);
                        DefaultListModel listaM = new DefaultListModel();
                        for (int i = 0; i < sh.potiuni.size(); i++)
                            listaM.add(i, sh.potiuni.get(i).toString());
                        JList<String> lista = new JList<>(listaM);
                        lista.setLayoutOrientation(JList.VERTICAL_WRAP);
                        lista.setVisibleRowCount(sh.potiuni.size());
                        JButton cumpara = new JButton("Cumpara");
                        JButton iesire = new JButton("Iesire");
                        JPanel PanelShop = new JPanel();
                        PanelShop.setBackground(new Color(153, 132, 111));
                        PanelShop.add(lista);
                        PanelShop.setBounds(150, 140, 400, 200);
                        iesire.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                greutater.setText("\nGreutateR: " + mapa.caractercrt.inventar.calculgreutate());
                                monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                fr.dispose();
                            }
                        });
                        cumpara.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (lista.isSelectionEmpty()) {
                                    return;
                                }
                                int index = lista.getSelectedIndex();
                                if (index == -1)
                                    JOptionPane.showMessageDialog(frame, "Nu ai selectat nicio potiune!",
                                            "EROARE", JOptionPane.ERROR_MESSAGE);
                                else if (mapa.caractercrt.inventar.NrMonede >= sh.potiuni.get(index).pret() && mapa.caractercrt.inventar.calculgreutate() >= sh.potiuni.get(index).greutate()) {
                                    mapa.caractercrt.inventar.potiuni.add(sh.potiuni.get(index));
                                    mapa.caractercrt.inventar.NrMonede -= sh.potiuni.get(index).pret();
                                    sh.potiuni.remove(index);
                                    listaM.remove(index);
                                    //lista.remove(index);
                                    greutater.setText("\nGreutateR: " + mapa.caractercrt.inventar.calculgreutate());
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                } else
                                    JOptionPane.showMessageDialog(frame, "Nu aveti destule monede sau inventarul este plin!",
                                            "EROARE", JOptionPane.ERROR_MESSAGE);
                                fr.revalidate();
                                fr.repaint();
                            }
                        });
                        PanelShop.add(cumpara);
                        PanelShop.add(iesire);
                        fr.add(PanelShop);
                        fr.add(image);
                        fr.pack();
                        fr.show();
                    }
                    if (mapa.celulacrt.element.toCharacter() == 'E' && mapa.celulacrt.vizitat == false) {
                        JFrame fr = new JFrame();
                        Enemy inamic = (Enemy) mapa.celulacrt.element;
                        fr.setMinimumSize(new Dimension(1000, 800));
                        ImageIcon you = new ImageIcon(getClass().getResource("you.png"));
                        Image img = you.getImage();
                        Image imgscale = img.getScaledInstance(120, 60, Image.SCALE_SMOOTH);
                        you = new ImageIcon(imgscale);
                        JLabel image = new JLabel();
                        image.setBounds(5, 10, 120, 60);
                        image.setIcon(you);
                        JPanel caracter = new JPanel();
                        caracter.setLayout(new GridLayout(13, 1));
                        caracter.add(image);
                        caracter.add(nume);
                        JTextField viata1 = new JTextField(15);
                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                        viata1.setForeground(Color.RED);
                        viata1.setEditable(false);
                        viata1.setBackground(new Color(145, 140, 145));
                        caracter.add(viata1);
                        JTextField vmax = new JTextField(15);
                        vmax.setText("\nVIATA MAXIMA: " + mapa.caractercrt.viatamaxima);
                        vmax.setForeground(Color.BLUE);
                        vmax.setEditable(false);
                        vmax.setBackground(new Color(145, 140, 145));
                        JTextField manamax = new JTextField(15);
                        manamax.setText("\nMANA MAXIMA: " + mapa.caractercrt.manamaxima);
                        manamax.setForeground(Color.YELLOW);
                        manamax.setEditable(false);
                        manamax.setBackground(new Color(145, 140, 145));
                        JTextField abilities = new JTextField(20);
                        String superputeri = new String();
                        superputeri = "ABILITATI: ";
                        for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
                            superputeri += mapa.caractercrt.abilitati.get(i).getClass().getSimpleName() + "; ";
                        abilities.setText(superputeri);
                        abilities.setForeground(Color.GREEN);
                        abilities.setEditable(false);
                        abilities.setBackground(new Color(145, 150, 145));
                        JTextField potions = new JTextField(15);
                        String potiuni = new String();
                        potiuni = "POTIUNI: ";
                        for (int i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++)
                            if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion"))
                                potiuni += "VIATA ; ";
                            else
                                potiuni += "MANA ; ";
                        potions.setText(potiuni);
                        potions.setForeground(new Color(120, 33, 78));
                        potions.setEditable(false);
                        potions.setBackground(new Color(145, 150, 145));
                        JTextField mana1 = new JTextField(15);
                        mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                        mana1.setForeground(Color.BLUE);
                        mana1.setEditable(false);
                        mana1.setBackground(new Color(145, 140, 145));
                        caracter.add(mana1);
                        caracter.add(manamax);
                        caracter.add(putere);
                        caracter.add(dex);
                        caracter.add(car);
                        caracter.add(potions);
                        caracter.add(abilities);
                        caracter.add(imune);
                        caracter.add(imunf);
                        caracter.add(imuni);

                        JTextField vinamic = new JTextField(15);
                        vinamic.setText("\nHP: " + inamic.viatacurenta);
                        vinamic.setForeground(Color.RED);
                        vinamic.setEditable(false);
                        vinamic.setBackground(new Color(145, 140, 145));
                        JTextField minamic = new JTextField(15);
                        minamic.setText("\nMANA: " + inamic.manacurenta);
                        minamic.setForeground(Color.BLUE);
                        minamic.setEditable(false);
                        minamic.setBackground(new Color(145, 140, 145));
                        JTextField imunf1 = new JTextField(15);
                        imunf1.setText("\nIMUN->FIRE: " + inamic.fire);
                        imunf1.setForeground(Color.BLACK);
                        imunf1.setEditable(false);
                        imunf1.setBackground(new Color(145, 140, 145));
                        JTextField imune1 = new JTextField(15);
                        imune1.setText("\nIMUN->EARTH: " + inamic.earth);
                        imune1.setForeground(Color.BLACK);
                        imune1.setEditable(false);
                        imune1.setBackground(new Color(145, 140, 145));
                        JTextField imuni1 = new JTextField(15);
                        imuni1.setText("\nIMUN->ICE: " + inamic.ice);
                        imuni1.setForeground(Color.BLACK);
                        imuni1.setEditable(false);
                        imuni1.setBackground(new Color(145, 140, 145));
                        JTextField abilitati = new JTextField(15);
                        String abil = new String();
                        for (int i = 0; i < inamic.abilitati.size(); i++)
                            abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                        abilitati.setText(abil);
                        abilitati.setForeground(Color.BLACK);
                        abilitati.setEditable(false);
                        abilitati.setBackground(new Color(145, 150, 145));
                        JPanel enemy = new JPanel();
                        ImageIcon in = new ImageIcon(getClass().getResource("enemyy.png"));
                        Image img1 = in.getImage();
                        Image imgscale1 = img1.getScaledInstance(120, 80, Image.SCALE_SMOOTH);
                        in = new ImageIcon(imgscale1);
                        JLabel image1 = new JLabel();
                        image1.setBounds(5, 10, 120, 60);
                        image1.setIcon(in);
                        enemy.setLayout(new GridLayout(7, 1));
                        enemy.add(image1);
                        enemy.add(vinamic);
                        enemy.add(minamic);
                        enemy.add(abilitati);
                        enemy.add(imune1);
                        enemy.add(imunf1);
                        enemy.add(imuni1);

                        Icon ear = new ImageIcon("src/Joculet/earthh.png");
                        Icon fir = new ImageIcon("src/Joculet/fire.png");
                        Icon ic = new ImageIcon("src/Joculet/snowflake1.png");
                        JButton atac = new JButton("Atac normal.");
                        atac.setBounds(80, 50, 360, 50);
                        JButton earth = new JButton(ear);
                        earth.setBounds(80, 100, 360, 50);
                        JButton fire = new JButton(fir);
                        fire.setBounds(80, 150, 360, 50);
                        JButton ice = new JButton(ic);
                        ice.setBounds(80, 200, 360, 50);
                        JButton vit = new JButton("Adauga viata.");
                        vit.setBounds(80, 250, 360, 50);
                        JButton mn = new JButton("Adauga mana.");
                        mn.setBounds(80, 300, 360, 50);
                        JPanel butoane = new JPanel();
                        butoane.setLayout(null);
                        butoane.setBounds(350, 20, 60, 400);
                        butoane.add(atac);
                        atac.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int viataprecin = inamic.viatacurenta;
                                inamic.receiveDamage(mapa.caractercrt.getDamage());
                                if (viataprecin == inamic.viatacurenta) {
                                    JOptionPane.showMessageDialog(fr, "Ati ratat atacul.", "ATAC",
                                            JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    int temp = inamic.viatacurenta - viataprecin;
                                    JOptionPane.showMessageDialog(fr, "Ati reusit sa dati " + temp + " damage.",
                                            "ATAC",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    vinamic.setText("\nHP: " + inamic.viatacurenta);
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    inamic.viatacurenta = 0;
                                } else {
                                    Random rand = new Random();
                                    int abilitate_inamic = rand.nextInt(2);
                                    if (inamic.abilitati.isEmpty()) {
                                        abilitate_inamic = 0;
                                    }
                                    if (abilitate_inamic == 0) {
                                        int viataprec = mapa.caractercrt.viatacurenta;
                                        mapa.caractercrt.receiveDamage(inamic.getDamage());
                                        if (viataprec == mapa.caractercrt.viatacurenta) {
                                            JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.", "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            int temp = mapa.caractercrt.viatacurenta - viataprec;
                                            JOptionPane.showMessageDialog(fr, "Inamicul a dat " + temp + " damage.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                        }
                                    } else {
                                        int nr = inamic.abilitati.size();
                                        Random newrand = new Random();
                                        int abilitate_random = newrand.nextInt(nr);
                                        inamic.abilitati.get(abilitate_random).set();
                                        int temp = mapa.caractercrt.viatacurenta;
                                        if (inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                            mapa.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                            if (temp == mapa.caractercrt.viatacurenta)
                                                JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.",
                                                        "ATAC",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                            else {
                                                int calc = mapa.caractercrt.viatacurenta - temp;
                                                JOptionPane.showMessageDialog(fr, "Inamicul a dat " + calc + " damage.",
                                                        "ATAC",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                                viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                            }
                                        }
                                        viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                        inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                        minamic.setText("\nMANA: " + inamic.manacurenta);
                                        inamic.abilitati.remove(abilitate_random);
                                        String abil = new String();
                                        for (int i = 0; i < inamic.abilitati.size(); i++)
                                            abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                        abilitati.setText(abil);

                                    }
                                }
                                if (mapa.caractercrt.viatacurenta <= 0) {
                                    JOptionPane.showMessageDialog(fr, "Ai pierdut! Dar totusi ai infrant " + nrinamici + " inamici.\n Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                            "GAME OVER", JOptionPane.ERROR_MESSAGE);
                                    fr.dispose();
                                    frame.dispose();
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    fr.dispose();
                                    Random randmonede = new Random();
                                    Random randsansa = new Random();
                                    Random experienta = new Random();
                                    int sansa = randsansa.nextInt(5);
                                    int monede1 = randmonede.nextInt(100);
                                    if (sansa != 0 && monede1 != 0) {
                                        JOptionPane.showMessageDialog(frame, "Felicitari, ai infrant inamicul si ai primit monede!", "WIN",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        mapa.caractercrt.inventar.NrMonede += monede1;
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.",
                                                "GHINION", JOptionPane.ERROR_MESSAGE);
                                    }
                                    mapa.caractercrt.experienta += experienta.nextInt(70);
                                    if (mapa.caractercrt.experienta >= 100) {
                                        mapa.caractercrt.nivel++;
                                        mapa.caractercrt.experienta -= 100;
                                        mapa.caractercrt.upgrade();
                                    }
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
                                    nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                }
                            }
                        });
                        vit.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int ok = 0, i;
                                for (i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++) {
                                    if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion")) {
                                        ok = 1;
                                        mapa.caractercrt.folosirepotiune(mapa.caractercrt.inventar.potiuni.get(i));
                                        viata1.setText("\nHP:" + mapa.caractercrt.viatacurenta);
                                        break;
                                    }
                                }
                                if (ok == 0) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti potiune de viata.", "HealthPotion",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(fr, "Ati folosit cu succes potiune de viata.", "HealthPotion",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                                String potiuni = "POTIUNI: ";
                                for (i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++)
                                    if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion"))
                                        potiuni += "VIATA ; ";
                                    else
                                        potiuni += "MANA ; ";
                                potions.setText(potiuni);
                                viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                            }
                        });
                        mn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int ok = 0, i;
                                for (i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++) {
                                    if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("ManaPotion")) {
                                        ok = 1;
                                        mapa.caractercrt.folosirepotiune(mapa.caractercrt.inventar.potiuni.get(i));
                                        mana.setText("\nMANA:" + mapa.caractercrt.manacurenta);
                                        mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                        break;
                                    }
                                }
                                if (ok == 0) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti potiune de mana.", "ManaPotion",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(fr, "Ati folosit cu succes potiune de mana.", "ManaPotion",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                                String potiuni = "POTIUNI: ";
                                for (i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++)
                                    if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion"))
                                        potiuni += "VIATA ; ";
                                    else
                                        potiuni += "MANA ; ";
                                potions.setText(potiuni);
                                mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                            }
                        });
                        earth.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int viataprecin = inamic.viatacurenta;
                                if (mapa.caractercrt.manacurenta < 60) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti destula mana.", "MANA",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    int ok = 0;
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++) {
                                        if (mapa.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Earth")) {
                                            ok = 1;
                                            inamic.accept(mapa.caractercrt.abilitati.get(i));
                                            System.out.println(mapa.caractercrt.abilitati.get(i).CostMana + "\n");
                                            mapa.caractercrt.manacurenta -= mapa.caractercrt.abilitati.get(i).CostMana;
                                            mapa.caractercrt.abilitati.remove(i);
                                            String abil = "ABILITATI: ";
                                            for (int j = 0; j < mapa.caractercrt.abilitati.size(); j++)
                                                abil += mapa.caractercrt.abilitati.get(j).getClass().getSimpleName() + " ; ";
                                            abilities.setText(abil);
                                            mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                            break;
                                        }
                                    }
                                    if (ok == 0) {
                                        JOptionPane.showMessageDialog(fr, "Nu exista aceasta abilitate.", "EARTH",
                                                JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        if (viataprecin == inamic.viatacurenta) {
                                            JOptionPane.showMessageDialog(fr, "Ati ratat abilitatea EARTH.", "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            int temp = inamic.viatacurenta - viataprecin;
                                            JOptionPane.showMessageDialog(fr, "Ati reusit sa dati " + temp + " damage.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            vinamic.setText("\nHP: " + inamic.viatacurenta);
                                        }
                                        if (inamic.viatacurenta <= 0) {
                                            nrinamici++;
                                            inamic.viatacurenta = 0;
                                        } else {
                                            Random rand = new Random();
                                            int abilitate_inamic = rand.nextInt(2);
                                            if (inamic.abilitati.isEmpty()) {
                                                abilitate_inamic = 0;
                                            }
                                            if (abilitate_inamic == 0) {
                                                int viataprec = mapa.caractercrt.viatacurenta;
                                                mapa.caractercrt.receiveDamage(inamic.getDamage());
                                                if (viataprec == mapa.caractercrt.viatacurenta) {
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.", "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                } else {
                                                    int temp = mapa.caractercrt.viatacurenta - viataprec;
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a dat " + temp + " damage.",
                                                            "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                    viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                }
                                            } else {
                                                int nr = inamic.abilitati.size();
                                                Random newrand = new Random();
                                                int abilitate_random = newrand.nextInt(nr);
                                                inamic.abilitati.get(abilitate_random).set();
                                                int temp = mapa.caractercrt.viatacurenta;
                                                if (inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                                    mapa.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                                    if (temp == mapa.caractercrt.viatacurenta)
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    else {
                                                        int calc = mapa.caractercrt.viatacurenta - temp;
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a dat " + calc + " damage.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                    }
                                                }
                                                viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                                minamic.setText("\nMANA: " + inamic.manacurenta);
                                                inamic.abilitati.remove(abilitate_random);
                                                String abil = new String();
                                                for (int i = 0; i < inamic.abilitati.size(); i++)
                                                    abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                                abilitati.setText(abil);
                                            }
                                        }
                                    }
                                }
                                if (mapa.caractercrt.viatacurenta <= 0) {
                                    JOptionPane.showMessageDialog(fr, "Ai pierdut! Dar totusi ai infrant " + nrinamici + " inamici.\n Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                            "GAME OVER", JOptionPane.ERROR_MESSAGE);
                                    fr.dispose();
                                    frame.dispose();
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    fr.dispose();
                                    Random randmonede = new Random();
                                    Random randsansa = new Random();
                                    Random experienta = new Random();
                                    int sansa = randsansa.nextInt(5);
                                    int monede1 = randmonede.nextInt(100);
                                    if (sansa != 0 && monede1 != 0) {
                                        JOptionPane.showMessageDialog(frame, "Felicitari, ai infrant inamicul si ai primit monede!", "WIN",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        mapa.caractercrt.inventar.NrMonede += monede1;
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.",
                                                "GHINION", JOptionPane.ERROR_MESSAGE);
                                    }
                                    mapa.caractercrt.experienta += experienta.nextInt(70);
                                    if (mapa.caractercrt.experienta >= 100) {
                                        mapa.caractercrt.nivel++;
                                        mapa.caractercrt.experienta -= 100;
                                        mapa.caractercrt.upgrade();
                                    }
                                    String abil = new String();
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
                                        abil += mapa.caractercrt.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                    abilities.setText(abil);
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
                                    nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                    mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                }
                            }
                        });
                        fire.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int viataprecin = inamic.viatacurenta;
                                if (mapa.caractercrt.manacurenta < 55) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti destula mana.", "MANA",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    int ok = 0;
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++) {
                                        if (mapa.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Fire")) {
                                            ok = 1;
                                            inamic.accept(mapa.caractercrt.abilitati.get(i));
                                            mapa.caractercrt.manacurenta -= mapa.caractercrt.abilitati.get(i).CostMana;
                                            mapa.caractercrt.abilitati.remove(i);
                                            String abil = new String();
                                            for (int j = 0; j < mapa.caractercrt.abilitati.size(); j++)
                                                abil += mapa.caractercrt.abilitati.get(j).getClass().getSimpleName() + " ; ";
                                            abilities.setText(abil);
                                            mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                            break;
                                        }
                                    }
                                    if (ok == 0) {
                                        JOptionPane.showMessageDialog(fr, "Nu exista aceasta abilitate.", "FIRE",
                                                JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        if (viataprecin == inamic.viatacurenta) {
                                            JOptionPane.showMessageDialog(fr, "Ati ratat abilitatea FIRE.", "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            int temp = inamic.viatacurenta - viataprecin;
                                            JOptionPane.showMessageDialog(fr, "Ati reusit sa dati " + temp + " damage.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            vinamic.setText("\nHP: " + inamic.viatacurenta);
                                        }
                                        if (inamic.viatacurenta <= 0) {
                                            nrinamici++;
                                            inamic.viatacurenta = 0;
                                        } else {
                                            Random rand = new Random();
                                            int abilitate_inamic = rand.nextInt(2);
                                            if (inamic.abilitati.isEmpty()) {
                                                abilitate_inamic = 0;
                                            }
                                            if (abilitate_inamic == 0) {
                                                int viataprec = mapa.caractercrt.viatacurenta;
                                                mapa.caractercrt.receiveDamage(inamic.getDamage());
                                                if (viataprec == mapa.caractercrt.viatacurenta) {
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.", "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                } else {
                                                    int temp = mapa.caractercrt.viatacurenta - viataprec;
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a dat " + temp + " damage.",
                                                            "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                    viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                }
                                            } else {
                                                int nr = inamic.abilitati.size();
                                                Random newrand = new Random();
                                                int abilitate_random = newrand.nextInt(nr);
                                                inamic.abilitati.get(abilitate_random).set();
                                                int temp = mapa.caractercrt.viatacurenta;
                                                if (inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                                    mapa.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                                    if (temp == mapa.caractercrt.viatacurenta)
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    else {
                                                        int calc = mapa.caractercrt.viatacurenta - temp;
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a dat " + calc + " damage.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                    }
                                                }
                                                viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                                minamic.setText("\nMANA: " + inamic.manacurenta);
                                                inamic.abilitati.remove(abilitate_random);
                                                String abil = new String();
                                                for (int i = 0; i < inamic.abilitati.size(); i++)
                                                    abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                                abilitati.setText(abil);
                                            }
                                        }
                                    }
                                }
                                if (mapa.caractercrt.viatacurenta <= 0) {
                                    JOptionPane.showMessageDialog(fr, "Ai pierdut! Dar totusi ai infrant " + nrinamici + " inamici.\n Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                            "GAME OVER", JOptionPane.ERROR_MESSAGE);
                                    fr.dispose();
                                    frame.dispose();
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    fr.dispose();
                                    Random randmonede = new Random();
                                    Random randsansa = new Random();
                                    Random experienta = new Random();
                                    int sansa = randsansa.nextInt(5);
                                    int monede1 = randmonede.nextInt(100);
                                    if (sansa != 0 && monede1 != 0) {
                                        JOptionPane.showMessageDialog(frame, "Felicitari, ai infrant inamicul si ai primit monede!", "WIN",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        mapa.caractercrt.inventar.NrMonede += monede1;
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.",
                                                "GHINION", JOptionPane.ERROR_MESSAGE);
                                    }
                                    mapa.caractercrt.experienta += experienta.nextInt(70);
                                    if (mapa.caractercrt.experienta >= 100) {
                                        mapa.caractercrt.nivel++;
                                        mapa.caractercrt.experienta -= 100;
                                        mapa.caractercrt.upgrade();
                                    }
                                    String abil = new String();
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
                                        abil += mapa.caractercrt.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                    abilities.setText(abil);
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
                                    nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                    mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                }
                            }
                        });
                        ice.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int viataprecin = inamic.viatacurenta;
                                if (mapa.caractercrt.manacurenta < 50) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti destula mana.", "MANA",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    int ok = 0;
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++) {
                                        if (mapa.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Ice")) {
                                            ok = 1;
                                            inamic.accept(mapa.caractercrt.abilitati.get(i));
                                            mapa.caractercrt.manacurenta -= mapa.caractercrt.abilitati.get(i).CostMana;
                                            mapa.caractercrt.abilitati.remove(i);
                                            String abil = "\nABILITATI: ";
                                            for (int j = 0; j < mapa.caractercrt.abilitati.size(); j++)
                                                abil += mapa.caractercrt.abilitati.get(j).getClass().getSimpleName() + " ; ";
                                            abilities.setText(abil);
                                            mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                            break;
                                        }
                                    }
                                    if (ok == 0) {
                                        JOptionPane.showMessageDialog(fr, "Nu exista aceasta abilitate.", "ICE",
                                                JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        if (viataprecin == inamic.viatacurenta) {
                                            JOptionPane.showMessageDialog(fr, "Ati ratat abilitatea ICE.", "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            int temp = inamic.viatacurenta - viataprecin;
                                            JOptionPane.showMessageDialog(fr, "Ati reusit sa dati " + temp + " damage.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            vinamic.setText("\nHP: " + inamic.viatacurenta);
                                        }
                                        if (inamic.viatacurenta <= 0) {
                                            nrinamici++;
                                            inamic.viatacurenta = 0;
                                        } else {
                                            Random rand = new Random();
                                            int abilitate_inamic = rand.nextInt(2);
                                            if (inamic.abilitati.isEmpty()) {
                                                abilitate_inamic = 0;
                                            }
                                            if (abilitate_inamic == 0) {
                                                int viataprec = mapa.caractercrt.viatacurenta;
                                                mapa.caractercrt.receiveDamage(inamic.getDamage());
                                                if (viataprec == mapa.caractercrt.viatacurenta) {
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.", "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                } else {
                                                    int temp = mapa.caractercrt.viatacurenta - viataprec;
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a dat " + temp + " damage.",
                                                            "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                    viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                }
                                            } else {
                                                int nr = inamic.abilitati.size();
                                                Random newrand = new Random();
                                                int abilitate_random = newrand.nextInt(nr);
                                                inamic.abilitati.get(abilitate_random).set();
                                                int temp = mapa.caractercrt.viatacurenta;
                                                if (inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                                    mapa.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                                    if (temp == mapa.caractercrt.viatacurenta)
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    else {
                                                        int calc = mapa.caractercrt.viatacurenta - temp;
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a dat " + calc + " damage.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                    }
                                                }
                                                viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                                minamic.setText("\nMANA: " + inamic.manacurenta);
                                                inamic.abilitati.remove(abilitate_random);
                                                String abil = new String();
                                                for (int i = 0; i < inamic.abilitati.size(); i++)
                                                    abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                                abilitati.setText(abil);
                                            }
                                        }
                                    }
                                }
                                if (mapa.caractercrt.viatacurenta <= 0) {
                                    JOptionPane.showMessageDialog(fr, "Ai pierdut! Dar totusi ai infrant " + nrinamici + " inamici.\n Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                            "GAME OVER", JOptionPane.ERROR_MESSAGE);
                                    fr.dispose();
                                    frame.dispose();
                                    ;
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    fr.dispose();
                                    Random randmonede = new Random();
                                    Random randsansa = new Random();
                                    Random experienta = new Random();
                                    int sansa = randsansa.nextInt(5);
                                    int monede1 = randmonede.nextInt(100);
                                    if (sansa != 0 && monede1 != 0) {
                                        JOptionPane.showMessageDialog(frame, "Felicitari, ai infrant inamicul si ai primit monede!", "WIN",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        mapa.caractercrt.inventar.NrMonede += monede1;
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.",
                                                "GHINION", JOptionPane.ERROR_MESSAGE);
                                    }
                                    mapa.caractercrt.experienta += experienta.nextInt(70);
                                    if (mapa.caractercrt.experienta >= 100) {
                                        mapa.caractercrt.nivel++;
                                        mapa.caractercrt.experienta -= 100;
                                        mapa.caractercrt.upgrade();
                                    }
                                    String abil = new String();
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
                                        abil += mapa.caractercrt.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                    abilities.setText(abil);
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
                                    nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                    mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                }
                            }
                        });
                        butoane.add(earth);
                        butoane.add(fire);
                        butoane.add(ice);
                        butoane.add(vit);
                        butoane.add(mn);
                        fr.add(butoane);

                        caracter.setBackground(new Color(102, 75, 75));
                        enemy.setBackground(new Color(102, 75, 75));
                        fr.add(caracter, BorderLayout.WEST);
                        fr.add(enemy, BorderLayout.EAST);
                        fr.getContentPane().setBackground(new Color(102, 75, 75));
                        fr.pack();
                        fr.show();
                    }
                    if (mapa.celulacrt.element.toCharacter() == 'E' && mapa.celulacrt.vizitat == true) {
                        JOptionPane.showMessageDialog(frame, "Ai luptat cu acest inamic deja.", "Inamic intalnit anterior",
                                JOptionPane.OK_OPTION);
                    }
                    if (mapa.celulacrt.element.toCharacter() == 'F') {
                        ImageIcon icon = new ImageIcon("src/Joculet/win.png");
                        JOptionPane.showMessageDialog(
                                null,
                                new JLabel("Felicitari! Ai ajuns la final!\nAi infrant " + nrinamici + " inamici. Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                        icon, JLabel.LEFT),
                                "GAME OVER", JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                    }
                    mapa.celulacrt.vizitat = true;
                }
            }
        });
        panel.add(nord);

        JButton sud = new JButton();
        sud.setIcon(new ImageIcon("src/Joculet/south.png"));
        sud.setBounds(520, 98, 36, 58);
        sud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox).Oy + 1 >= mapa.lungime)
                    JOptionPane.showMessageDialog(frame, "Nu te poti deplasa spre sud!",
                            "EROARE", JOptionPane.ERROR_MESSAGE);
                else {
                    if (mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox).element.toCharacter() == 'F')
                        table.setValueAt(new ImageIcon("src/Joculet/finish.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    else if (mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox).element.toCharacter() == 'S')
                        table.setValueAt(new ImageIcon("src/Joculet/shopp.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    else if (mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox).element.toCharacter() == 'E')
                        table.setValueAt(new ImageIcon("src/Joculet/poison.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    else
                        table.setValueAt(new ImageIcon("src/Joculet/n.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    mapa.celulacrt = mapa.get(mapa.celulacrt.Oy + 1).get(mapa.celulacrt.Ox);
                    table.setValueAt(new ImageIcon("src/Joculet/man.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    mapa.caractercrt.Ox = mapa.celulacrt.Ox;
                    mapa.caractercrt.Oy = mapa.celulacrt.Oy;
                    if (mapa.celulacrt.vizitat == false) {
                        JOptionPane.showMessageDialog(frame, Game.parsarepovesti(mapa.celulacrt),
                                "POVESTE", JOptionPane.INFORMATION_MESSAGE);
                        Random rand = new Random();
                        int mon;
                        if (rand.nextInt(5) == 1) {
                            mon = rand.nextInt(50);
                            mapa.caractercrt.inventar.NrMonede += mon;
                            JOptionPane.showMessageDialog(frame, "Felicitari! Ai gasit o comoara cu : " + mon
                                            + " monede.",
                                    "COMOARA NEDESCOPERITA", JOptionPane.INFORMATION_MESSAGE);
                            monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                        }
                    }
                    if (mapa.celulacrt.element.toCharacter() == 'S') {
                        JFrame fr = new JFrame("Magazin");
                        Shop sh = (Shop) mapa.celulacrt.element;
                        fr.setMinimumSize(new Dimension(600, 400));
                        fr.getContentPane().setBackground(new Color(153, 132, 111));
                        ImageIcon login = new ImageIcon(getClass().getResource("shopare.png"));
                        Image img = login.getImage();
                        Image imgscale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        login = new ImageIcon(imgscale);
                        JLabel image = new JLabel();
                        image.setBounds(5, 60, 120, 120);
                        image.setIcon(login);
                        DefaultListModel listaM = new DefaultListModel();
                        for (int i = 0; i < sh.potiuni.size(); i++)
                            listaM.add(i, sh.potiuni.get(i).toString());
                        JList<String> lista = new JList<>(listaM);
                        lista.setLayoutOrientation(JList.VERTICAL_WRAP);
                        lista.setVisibleRowCount(sh.potiuni.size());
                        JButton cumpara = new JButton("Cumpara");
                        JButton iesire = new JButton("Iesire");
                        JPanel PanelShop = new JPanel();
                        PanelShop.setBackground(new Color(153, 132, 111));
                        PanelShop.add(lista);
                        PanelShop.setBounds(150, 140, 400, 200);
                        iesire.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                greutater.setText("\nGreutateR: " + mapa.caractercrt.inventar.calculgreutate());
                                monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                fr.dispose();
                            }
                        });
                        cumpara.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (lista.isSelectionEmpty()) {
                                    return;
                                }
                                int index = lista.getSelectedIndex();
                                if (index == -1)
                                    JOptionPane.showMessageDialog(frame, "Nu ai selectat nicio potiune!",
                                            "EROARE", JOptionPane.ERROR_MESSAGE);
                                else if (mapa.caractercrt.inventar.NrMonede >= sh.potiuni.get(index).pret() && mapa.caractercrt.inventar.calculgreutate() >= sh.potiuni.get(index).greutate()) {
                                    mapa.caractercrt.inventar.potiuni.add(sh.potiuni.get(index));
                                    mapa.caractercrt.inventar.NrMonede -= sh.potiuni.get(index).pret();
                                    sh.potiuni.remove(index);
                                    listaM.remove(index);
                                    //lista.remove(index);
                                    greutater.setText("\nGreutateR: " + mapa.caractercrt.inventar.calculgreutate());
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                } else
                                    JOptionPane.showMessageDialog(frame, "Nu aveti destule monede sau inventarul este plin!",
                                            "EROARE", JOptionPane.ERROR_MESSAGE);
                                fr.revalidate();
                                fr.repaint();
                            }
                        });
                        PanelShop.add(cumpara);
                        PanelShop.add(iesire);
                        fr.add(PanelShop);
                        fr.add(image);
                        fr.pack();
                        fr.show();
                    }
                    if (mapa.celulacrt.element.toCharacter() == 'E' && mapa.celulacrt.vizitat == false) {
                        JFrame fr = new JFrame();
                        Enemy inamic = (Enemy) mapa.celulacrt.element;
                        fr.setMinimumSize(new Dimension(1000, 800));
                        ImageIcon you = new ImageIcon(getClass().getResource("you.png"));
                        Image img = you.getImage();
                        Image imgscale = img.getScaledInstance(120, 60, Image.SCALE_SMOOTH);
                        you = new ImageIcon(imgscale);
                        JLabel image = new JLabel();
                        image.setBounds(5, 10, 120, 60);
                        image.setIcon(you);
                        JPanel caracter = new JPanel();
                        caracter.setLayout(new GridLayout(13, 1));
                        caracter.add(image);
                        caracter.add(nume);
                        JTextField viata1 = new JTextField(15);
                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                        viata1.setForeground(Color.RED);
                        viata1.setEditable(false);
                        viata1.setBackground(new Color(145, 140, 145));
                        caracter.add(viata1);
                        JTextField vmax = new JTextField(15);
                        vmax.setText("\nVIATA MAXIMA: " + mapa.caractercrt.viatamaxima);
                        vmax.setForeground(Color.BLUE);
                        vmax.setEditable(false);
                        vmax.setBackground(new Color(145, 140, 145));
                        JTextField manamax = new JTextField(15);
                        manamax.setText("\nMANA MAXIMA: " + mapa.caractercrt.manamaxima);
                        manamax.setForeground(Color.YELLOW);
                        manamax.setEditable(false);
                        manamax.setBackground(new Color(145, 140, 145));
                        JTextField abilities = new JTextField(20);
                        String superputeri = new String();
                        superputeri = "ABILITATI: ";
                        for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
                            superputeri += mapa.caractercrt.abilitati.get(i).getClass().getSimpleName() + "; ";
                        abilities.setText(superputeri);
                        abilities.setForeground(Color.GREEN);
                        abilities.setEditable(false);
                        abilities.setBackground(new Color(145, 150, 145));
                        JTextField potions = new JTextField(15);
                        String potiuni = new String();
                        potiuni = "POTIUNI: ";
                        for (int i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++)
                            if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion"))
                                potiuni += "VIATA ; ";
                            else
                                potiuni += "MANA ; ";
                        potions.setText(potiuni);
                        potions.setForeground(new Color(120, 33, 78));
                        potions.setEditable(false);
                        potions.setBackground(new Color(145, 150, 145));
                        JTextField mana1 = new JTextField(15);
                        mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                        mana1.setForeground(Color.BLUE);
                        mana1.setEditable(false);
                        mana1.setBackground(new Color(145, 140, 145));
                        caracter.add(mana1);
                        caracter.add(manamax);
                        caracter.add(putere);
                        caracter.add(dex);
                        caracter.add(car);
                        caracter.add(potions);
                        caracter.add(abilities);
                        caracter.add(imune);
                        caracter.add(imunf);
                        caracter.add(imuni);

                        JTextField vinamic = new JTextField(15);
                        vinamic.setText("\nHP: " + inamic.viatacurenta);
                        vinamic.setForeground(Color.RED);
                        vinamic.setEditable(false);
                        vinamic.setBackground(new Color(145, 140, 145));
                        JTextField minamic = new JTextField(15);
                        minamic.setText("\nMANA: " + inamic.manacurenta);
                        minamic.setForeground(Color.BLUE);
                        minamic.setEditable(false);
                        minamic.setBackground(new Color(145, 140, 145));
                        JTextField imunf1 = new JTextField(15);
                        imunf1.setText("\nIMUN->FIRE: " + inamic.fire);
                        imunf1.setForeground(Color.BLACK);
                        imunf1.setEditable(false);
                        imunf1.setBackground(new Color(145, 140, 145));
                        JTextField imune1 = new JTextField(15);
                        imune1.setText("\nIMUN->EARTH: " + inamic.earth);
                        imune1.setForeground(Color.BLACK);
                        imune1.setEditable(false);
                        imune1.setBackground(new Color(145, 140, 145));
                        JTextField imuni1 = new JTextField(15);
                        imuni1.setText("\nIMUN->ICE: " + inamic.ice);
                        imuni1.setForeground(Color.BLACK);
                        imuni1.setEditable(false);
                        imuni1.setBackground(new Color(145, 140, 145));
                        JTextField abilitati = new JTextField(15);
                        String abil = new String();
                        for (int i = 0; i < inamic.abilitati.size(); i++)
                            abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                        abilitati.setText(abil);
                        abilitati.setForeground(Color.BLACK);
                        abilitati.setEditable(false);
                        abilitati.setBackground(new Color(145, 150, 145));
                        JPanel enemy = new JPanel();
                        ImageIcon in = new ImageIcon(getClass().getResource("enemyy.png"));
                        Image img1 = in.getImage();
                        Image imgscale1 = img1.getScaledInstance(120, 80, Image.SCALE_SMOOTH);
                        in = new ImageIcon(imgscale1);
                        JLabel image1 = new JLabel();
                        image1.setBounds(5, 10, 120, 60);
                        image1.setIcon(in);
                        enemy.setLayout(new GridLayout(7, 1));
                        enemy.add(image1);
                        enemy.add(vinamic);
                        enemy.add(minamic);
                        enemy.add(abilitati);
                        enemy.add(imune1);
                        enemy.add(imunf1);
                        enemy.add(imuni1);

                        Icon ear = new ImageIcon("src/Joculet/earthh.png");
                        Icon fir = new ImageIcon("src/Joculet/fire.png");
                        Icon ic = new ImageIcon("src/Joculet/snowflake1.png");
                        JButton atac = new JButton("Atac normal.");
                        atac.setBounds(80, 50, 360, 50);
                        JButton earth = new JButton(ear);
                        earth.setBounds(80, 100, 360, 50);
                        JButton fire = new JButton(fir);
                        fire.setBounds(80, 150, 360, 50);
                        JButton ice = new JButton(ic);
                        ice.setBounds(80, 200, 360, 50);
                        JButton vit = new JButton("Adauga viata.");
                        vit.setBounds(80, 250, 360, 50);
                        JButton mn = new JButton("Adauga mana.");
                        mn.setBounds(80, 300, 360, 50);
                        JPanel butoane = new JPanel();
                        butoane.setLayout(null);
                        butoane.setBounds(350, 20, 60, 400);
                        butoane.add(atac);
                        atac.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int viataprecin = inamic.viatacurenta;
                                inamic.receiveDamage(mapa.caractercrt.getDamage());
                                if (viataprecin == inamic.viatacurenta) {
                                    JOptionPane.showMessageDialog(fr, "Ati ratat atacul.", "ATAC",
                                            JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    int temp = inamic.viatacurenta - viataprecin;
                                    JOptionPane.showMessageDialog(fr, "Ati reusit sa dati " + temp + " damage.",
                                            "ATAC",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    vinamic.setText("\nHP: " + inamic.viatacurenta);
                                }
                                Random rand = new Random();
                                int abilitate_inamic = rand.nextInt(2);
                                if (inamic.abilitati.isEmpty()) {
                                    abilitate_inamic = 0;
                                }
                                if (abilitate_inamic == 0) {
                                    int viataprec = mapa.caractercrt.viatacurenta;
                                    mapa.caractercrt.receiveDamage(inamic.getDamage());
                                    if (viataprec == mapa.caractercrt.viatacurenta) {
                                        JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.", "ATAC",
                                                JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        int temp = mapa.caractercrt.viatacurenta - viataprec;
                                        JOptionPane.showMessageDialog(fr, "Inamicul a dat " + temp + " damage.",
                                                "ATAC",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    }
                                } else {
                                    int nr = inamic.abilitati.size();
                                    Random newrand = new Random();
                                    int abilitate_random = newrand.nextInt(nr);
                                    inamic.abilitati.get(abilitate_random).set();
                                    int temp = mapa.caractercrt.viatacurenta;
                                    if (inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                        mapa.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                        if (temp == mapa.caractercrt.viatacurenta)
                                            JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        else {
                                            int calc = mapa.caractercrt.viatacurenta - temp;
                                            JOptionPane.showMessageDialog(fr, "Inamicul a dat " + calc + " damage.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                        }
                                    }
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                    minamic.setText("\nMANA: " + inamic.manacurenta);
                                    inamic.abilitati.remove(abilitate_random);
                                    String abil = new String();
                                    for (int i = 0; i < inamic.abilitati.size(); i++)
                                        abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                    abilitati.setText(abil);

                                }
                                if (mapa.caractercrt.viatacurenta <= 0) {
                                    JOptionPane.showMessageDialog(fr, "Ai pierdut! Dar totusi ai infrant " + nrinamici + " inamici.\n Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                            "GAME OVER", JOptionPane.ERROR_MESSAGE);
                                    fr.dispose();
                                    frame.dispose();
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    fr.dispose();
                                    Random randmonede = new Random();
                                    Random randsansa = new Random();
                                    Random experienta = new Random();
                                    int sansa = randsansa.nextInt(5);
                                    int monede1 = randmonede.nextInt(100);
                                    if (sansa != 0 && monede1 != 0) {
                                        JOptionPane.showMessageDialog(frame, "Felicitari, ai infrant inamicul si ai primit monede!", "WIN",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        mapa.caractercrt.inventar.NrMonede += monede1;
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.",
                                                "GHINION", JOptionPane.ERROR_MESSAGE);
                                    }
                                    mapa.caractercrt.experienta += experienta.nextInt(70);
                                    if (mapa.caractercrt.experienta >= 100) {
                                        mapa.caractercrt.nivel++;
                                        mapa.caractercrt.experienta -= 100;
                                        mapa.caractercrt.upgrade();
                                    }
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
                                    nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                }
                            }
                        });
                        vit.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int ok = 0, i;
                                for (i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++) {
                                    if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion")) {
                                        ok = 1;
                                        mapa.caractercrt.folosirepotiune(mapa.caractercrt.inventar.potiuni.get(i));
                                        viata1.setText("\nHP:" + mapa.caractercrt.viatacurenta);
                                        break;
                                    }
                                }
                                if (ok == 0) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti potiune de viata.", "HealthPotion",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(fr, "Ati folosit cu succes potiune de viata.", "HealthPotion",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                                String potiuni = "POTIUNI: ";
                                for (i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++)
                                    if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion"))
                                        potiuni += "VIATA ; ";
                                    else
                                        potiuni += "MANA ; ";
                                potions.setText(potiuni);
                                viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                            }
                        });
                        mn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int ok = 0, i;
                                for (i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++) {
                                    if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("ManaPotion")) {
                                        ok = 1;
                                        mapa.caractercrt.folosirepotiune(mapa.caractercrt.inventar.potiuni.get(i));
                                        mana1.setText("\nMANA:" + mapa.caractercrt.manacurenta);
                                        break;
                                    }
                                }
                                if (ok == 0) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti potiune de mana.", "ManaPotion",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(fr, "Ati folosit cu succes potiune de mana.", "ManaPotion",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                                String potiuni = "POTIUNI: ";
                                for (i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++)
                                    if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion"))
                                        potiuni += "VIATA ; ";
                                    else
                                        potiuni += "MANA ; ";
                                potions.setText(potiuni);
                                mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                            }
                        });
                        earth.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int viataprecin = inamic.viatacurenta;
                                if (mapa.caractercrt.manacurenta < 60) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti destula mana.", "MANA",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    int ok = 0;
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++) {
                                        if (mapa.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Earth")) {
                                            ok = 1;
                                            inamic.accept(mapa.caractercrt.abilitati.get(i));
                                            System.out.println(mapa.caractercrt.abilitati.get(i).CostMana + "\n");
                                            mapa.caractercrt.manacurenta -= mapa.caractercrt.abilitati.get(i).CostMana;
                                            mapa.caractercrt.abilitati.remove(i);
                                            String abil = "ABILITATI: ";
                                            for (int j = 0; j < mapa.caractercrt.abilitati.size(); j++)
                                                abil += mapa.caractercrt.abilitati.get(j).getClass().getSimpleName() + " ; ";
                                            abilities.setText(abil);
                                            mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                            break;
                                        }
                                    }
                                    if (ok == 0) {
                                        JOptionPane.showMessageDialog(fr, "Nu exista aceasta abilitate.", "EARTH",
                                                JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        if (viataprecin == inamic.viatacurenta) {
                                            JOptionPane.showMessageDialog(fr, "Ati ratat abilitatea EARTH.", "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            int temp = inamic.viatacurenta - viataprecin;
                                            JOptionPane.showMessageDialog(fr, "Ati reusit sa dati " + temp + " damage.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            vinamic.setText("\nHP: " + inamic.viatacurenta);
                                        }
                                        if (inamic.viatacurenta <= 0) {
                                            nrinamici++;
                                            inamic.viatacurenta = 0;
                                        } else {
                                            Random rand = new Random();
                                            int abilitate_inamic = rand.nextInt(2);
                                            if (inamic.abilitati.isEmpty()) {
                                                abilitate_inamic = 0;
                                            }
                                            if (abilitate_inamic == 0) {
                                                int viataprec = mapa.caractercrt.viatacurenta;
                                                mapa.caractercrt.receiveDamage(inamic.getDamage());
                                                if (viataprec == mapa.caractercrt.viatacurenta) {
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.", "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                } else {
                                                    int temp = mapa.caractercrt.viatacurenta - viataprec;
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a dat " + temp + " damage.",
                                                            "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                    viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                }
                                            } else {
                                                int nr = inamic.abilitati.size();
                                                Random newrand = new Random();
                                                int abilitate_random = newrand.nextInt(nr);
                                                inamic.abilitati.get(abilitate_random).set();
                                                int temp = mapa.caractercrt.viatacurenta;
                                                if (inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                                    mapa.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                                    if (temp == mapa.caractercrt.viatacurenta)
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    else {
                                                        int calc = mapa.caractercrt.viatacurenta - temp;
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a dat " + calc + " damage.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                    }
                                                }
                                                viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                                minamic.setText("\nMANA: " + inamic.manacurenta);
                                                inamic.abilitati.remove(abilitate_random);
                                                String abil = new String();
                                                for (int i = 0; i < inamic.abilitati.size(); i++)
                                                    abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                                abilitati.setText(abil);
                                            }
                                        }
                                    }
                                }
                                if (mapa.caractercrt.viatacurenta <= 0) {
                                    JOptionPane.showMessageDialog(fr, "Ai pierdut! Dar totusi ai infrant " + nrinamici + " inamici.\n Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                            "GAME OVER", JOptionPane.ERROR_MESSAGE);
                                    fr.dispose();
                                    frame.dispose();
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    fr.dispose();
                                    Random randmonede = new Random();
                                    Random randsansa = new Random();
                                    Random experienta = new Random();
                                    int sansa = randsansa.nextInt(5);
                                    int monede1 = randmonede.nextInt(100);
                                    if (sansa != 0 && monede1 != 0) {
                                        JOptionPane.showMessageDialog(frame, "Felicitari, ai infrant inamicul si ai primit monede!", "WIN",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        mapa.caractercrt.inventar.NrMonede += monede1;
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.",
                                                "GHINION", JOptionPane.ERROR_MESSAGE);
                                    }
                                    mapa.caractercrt.experienta += experienta.nextInt(70);
                                    if (mapa.caractercrt.experienta >= 100) {
                                        mapa.caractercrt.nivel++;
                                        mapa.caractercrt.experienta -= 100;
                                        mapa.caractercrt.upgrade();
                                    }
                                    String abil = new String();
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
                                        abil += mapa.caractercrt.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                    abilities.setText(abil);
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
                                    nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                    mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                }
                            }
                        });
                        fire.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int viataprecin = inamic.viatacurenta;
                                if (mapa.caractercrt.manacurenta < 55) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti destula mana.", "MANA",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    int ok = 0;
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++) {
                                        if (mapa.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Fire")) {
                                            ok = 1;
                                            inamic.accept(mapa.caractercrt.abilitati.get(i));
                                            mapa.caractercrt.manacurenta -= mapa.caractercrt.abilitati.get(i).CostMana;
                                            mapa.caractercrt.abilitati.remove(i);
                                            String abil = new String();
                                            for (int j = 0; j < mapa.caractercrt.abilitati.size(); j++)
                                                abil += mapa.caractercrt.abilitati.get(j).getClass().getSimpleName() + " ; ";
                                            abilities.setText(abil);
                                            mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                            break;
                                        }
                                    }
                                    if (ok == 0) {
                                        JOptionPane.showMessageDialog(fr, "Nu exista aceasta abilitate.", "FIRE",
                                                JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        if (viataprecin == inamic.viatacurenta) {
                                            JOptionPane.showMessageDialog(fr, "Ati ratat abilitatea FIRE.", "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            int temp = inamic.viatacurenta - viataprecin;
                                            JOptionPane.showMessageDialog(fr, "Ati reusit sa dati " + temp + " damage.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            vinamic.setText("\nHP: " + inamic.viatacurenta);
                                        }
                                        if (inamic.viatacurenta <= 0) {
                                            nrinamici++;
                                            inamic.viatacurenta = 0;
                                        } else {
                                            Random rand = new Random();
                                            int abilitate_inamic = rand.nextInt(2);
                                            if (inamic.abilitati.isEmpty()) {
                                                abilitate_inamic = 0;
                                            }
                                            if (abilitate_inamic == 0) {
                                                int viataprec = mapa.caractercrt.viatacurenta;
                                                mapa.caractercrt.receiveDamage(inamic.getDamage());
                                                if (viataprec == mapa.caractercrt.viatacurenta) {
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.", "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                } else {
                                                    int temp = mapa.caractercrt.viatacurenta - viataprec;
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a dat " + temp + " damage.",
                                                            "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                    viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                }
                                            } else {
                                                int nr = inamic.abilitati.size();
                                                Random newrand = new Random();
                                                int abilitate_random = newrand.nextInt(nr);
                                                inamic.abilitati.get(abilitate_random).set();
                                                int temp = mapa.caractercrt.viatacurenta;
                                                if (inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                                    mapa.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                                    if (temp == mapa.caractercrt.viatacurenta)
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    else {
                                                        int calc = mapa.caractercrt.viatacurenta - temp;
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a dat " + calc + " damage.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                    }
                                                }
                                                viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                                minamic.setText("\nMANA: " + inamic.manacurenta);
                                                inamic.abilitati.remove(abilitate_random);
                                                String abil = new String();
                                                for (int i = 0; i < inamic.abilitati.size(); i++)
                                                    abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                                abilitati.setText(abil);
                                            }
                                        }
                                    }
                                }
                                if (mapa.caractercrt.viatacurenta <= 0) {
                                    JOptionPane.showMessageDialog(fr, "Ai pierdut! Dar totusi ai infrant " + nrinamici + " inamici.\n Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                            "GAME OVER", JOptionPane.ERROR_MESSAGE);
                                    fr.dispose();
                                    frame.dispose();
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    fr.dispose();
                                    Random randmonede = new Random();
                                    Random randsansa = new Random();
                                    Random experienta = new Random();
                                    int sansa = randsansa.nextInt(5);
                                    int monede1 = randmonede.nextInt(100);
                                    if (sansa != 0 && monede1 != 0) {
                                        JOptionPane.showMessageDialog(frame, "Felicitari, ai infrant inamicul si ai primit monede!", "WIN",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        mapa.caractercrt.inventar.NrMonede += monede1;
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.",
                                                "GHINION", JOptionPane.ERROR_MESSAGE);
                                    }
                                    mapa.caractercrt.experienta += experienta.nextInt(70);
                                    if (mapa.caractercrt.experienta >= 100) {
                                        mapa.caractercrt.nivel++;
                                        mapa.caractercrt.experienta -= 100;
                                        mapa.caractercrt.upgrade();
                                    }
                                    String abil = new String();
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
                                        abil += mapa.caractercrt.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                    abilities.setText(abil);
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
                                    nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                    mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                }
                            }
                        });
                        ice.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int viataprecin = inamic.viatacurenta;
                                if (mapa.caractercrt.manacurenta < 50) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti destula mana.", "MANA",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    int ok = 0;
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++) {
                                        if (mapa.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Ice")) {
                                            ok = 1;
                                            inamic.accept(mapa.caractercrt.abilitati.get(i));
                                            mapa.caractercrt.manacurenta -= mapa.caractercrt.abilitati.get(i).CostMana;
                                            mapa.caractercrt.abilitati.remove(i);
                                            String abil = "\nABILITATI: ";
                                            for (int j = 0; j < mapa.caractercrt.abilitati.size(); j++)
                                                abil += mapa.caractercrt.abilitati.get(j).getClass().getSimpleName() + " ; ";
                                            abilities.setText(abil);
                                            mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                            break;
                                        }
                                    }
                                    if (ok == 0) {
                                        JOptionPane.showMessageDialog(fr, "Nu exista aceasta abilitate.", "ICE",
                                                JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        if (viataprecin == inamic.viatacurenta) {
                                            JOptionPane.showMessageDialog(fr, "Ati ratat abilitatea ICE.", "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            int temp = inamic.viatacurenta - viataprecin;
                                            JOptionPane.showMessageDialog(fr, "Ati reusit sa dati " + temp + " damage.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            vinamic.setText("\nHP: " + inamic.viatacurenta);
                                        }
                                        if (inamic.viatacurenta <= 0) {
                                            nrinamici++;
                                            inamic.viatacurenta = 0;
                                        } else {
                                            Random rand = new Random();
                                            int abilitate_inamic = rand.nextInt(2);
                                            if (inamic.abilitati.isEmpty()) {
                                                abilitate_inamic = 0;
                                            }
                                            if (abilitate_inamic == 0) {
                                                int viataprec = mapa.caractercrt.viatacurenta;
                                                mapa.caractercrt.receiveDamage(inamic.getDamage());
                                                if (viataprec == mapa.caractercrt.viatacurenta) {
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.", "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                } else {
                                                    int temp = mapa.caractercrt.viatacurenta - viataprec;
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a dat " + temp + " damage.",
                                                            "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                    viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                }
                                            } else {
                                                int nr = inamic.abilitati.size();
                                                Random newrand = new Random();
                                                int abilitate_random = newrand.nextInt(nr);
                                                inamic.abilitati.get(abilitate_random).set();
                                                int temp = mapa.caractercrt.viatacurenta;
                                                if (inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                                    mapa.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                                    if (temp == mapa.caractercrt.viatacurenta)
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    else {
                                                        int calc = mapa.caractercrt.viatacurenta - temp;
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a dat " + calc + " damage.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                    }
                                                }
                                                viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                                minamic.setText("\nMANA: " + inamic.manacurenta);
                                                inamic.abilitati.remove(abilitate_random);
                                                String abil = new String();
                                                for (int i = 0; i < inamic.abilitati.size(); i++)
                                                    abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                                abilitati.setText(abil);
                                            }
                                        }
                                    }
                                }
                                if (mapa.caractercrt.viatacurenta <= 0) {
                                    JOptionPane.showMessageDialog(fr, "Ai pierdut! Dar totusi ai infrant " + nrinamici + " inamici.\n Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                            "GAME OVER", JOptionPane.ERROR_MESSAGE);
                                    fr.dispose();
                                    frame.dispose();
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    fr.dispose();
                                    Random randmonede = new Random();
                                    Random randsansa = new Random();
                                    Random experienta = new Random();
                                    int sansa = randsansa.nextInt(5);
                                    int monede1 = randmonede.nextInt(100);
                                    if (sansa != 0 && monede1 != 0) {
                                        JOptionPane.showMessageDialog(frame, "Felicitari, ai infrant inamicul si ai primit monede!", "WIN",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        mapa.caractercrt.inventar.NrMonede += monede1;
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.",
                                                "GHINION", JOptionPane.ERROR_MESSAGE);
                                    }
                                    mapa.caractercrt.experienta += experienta.nextInt(70);
                                    if (mapa.caractercrt.experienta >= 100) {
                                        mapa.caractercrt.nivel++;
                                        mapa.caractercrt.experienta -= 100;
                                        mapa.caractercrt.upgrade();
                                    }
                                    String abil = new String();
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
                                        abil += mapa.caractercrt.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                    abilities.setText(abil);
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
                                    nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                    mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                }
                            }
                        });
                        butoane.add(earth);
                        butoane.add(fire);
                        butoane.add(ice);
                        butoane.add(vit);
                        butoane.add(mn);
                        fr.add(butoane);

                        caracter.setBackground(new Color(102, 75, 75));
                        enemy.setBackground(new Color(102, 75, 75));
                        fr.add(caracter, BorderLayout.WEST);
                        fr.add(enemy, BorderLayout.EAST);
                        fr.getContentPane().setBackground(new Color(102, 75, 75));
                        fr.pack();
                        fr.show();
                    }
                    if (mapa.celulacrt.element.toCharacter() == 'E' && mapa.celulacrt.vizitat == true) {
                        JOptionPane.showMessageDialog(frame, "Ai luptat cu acest inamic deja.", "Inamic intalnit anterior",
                                JOptionPane.OK_OPTION);
                    }
                    if (mapa.celulacrt.element.toCharacter() == 'F') {
                        ImageIcon icon = new ImageIcon("src/Joculet/win.png");
                        JOptionPane.showMessageDialog(
                                null,
                                new JLabel("Felicitari! Ai ajuns la final!\nAi infrant " + nrinamici + " inamici.. Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                        icon, JLabel.LEFT),
                                "GAME OVER", JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                    }
                    mapa.celulacrt.vizitat = true;
                }
            }
        });
        panel.add(sud);

        JButton est = new JButton();
        est.setIcon(new ImageIcon("src/Joculet/east.png"));
        est.setBounds(555, 63, 58, 36);
        est.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox).Ox + 1 >= mapa.latime)
                    JOptionPane.showMessageDialog(frame, "Nu te poti deplasa spre est!",
                            "EROARE", JOptionPane.ERROR_MESSAGE);
                else {
                    if (mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox).element.toCharacter() == 'F')
                        table.setValueAt(new ImageIcon("src/Joculet/finish.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    else if (mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox).element.toCharacter() == 'S')
                        table.setValueAt(new ImageIcon("src/Joculet/shopp.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    else if (mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox).element.toCharacter() == 'E')
                        table.setValueAt(new ImageIcon("src/Joculet/poison.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    else
                        table.setValueAt(new ImageIcon("src/Joculet/n.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    mapa.celulacrt = mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox + 1);
                    table.setValueAt(new ImageIcon("src/Joculet/man.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    mapa.caractercrt.Ox = mapa.celulacrt.Ox;
                    mapa.caractercrt.Oy = mapa.celulacrt.Oy;
                    if (mapa.celulacrt.vizitat == false) {
                        JOptionPane.showMessageDialog(frame, Game.parsarepovesti(mapa.celulacrt),
                                "POVESTE", JOptionPane.INFORMATION_MESSAGE);
                        Random rand = new Random();
                        int mon;
                        if (rand.nextInt(5) == 1) {
                            mon = rand.nextInt(50);
                            mapa.caractercrt.inventar.NrMonede += mon;
                            JOptionPane.showMessageDialog(frame, "Felicitari! Ai gasit o comoara cu : " + mon
                                            + " monede.",
                                    "COMOARA NEDESCOPERITA", JOptionPane.INFORMATION_MESSAGE);
                            monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                        }
                    }
                    if (mapa.celulacrt.element.toCharacter() == 'S') {
                        JFrame fr = new JFrame("Magazin");
                        Shop sh = (Shop) mapa.celulacrt.element;
                        fr.setMinimumSize(new Dimension(600, 400));
                        fr.getContentPane().setBackground(new Color(153, 132, 111));
                        ImageIcon login = new ImageIcon(getClass().getResource("shopare.png"));
                        Image img = login.getImage();
                        Image imgscale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        login = new ImageIcon(imgscale);
                        JLabel image = new JLabel();
                        image.setBounds(5, 60, 120, 120);
                        image.setIcon(login);
                        DefaultListModel listaM = new DefaultListModel();
                        for (int i = 0; i < sh.potiuni.size(); i++)
                            listaM.add(i, sh.potiuni.get(i).toString());
                        JList<String> lista = new JList<>(listaM);
                        lista.setLayoutOrientation(JList.VERTICAL_WRAP);
                        lista.setVisibleRowCount(sh.potiuni.size());
                        JButton cumpara = new JButton("Cumpara");
                        JButton iesire = new JButton("Iesire");
                        JPanel PanelShop = new JPanel();
                        PanelShop.setBackground(new Color(153, 132, 111));
                        PanelShop.add(lista);
                        PanelShop.setBounds(150, 140, 400, 200);
                        iesire.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                greutater.setText("\nGreutateR: " + mapa.caractercrt.inventar.calculgreutate());
                                monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                fr.dispose();
                            }
                        });
                        cumpara.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (lista.isSelectionEmpty()) {
                                    return;
                                }
                                int index = lista.getSelectedIndex();
                                if (index == -1)
                                    JOptionPane.showMessageDialog(frame, "Nu ai selectat nicio potiune!",
                                            "EROARE", JOptionPane.ERROR_MESSAGE);
                                else if (mapa.caractercrt.inventar.NrMonede >= sh.potiuni.get(index).pret() && mapa.caractercrt.inventar.calculgreutate() >= sh.potiuni.get(index).greutate()) {
                                    mapa.caractercrt.inventar.potiuni.add(sh.potiuni.get(index));
                                    mapa.caractercrt.inventar.NrMonede -= sh.potiuni.get(index).pret();
                                    sh.potiuni.remove(index);
                                    listaM.remove(index);
                                    //lista.remove(index);
                                    greutater.setText("\nGreutateR: " + mapa.caractercrt.inventar.calculgreutate());
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                } else
                                    JOptionPane.showMessageDialog(frame, "Nu aveti destule monede sau inventarul este plin!",
                                            "EROARE", JOptionPane.ERROR_MESSAGE);
                                fr.revalidate();
                                fr.repaint();
                            }
                        });
                        PanelShop.add(cumpara);
                        PanelShop.add(iesire);
                        fr.add(PanelShop);
                        fr.add(image);
                        fr.pack();
                        fr.show();
                    }
                    if (mapa.celulacrt.element.toCharacter() == 'E' && mapa.celulacrt.vizitat == false) {
                        JFrame fr = new JFrame();
                        Enemy inamic = (Enemy) mapa.celulacrt.element;
                        fr.setMinimumSize(new Dimension(1000, 800));
                        ImageIcon you = new ImageIcon(getClass().getResource("you.png"));
                        Image img = you.getImage();
                        Image imgscale = img.getScaledInstance(120, 60, Image.SCALE_SMOOTH);
                        you = new ImageIcon(imgscale);
                        JLabel image = new JLabel();
                        image.setBounds(5, 10, 120, 60);
                        image.setIcon(you);
                        JPanel caracter = new JPanel();
                        caracter.setLayout(new GridLayout(13, 1));
                        caracter.add(image);
                        caracter.add(nume);
                        JTextField viata1 = new JTextField(15);
                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                        viata1.setForeground(Color.RED);
                        viata1.setEditable(false);
                        viata1.setBackground(new Color(145, 140, 145));
                        caracter.add(viata1);
                        JTextField vmax = new JTextField(15);
                        vmax.setText("\nVIATA MAXIMA: " + mapa.caractercrt.viatamaxima);
                        vmax.setForeground(Color.BLUE);
                        vmax.setEditable(false);
                        vmax.setBackground(new Color(145, 140, 145));
                        JTextField manamax = new JTextField(15);
                        manamax.setText("\nMANA MAXIMA: " + mapa.caractercrt.manamaxima);
                        manamax.setForeground(Color.YELLOW);
                        manamax.setEditable(false);
                        manamax.setBackground(new Color(145, 140, 145));
                        JTextField abilities = new JTextField(20);
                        String superputeri = new String();
                        superputeri = "ABILITATI: ";
                        for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
                            superputeri += mapa.caractercrt.abilitati.get(i).getClass().getSimpleName() + "; ";
                        abilities.setText(superputeri);
                        abilities.setForeground(Color.GREEN);
                        abilities.setEditable(false);
                        abilities.setBackground(new Color(145, 150, 145));
                        JTextField potions = new JTextField(15);
                        String potiuni = new String();
                        potiuni = "POTIUNI: ";
                        for (int i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++)
                            if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion"))
                                potiuni += "VIATA ; ";
                            else
                                potiuni += "MANA ; ";
                        potions.setText(potiuni);
                        potions.setForeground(new Color(120, 33, 78));
                        potions.setEditable(false);
                        potions.setBackground(new Color(145, 150, 145));
                        JTextField mana1 = new JTextField(15);
                        mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                        mana1.setForeground(Color.BLUE);
                        mana1.setEditable(false);
                        mana1.setBackground(new Color(145, 140, 145));
                        caracter.add(mana1);
                        caracter.add(manamax);
                        caracter.add(putere);
                        caracter.add(dex);
                        caracter.add(car);
                        caracter.add(potions);
                        caracter.add(abilities);
                        caracter.add(imune);
                        caracter.add(imunf);
                        caracter.add(imuni);

                        JTextField vinamic = new JTextField(15);
                        vinamic.setText("\nHP: " + inamic.viatacurenta);
                        vinamic.setForeground(Color.RED);
                        vinamic.setEditable(false);
                        vinamic.setBackground(new Color(145, 140, 145));
                        JTextField minamic = new JTextField(15);
                        minamic.setText("\nMANA: " + inamic.manacurenta);
                        minamic.setForeground(Color.BLUE);
                        minamic.setEditable(false);
                        minamic.setBackground(new Color(145, 140, 145));
                        JTextField imunf1 = new JTextField(15);
                        imunf1.setText("\nIMUN->FIRE: " + inamic.fire);
                        imunf1.setForeground(Color.BLACK);
                        imunf1.setEditable(false);
                        imunf1.setBackground(new Color(145, 140, 145));
                        JTextField imune1 = new JTextField(15);
                        imune1.setText("\nIMUN->EARTH: " + inamic.earth);
                        imune1.setForeground(Color.BLACK);
                        imune1.setEditable(false);
                        imune1.setBackground(new Color(145, 140, 145));
                        JTextField imuni1 = new JTextField(15);
                        imuni1.setText("\nIMUN->ICE: " + inamic.ice);
                        imuni1.setForeground(Color.BLACK);
                        imuni1.setEditable(false);
                        imuni1.setBackground(new Color(145, 140, 145));
                        JTextField abilitati = new JTextField(15);
                        String abil = new String();
                        for (int i = 0; i < inamic.abilitati.size(); i++)
                            abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                        abilitati.setText(abil);
                        abilitati.setForeground(Color.BLACK);
                        abilitati.setEditable(false);
                        abilitati.setBackground(new Color(145, 150, 145));
                        JPanel enemy = new JPanel();
                        ImageIcon in = new ImageIcon(getClass().getResource("enemyy.png"));
                        Image img1 = in.getImage();
                        Image imgscale1 = img1.getScaledInstance(120, 80, Image.SCALE_SMOOTH);
                        in = new ImageIcon(imgscale1);
                        JLabel image1 = new JLabel();
                        image1.setBounds(5, 10, 120, 60);
                        image1.setIcon(in);
                        enemy.setLayout(new GridLayout(7, 1));
                        enemy.add(image1);
                        enemy.add(vinamic);
                        enemy.add(minamic);
                        enemy.add(abilitati);
                        enemy.add(imune1);
                        enemy.add(imunf1);
                        enemy.add(imuni1);

                        Icon ear = new ImageIcon("src/Joculet/earthh.png");
                        Icon fir = new ImageIcon("src/Joculet/fire.png");
                        Icon ic = new ImageIcon("src/Joculet/snowflake1.png");
                        JButton atac = new JButton("Atac normal.");
                        atac.setBounds(80, 50, 360, 50);
                        JButton earth = new JButton(ear);
                        earth.setBounds(80, 100, 360, 50);
                        JButton fire = new JButton(fir);
                        fire.setBounds(80, 150, 360, 50);
                        JButton ice = new JButton(ic);
                        ice.setBounds(80, 200, 360, 50);
                        JButton vit = new JButton("Adauga viata.");
                        vit.setBounds(80, 250, 360, 50);
                        JButton mn = new JButton("Adauga mana.");
                        mn.setBounds(80, 300, 360, 50);
                        JPanel butoane = new JPanel();
                        butoane.setLayout(null);
                        butoane.setBounds(350, 20, 60, 400);
                        butoane.add(atac);
                        atac.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int viataprecin = inamic.viatacurenta;
                                inamic.receiveDamage(mapa.caractercrt.getDamage());
                                if (viataprecin == inamic.viatacurenta) {
                                    JOptionPane.showMessageDialog(fr, "Ati ratat atacul.", "ATAC",
                                            JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    int temp = inamic.viatacurenta - viataprecin;
                                    JOptionPane.showMessageDialog(fr, "Ati reusit sa dati " + temp + " damage.",
                                            "ATAC",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    vinamic.setText("\nHP: " + inamic.viatacurenta);
                                }
                                Random rand = new Random();
                                int abilitate_inamic = rand.nextInt(2);
                                if (inamic.abilitati.isEmpty()) {
                                    abilitate_inamic = 0;
                                }
                                if (abilitate_inamic == 0) {
                                    int viataprec = mapa.caractercrt.viatacurenta;
                                    mapa.caractercrt.receiveDamage(inamic.getDamage());
                                    if (viataprec == mapa.caractercrt.viatacurenta) {
                                        JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.", "ATAC",
                                                JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        int temp = mapa.caractercrt.viatacurenta - viataprec;
                                        JOptionPane.showMessageDialog(fr, "Inamicul a dat " + temp + " damage.",
                                                "ATAC",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    }
                                } else {
                                    int nr = inamic.abilitati.size();
                                    Random newrand = new Random();
                                    int abilitate_random = newrand.nextInt(nr);
                                    inamic.abilitati.get(abilitate_random).set();
                                    int temp = mapa.caractercrt.viatacurenta;
                                    if (inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                        mapa.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                        if (temp == mapa.caractercrt.viatacurenta)
                                            JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        else {
                                            int calc = mapa.caractercrt.viatacurenta - temp;
                                            JOptionPane.showMessageDialog(fr, "Inamicul a dat " + calc + " damage.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                        }
                                    }
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                    minamic.setText("\nMANA: " + inamic.manacurenta);
                                    inamic.abilitati.remove(abilitate_random);
                                    String abil = new String();
                                    for (int i = 0; i < inamic.abilitati.size(); i++)
                                        abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                    abilitati.setText(abil);

                                }
                                if (mapa.caractercrt.viatacurenta <= 0) {
                                    JOptionPane.showMessageDialog(fr, "Ai pierdut! Dar totusi ai infrant " + nrinamici + " inamici.\n Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                            "GAME OVER", JOptionPane.ERROR_MESSAGE);
                                    fr.dispose();
                                    frame.dispose();
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    fr.dispose();
                                    Random randmonede = new Random();
                                    Random randsansa = new Random();
                                    Random experienta = new Random();
                                    int sansa = randsansa.nextInt(5);
                                    int monede1 = randmonede.nextInt(100);
                                    if (sansa != 0 && monede1 != 0) {
                                        JOptionPane.showMessageDialog(frame, "Felicitari, ai infrant inamicul si ai primit monede!", "WIN",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        mapa.caractercrt.inventar.NrMonede += monede1;
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.",
                                                "GHINION", JOptionPane.ERROR_MESSAGE);
                                    }
                                    mapa.caractercrt.experienta += experienta.nextInt(70);
                                    if (mapa.caractercrt.experienta >= 100) {
                                        mapa.caractercrt.nivel++;
                                        mapa.caractercrt.experienta -= 100;
                                        mapa.caractercrt.upgrade();
                                    }
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
                                    nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                }
                            }
                        });
                        vit.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int ok = 0, i;
                                for (i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++) {
                                    if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion")) {
                                        ok = 1;
                                        mapa.caractercrt.folosirepotiune(mapa.caractercrt.inventar.potiuni.get(i));
                                        viata1.setText("\nHP:" + mapa.caractercrt.viatacurenta);
                                        break;
                                    }
                                }
                                if (ok == 0) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti potiune de viata.", "HealthPotion",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(fr, "Ati folosit cu succes potiune de viata.", "HealthPotion",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                                String potiuni = "POTIUNI: ";
                                for (i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++)
                                    if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion"))
                                        potiuni += "VIATA ; ";
                                    else
                                        potiuni += "MANA ; ";
                                potions.setText(potiuni);
                                viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                            }
                        });
                        mn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int ok = 0, i;
                                for (i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++) {
                                    if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("ManaPotion")) {
                                        ok = 1;
                                        mapa.caractercrt.folosirepotiune(mapa.caractercrt.inventar.potiuni.get(i));
                                        mana1.setText("\nMANA:" + mapa.caractercrt.manacurenta);
                                        break;
                                    }
                                }
                                if (ok == 0) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti potiune de mana.", "ManaPotion",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(fr, "Ati folosit cu succes potiune de mana.", "ManaPotion",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                                String potiuni = "POTIUNI: ";
                                for (i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++)
                                    if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion"))
                                        potiuni += "VIATA ; ";
                                    else
                                        potiuni += "MANA ; ";
                                potions.setText(potiuni);
                                mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                            }
                        });
                        earth.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int viataprecin = inamic.viatacurenta;
                                if (mapa.caractercrt.manacurenta < 60) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti destula mana.", "MANA",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    int ok = 0;
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++) {
                                        if (mapa.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Earth")) {
                                            ok = 1;
                                            inamic.accept(mapa.caractercrt.abilitati.get(i));
                                            System.out.println(mapa.caractercrt.abilitati.get(i).CostMana + "\n");
                                            mapa.caractercrt.manacurenta -= mapa.caractercrt.abilitati.get(i).CostMana;
                                            mapa.caractercrt.abilitati.remove(i);
                                            String abil = "ABILITATI: ";
                                            for (int j = 0; j < mapa.caractercrt.abilitati.size(); j++)
                                                abil += mapa.caractercrt.abilitati.get(j).getClass().getSimpleName() + " ; ";
                                            abilities.setText(abil);
                                            mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                            break;
                                        }
                                    }
                                    if (ok == 0) {
                                        JOptionPane.showMessageDialog(fr, "Nu exista aceasta abilitate.", "EARTH",
                                                JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        if (viataprecin == inamic.viatacurenta) {
                                            JOptionPane.showMessageDialog(fr, "Ati ratat abilitatea EARTH.", "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            int temp = inamic.viatacurenta - viataprecin;
                                            JOptionPane.showMessageDialog(fr, "Ati reusit sa dati " + temp + " damage.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            vinamic.setText("\nHP: " + inamic.viatacurenta);
                                        }
                                        if (inamic.viatacurenta <= 0) {
                                            nrinamici++;
                                            inamic.viatacurenta = 0;
                                        } else {
                                            Random rand = new Random();
                                            int abilitate_inamic = rand.nextInt(2);
                                            if (inamic.abilitati.isEmpty()) {
                                                abilitate_inamic = 0;
                                            }
                                            if (abilitate_inamic == 0) {
                                                int viataprec = mapa.caractercrt.viatacurenta;
                                                mapa.caractercrt.receiveDamage(inamic.getDamage());
                                                if (viataprec == mapa.caractercrt.viatacurenta) {
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.", "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                } else {
                                                    int temp = mapa.caractercrt.viatacurenta - viataprec;
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a dat " + temp + " damage.",
                                                            "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                    viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                }
                                            } else {
                                                int nr = inamic.abilitati.size();
                                                Random newrand = new Random();
                                                int abilitate_random = newrand.nextInt(nr);
                                                inamic.abilitati.get(abilitate_random).set();
                                                int temp = mapa.caractercrt.viatacurenta;
                                                if (inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                                    mapa.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                                    if (temp == mapa.caractercrt.viatacurenta)
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    else {
                                                        int calc = mapa.caractercrt.viatacurenta - temp;
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a dat " + calc + " damage.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                    }
                                                }
                                                viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                                minamic.setText("\nMANA: " + inamic.manacurenta);
                                                inamic.abilitati.remove(abilitate_random);
                                                String abil = new String();
                                                for (int i = 0; i < inamic.abilitati.size(); i++)
                                                    abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                                abilitati.setText(abil);
                                            }
                                        }
                                    }
                                }
                                if (mapa.caractercrt.viatacurenta <= 0) {
                                    JOptionPane.showMessageDialog(fr, "Ai pierdut! Dar totusi ai infrant " + nrinamici + " inamici.\n Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                            "GAME OVER", JOptionPane.ERROR_MESSAGE);
                                    fr.dispose();
                                    frame.dispose();
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    fr.dispose();
                                    Random randmonede = new Random();
                                    Random randsansa = new Random();
                                    Random experienta = new Random();
                                    int sansa = randsansa.nextInt(5);
                                    int monede1 = randmonede.nextInt(100);
                                    if (sansa != 0 && monede1 != 0) {
                                        JOptionPane.showMessageDialog(frame, "Felicitari, ai infrant inamicul si ai primit monede!", "WIN",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        mapa.caractercrt.inventar.NrMonede += monede1;
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.",
                                                "GHINION", JOptionPane.ERROR_MESSAGE);
                                    }
                                    mapa.caractercrt.experienta += experienta.nextInt(70);
                                    if (mapa.caractercrt.experienta >= 100) {
                                        mapa.caractercrt.nivel++;
                                        mapa.caractercrt.experienta -= 100;
                                        mapa.caractercrt.upgrade();
                                    }
                                    String abil = new String();
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
                                        abil += mapa.caractercrt.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                    abilities.setText(abil);
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
                                    nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                    mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                }
                            }
                        });
                        fire.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int viataprecin = inamic.viatacurenta;
                                if (mapa.caractercrt.manacurenta < 55) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti destula mana.", "MANA",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    int ok = 0;
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++) {
                                        if (mapa.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Fire")) {
                                            ok = 1;
                                            inamic.accept(mapa.caractercrt.abilitati.get(i));
                                            mapa.caractercrt.manacurenta -= mapa.caractercrt.abilitati.get(i).CostMana;
                                            mapa.caractercrt.abilitati.remove(i);
                                            String abil = new String();
                                            for (int j = 0; j < mapa.caractercrt.abilitati.size(); j++)
                                                abil += mapa.caractercrt.abilitati.get(j).getClass().getSimpleName() + " ; ";
                                            abilities.setText(abil);
                                            mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                            break;
                                        }
                                    }
                                    if (ok == 0) {
                                        JOptionPane.showMessageDialog(fr, "Nu exista aceasta abilitate.", "FIRE",
                                                JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        if (viataprecin == inamic.viatacurenta) {
                                            JOptionPane.showMessageDialog(fr, "Ati ratat abilitatea FIRE.", "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            int temp = inamic.viatacurenta - viataprecin;
                                            JOptionPane.showMessageDialog(fr, "Ati reusit sa dati " + temp + " damage.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            vinamic.setText("\nHP: " + inamic.viatacurenta);
                                        }
                                        if (inamic.viatacurenta <= 0) {
                                            nrinamici++;
                                            inamic.viatacurenta = 0;
                                        } else {
                                            Random rand = new Random();
                                            int abilitate_inamic = rand.nextInt(2);
                                            if (inamic.abilitati.isEmpty()) {
                                                abilitate_inamic = 0;
                                            }
                                            if (abilitate_inamic == 0) {
                                                int viataprec = mapa.caractercrt.viatacurenta;
                                                mapa.caractercrt.receiveDamage(inamic.getDamage());
                                                if (viataprec == mapa.caractercrt.viatacurenta) {
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.", "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                } else {
                                                    int temp = mapa.caractercrt.viatacurenta - viataprec;
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a dat " + temp + " damage.",
                                                            "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                    viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                }
                                            } else {
                                                int nr = inamic.abilitati.size();
                                                Random newrand = new Random();
                                                int abilitate_random = newrand.nextInt(nr);
                                                inamic.abilitati.get(abilitate_random).set();
                                                int temp = mapa.caractercrt.viatacurenta;
                                                if (inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                                    mapa.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                                    if (temp == mapa.caractercrt.viatacurenta)
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    else {
                                                        int calc = mapa.caractercrt.viatacurenta - temp;
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a dat " + calc + " damage.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                    }
                                                }
                                                viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                                minamic.setText("\nMANA: " + inamic.manacurenta);
                                                inamic.abilitati.remove(abilitate_random);
                                                String abil = new String();
                                                for (int i = 0; i < inamic.abilitati.size(); i++)
                                                    abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                                abilitati.setText(abil);
                                            }
                                        }
                                    }
                                }
                                if (mapa.caractercrt.viatacurenta <= 0) {
                                    JOptionPane.showMessageDialog(fr, "Ai pierdut! Dar totusi ai infrant " + nrinamici + " inamici.\n Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                            "GAME OVER", JOptionPane.ERROR_MESSAGE);
                                    fr.dispose();
                                    frame.dispose();
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    fr.dispose();
                                    Random randmonede = new Random();
                                    Random randsansa = new Random();
                                    Random experienta = new Random();
                                    int sansa = randsansa.nextInt(5);
                                    int monede1 = randmonede.nextInt(100);
                                    if (sansa != 0 && monede1 != 0) {
                                        JOptionPane.showMessageDialog(frame, "Felicitari, ai infrant inamicul si ai primit monede!", "WIN",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        mapa.caractercrt.inventar.NrMonede += monede1;
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.",
                                                "GHINION", JOptionPane.ERROR_MESSAGE);
                                    }
                                    mapa.caractercrt.experienta += experienta.nextInt(70);
                                    if (mapa.caractercrt.experienta >= 100) {
                                        mapa.caractercrt.nivel++;
                                        mapa.caractercrt.experienta -= 100;
                                        mapa.caractercrt.upgrade();
                                    }
                                    String abil = new String();
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
                                        abil += mapa.caractercrt.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                    abilities.setText(abil);
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
                                    nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                    mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                }
                            }
                        });
                        ice.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int viataprecin = inamic.viatacurenta;
                                if (mapa.caractercrt.manacurenta < 50) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti destula mana.", "MANA",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    int ok = 0;
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++) {
                                        if (mapa.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Ice")) {
                                            ok = 1;
                                            inamic.accept(mapa.caractercrt.abilitati.get(i));
                                            mapa.caractercrt.manacurenta -= mapa.caractercrt.abilitati.get(i).CostMana;
                                            mapa.caractercrt.abilitati.remove(i);
                                            String abil = "\nABILITATI: ";
                                            for (int j = 0; j < mapa.caractercrt.abilitati.size(); j++)
                                                abil += mapa.caractercrt.abilitati.get(j).getClass().getSimpleName() + " ; ";
                                            abilities.setText(abil);
                                            mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                            break;
                                        }
                                    }
                                    if (ok == 0) {
                                        JOptionPane.showMessageDialog(fr, "Nu exista aceasta abilitate.", "ICE",
                                                JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        if (viataprecin == inamic.viatacurenta) {
                                            JOptionPane.showMessageDialog(fr, "Ati ratat abilitatea ICE.", "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            int temp = inamic.viatacurenta - viataprecin;
                                            JOptionPane.showMessageDialog(fr, "Ati reusit sa dati " + temp + " damage.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            vinamic.setText("\nHP: " + inamic.viatacurenta);
                                        }
                                        if (inamic.viatacurenta <= 0) {
                                            nrinamici++;
                                            inamic.viatacurenta = 0;
                                        } else {
                                            Random rand = new Random();
                                            int abilitate_inamic = rand.nextInt(2);
                                            if (inamic.abilitati.isEmpty()) {
                                                abilitate_inamic = 0;
                                            }
                                            if (abilitate_inamic == 0) {
                                                int viataprec = mapa.caractercrt.viatacurenta;
                                                mapa.caractercrt.receiveDamage(inamic.getDamage());
                                                if (viataprec == mapa.caractercrt.viatacurenta) {
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.", "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                } else {
                                                    int temp = mapa.caractercrt.viatacurenta - viataprec;
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a dat " + temp + " damage.",
                                                            "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                    viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                }
                                            } else {
                                                int nr = inamic.abilitati.size();
                                                Random newrand = new Random();
                                                int abilitate_random = newrand.nextInt(nr);
                                                inamic.abilitati.get(abilitate_random).set();
                                                int temp = mapa.caractercrt.viatacurenta;
                                                if (inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                                    mapa.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                                    if (temp == mapa.caractercrt.viatacurenta)
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    else {
                                                        int calc = mapa.caractercrt.viatacurenta - temp;
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a dat " + calc + " damage.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                    }
                                                }
                                                viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                                minamic.setText("\nMANA: " + inamic.manacurenta);
                                                inamic.abilitati.remove(abilitate_random);
                                                String abil = new String();
                                                for (int i = 0; i < inamic.abilitati.size(); i++)
                                                    abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                                abilitati.setText(abil);
                                            }
                                        }
                                    }
                                }
                                if (mapa.caractercrt.viatacurenta <= 0) {
                                    JOptionPane.showMessageDialog(fr, "Ai pierdut! Dar totusi ai infrant " + nrinamici + " inamici.\n Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                            "GAME OVER", JOptionPane.ERROR_MESSAGE);
                                    fr.dispose();
                                    frame.dispose();
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    fr.dispose();
                                    Random randmonede = new Random();
                                    Random randsansa = new Random();
                                    Random experienta = new Random();
                                    int sansa = randsansa.nextInt(5);
                                    int monede1 = randmonede.nextInt(100);
                                    if (sansa != 0 && monede1 != 0) {
                                        JOptionPane.showMessageDialog(frame, "Felicitari, ai infrant inamicul si ai primit monede!", "WIN",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        mapa.caractercrt.inventar.NrMonede += monede1;
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.",
                                                "GHINION", JOptionPane.ERROR_MESSAGE);
                                    }
                                    mapa.caractercrt.experienta += experienta.nextInt(70);
                                    if (mapa.caractercrt.experienta >= 100) {
                                        mapa.caractercrt.nivel++;
                                        mapa.caractercrt.experienta -= 100;
                                        mapa.caractercrt.upgrade();
                                    }
                                    String abil = new String();
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
                                        abil += mapa.caractercrt.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                    abilities.setText(abil);
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
                                    nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                    mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                }
                            }
                        });
                        butoane.add(earth);
                        butoane.add(fire);
                        butoane.add(ice);
                        butoane.add(vit);
                        butoane.add(mn);
                        fr.add(butoane);

                        caracter.setBackground(new Color(102, 75, 75));
                        enemy.setBackground(new Color(102, 75, 75));
                        fr.add(caracter, BorderLayout.WEST);
                        fr.add(enemy, BorderLayout.EAST);
                        fr.getContentPane().setBackground(new Color(102, 75, 75));
                        fr.pack();
                        fr.show();
                    }
                    if (mapa.celulacrt.element.toCharacter() == 'E' && mapa.celulacrt.vizitat == true) {
                        JOptionPane.showMessageDialog(frame, "Ai luptat cu acest inamic deja.", "Inamic intalnit anterior",
                                JOptionPane.OK_OPTION);
                    }
                    if (mapa.celulacrt.element.toCharacter() == 'F') {
                        ImageIcon icon = new ImageIcon("src/Joculet/win.png");
                        JOptionPane.showMessageDialog(
                                null,
                                new JLabel("Felicitari! Ai ajuns la final!\nAi infrant " + nrinamici + " inamici. Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                        icon, JLabel.LEFT),
                                "GAME OVER", JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                    }
                    mapa.celulacrt.vizitat = true;
                }
            }
        });
        panel.add(est);

        JButton vest = new JButton();
        vest.setIcon(new ImageIcon("src/Joculet/west.png"));
        vest.setBounds(462, 63, 58, 36);
        vest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox).Ox - 1 < 0)
                    JOptionPane.showMessageDialog(frame, "Nu te poti deplasa spre vest!",
                            "EROARE", JOptionPane.ERROR_MESSAGE);
                else {
                    if (mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox).element.toCharacter() == 'F')
                        table.setValueAt(new ImageIcon("src/Joculet/finish.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    else if (mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox).element.toCharacter() == 'S')
                        table.setValueAt(new ImageIcon("src/Joculet/shopp.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    else if (mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox).element.toCharacter() == 'E')
                        table.setValueAt(new ImageIcon("src/Joculet/poison.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    else
                        table.setValueAt(new ImageIcon("src/Joculet/n.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    mapa.celulacrt = mapa.get(mapa.celulacrt.Oy).get(mapa.celulacrt.Ox - 1);
                    table.setValueAt(new ImageIcon("src/Joculet/man.png"), mapa.celulacrt.Oy, mapa.celulacrt.Ox);
                    mapa.caractercrt.Ox = mapa.celulacrt.Ox;
                    mapa.caractercrt.Oy = mapa.celulacrt.Oy;
                    if (mapa.celulacrt.vizitat == false) {
                        JOptionPane.showMessageDialog(frame, Game.parsarepovesti(mapa.celulacrt),
                                "POVESTE", JOptionPane.INFORMATION_MESSAGE);
                        Random rand = new Random();
                        int mon;
                        if (rand.nextInt(5) == 1) {
                            mon = rand.nextInt(50);
                            mapa.caractercrt.inventar.NrMonede += mon;
                            JOptionPane.showMessageDialog(frame, "Felicitari! Ai gasit o comoara cu : " + mon
                                            + " monede.",
                                    "COMOARA NEDESCOPERITA", JOptionPane.INFORMATION_MESSAGE);
                            monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                        }
                    }
                    if (mapa.celulacrt.element.toCharacter() == 'S') {
                        JFrame fr = new JFrame("Magazin");
                        Shop sh = (Shop) mapa.celulacrt.element;
                        fr.setMinimumSize(new Dimension(600, 400));
                        fr.getContentPane().setBackground(new Color(153, 132, 111));
                        ImageIcon login = new ImageIcon(getClass().getResource("shopare.png"));
                        Image img = login.getImage();
                        Image imgscale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        login = new ImageIcon(imgscale);
                        JLabel image = new JLabel();
                        image.setBounds(5, 60, 120, 120);
                        image.setIcon(login);
                        Vector potiuni = new Vector<>();
                        DefaultListModel listaM = new DefaultListModel();
                        for (int i = 0; i < sh.potiuni.size(); i++)
                            listaM.add(i, sh.potiuni.get(i).toString());
                        JList<String> lista = new JList<>(listaM);
                        lista.setLayoutOrientation(JList.VERTICAL_WRAP);
                        lista.setVisibleRowCount(sh.potiuni.size());
                        JButton cumpara = new JButton("Cumpara");
                        JButton iesire = new JButton("Iesire");
                        JPanel PanelShop = new JPanel();
                        PanelShop.setBackground(new Color(153, 132, 111));
                        PanelShop.add(lista);
                        PanelShop.setBounds(150, 140, 400, 200);
                        iesire.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                greutater.setText("\nGreutateR: " + mapa.caractercrt.inventar.calculgreutate());
                                monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                fr.dispose();
                            }
                        });
                        cumpara.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (lista.isSelectionEmpty()) {
                                    return;
                                }
                                int index = lista.getSelectedIndex();
                                if (index == -1)
                                    JOptionPane.showMessageDialog(frame, "Nu ai selectat nicio potiune!",
                                            "EROARE", JOptionPane.ERROR_MESSAGE);
                                else if (mapa.caractercrt.inventar.NrMonede >= sh.potiuni.get(index).pret() && mapa.caractercrt.inventar.calculgreutate() >= sh.potiuni.get(index).greutate()) {
                                    mapa.caractercrt.inventar.potiuni.add(sh.potiuni.get(index));
                                    mapa.caractercrt.inventar.NrMonede -= sh.potiuni.get(index).pret();
                                    sh.potiuni.remove(index);
                                    listaM.remove(index);
                                    greutater.setText("\nGreutateR: " + mapa.caractercrt.inventar.calculgreutate());
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                } else
                                    JOptionPane.showMessageDialog(frame, "Nu aveti destule monede sau inventarul este plin!",
                                            "EROARE", JOptionPane.ERROR_MESSAGE);
                                fr.revalidate();
                                fr.repaint();
                            }
                        });
                        PanelShop.add(cumpara);
                        PanelShop.add(iesire);
                        fr.add(PanelShop);
                        fr.add(image);
                        fr.pack();
                        fr.show();
                    }
                    if (mapa.celulacrt.element.toCharacter() == 'E' && mapa.celulacrt.vizitat == false) {
                        JFrame fr = new JFrame();
                        Enemy inamic = (Enemy) mapa.celulacrt.element;
                        fr.setMinimumSize(new Dimension(1000, 800));
                        ImageIcon you = new ImageIcon(getClass().getResource("you.png"));
                        Image img = you.getImage();
                        Image imgscale = img.getScaledInstance(120, 60, Image.SCALE_SMOOTH);
                        you = new ImageIcon(imgscale);
                        JLabel image = new JLabel();
                        image.setBounds(5, 10, 120, 60);
                        image.setIcon(you);
                        JPanel caracter = new JPanel();
                        caracter.setLayout(new GridLayout(13, 1));
                        caracter.add(image);
                        caracter.add(nume);
                        JTextField viata1 = new JTextField(15);
                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                        viata1.setForeground(Color.RED);
                        viata1.setEditable(false);
                        viata1.setBackground(new Color(145, 140, 145));
                        caracter.add(viata1);
                        JTextField vmax = new JTextField(15);
                        vmax.setText("\nVIATA MAXIMA: " + mapa.caractercrt.viatamaxima);
                        vmax.setForeground(Color.BLUE);
                        vmax.setEditable(false);
                        vmax.setBackground(new Color(145, 140, 145));
                        JTextField manamax = new JTextField(15);
                        manamax.setText("\nMANA MAXIMA: " + mapa.caractercrt.manamaxima);
                        manamax.setForeground(Color.YELLOW);
                        manamax.setEditable(false);
                        manamax.setBackground(new Color(145, 140, 145));
                        JTextField abilities = new JTextField(20);
                        String superputeri = new String();
                        superputeri = "ABILITATI: ";
                        for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
                            superputeri += mapa.caractercrt.abilitati.get(i).getClass().getSimpleName() + "; ";
                        abilities.setText(superputeri);
                        abilities.setForeground(Color.GREEN);
                        abilities.setEditable(false);
                        abilities.setBackground(new Color(145, 150, 145));
                        JTextField potions = new JTextField(15);
                        String potiuni = new String();
                        potiuni = "POTIUNI: ";
                        for (int i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++)
                            if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion"))
                                potiuni += "VIATA ; ";
                            else
                                potiuni += "MANA ; ";
                        potions.setText(potiuni);
                        potions.setForeground(new Color(120, 33, 78));
                        potions.setEditable(false);
                        potions.setBackground(new Color(145, 150, 145));
                        JTextField mana1 = new JTextField(15);
                        mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                        mana1.setForeground(Color.BLUE);
                        mana1.setEditable(false);
                        mana1.setBackground(new Color(145, 140, 145));
                        caracter.add(mana1);
                        caracter.add(manamax);
                        caracter.add(putere);
                        caracter.add(dex);
                        caracter.add(car);
                        caracter.add(potions);
                        caracter.add(abilities);
                        caracter.add(imune);
                        caracter.add(imunf);
                        caracter.add(imuni);

                        JTextField vinamic = new JTextField(15);
                        vinamic.setText("\nHP: " + inamic.viatacurenta);
                        vinamic.setForeground(Color.RED);
                        vinamic.setEditable(false);
                        vinamic.setBackground(new Color(145, 140, 145));
                        JTextField minamic = new JTextField(15);
                        minamic.setText("\nMANA: " + inamic.manacurenta);
                        minamic.setForeground(Color.BLUE);
                        minamic.setEditable(false);
                        minamic.setBackground(new Color(145, 140, 145));
                        JTextField imunf1 = new JTextField(15);
                        imunf1.setText("\nIMUN->FIRE: " + inamic.fire);
                        imunf1.setForeground(Color.BLACK);
                        imunf1.setEditable(false);
                        imunf1.setBackground(new Color(145, 140, 145));
                        JTextField imune1 = new JTextField(15);
                        imune1.setText("\nIMUN->EARTH: " + inamic.earth);
                        imune1.setForeground(Color.BLACK);
                        imune1.setEditable(false);
                        imune1.setBackground(new Color(145, 140, 145));
                        JTextField imuni1 = new JTextField(15);
                        imuni1.setText("\nIMUN->ICE: " + inamic.ice);
                        imuni1.setForeground(Color.BLACK);
                        imuni1.setEditable(false);
                        imuni1.setBackground(new Color(145, 140, 145));
                        JTextField abilitati = new JTextField(15);
                        String abil = new String();
                        for (int i = 0; i < inamic.abilitati.size(); i++)
                            abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                        abilitati.setText(abil);
                        abilitati.setForeground(Color.BLACK);
                        abilitati.setEditable(false);
                        abilitati.setBackground(new Color(145, 150, 145));
                        JPanel enemy = new JPanel();
                        ImageIcon in = new ImageIcon(getClass().getResource("enemyy.png"));
                        Image img1 = in.getImage();
                        Image imgscale1 = img1.getScaledInstance(120, 80, Image.SCALE_SMOOTH);
                        in = new ImageIcon(imgscale1);
                        JLabel image1 = new JLabel();
                        image1.setBounds(5, 10, 120, 60);
                        image1.setIcon(in);
                        enemy.setLayout(new GridLayout(7, 1));
                        enemy.add(image1);
                        enemy.add(vinamic);
                        enemy.add(minamic);
                        enemy.add(abilitati);
                        enemy.add(imune1);
                        enemy.add(imunf1);
                        enemy.add(imuni1);

                        Icon ear = new ImageIcon("src/Joculet/earthh.png");
                        Icon fir = new ImageIcon("src/Joculet/fire.png");
                        Icon ic = new ImageIcon("src/Joculet/snowflake1.png");
                        JButton atac = new JButton("Atac normal.");
                        atac.setBounds(80, 50, 360, 50);
                        JButton earth = new JButton(ear);
                        earth.setBounds(80, 100, 360, 50);
                        JButton fire = new JButton(fir);
                        fire.setBounds(80, 150, 360, 50);
                        JButton ice = new JButton(ic);
                        ice.setBounds(80, 200, 360, 50);
                        JButton vit = new JButton("Adauga viata.");
                        vit.setBounds(80, 250, 360, 50);
                        JButton mn = new JButton("Adauga mana.");
                        mn.setBounds(80, 300, 360, 50);
                        JPanel butoane = new JPanel();
                        butoane.setLayout(null);
                        butoane.setBounds(350, 20, 60, 400);
                        butoane.add(atac);
                        atac.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int viataprecin = inamic.viatacurenta;
                                inamic.receiveDamage(mapa.caractercrt.getDamage());
                                if (viataprecin == inamic.viatacurenta) {
                                    JOptionPane.showMessageDialog(fr, "Ati ratat atacul.", "ATAC",
                                            JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    int temp = inamic.viatacurenta - viataprecin;
                                    JOptionPane.showMessageDialog(fr, "Ati reusit sa dati " + temp + " damage.",
                                            "ATAC",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    vinamic.setText("\nHP: " + inamic.viatacurenta);
                                }
                                Random rand = new Random();
                                int abilitate_inamic = rand.nextInt(2);
                                if (inamic.abilitati.isEmpty()) {
                                    abilitate_inamic = 0;
                                }
                                if (abilitate_inamic == 0) {
                                    int viataprec = mapa.caractercrt.viatacurenta;
                                    mapa.caractercrt.receiveDamage(inamic.getDamage());
                                    if (viataprec == mapa.caractercrt.viatacurenta) {
                                        JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.", "ATAC",
                                                JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        int temp = mapa.caractercrt.viatacurenta - viataprec;
                                        JOptionPane.showMessageDialog(fr, "Inamicul a dat " + temp + " damage.",
                                                "ATAC",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    }
                                } else {
                                    int nr = inamic.abilitati.size();
                                    Random newrand = new Random();
                                    int abilitate_random = newrand.nextInt(nr);
                                    inamic.abilitati.get(abilitate_random).set();
                                    int temp = mapa.caractercrt.viatacurenta;
                                    if (inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                        mapa.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                        if (temp == mapa.caractercrt.viatacurenta)
                                            JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        else {
                                            int calc = mapa.caractercrt.viatacurenta - temp;
                                            JOptionPane.showMessageDialog(fr, "Inamicul a dat " + calc + " damage.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                        }
                                    }
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                    minamic.setText("\nMANA: " + inamic.manacurenta);
                                    inamic.abilitati.remove(abilitate_random);
                                    String abil = new String();
                                    for (int i = 0; i < inamic.abilitati.size(); i++)
                                        abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                    abilitati.setText(abil);

                                }
                                if (mapa.caractercrt.viatacurenta <= 0) {
                                    JOptionPane.showMessageDialog(fr, "Ai pierdut! Dar totusi ai infrant " + nrinamici + " inamici.\n Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                            "GAME OVER", JOptionPane.ERROR_MESSAGE);
                                    fr.dispose();
                                    frame.dispose();
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    fr.dispose();
                                    Random randmonede = new Random();
                                    Random randsansa = new Random();
                                    Random experienta = new Random();
                                    int sansa = randsansa.nextInt(5);
                                    int monede1 = randmonede.nextInt(100);
                                    if (sansa != 0 && monede1 != 0) {
                                        JOptionPane.showMessageDialog(frame, "Felicitari, ai infrant inamicul si ai primit monede!", "WIN",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        mapa.caractercrt.inventar.NrMonede += monede1;
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.",
                                                "GHINION", JOptionPane.ERROR_MESSAGE);
                                    }
                                    mapa.caractercrt.experienta += experienta.nextInt(70);
                                    if (mapa.caractercrt.experienta >= 100) {
                                        mapa.caractercrt.nivel++;
                                        mapa.caractercrt.experienta -= 100;
                                        mapa.caractercrt.upgrade();
                                    }
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
                                    nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                }
                            }
                        });
                        vit.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int ok = 0, i;
                                for (i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++) {
                                    if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion")) {
                                        ok = 1;
                                        mapa.caractercrt.folosirepotiune(mapa.caractercrt.inventar.potiuni.get(i));
                                        viata1.setText("\nHP:" + mapa.caractercrt.viatacurenta);
                                        break;
                                    }
                                }
                                if (ok == 0) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti potiune de viata.", "HealthPotion",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(fr, "Ati folosit cu succes potiune de viata.", "HealthPotion",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                                String potiuni = "POTIUNI: ";
                                for (i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++)
                                    if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion"))
                                        potiuni += "VIATA ; ";
                                    else
                                        potiuni += "MANA ; ";
                                potions.setText(potiuni);
                                viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                            }
                        });
                        mn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int ok = 0, i;
                                for (i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++) {
                                    if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("ManaPotion")) {
                                        ok = 1;
                                        mapa.caractercrt.folosirepotiune(mapa.caractercrt.inventar.potiuni.get(i));
                                        mana1.setText("\nMANA:" + mapa.caractercrt.manacurenta);
                                        break;
                                    }
                                }
                                if (ok == 0) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti potiune de mana.", "ManaPotion",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(fr, "Ati folosit cu succes potiune de mana.", "ManaPotion",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                                String potiuni = "POTIUNI: ";
                                for (i = 0; i < mapa.caractercrt.inventar.potiuni.size(); i++)
                                    if (mapa.caractercrt.inventar.potiuni.get(i).getClass().getSimpleName().equals("HealthPotion"))
                                        potiuni += "VIATA ; ";
                                    else
                                        potiuni += "MANA ; ";
                                potions.setText(potiuni);
                                mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                            }
                        });
                        earth.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int viataprecin = inamic.viatacurenta;
                                if (mapa.caractercrt.manacurenta < 60) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti destula mana.", "MANA",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    int ok = 0;
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++) {
                                        if (mapa.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Earth")) {
                                            ok = 1;
                                            inamic.accept(mapa.caractercrt.abilitati.get(i));
                                            System.out.println(mapa.caractercrt.abilitati.get(i).CostMana + "\n");
                                            mapa.caractercrt.manacurenta -= mapa.caractercrt.abilitati.get(i).CostMana;
                                            mapa.caractercrt.abilitati.remove(i);
                                            String abil = "ABILITATI: ";
                                            for (int j = 0; j < mapa.caractercrt.abilitati.size(); j++)
                                                abil += mapa.caractercrt.abilitati.get(j).getClass().getSimpleName() + " ; ";
                                            abilities.setText(abil);
                                            mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                            break;
                                        }
                                    }
                                    if (ok == 0) {
                                        JOptionPane.showMessageDialog(fr, "Nu exista aceasta abilitate.", "EARTH",
                                                JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        if (viataprecin == inamic.viatacurenta) {
                                            JOptionPane.showMessageDialog(fr, "Ati ratat abilitatea EARTH.", "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            int temp = inamic.viatacurenta - viataprecin;
                                            JOptionPane.showMessageDialog(fr, "Ati reusit sa dati " + temp + " damage.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            vinamic.setText("\nHP: " + inamic.viatacurenta);
                                        }
                                        if (inamic.viatacurenta <= 0) {
                                            nrinamici++;
                                            inamic.viatacurenta = 0;
                                        } else {
                                            Random rand = new Random();
                                            int abilitate_inamic = rand.nextInt(2);
                                            if (inamic.abilitati.isEmpty()) {
                                                abilitate_inamic = 0;
                                            }
                                            if (abilitate_inamic == 0) {
                                                int viataprec = mapa.caractercrt.viatacurenta;
                                                mapa.caractercrt.receiveDamage(inamic.getDamage());
                                                if (viataprec == mapa.caractercrt.viatacurenta) {
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.", "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                } else {
                                                    int temp = mapa.caractercrt.viatacurenta - viataprec;
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a dat " + temp + " damage.",
                                                            "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                    viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                }
                                            } else {
                                                int nr = inamic.abilitati.size();
                                                Random newrand = new Random();
                                                int abilitate_random = newrand.nextInt(nr);
                                                inamic.abilitati.get(abilitate_random).set();
                                                int temp = mapa.caractercrt.viatacurenta;
                                                if (inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                                    mapa.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                                    if (temp == mapa.caractercrt.viatacurenta)
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    else {
                                                        int calc = mapa.caractercrt.viatacurenta - temp;
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a dat " + calc + " damage.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    }
                                                }
                                                viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                                minamic.setText("\nMANA: " + inamic.manacurenta);
                                                inamic.abilitati.remove(abilitate_random);
                                                String abil = new String();
                                                for (int i = 0; i < inamic.abilitati.size(); i++)
                                                    abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                                abilitati.setText(abil);
                                            }
                                        }
                                    }
                                }
                                if (mapa.caractercrt.viatacurenta <= 0) {
                                    JOptionPane.showMessageDialog(fr, "Ai pierdut! Dar totusi ai infrant " + nrinamici + " inamici.\n Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                            "GAME OVER", JOptionPane.ERROR_MESSAGE);
                                    fr.dispose();
                                    frame.dispose();
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    fr.dispose();
                                    Random randmonede = new Random();
                                    Random randsansa = new Random();
                                    Random experienta = new Random();
                                    int sansa = randsansa.nextInt(5);
                                    int monede1 = randmonede.nextInt(100);
                                    if (sansa != 0 && monede1 != 0) {
                                        JOptionPane.showMessageDialog(frame, "Felicitari, ai infrant inamicul si ai primit monede!", "WIN",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        mapa.caractercrt.inventar.NrMonede += monede1;
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.",
                                                "GHINION", JOptionPane.ERROR_MESSAGE);
                                    }
                                    mapa.caractercrt.experienta += experienta.nextInt(70);
                                    if (mapa.caractercrt.experienta >= 100) {
                                        mapa.caractercrt.nivel++;
                                        mapa.caractercrt.experienta -= 100;
                                        mapa.caractercrt.upgrade();
                                    }
                                    String abil = new String();
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
                                        abil += mapa.caractercrt.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                    abilities.setText(abil);
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
                                    nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                    mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                }
                            }
                        });
                        fire.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int viataprecin = inamic.viatacurenta;
                                if (mapa.caractercrt.manacurenta < 55) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti destula mana.", "MANA",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    int ok = 0;
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++) {
                                        if (mapa.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Fire")) {
                                            ok = 1;
                                            inamic.accept(mapa.caractercrt.abilitati.get(i));
                                            mapa.caractercrt.manacurenta -= mapa.caractercrt.abilitati.get(i).CostMana;
                                            mapa.caractercrt.abilitati.remove(i);
                                            String abil = new String();
                                            for (int j = 0; j < mapa.caractercrt.abilitati.size(); j++)
                                                abil += mapa.caractercrt.abilitati.get(j).getClass().getSimpleName() + " ; ";
                                            abilities.setText(abil);
                                            mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                            break;
                                        }
                                    }
                                    if (ok == 0) {
                                        JOptionPane.showMessageDialog(fr, "Nu exista aceasta abilitate.", "FIRE",
                                                JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        if (viataprecin == inamic.viatacurenta) {
                                            JOptionPane.showMessageDialog(fr, "Ati ratat abilitatea FIRE.", "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            int temp = inamic.viatacurenta - viataprecin;
                                            JOptionPane.showMessageDialog(fr, "Ati reusit sa dati " + temp + " damage.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            vinamic.setText("\nHP: " + inamic.viatacurenta);
                                        }
                                        if (inamic.viatacurenta <= 0) {
                                            nrinamici++;
                                            inamic.viatacurenta = 0;
                                        } else {
                                            Random rand = new Random();
                                            int abilitate_inamic = rand.nextInt(2);
                                            if (inamic.abilitati.isEmpty()) {
                                                abilitate_inamic = 0;
                                            }
                                            if (abilitate_inamic == 0) {
                                                int viataprec = mapa.caractercrt.viatacurenta;
                                                mapa.caractercrt.receiveDamage(inamic.getDamage());
                                                if (viataprec == mapa.caractercrt.viatacurenta) {
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.", "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                } else {
                                                    int temp = mapa.caractercrt.viatacurenta - viataprec;
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a dat " + temp + " damage.",
                                                            "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                    viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                }
                                            } else {
                                                int nr = inamic.abilitati.size();
                                                Random newrand = new Random();
                                                int abilitate_random = newrand.nextInt(nr);
                                                inamic.abilitati.get(abilitate_random).set();
                                                int temp = mapa.caractercrt.viatacurenta;
                                                if (inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                                    mapa.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                                    if (temp == mapa.caractercrt.viatacurenta)
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    else {
                                                        int calc = mapa.caractercrt.viatacurenta - temp;
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a dat " + calc + " damage.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                    }
                                                }
                                                viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                                minamic.setText("\nMANA: " + inamic.manacurenta);
                                                inamic.abilitati.remove(abilitate_random);
                                                String abil = new String();
                                                for (int i = 0; i < inamic.abilitati.size(); i++)
                                                    abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                                abilitati.setText(abil);
                                            }
                                        }
                                    }
                                }
                                if (mapa.caractercrt.viatacurenta <= 0) {
                                    JOptionPane.showMessageDialog(fr, "Ai pierdut! Dar totusi ai infrant " + nrinamici + " inamici.\n Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                            "GAME OVER", JOptionPane.ERROR_MESSAGE);
                                    fr.dispose();
                                    frame.dispose();
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    fr.dispose();
                                    Random randmonede = new Random();
                                    Random randsansa = new Random();
                                    Random experienta = new Random();
                                    int sansa = randsansa.nextInt(5);
                                    int monede1 = randmonede.nextInt(100);
                                    if (sansa != 0 && monede1 != 0) {
                                        JOptionPane.showMessageDialog(frame, "Felicitari, ai infrant inamicul si ai primit monede!", "WIN",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        mapa.caractercrt.inventar.NrMonede += monede1;
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.",
                                                "GHINION", JOptionPane.ERROR_MESSAGE);
                                    }
                                    mapa.caractercrt.experienta += experienta.nextInt(70);
                                    if (mapa.caractercrt.experienta >= 100) {
                                        mapa.caractercrt.nivel++;
                                        mapa.caractercrt.experienta -= 100;
                                        mapa.caractercrt.upgrade();
                                    }
                                    String abil = new String();
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
                                        abil += mapa.caractercrt.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                    abilities.setText(abil);
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
                                    nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                    mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                }
                            }
                        });
                        ice.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int viataprecin = inamic.viatacurenta;
                                if (mapa.caractercrt.manacurenta < 50) {
                                    JOptionPane.showMessageDialog(fr, "Nu aveti destula mana.", "MANA",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    int ok = 0;
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++) {
                                        if (mapa.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Ice")) {
                                            ok = 1;
                                            inamic.accept(mapa.caractercrt.abilitati.get(i));
                                            mapa.caractercrt.manacurenta -= mapa.caractercrt.abilitati.get(i).CostMana;
                                            mapa.caractercrt.abilitati.remove(i);
                                            String abil = "\nABILITATI: ";
                                            for (int j = 0; j < mapa.caractercrt.abilitati.size(); j++)
                                                abil += mapa.caractercrt.abilitati.get(j).getClass().getSimpleName() + " ; ";
                                            abilities.setText(abil);
                                            mana1.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                            break;
                                        }
                                    }
                                    if (ok == 0) {
                                        JOptionPane.showMessageDialog(fr, "Nu exista aceasta abilitate.", "ICE",
                                                JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        if (viataprecin == inamic.viatacurenta) {
                                            JOptionPane.showMessageDialog(fr, "Ati ratat abilitatea ICE.", "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        } else {
                                            int temp = inamic.viatacurenta - viataprecin;
                                            JOptionPane.showMessageDialog(fr, "Ati reusit sa dati " + temp + " damage.",
                                                    "ATAC",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            vinamic.setText("\nHP: " + inamic.viatacurenta);
                                        }
                                        if (inamic.viatacurenta <= 0) {
                                            nrinamici++;
                                            inamic.viatacurenta = 0;
                                        } else {
                                            Random rand = new Random();
                                            int abilitate_inamic = rand.nextInt(2);
                                            if (inamic.abilitati.isEmpty()) {
                                                abilitate_inamic = 0;
                                            }
                                            if (abilitate_inamic == 0) {
                                                int viataprec = mapa.caractercrt.viatacurenta;
                                                mapa.caractercrt.receiveDamage(inamic.getDamage());
                                                if (viataprec == mapa.caractercrt.viatacurenta) {
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.", "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                } else {
                                                    int temp = mapa.caractercrt.viatacurenta - viataprec;
                                                    JOptionPane.showMessageDialog(fr, "Inamicul a dat " + temp + " damage.",
                                                            "ATAC",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                    viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                }
                                            } else {
                                                int nr = inamic.abilitati.size();
                                                Random newrand = new Random();
                                                int abilitate_random = newrand.nextInt(nr);
                                                inamic.abilitati.get(abilitate_random).set();
                                                int temp = mapa.caractercrt.viatacurenta;
                                                if (inamic.manacurenta >= inamic.abilitati.get(abilitate_random).CostMana) {
                                                    mapa.caractercrt.accept(inamic.abilitati.get(abilitate_random));
                                                    if (temp == mapa.caractercrt.viatacurenta)
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a ratat atacul.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    else {
                                                        int calc = mapa.caractercrt.viatacurenta - temp;
                                                        JOptionPane.showMessageDialog(fr, "Inamicul a dat " + calc + " damage.",
                                                                "ATAC",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                        viata1.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                    }
                                                }
                                                viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                                inamic.manacurenta -= inamic.abilitati.get(abilitate_random).CostMana;
                                                minamic.setText("\nMANA: " + inamic.manacurenta);
                                                inamic.abilitati.remove(abilitate_random);
                                                String abil = new String();
                                                for (int i = 0; i < inamic.abilitati.size(); i++)
                                                    abil += inamic.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                                abilitati.setText(abil);
                                            }
                                        }
                                    }
                                }
                                if (mapa.caractercrt.viatacurenta <= 0) {
                                    JOptionPane.showMessageDialog(fr, "Ai pierdut! Dar totusi ai infrant " + nrinamici + " inamici.\n Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                            "GAME OVER", JOptionPane.ERROR_MESSAGE);
                                    fr.dispose();
                                    frame.dispose();
                                }
                                if (inamic.viatacurenta <= 0) {
                                    nrinamici++;
                                    fr.dispose();
                                    Random randmonede = new Random();
                                    Random randsansa = new Random();
                                    Random experienta = new Random();
                                    int sansa = randsansa.nextInt(5);
                                    int monede1 = randmonede.nextInt(100);
                                    if (sansa != 0 && monede1 != 0) {
                                        JOptionPane.showMessageDialog(frame, "Felicitari, ai infrant inamicul si ai primit monede!", "WIN",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        mapa.caractercrt.inventar.NrMonede += monede1;
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Azi ai avut ghinion, ai infrant inamicul dar nu ai primit monede.",
                                                "GHINION", JOptionPane.ERROR_MESSAGE);
                                    }
                                    mapa.caractercrt.experienta += experienta.nextInt(70);
                                    if (mapa.caractercrt.experienta >= 100) {
                                        mapa.caractercrt.nivel++;
                                        mapa.caractercrt.experienta -= 100;
                                        mapa.caractercrt.upgrade();
                                    }
                                    String abil = new String();
                                    for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++)
                                        abil += mapa.caractercrt.abilitati.get(i).getClass().getSimpleName() + " ; ";
                                    abilities.setText(abil);
                                    viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
                                    exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
                                    nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
                                    monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
                                    mana.setText("\nMANA: " + mapa.caractercrt.manacurenta);
                                }
                            }
                        });
                        butoane.add(earth);
                        butoane.add(fire);
                        butoane.add(ice);
                        butoane.add(vit);
                        butoane.add(mn);
                        fr.add(butoane);

                        caracter.setBackground(new Color(102, 75, 75));
                        enemy.setBackground(new Color(102, 75, 75));
                        fr.add(caracter, BorderLayout.WEST);
                        fr.add(enemy, BorderLayout.EAST);
                        fr.getContentPane().setBackground(new Color(102, 75, 75));
                        fr.pack();
                        fr.show();
                    }
                    if (mapa.celulacrt.element.toCharacter() == 'E' && mapa.celulacrt.vizitat == true) {
                        JOptionPane.showMessageDialog(frame, "Ai luptat cu acest inamic deja.", "Inamic intalnit anterior",
                                JOptionPane.OK_OPTION);
                    }
                    if (mapa.celulacrt.element.toCharacter() == 'F') {
                        ImageIcon icon = new ImageIcon("src/Joculet/win.png");
                        JOptionPane.showMessageDialog(
                                null,
                                new JLabel("Felicitari! Ai ajuns la final!\nAi infrant " + nrinamici + " inamici. Ai ajuns la nivelul " + mapa.caractercrt.nivel + "!",
                                        icon, JLabel.LEFT),
                                "GAME OVER", JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                    }
                    mapa.celulacrt.vizitat = true;
                }
            }
        });
        panel.add(vest);

        JLabel text = new JLabel("Abilitati initiale:");
        text.setBounds(460, 100, 250, 150);
        text.setFont(new Font("Monospaced", Font.BOLD + Font.ITALIC, 14));
        text.setForeground(new Color(140, 0, 1));
        panel.add(text);
        for (int i = 0; i < mapa.caractercrt.abilitati.size(); i++) {
            JLabel abilitate = null;
            if (mapa.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Ice")) {
                abilitate = new JLabel("  " + mapa.caractercrt.abilitati.get(i).getClass().getSimpleName());
                abilitate.setIcon(new ImageIcon("src/Joculet/snowflake1.png"));
                abilitate.setBounds(480, 190 + i * 50, 150, 50);
            }
            if (mapa.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Earth")) {
                abilitate = new JLabel(mapa.caractercrt.abilitati.get(i).getClass().getSimpleName());
                abilitate.setIcon(new ImageIcon("src/Joculet/earthh.png"));
                abilitate.setBounds(475, 190 + i * 50, 150, 50);
            }
            if (mapa.caractercrt.abilitati.get(i).getClass().getSimpleName().equals("Fire")) {
                abilitate = new JLabel("  " + mapa.caractercrt.abilitati.get(i).getClass().getSimpleName());
                abilitate.setIcon(new ImageIcon("src/Joculet/fire.png"));
                abilitate.setBounds(480, 190 + i * 50, 150, 50);
            }
            panel.add(abilitate);
        }
        viata.setText("\nHP: " + mapa.caractercrt.viatacurenta);
        exp.setText("\nEXPERIENTA: " + mapa.caractercrt.experienta);
        nivel.setText("\nNIVEL: " + mapa.caractercrt.nivel);
        monede.setText("\nMONEDE: " + mapa.caractercrt.inventar.NrMonede);
        frame.add(panel2, BorderLayout.EAST);
        frame.revalidate();
        frame.add(panel);
        frame.pack();
        frame.show();
    }
}


