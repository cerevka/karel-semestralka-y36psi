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
    private enum Direction {NAHORU, DOLU, VLEVO, VPRAVO};
    private Direction direction;
    private boolean error = false;

    //==KONSTRUKTOR=============================================================
    public ControlModul() {
        // vygeneruje jmeno pro robota
        this.generateName();
        System.out.println("Jmeno robota je: "+name);

        // vygeneruje vychozi souradnice pro robota
        this.generateCoordinate();
        System.out.println("Souradnice jsou - X:"+coordinate.x+" Y:"+coordinate.y);

        // vygeneruje smer
        this.generateDirection();
        System.out.println(direction.name());
    }

    //==SOUKROME OBJEKTOVE METODY===============================================
    private void generateCoordinate() {
        coordinate = new Point ((int) (Math.random() * (16 - (-16)) + (-16)),
                                (int) (Math.random() * (16 - (-16)) + (-16)));
    }

    private void generateName() {
        int number = (int) (Math.random()*10);

        switch (number) {
            case 0 : name = "Pekelny stroj"; break;
            case 1 : name = "Deus ex Machina"; break;
            case 2 : name = "Nastroj dabla"; break;
            case 3 : name = "Divitvorny stroj"; break;
            case 4 : name = "Jesus"; break;
            case 5 : name = "Maniodepresivni Marwin"; break;
            case 6 : name = "Duch ze stroje"; break;
            case 7 : name = "Apokalypsa"; break;
            case 8 : name = "Maly bily kralicek"; break;
            case 9 : name = "Rada konstruktu"; break;
            default: name = "Zahadny navstevnik"; break;
        }
    }

    private void generateDirection() {
        int number = (int) (Math.random() * 4);

        switch (number) {
            case 0 : direction = Direction.NAHORU; break;
            case 1 : direction = Direction.DOLU; break;
            case 2 : direction = Direction.VLEVO; break;
            case 3 : direction = Direction.VPRAVO; break;
        }
    }
    
    //==VEREJNE OBJEKTOVE METODY================================================
    public void closeConnection() {
        
    }




}
