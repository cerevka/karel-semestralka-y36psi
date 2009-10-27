/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package karel;

import java.awt.Point;

/**
 * Trida s aplikacni logikou serveru.
 */
public class ControlModul {

    //==SOUKROME OBJEKTOVE PROMENNE=============================================
    private Point coordinate;
    private String name;

    private enum Direction {

        NAHORU, VLEVO, DOLU, VPRAVO
    };
    private Direction direction;
    private boolean error = false;
    private int errorBlock = 0;

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
        //int number = (int) (Math.random() * 10);
        int number = 0;

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
        // poruchovost 25%
        if (((int) (Math.random() * 4)) == 3) {
            // generuje se blok od 1 do 9
            errorBlock = ((int) (Math.random() * 9 + 1));
            result = true;
        } else {
            errorBlock = 0;
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
                    case NAHORU : coordinate.y++; break;
                    case DOLU : coordinate.y--; break;
                    case VLEVO : coordinate.x--; break;
                    case VPRAVO : coordinate.x++; break;                    
                }
                
                // zkontroluje se, zda robot nevysel mimo mesto
                if ((coordinate.x<(-17)) || (coordinate.x>17) ||
                    (coordinate.y<(-17)) || (coordinate.y>17)) {
                    result = 2;                    
                }
            }
        }
        return result;
    }

    //==VEREJNE OBJETOVE METODY - GETERY A SETTERY==============================
    public String getName() {
        return name;
    }

    public String getCoordination() {
        return "(" + coordinate.x + "," + coordinate.y + ")";
    }
}
