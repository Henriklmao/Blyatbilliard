import sum.kern.Bildschirm;
import sum.werkzeuge.Uhr;

import java.util.ArrayList;

public class Main {
    static boolean IsGameOver = false;
    static double aw = .1;
    static double ah = .1;
    static double bw = .6;
    static double bh = .1;
    static double ballAS = 6;
    static double ballBS = 5;

    public static void main(String[] args) {
        Bildschirm bildschirm = new Bildschirm(true);

        Tisch tischA = new Tisch();
        Tisch tischB = new Tisch();

        var KugelnA = new ArrayList<Kugel>();
        var KugelnB = new ArrayList<Kugel>();

        for (int i = 0; i < 70; i++) { // middle value is number of balls
            Kugel kugel = new Kugel();
            KugelnA.add(kugel);
        }
        for (int i = 0; i < 100; i++) { // middle value is number of balls
            Kugel kugel = new Kugel();
            KugelnB.add(kugel);
        }

        // Init draw of tables
        tischA.zeichnen(bildschirm.breite(), bildschirm.hoehe(), aw, ah); // Factor of Breite and Höhe
        tischB.zeichnen(bildschirm.breite(), bildschirm.hoehe(), bw, bh);

        init(bildschirm, KugelnA, KugelnB);
        Program(bildschirm, tischA, tischB, KugelnA, KugelnB);

    }

    static void init(Bildschirm bildschirm, ArrayList<Kugel> KugelnA, ArrayList<Kugel> KugelnB) {
        bildschirm.setTitle("8-Ball 2.0");
        System.out.println("Made by Henrik B.");

        for (int i = 0; i < KugelnA.size(); i++) {
            KugelnA.get(i).zeichneKugel(bildschirm, aw, ah, ballAS, i);
        }
        for (int i = 0; i < KugelnB.size(); i++) {
            KugelnB.get(i).zeichneKugel(bildschirm, bw, bh, ballBS, i);
        }
    }

    static void Program(Bildschirm bildschirm, Tisch tischA, Tisch tischB, ArrayList<Kugel> KugelnA, ArrayList<Kugel> KugelnB) {
        while (!IsGameOver) {
            tischA.zeichnen(bildschirm.breite(), bildschirm.hoehe(), aw, ah);
            tischB.zeichnen(bildschirm.breite(), bildschirm.hoehe(), bw, bh);

            for (int i = 0; i < KugelnA.size(); i++) {
                KugelnA.get(i).bewegeKugel(bildschirm, aw, ah, ballAS, tischA);
            }
            for (int i = 0; i < KugelnB.size(); i++) {
                KugelnB.get(i).bewegeKugel(bildschirm, bw, bh, ballBS, tischB);
            }
            bildschirm.zeichneDich();

            // Test for death
            for (int i = 0; i < KugelnA.size(); i++) {
                if (!KugelnA.get(i).isAlive()) {
                    KugelnA.remove(i);
                    System.out.println(i + " of A is dead");// announce death
                }
            }
            for (int i = 0; i < KugelnB.size(); i++) {
                if (!KugelnB.get(i).isAlive()) {
                    KugelnB.remove(i);
                    System.out.println(i + " of B is dead");// announce death
                }
            }

            // Test for Game Over
            if (KugelnA.isEmpty() && KugelnB.isEmpty()) {
                IsGameOver = true;
                System.out.println("Game Over");
            }
            Uhr uhr = new Uhr();
            uhr.warte(20);
        }
        // Placeholder for Gameover screen.

    }
}
