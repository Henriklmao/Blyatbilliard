import sum.kern.Bildschirm;
import sum.kern.Buntstift;

import java.util.Random;

public class Kugeldos {
    static Buntstift kugelB = new Buntstift();
    static Random rng = new Random();
    static Random randomB = new Random();
    static int angB;
    static int speedB = 20; // Verzögerung zwischen bewegungen in 1/1000 sec.
    static int reset;

    static void zeichneKugel(Bildschirm bildschirm) {

        int angB = rng.nextInt(359);
        System.out.println("Random bearing Kugeluno:" + angB + "°");
        kugelB.bewegeBis(bildschirm.breite() * 0.5, bildschirm.hoehe() * 0.5);
        kugelB.normal();
        kugelB.zeichneKreis(20);
        kugelB.dreheBis(angB);
    }

    static void bewegeKugel(Bildschirm bildschirm, int reset) {

        kugelB.radiere();
        kugelB.zeichneKreis(20);
        kugelB.normal();
        kugelB.bewegeUm(20); // normal: speedB / 20px
        kugelB.zeichneKreis(20);

        /*
        if (speedB < 5) {
            speedB = speedB - 1;
            if (speedB == 1) {
                System.out.println("Object Kugeluno stopped.");
            }
        }
        */

        // Test for wall colision
        if (reset > 5) {
            if (kugelB.hPosition() < bildschirm.breite() * 0.12 || kugelB.hPosition() > bildschirm.breite() * 0.88 || kugelB.vPosition() < bildschirm.hoehe() * 0.22 || kugelB.vPosition() > bildschirm.hoehe() * 0.78) {
                speedB = speedB - 5; // Slow down by 3pixels per tick each time
                Bounce(bildschirm);
            }
        }
    }


    private static void Bounce(Bildschirm bildschirm) {
        reset = 0;

        // Set correct bounce angle
        if (kugelB.hPosition() < bildschirm.breite() * 0.12) {
            if (randomB.nextInt(1, 10) < 5) {
                angB = randomB.nextInt(1, 80);
            } else {
                angB = randomB.nextInt(260, 359);
            }

        } else if (kugelB.hPosition() > bildschirm.breite() * 0.88) {
            angB = randomB.nextInt(100, 260);
        } else if (kugelB.vPosition() < bildschirm.hoehe() * 0.22) {
            angB = randomB.nextInt(170, 350);
        } else if (kugelB.vPosition() > bildschirm.hoehe() * 0.78) {
            angB = randomB.nextInt(10, 170);
        } else {
            System.out.println("Illegal position");
        }
        kugelB.dreheBis(angB);
        System.out.println("Bounce angB: " + angB);
        Tisch.tischLoop();
    }
}