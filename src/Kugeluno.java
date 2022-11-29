import sum.kern.Bildschirm;
import sum.kern.Buntstift;

import java.util.Random;

public class Kugeluno {
    static Buntstift kugelA = new Buntstift();
    static Random rng = new Random();
    static Random randomA = new Random();
    static int angA;
    static int speedA = 20; // Verzögerung zwischen bewegungen in 1/1000 sec.
    static int reset;

    static void zeichneKugel(Bildschirm bildschirm) {

        int angA = rng.nextInt(359);
        System.out.println("Random bearing Kugeluno:" + angA + "°");
        kugelA.bewegeBis(bildschirm.breite() * 0.5, bildschirm.hoehe() * 0.5);
        kugelA.normal();
        kugelA.zeichneKreis(20);
        kugelA.dreheBis(angA);
    }

    static void bewegeKugel(Bildschirm bildschirm, int reset) {

        kugelA.radiere();
        kugelA.zeichneKreis(20);
        kugelA.normal();
        kugelA.bewegeUm(20); // normal: speedA / 20px
        kugelA.zeichneKreis(20);
        // uhr.warte(20);

        /*
        if (speedA < 5) {
            speedA = speedA - 1;
            if (speedA == 1) {
                System.out.println("Object Kugeluno stopped.");
            }
        }
        */

        // Test for wall colision
        if (reset > 5) {
            if (kugelA.hPosition() < bildschirm.breite() * 0.12 || kugelA.hPosition() > bildschirm.breite() * 0.88 || kugelA.vPosition() < bildschirm.hoehe() * 0.22 || kugelA.vPosition() > bildschirm.hoehe() * 0.78) {
                speedA = speedA - 5; // Slow down by 3pixels per tick each time
                Bounce(bildschirm);
            }
        }
    }


    private static void Bounce(Bildschirm bildschirm) {
        reset = 0;

        // Set correct bounce angle
        if (kugelA.hPosition() < bildschirm.breite() * 0.12) {
            if (randomA.nextInt(1, 10) < 5) {
                angA = randomA.nextInt(1, 80);
            } else {
                angA = randomA.nextInt(260, 359);
            }

        } else if (kugelA.hPosition() > bildschirm.breite() * 0.88) {
            angA = randomA.nextInt(100, 260);
        } else if (kugelA.vPosition() < bildschirm.hoehe() * 0.22) {
            angA = randomA.nextInt(170, 350);
        } else if (kugelA.vPosition() > bildschirm.hoehe() * 0.78) {
            angA = randomA.nextInt(10, 170);
        } else {
            System.out.println("Illegal position");
        }
        kugelA.dreheBis(angA);
        System.out.println("Bounce angA: " + angA);
        Tisch.tischLoop();
    }
}