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
    public String jmeno = "";

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

    public static void kontrolaJmena(String text) {
        String jmenoTest = "";
        if (text.length()==0) {
            System.exit(0);
        }
        for(int i=0; i<text.length(); i++) {
             if (text.charAt(0) == ' ') {
                 if (jmenoTest.equals(jmeno)==false) {
                     System.exit(0);
                 }
             }
             jmenoTest += text.charAt(i);
        }
    }

    public static void main(String[] args) {
        jmeno = vygenerujJmeno();
        System.out.println("220 Oslovuj mne " + jmeno);

        Scanner vstup = new Scanner(System.in);
        String text = vstup.nextLine();

        kontrolaJmena(text);
    }
}
