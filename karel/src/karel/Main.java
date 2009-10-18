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
    static int smer = 0;

    public static String zacniHru() {
        String jmenoGen = "";
        double cisloD = (10 * Math.random());
        int cislo = (int) cisloD;
        if (cislo == 0) {
            jmenoGen = "blb";
            smer = 1;
        }
        if (cislo == 1) {
            jmenoGen = "blb";
            smer = 2;
        }
        if (cislo == 2) {
            jmenoGen = "blb";
            smer = 3;
        }
        if (cislo == 3) {
            jmenoGen = "blb";
            smer = 4;
        }
        if (cislo == 4) {
            jmenoGen = "blb";
            smer = 1;
        }
        if (cislo == 5) {
            jmenoGen = "blb";
            smer = 2;
        }
        if (cislo == 6) {
            jmenoGen = "blb";
            smer = 3;
        }
        if (cislo == 7) {
            jmenoGen = "blb";
            smer = 4;
        }
        if (cislo == 8) {
            jmenoGen = "blb";
            smer = 1;
        }
        if (cislo == 9) {
            jmenoGen = "blb";
            smer = 2;
        }
        if (cislo == 10) {
            jmenoGen = "blb";
            smer = 3;
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
                    String vratka = "";
                    for (int p = (i + 1); p < text.length(); p++) {
                        vratka += text.charAt(p);
                    }
                    return vratka;
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

    public static boolean postupuj(String text) {
        String postup = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if ((postup.equals("KROK") == true) || (postup.equals("VLEVO") == true) || (postup.equals("ZVEDNI") == true)) {
                    i = text.length(); //toto ukonci zapisovani
                } else {
                    if (postup.equals("OPRAVIT") == true) {
                        String vratka = "";
                        for (int p = (i + 1); p < text.length(); p++) {
                            vratka += text.charAt(p);
                        }
                        //oprav(); //preda se k oprave
                    } else {
                        System.out.println("Chyba");
                    }
                }
                postup += text.charAt(i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        jmeno = zacniHru();
        System.out.println("220 Oslovuj mne " + jmeno);
        boolean vyhra = false;

        while (vyhra == false) {
            Scanner vstup = new Scanner(System.in);
            String text = vstup.nextLine();

            String postup = kontrolaJmena(text);
            vyhra = postupuj(postup);
        }

    }
}
