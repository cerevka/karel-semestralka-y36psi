package karel;

/**
 * @version 1.0
 * @author Jan Cermak (cermaja9@fel.cvut.cz) & Tomas Cerevka (cerevtom@fel.cvut.cz)
 * Semestrální práce z Y36PSI - práce číslo 1 - Karel server
 * zadání: https://dsn.felk.cvut.cz/wiki/vyuka/y36psi/cviceni/uloha1-karel-zadani
 * Dokonceno: 28.10.2009
 */

import java.awt.Point;

/**
 * Trida s aplikacni logikou serveru.
 */
public class ControlModul {
    //==SOUKROME TRIDNI PROMENNE================================================
    // slouzi k pocitani instanci a nasledne identifikaci klientu

    static int counter = 1;
    //==SOUKROME OBJEKTOVE PROMENNE=============================================
    // obsahuje cislo instance
    private int count = counter++;
    // souradnice robota
    private Point coordinate;
    // jmeno
    private String name;

    // mozne smery
    private enum Direction {

        NAHORU, VLEVO, DOLU, VPRAVO
    };
    // smer robota
    private Direction direction;
    // priznak rozbitosti robota
    private boolean error = false;
    // cislo porouchaneho bloku
    private int errorBlock = 0;
    private int countOfStep = 0;

    //==KONSTRUKTOR=============================================================
    public ControlModul() {
        // vygeneruje jmeno pro robota
        this.generateName();

        // vygeneruje vychozi souradnice pro robota
        this.generateCoordinate();

        // vygeneruje smer
        this.generateDirection();
    }

    //==SOUKROME OBJEKTOVE METODY===============================================
    private void generateCoordinate() {
        coordinate = new Point((int) (Math.random() * (16 - (-16)) + (-16)),
                (int) (Math.random() * (16 - (-16)) + (-16)));
    }

    private void generateName() {
        int number = (int) (Math.random() * 10);
        //int number = 0;

        switch (number) {
            case 0:
                name = "Pekelny stroj";
                break;
            case 1:
                name = "Deus ex Machina";
                break;
            case 2:
                name = "Nastroj dabla";
                break;
            case 3:
                name = "Divitvorny stroj";
                break;
            case 4:
                name = "Jesus";
                break;
            case 5:
                name = "Maniodepresivni Marwin";
                break;
            case 6:
                name = "Duch ze stroje";
                break;
            case 7:
                name = "Apokalypsa";
                break;
            case 8:
                name = "Maly bily kralicek";
                break;
            case 9:
                name = "Rada konstruktu";
                break;
            default:
                name = "Zahadny navstevnik";
                break;
        }
    }

    private void generateDirection() {
        int number = (int) (Math.random() * 4);

        switch (number) {
            case 0:
                direction = Direction.NAHORU;
                break;
            case 1:
                direction = Direction.DOLU;
                break;
            case 2:
                direction = Direction.VLEVO;
                break;
            case 3:
                direction = Direction.VPRAVO;
                break;
        }
    }

    /**
     * Metoda generujici poruchu s pravdepobnosti 25%.
     * Porouchane bloky muzou mit cislo 1 - 9.
     * @return true -> robot se porouchal, false -> nic se nestalo
     */
    private boolean generateError() {
        boolean result = false;
        // na desatem kroku se robot vzdy poroucha
        if (countOfStep == 10) {
            error = true;
            errorBlock = ((int) (Math.random() * 9 + 1));
            result = true;
            countOfStep = 0;

        } else {
            // poruchovost 25%
            if (((int) (Math.random() * 4)) == 3) {
                // generuje se blok od 1 do 9
                errorBlock = ((int) (Math.random() * 9 + 1));
                error = true;
                result = true;
            } else {
                errorBlock = 0;
                error = false;
            }
        }
        return result;
    }

    //==VEREJNE OBJEKTOVE METODY================================================
    /**
     * Metoda, ktera provede krok robota na planu mesta.
     * @return 1 = 250 OK, 2 = 530 Havarie, 3 = 570 Porucha blok x, 4 = 572
     *         Robot se rozpadl
     *         Na nove souradnice se da nasledne extra zeptat, stejne tak na
     *         porouchany blok -> zbytecne vraceni vice hodnot
     */
    public byte step() {
        byte result = 1;
        // pokud je robot porouchany a pokusi se udelat krok, nenavratne se
        //  rozpadne
        if (error) {
            result = 4;
        } else {
            // vyhodnoti se, zda robot nehavaroval
            if (generateError()) {
                // robot se porouchal
                result = 3;
            } else {
                // robot dal funguje OK - provede se krok
                switch (direction) {
                    case NAHORU:
                        coordinate.y++;
                        break;
                    case DOLU:
                        coordinate.y--;
                        break;
                    case VLEVO:
                        coordinate.x--;
                        break;
                    case VPRAVO:
                        coordinate.x++;
                        break;
                }
                countOfStep++;

                // zkontroluje se, zda robot nevysel mimo mesto
                if ((coordinate.x < (-17)) || (coordinate.x > 17) ||
                        (coordinate.y < (-17)) || (coordinate.y > 17)) {
                    result = 2;
                }
            }
        }
        return result;
    }

    /**
     * Zajisti otoceni robota o 90 stupnu.
     */
    public void left() {
        switch (direction) {
            case NAHORU:
                direction = Direction.VLEVO;
                break;
            case VLEVO:
                direction = Direction.DOLU;
                break;
            case DOLU:
                direction = Direction.VPRAVO;
                break;
            case VPRAVO:
                direction = Direction.NAHORU;
                break;
        }

    }

    /**
     * Metoda, ktera vrati tajemstvi, pokud robot stoji na souradnicich [0,0]. 
     * Pokud ne, metoda vraci tuto informaci v retezci "ERROR". Po pouziti teto
     * metody je treba ukoncit spojeni.
     * @return tajemstvi, kdyz souradnice neni [0,0], tak "ERROR"
     */
    public String secret() {
        String result = "ERROR";
        if (coordinate.x == 0 && coordinate.y == 0) {
            result = "Pozor, stojis na bombe.";
        }
        return result;
    }

    /**
     * Opravi porouchanu blok
     * @param block cislo bloku, ktery se ma opravit
     * @return true -> blok byl opraven, false -> na bloku nebyla porucha
     */
    public boolean repair(int block) {
        boolean result = false;

        // overeni, zda je nejaka porucha indikovana
        if (error) {
            // pokud se ma opravovat blok, ktery je opravdu poskozen
            if (errorBlock == block) {
                // odstrani se priznak poruchy
                error = false;
                // vrati se, ze byla porucha odstranena
                result = true;
            }
        }

        // pokud nebyla zadna porucha, vraci se false
        // pokud bylo zadano spatne cislo bloku a nic opraveno nebylo, vraci
        //   se false
        return result;
    }

    //==VEREJNE OBJETOVE METODY - GETERY A SETTERY==============================
    /**
     * Vrati jmeno robota
     * @return Jmeno robota
     */
    public String getName() {
        return name;
    }

    /**
     * Vrati souradnice robota
     * @return souradnice robota v podobe Stringu (x,y)
     */
    public String getCoordinate() {
        return "(" + coordinate.x + "," + coordinate.y + ")";
    }

    /**
     * Vrati cislo porouchaneho bloku
     * @return cislo porouchaneho bloku
     */
    public int getError() {
        return errorBlock;
    }

    /**
     * Vrati cislo instance - jednoznacna identifikace klienta
     * @return cislo instance
     */
    public int getCount() {
        return count;
    }
}
