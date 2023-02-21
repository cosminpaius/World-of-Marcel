package Joculet;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Account {
    Information info;
    ArrayList<Character> personaje;
    int numarjocuri;
    @Override
    public String toString() {
        return "" + info +
                "\nCaracterele din cont: \n" + personaje;
    }

    public Account(Information info, ArrayList<Character> personaje, int numarjocuri){
        this.info = info;
        this.numarjocuri = numarjocuri;
        this.personaje = personaje;
    }
    static class Information{
        private Credentials credentiale;
        private TreeSet<String> jocuripreferate;
        private String nume;
        private String tara;
        private Information(InformationBuilder bld){
            this.nume = bld.nume;
            this.tara = bld.tara;
            this.jocuripreferate = bld.jocuripreferate;
            this.credentiale = bld.credentiale;
        }

        public Credentials getCredentiale() {
            return credentiale;
        }

        public String getNume() {
            return nume;
        }

        public void setCredentiale(Credentials credentiale) {
            this.credentiale = credentiale;
        }

        public void setJocuripreferate(TreeSet<String> jocuripreferate) {
            this.jocuripreferate = jocuripreferate;
        }

        public void setNume(String nume) {
            this.nume = nume;
        }

        public void setTara(String tara) {
            this.tara = tara;
        }

        public String getTara() {
            return tara;
        }

        public TreeSet<String> getJocuripreferate() {
            return jocuripreferate;
        }

        @Override
        public String toString() {
            return " " + credentiale +
                    "jocuripreferate = " + jocuripreferate +
                    "\nNume = " + nume +
                    "\nTara = " + tara ;
        }

        public static class InformationBuilder{
            private Credentials credentiale;
            private TreeSet<String> jocuripreferate;
            private String nume;
            private String tara;
            public InformationBuilder(Credentials credentiale, String nume){
                this.credentiale = credentiale;
                this.nume = nume;
            }
            public InformationBuilder setjocuripreferate(TreeSet<String> jocuripreferate){
                this.jocuripreferate = jocuripreferate;
                return this;
            }
            public InformationBuilder settara(String tara){
                this.tara = tara;
                return this;
            }
            public Information bld(){
                return new Information(this);
            }
        }
    }
}
