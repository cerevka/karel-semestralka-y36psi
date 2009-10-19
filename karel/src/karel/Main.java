package karel;

import java.util.Random;
import java.util.Scanner;

/**
 * @version 1.0
 * @author Jan Cermak (cermaja9@fel.cvut.cz) & Tomas Cerevka (cerevtom@fel.cvut.cz)
 * Semestrální práce z Y36PSI - práce číslo 1 - Karel
 * zadání: https://dsn.felk.cvut.cz/wiki/vyuka/y36psi/cviceni/uloha1-karel-zadani
 */
public class Main {

    static int pokusJmeno = 0; //pokusy na jmeno
    static String jmeno = "";
    static int smer = 0;
    static int poziceX = 0;
    static int poziceY = 0;
    static int pokus = 5;
    static int porucha = 0;
    static Random rand = new Random();

    public static String zacniHru() {
        String jmenoGen = "";
        double cisloD = (10 * Math.random());
        poziceX = rand.nextInt() % 16; // rozsah -16 až +16
        poziceY = rand.nextInt() % 16; // rozsah -16 až +16
        pokus = (Math.abs(rand.nextInt() % 12) + 1); //rozsah 1 - 13
        smer = (Math.abs(rand.nextInt() % 3) + 1); //rozsah 1 - 4


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

    public static void jePorucha() {
        if (porucha != 0) { //pokud je to porucha
            System.out.println("572 ROBOT SE ROZPADL");
            System.exit(0);
        }
    }

    public static boolean postupuj(String text, int chyba) {
        //generovani poruchy
        if ((Math.abs(rand.nextInt() % 3) + 1) == 4) { //generovani 1-4, kazdy ctvrty je porouchany
            porucha = (Math.abs(rand.nextInt() % 8) + 1); //generovani bloku poruchy
        }
        //konec generovani
        String postup = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if ((postup.equals("KROK") == true) || (postup.equals("VLEVO") == true) || (postup.equals("ZVEDNI") == true)) {
                    jePorucha(); //kotrola jestli je porucha
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
                        return false;
                    }
                    //konec VLEVO
                    //ZVEDNI
                    if ((postup.equals("ZVEDNI") == true) && (text.length() == 6) && (poziceX == 0) && (poziceY == 0)) {
                        System.out.println("221 USPECH Text tajemství");
                        return true;
                    } else {
                        if (postup.equals("ZVEDNI") == true) {
                            System.out.println("550 NELZE ZVEDNOUT ZNACKU");
                            pokus--;
                            return false;
                        }

                    }
                    //konec ZVEDNI
                    //OPRAVIT
                } else {
                    if (postup.equals("OPRAVIT") == true) {
                        int vratka = 0;
                        for (int p = (i + 1); p < text.length(); p++) {
                            vratka += text.charAt(p);
                        }
                        if (porucha == 0) { // je nejka porucha vubec?
                            System.out.println("571 NENI PORUCHA");
                            pokus--;
                        } else {
                            if (vratka != porucha) { //nesouhlasi porouchanj blok
                                System.out.println("571 NENI PORUCHA");
                                pokus--;
                            } else {
                                if (vratka == porucha) { //opraveni poruchy
                                    porucha = 0;
                                }
                            }
                        }
                    } else {
                        System.out.println("500 NEZNAMY PRIKAZ");
                        pokus--;
                    }
                    //konec OPRAVIT
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
            String text = vstup.nextLine(); //vstup uzivatele

            String postup = kontrolaJmena(text); //zkontroluje jmeno
            vyhra = postupuj(postup, chyba); //urci vyhra true/flase

            if (pokus <= 0) {  //zkotroluje pocet pokusu
                System.out.println("Překročen počet pokusů.s Ukončuji spojení.");
                System.exit(0);
            }

        }
    }
}
