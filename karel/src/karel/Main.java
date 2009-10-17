/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package karel;

import java.util.Scanner;

/**
 *
 * @author honza
 */
public class Main {

    static int pokusJmeno = 0; //pokusy na jmeno
    static String jmeno = "";

    public static String vygenerujJmeno() {
        String jmenoGen = "";
        double cisloD = (10 * Math.random());
        int cislo = (int) cisloD;
        if (cislo == 0) {
            jmenoGen = "blb";
        }
        if (cislo == 1) {
            jmenoGen = "blb";
        }
        if (cislo == 2) {
            jmenoGen = "blb";
        }
        if (cislo == 3) {
            jmenoGen = "blb";
        }
        if (cislo == 4) {
            jmenoGen = "blb";
        }
        if (cislo == 5) {
            jmenoGen = "blb";
        }
        if (cislo == 6) {
            jmenoGen = "blb";
        }
        if (cislo == 7) {
            jmenoGen = "blb";
        }
        if (cislo == 8) {
            jmenoGen = "blb";
        }
        if (cislo == 9) {
            jmenoGen = "blb";
        }
        if (cislo == 10) {
            jmenoGen = "blb";
        }
        return jmenoGen;
    }

    public static String kontrolaJmena(String text) {
        String jmenoTest = "";
        String nic = "";
        if (text.length() == 0) { //pokud je parametr prazdnej, ukonci to
            System.exit(0);
        }
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (jmenoTest.equals(jmeno) == false) {
                    jmenoTest += text.charAt(i);
                    i++;
                } else {
                    //pokud jmeno odpovida
                    for (int p = (i+1); p == 7; p++) {
                        String vratka = "";
                        vratka += text.charAt(p);
                        System.out.println(p);
                        System.out.println(vratka);
                        return vratka;
                    }
                }
            }
            jmenoTest += text.charAt(i);
            if (i == text.length() - 1) { //pokud je i mensi jak delka
                System.exit(0);
            }
        }
        System.exit(0);
        return nic;
    }

    public static void main(String[] args) {
        jmeno = vygenerujJmeno();
        System.out.println("220 Oslovuj mne " + jmeno);

        Scanner vstup = new Scanner(System.in);
        String text = vstup.nextLine();

        String postup = kontrolaJmena(text);
        System.out.println(postup);
    }
}
