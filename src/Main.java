/**
 *      ---Blyatbilliard Main class by Henrik---
 *
 *  Sum.kern (https://www.mg-werl.de/sum/) based simulation of 2D billiard balls on Tables
 *  Idea: School Project - Billiardspiel (2022 BGH)
 *
 */

import sum.kern.Bildschirm;

import java.util.ArrayList;

public class Main {
    static boolean IsGameOver = false;
    static double aw = .1;
    static double ah = .1;
    static double bw = .6;
    static double bh = .1;
    static double ballAS = 15;
    static double ballBS = 20;

    public static void main(String[] args) throws InterruptedException {
        Bildschirm bildschirm = new Bildschirm(true);

        Tisch tischA = new Tisch(bildschirm.breite()*.35, bildschirm.hoehe()*.85);
        Tisch tischB = new Tisch(bildschirm.breite()*.35, bildschirm.hoehe()*.85);
        tischA.bewegeBis(bildschirm.breite()*aw, bildschirm.hoehe()*ah);
        tischB.bewegeBis(bildschirm.breite()*bw, bildschirm.hoehe()*bh);

        var KugelnA = new ArrayList<Kugel>();
        var KugelnB = new ArrayList<Kugel>();

        for (int i = 0; i < 12; i++) { // middle value is number of balls
            Kugel kugel = new Kugel(i, ballAS, bildschirm, tischA);
            KugelnA.add(kugel);
        }
        for (int i = 0; i < 16; i++) { // middle value is number of balls
            Kugel kugel = new Kugel(i, ballBS, bildschirm, tischB);
            KugelnB.add(kugel);
        }

        // Init draw of tables
        tischA.zeichnen(); // Factor of Breite and Höhe
        tischB.zeichnen();

        init(bildschirm, KugelnA, KugelnB);
        Program(bildschirm, tischA, tischB, KugelnA, KugelnB);

    }

    static void init(Bildschirm bildschirm, ArrayList<Kugel> KugelnA, ArrayList<Kugel> KugelnB) {
        bildschirm.setTitle("8-Ball 2.0");
        System.out.println("Made by Henrik B.");

        for (Kugel value : KugelnA) {
            value.zeichneKugel();
        }
        for (Kugel kugel : KugelnB) {
            kugel.zeichneKugel();
        }
    }

    static void Program(Bildschirm bildschirm, Tisch tischA, Tisch tischB, ArrayList<Kugel> KugelnA,
            ArrayList<Kugel> KugelnB) throws InterruptedException {
        while (!IsGameOver) {
            System.gc();

            tischA.zeichnen();
            tischB.zeichnen();

            for (Kugel kugel : KugelnA) {
                kugel.bewegeKugel();
            }
            for (Kugel kugel : KugelnB) {
                kugel.bewegeKugel();
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
            // Construct two threads
            Collisionhandler collider1 = new Collisionhandler(KugelnA, ballAS);
            Collisionhandler collider2 = new Collisionhandler(KugelnB, ballBS);
            // Start both Threads
            collider1.start();
            collider2.start();
            // wait for both threads to be finished
            collider1.join();
            collider2.join();

        }
        // Placeholder for Gameover screen.

    }
}
