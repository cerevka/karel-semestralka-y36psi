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
        String vratka = "";
        if (text.length() == 0) { //pokud je parametr prazdnej, ukonci to
            System.exit(0);
        }
        
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (jmenoTest.equals(jmeno) == false) {
                    jmenoTest += text.charAt(i);
                    i++;
                } else {
                    //if ()
                    for (int j = text.length() - i; j < text.length(); j++) {
                        vratka += text.charAt(j);
                        return vratka;
                    }
                }
            }
            jmenoTest += text.charAt(i);
            if (i == text.length() - 1) {
                System.exit(0);
            }
        }
        System.exit(0);
        return vratka;
    }

    public static void main(String[] args) {
        jmeno = vygenerujJmeno();
        System.out.println("220 Oslovuj mne " + jmeno);

        Scanner vstup = new Scanner(System.in);
        String text = vstup.nextLine();

        kontrolaJmena(text);
        System.out.println("jde to");
    }
}
