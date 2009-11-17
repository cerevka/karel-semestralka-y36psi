package psi2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author honza
 */
public class Main {

    public static DatagramSocket prijem;
    public static DatagramPacket paket_cteni;
    public static DatagramPacket paket_odesilani;
    public static InetAddress adresa;
    public static int port;
    public static byte [] buf = new byte[266];

    /**
     * Určí o jaký jde příkaz a zavolá k němu příslušnou metodu
     * @param args0 - hostname, 1 - port, 2 - prikaz, 3 - zdroj, 4 - cil
     */
    public static void main(String[] args2) {
        String[] args = {"localhost", "3555", "PING"};

        osetreniNevalidnichVstupu(args);

       nastaveniSitoveKomunikace(args);

        System.out.println("Běží");


        while (true) {
            if (args[2].equals("PING")) {
                //     volejPing();
            } else {
                if (args[2].equals("LIST")) {
                    volejList(args[0], args[1]);
                } else {
                    if (args[2].equals("GET")) {
                        volejGet(args[0], args[1], args[3], args[4]);
                    } else {
                        if (args[2].equals("PUSH")) {
                            volejPush(args[0], args[1], args[3], args[4]);
                        }
                    }
                }
            }
        }
    }

    /**
     * Klient nejdříve naváže spojení a poté odešle serveru příkaz PING. Ten
     * odpoví paketem s daty a následně paketem s přízkakem FIN. Klient potvrdí
     * uzavření spojení ze strany serveru a sám uzavře spojení. Přijme potvrzení uzavření.
     * @param socket adresa
     */
    /*    public static void volejPing(DatagramSocket socket) {
    packet = new DatagramPacket(socket, 256, )


    try {
    socket.send(packet);
    } catch (IOException ex) {
    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    System.err.println("Hups, chyba u pingu u odesílání.");
    }
    }

    /**
     *
     * @param hostname - adresa
     * @param port - port
     */
    public static void volejList(String hostname, String port) {
    }

    /**
     *
     * @param hostname
     * @param port
     * @param zdroj
     * @param cil
     */
    public static void volejGet(String hostname, String port, String zdroj, String cil) {
    }

    /**
     *
     * @param hostname
     * @param port
     * @param zdroj
     * @param cil
     */
    public static void volejPush(String hostname, String port, String zdroj, String cil) {
    }

    /**
     * Provede kontrolu validnosti příkazu.
     * @param args - parametry z příkazové řádky
     */
    private static void osetreniNevalidnichVstupu(String args[]) {
        if (args.length == 0) {
            System.out.println("Není zadán žádný parametr. Program bude ukončen.");
            System.exit(0);
        }

        if (args[0].length() == 0) {
            System.out.println("Není zadaná adresa. Program bude ukončen.");
            System.exit(0);
        }

        if (args[1].length() != 4) {
            System.out.println("Chybně zadaný port. Program bude ukončen.");
            System.exit(0);
        }

        if ((args[2].equals("PING")) == false & (args[2].equals("LIST")) == false & (args[2].equals("GET")) == false & (args[2].equals("PUSH")) == false) {
            System.out.println("Chybný příkaz. Lze zadat pouze \"PING\", \"LIST\", \"GET\", nebo \"PUSH\". Program bude ukončen.");
            System.exit(0);
        }
    }

    /**
     * Nastavi se sitova komunkace tak, ze se vytvori DatagramPacket a DatagramSocket
     * a zacne se prijimat. Jako adresa se jim predhodi ta, ktera je zadana uzivatelem.
     * @param args - parametry z příkazové řádky
     */

    @SuppressWarnings("empty-statement")
    private static void nastaveniSitoveKomunikace(String[] args) {
        //udelam z toho stringu port jako int
        String portS = args[1];
        port = Integer.valueOf(portS);

        //vrati adresu v prijatelnem formatu
        try {
            adresa = InetAddress.getByName(args[0]);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(adresa);

        //=====ODELSANI=========================================================
        int [] data  = new int [266];

   //     String chciPoslat = "000000001234000001"; //delka retezce je 18
  //      data = chciPoslat.getBytes();
  //      System.out.println(data + ", " + data.length);
        //naplneni pole
//        for (int i = data.length; i < 264; i++) {
 //           data[i] = 0;
        }
        System.out.println("Data jsou: " + data + "a délka je" + data.length);
        paket_odesilani = new DatagramPacket(data, 265, adresa, port);
        System.out.println(paket_odesilani);
        try {
            prijem.send(paket_odesilani);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //======PRIJEM==========================================================
        //nastavim si abych mohl prijimat a odesilat data
        try {
            prijem = new DatagramSocket(port, adresa);
        } catch (SocketException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        int delkaPaketu = 265;
        int offset = 1; //offset = kde zacina ukazovatko
        paket_cteni = new DatagramPacket(buf, offset, delkaPaketu, adresa, port);

        //nastavim se abych mohl prijimat
        try {
            prijem.receive(paket_cteni);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Chyba IOException při čtení.");
        }
    }
}

