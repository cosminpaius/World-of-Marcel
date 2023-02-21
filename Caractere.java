package Joculet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class Caractere{
    public Caractere(int indicecont, ArrayList<Account> conturi) {
        JFrame frame = new JFrame(" Pagina de start");
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(360, 340));
        panel.setLayout(null);

        java.net.URL imgUrl = getClass().getResource("world of marcel.png");
        ImageIcon icon = new ImageIcon(imgUrl);
        Image Image = icon.getImage();
        frame.setIconImage(Image);

        ImageIcon caract = new ImageIcon(getClass().getResource("caracter.png"));
        Image img = caract.getImage();
        Image imgscale = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        caract = new ImageIcon(imgscale);
        JLabel image = new JLabel();
        image.setBounds(120, 10, 120, 120);
        image.setIcon(caract);
        panel.add(image,BorderLayout.NORTH);


        JLabel Txt = new JLabel("ATI ALES CONTUL CU URMATOARELE CARACTERE: ");
        Txt.setBounds(20, 140, 400, 25);
        Txt.setFont(new Font("Courier", Font.BOLD,12));
        Txt.setForeground(Color.BLACK);
        panel.add(Txt);

        Vector caractere = new Vector<>();
        for(int i = 0 ; i < conturi.get(indicecont).personaje.size(); i++)
            caractere.add(conturi.get(indicecont).personaje.get(i).toString1());
        JList<String> list = new JList<>(caractere);
        list.setLayoutOrientation(JList.VERTICAL_WRAP);
        list.setVisibleRowCount(5);

        JPanel panel2 = new JPanel();
        panel2.add(list,BorderLayout.PAGE_START);
        JButton buton = new JButton("Alege caracterul");

        buton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(list.isSelectionEmpty())
                    return;
                int indexcaracter = list.getSelectedIndex();
                frame.dispose();
                Cell cell = new Cell(0, 0, new CellElement() {
                    @Override
                    public char toCharacter() {
                        return 'N';
                    }
                });
                Grid mapa = Grid.generareint(CharacterFactory.factory(conturi.get(indicecont).personaje.get(indexcaracter).getClass().getSimpleName(), conturi.get(indicecont).personaje.get(indexcaracter).nume, conturi.get(indicecont).personaje.get(indexcaracter).nivel, conturi.get(indicecont).personaje.get(indexcaracter).experienta), cell);
                new Joc(indexcaracter, mapa);
            }
        });


        panel2.add(buton, BorderLayout.PAGE_END);
        panel2.setBounds(0,160,350,180);
        frame.add(panel2);

        frame.add(panel);
        frame.setVisible(true);
        frame.pack();
        frame.show();
    }
}
