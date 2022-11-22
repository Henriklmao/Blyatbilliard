import sum.kern.Bildschirm;
import sum.kern.Buntstift;
import sum.werkzeuge.Uhr;

import java.util.Random;

public class Kugeldos {
    static Buntstift kugelB = new Buntstift();
    static Random rng = new Random();
    static Random randomB = new Random();
    static Uhr uhr = new Uhr();
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
        kugelB.bewegeUm(speedB);
        kugelB.zeichneKreis(20);
        uhr.warte(20);

        if (speedB < 5) {
            speedB = speedB - 1;
            if (speedB == 1) {
                System.out.println("Object Kugeluno stopped.");
            }
        }

        // Test for wall colision
        if (reset > 5) {
            if (kugelB.hPosition() < bildschirm.breite() * 0.1 || kugelB.hPosition() > bildschirm.breite() * 0.9 || kugelB.vPosition() < bildschirm.hoehe() * 0.1 || kugelB.vPosition() > bildschirm.hoehe() * 0.9) {
                speedB = speedB - 3; // Slow down by 3pixels per tick each time
                Bounce(bildschirm);
            }
        }
    }


    private static void Bounce(Bildschirm bildschirm) {
        reset = 0;
        // Set correct bounce angle
        if (kugelB.hPosition() < bildschirm.breite() * 0.1) {
            if (randomB.nextInt(1, 2) == 1) {
                angB = randomB.nextInt(0, 80);
            } else {
                angB = randomB.nextInt(260, 259);
            }
        } else if (kugelB.hPosition() > bildschirm.breite() * 0.9) {
            angB = randomB.nextInt(100, 260);
        } else if (kugelB.vPosition() < bildschirm.hoehe() * 0.1) {
            angB = randomB.nextInt(170, 350);
        } else if (kugelB.vPosition() > bildschirm.hoehe() * 0.9) {
            angB = randomB.nextInt(10, 170);
        } else {
            System.out.println("Illegal position");
        }
        kugelB.dreheBis(angB);
        System.out.println("Bounce angle: " + angB);
        Tisch.tischLoop();
    }
}