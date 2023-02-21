package Joculet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Logare {
    public Logare(ArrayList<Account> conturi){
        JFrame frame = new JFrame(" Autentificare");
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setMinimumSize(new Dimension(350,350));
        panel.setLayout(null);

        java.net.URL imgUrl = getClass().getResource("world of marcel.png");
        ImageIcon icon = new ImageIcon(imgUrl);
        Image Image = icon.getImage();
        frame.setIconImage(Image);
        ImageIcon login = new ImageIcon(getClass().getResource("login.png"));
        Image img = login.getImage();
        Image imgscale = img.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        login = new ImageIcon(imgscale);
        JLabel image = new JLabel();
        image.setBounds(120,10,120,120);
        image.setIcon(login);
        panel.add(image);

        JLabel email = new JLabel("Email: ");
        email.setBounds(30,150,100,25);
        panel.add(email);
        JLabel pass = new JLabel("Parola: ");
        pass.setBounds(30,190,100,30);
        panel.add(pass);
        JTextField addemail = new JTextField(50);
        addemail.setBounds(100,150,150,25);
        addemail.setEnabled(true);
        JPasswordField addpass = new JPasswordField(50);
        addpass.setBounds(100,190,150,25);
        addpass.setEnabled(true);
        panel.add(addemail);
        panel.add(addpass);

        JButton logare = new JButton("Login");
        logare.setBounds(110,230,120,30);

        JLabel fail = new JLabel("");
        fail.setBounds(50,270,200,25);
        fail.setForeground(Color.RED);
        panel.add(fail);
        JLabel succes = new JLabel("");
        succes.setBounds(50,270,200,25);
        succes.setForeground(Color.GREEN);
        panel.add(succes);

        logare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = addemail.getText();
                String parola = addpass.getText();
                int oknume = -1, okparola = -1;
                for (int i = 0; i < conturi.size(); i++) {
                    if (conturi.get(i).info.getCredentiale().getEmail().equals(user))
                        oknume = i;
                }
                if(oknume == -1){
                    fail.setText("Acest cont nu exista!");
                }
                else {
                    if (conturi.get(oknume).info.getCredentiale().getParola().equals(parola))
                        okparola = 1;
                    if (oknume == -1 || okparola == -1) {
                        fail.setText("Acest cont nu exista.");
                    }
                    else {
                        fail.setVisible(false);
                        succes.setText("Conectare reusita!");
                        frame.dispose();
                        new Caractere(oknume, conturi);
                }
                }
            }
        });

        panel.add(logare);
        frame.setVisible(true);
        frame.pack();
        frame.show();
    }
}
