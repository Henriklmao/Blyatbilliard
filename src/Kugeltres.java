import sum.kern.Bildschirm;
import sum.kern.Buntstift;

import java.util.Random;

public class Kugeltres {
    static Buntstift kugelC = new Buntstift();
    static Random rng = new Random();
    static Random randomC = new Random();
    static int angC;
    static int speedC = 20; // Verzögerung zwischen bewegungen in 1/1000 sec.
    static int reset;

    static void zeichneKugel(Bildschirm bildschirm) {

        int angC = rng.nextInt(359);
        System.out.println("Random bearing Kugeluno:" + angC + "°");
        kugelC.bewegeBis(bildschirm.breite() * 0.5, bildschirm.hoehe() * 0.5);
        kugelC.normal();
        kugelC.zeichneKreis(20);
        kugelC.dreheBis(angC);
    }

    static void bewegeKugel(Bildschirm bildschirm, int reset) {

        kugelC.radiere();
        kugelC.zeichneKreis(20);
        kugelC.normal();
        kugelC.bewegeUm(20); // normal: speedC / 20px
        kugelC.zeichneKreis(20);

        /*
        if (speedC < 5) {
            speedC = speedC - 1;
            if (speedC == 1) {
                System.out.println("Object Kugeluno stopped.");
            }
        }
        */

        // Test for wall colision
        if (reset > 5) {
            if (kugelC.hPosition() < bildschirm.breite() * 0.12 || kugelC.hPosition() > bildschirm.breite() * 0.88 || kugelC.vPosition() < bildschirm.hoehe() * 0.22 || kugelC.vPosition() > bildschirm.hoehe() * 0.78) {
                speedC = speedC - 5; // Slow down by 3pixels per tick each time
                Bounce(bildschirm);
            }
        }
    }


    private static void Bounce(Bildschirm bildschirm) {
        reset = 0;

        // Set correct bounce angle
        if (kugelC.hPosition() < bildschirm.breite() * 0.12) {
            if (randomC.nextInt(1, 10) < 5) {
                angC = randomC.nextInt(1, 80);
            } else {
                angC = randomC.nextInt(260, 359);
            }

        } else if (kugelC.hPosition() > bildschirm.breite() * 0.88) {
            angC = randomC.nextInt(100, 260);
        } else if (kugelC.vPosition() < bildschirm.hoehe() * 0.22) {
            angC = randomC.nextInt(170, 350);
        } else if (kugelC.vPosition() > bildschirm.hoehe() * 0.78) {
            angC = randomC.nextInt(10, 170);
        } else {
            System.out.println("Illegal position");
        }
        kugelC.dreheBis(angC);
        System.out.println("Bounce angC: " + angC);
        Tisch.tischLoop();
    }
}