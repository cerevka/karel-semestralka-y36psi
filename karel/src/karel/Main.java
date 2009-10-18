/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package karel;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author honza
 */
public class Main {

    static int pokusJmeno = 0; //pokusy na jmeno
    static String jmeno = "";
    static int smer = 0;
    static int poziceX = 0;
    static int poziceY = 0;
    static int pokus = 5;

    public static String zacniHru() {
        String jmenoGen = "";
        double cisloD = (10 * Math.random());
        Random rand = new Random();
        poziceX = rand.nextInt() % 16; // rozsah -16 až +16
        poziceY = rand.nextInt() % 16; // rozsah -16 až +16
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

    public static boolean postupuj(String text, int chyba) {
        String postup = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if ((postup.equals("KROK") == true) || (postup.equals("VLEVO") == true) || (postup.equals("ZVEDNI") == true)) {
                    i = text.length(); //toto ukonci zapisovani

                    // KROK
                    if ((postup.equals("KROK") == true) && (text.length() == 4)) {
                        if (smer == 1) {
                            poziceX++;
                        } else {
                            if (smer == 2) {
                                poziceY++;
                            } else {
                                if (smer == 3) {
                                    poziceX--;
                                } else {
                                    if (smer == 4) {
                                        poziceY--;
                                    } else {
                                        System.out.println("Nastala neznámá chyba");
                                    }
                                }
                            }

                        }
                        return false;
                    }
                    //konec KROK
                    //VLEVO
                    if ((postup.equals("VLEVO") == true) && (text.length() == 5)) {
                        if (smer == 4) {
                            smer = 1;
                        } else {
                            smer++;
                        }
                    }
                    return false;
                    //konec VLEVO
                    //ZVEDNI
                    if ((postup.equals("ZVEDNI") == true) && (text.length() == 6) && (poziceX==0) && (poziceY==0)) {
                        System.out.println("221 USPECH Text tajemství");
                        return true;
                    } else {
                        if (postup.equals("ZVEDNI")==true) {
                           System.out.println("550 NELZE ZVEDNOUT ZNACKU");
                           pokus--;
                           return false;
                        }

                    }
                    //konec ZVEDNI

                } else {
                    if (postup.equals("OPRAVIT") == true) {
                        int vratka = 0;
                        for (int p = (i + 1); p < text.length(); p++) {
                            vratka += text.charAt(p);
                        }
                        if (vratka == 0) {
                            System.out.println("571 NENI PORUCHA");
                            pokus--;
                        } else {
                            if (vratka != chyba) {
                                System.out.println("571 NENI PORUCHA");
                                pokus--;
                            }
                        }
                    } else {
                        System.out.println("500 NEZNAMY PRIKAZ");
                        pokus--;
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
        int chyba = 0;

        while (vyhra == false) {
            Scanner vstup = new Scanner(System.in);
            String text = vstup.nextLine();

            String postup = kontrolaJmena(text);
            vyhra = postupuj(postup, chyba);
        }

    }
}
