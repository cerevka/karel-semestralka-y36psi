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
    static int porucha = 0;
    static Random rand = new Random();
    static boolean chybka = false;

    public static String zacniHru() {
        String jmenoGen = "";
        double cisloD = (10 * Math.random());
        poziceX = rand.nextInt() % 17; // rozsah -17 až +17
        poziceY = rand.nextInt() % 17; // rozsah -17 až +17
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
            ukonciSpojeni();
        }
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') { //narazime na mezeru
                if (jmenoTest.equals(jmeno) == false) {
                    jmenoTest += text.charAt(i); //jmeno neopodvida, pokracuj
                    i++;
                } else {
                    //pokud jmeno odpovida
                    String vratka = "";
                    for (int p = (i + 1); p < text.length(); p++) {
                        vratka += text.charAt(p); //vrat zbytek
                    }
                    return vratka;
                }
            }
            jmenoTest += text.charAt(i);
            if (i == text.length() - 1) { //pokud je i mensi jak delka;
            }
        }
        System.out.println("Chybne jmeno");
        chybka = true;
        return nic;
    }

    public static void jePorucha() {
        if (porucha != 0) { //pokud je to porucha
            System.out.println("572 ROBOT SE ROZPADL");
            System.exit(0);
        }
    }

    public static boolean postupuj(String text) {
        int i = 0;
        if (text.length() == 0) {//kotrola zda predchotzi metoda zamitla kontrolu jmena
            chybka = true;
            return false;
        }

        String postup = "";
        for (i = 0; i < text.length(); i++) { //jede cyklus
            postup += text.charAt(i);
        }
        if ((text.equals("KROK") == true) || (text.equals("VLEVO") == true) || (text.equals("ZVEDNI") == true)) { //pokud na ni narazi, zkontroluje jetsli text neni
            jePorucha(); //kotrola jestli je porucha
            // KROK
            if ((text.equals("KROK") == true) && (text.length() == 4) && (porucha == 0)) {
                if (smer == 1) {
                    if (poziceX < 17) {
                        poziceX++;
                    } else {
                        System.out.println("530 HAVARIE");
                        ukonciSpojeni();
                    }
                } else {
                    if (smer == 2) {
                        if (poziceY < 17) {
                            poziceY++;
                        } else {
                            System.out.println("530 HAVARIE");
                            ukonciSpojeni();
                        }
                    } else {
                        if (smer == 3) {
                            if (poziceX > -17) {
                                poziceX--;
                            } else {
                                System.out.println("530 HAVARIE");
                                ukonciSpojeni();
                            }
                        } else {
                            if (smer == 4) {
                                if (poziceY > -17) {
                                    poziceY--;
                                } else {
                                    System.out.println("530 HAVARIE");
                                    ukonciSpojeni();
                                }
                            } else {
                                System.out.println("Nastala neznámá chyba");
                                chybka = true;
                            }
                        }
                    }
                }

                return false;
            }
            //konec KROK
            //VLEVO
            if ((text.equals("VLEVO") == true) && (text.length() == 5) && (porucha == 0)) {
                if (smer == 4) {
                    smer = 1;
                } else {
                    smer++;
                }

                return false;
            }
            //konec VLEVO
            //ZVEDNI
            if ((text.equals("ZVEDNI") == true) && (text.length() == 6) && (poziceX == 0) && (poziceY == 0) && (porucha == 0)) {
                System.out.println("221 USPECH Text tajemství");
                chybka = true;
                return true;
            } else {
                if (text.equals("ZVEDNI") == true && (porucha == 0)) {
                    System.out.println("550 NELZE ZVEDNOUT ZNACKU");
                    chybka = true;
                    ukonciSpojeni();
                }

            }
        }
        //konec ZVEDNI
        //zacatek OPRAVIT
        if (text.length() > 8) {
            String oprava = "";
            for (int j = 0; j <= 6; j++) {
                oprava += text.charAt(j);
            }

            if (oprava.equals("OPRAVIT") == true) {
                if (String.valueOf(porucha).charAt(0) != text.charAt(8)) { //nesouhlasi porouchanej blok
                    System.out.println("571 NENI PORUCHA");
                    chybka = true;
                    ukonciSpojeni();
                } else {
                    if (text.charAt(8) == String.valueOf(porucha).charAt(0)) { //opraveni poruchy
                        porucha = 0;
                        return false;
                    } else {
                        System.out.println("571 NENI PORUCHA");
                        chybka = true;
                        ukonciSpojeni();
                    }
                }
            }  //konec OPRAVIT
        }
        System.out.println("500 NEZNAMY PRIKAZ");
        chybka = true;
        return false;

    }

    public static void ukonciSpojeni() {
        System.out.println("Spojeni je ukonceno");
        System.exit(0);
    }

    public static void vypisPolohu() {
        if (chybka == false) {
            System.out.println("250 OK (" + poziceX + "," + poziceY + ")");
        }
    }

    public static void generujPoruchu() {
        //generovani poruchy
        if ((Math.abs(rand.nextInt() % 3) + 1) == 3) { //generovani 1-4, kazdy ctvrty je porouchany
            porucha = (Math.abs(rand.nextInt() % 8) + 1); //generovani bloku poruchy
            System.out.println("570 PORUCHA BLOK " + porucha);
        } else {
            porucha = 0;
            vypisPolohu();
        }
        //konec generovani poruchy

    }

    public static void main(String[] args) {
        jmeno = zacniHru();
        System.out.println("220 Oslovuj mne " + jmeno);
        boolean vyhra = false;

        while (vyhra == false) {
            
            Scanner vstup = new Scanner(System.in);
            String text = vstup.nextLine(); //vstup uzivatele

            chybka = false;
            String postup = kontrolaJmena(text); //zkontroluje jmeno
            vyhra = postupuj(postup); //urci vyhra true/flase
            generujPoruchu(); //vygeneruje se porucha

        }
    }
}
