import sum.kern.Bildschirm;
import sum.kern.Buntstift;

import java.util.Random;

public class Kugelcuatro {
    static Buntstift kugelD = new Buntstift();
    static Random rng = new Random();
    static Random randomD = new Random();
    static int angD;
    static int speedD = 20; // Verzögerung zwischen bewegungen in 1/1000 sec.
    static int reset;

    static void zeichneKugel(Bildschirm bildschirm) {

        int angD = rng.nextInt(359);
        System.out.println("Random bearing Kugeluno:" + angD + "°");
        kugelD.bewegeBis(bildschirm.breite() * 0.5, bildschirm.hoehe() * 0.5);
        kugelD.normal();
        kugelD.zeichneKreis(20);
        kugelD.dreheBis(angD);
    }

    static void bewegeKugel(Bildschirm bildschirm, int reset) {

        kugelD.radiere();
        kugelD.zeichneKreis(20);
        kugelD.normal();
        kugelD.bewegeUm(20); // normal: speedD / 20px
        kugelD.zeichneKreis(20);

        /*
        if (speedD < 5) {
            speedD = speedD - 1;
            if (speedD == 1) {
                System.out.println("Object Kugeluno stopped.");
            }
        }
        */

        // Test for wall colision
        if (reset > 5) {
            if (kugelD.hPosition() < bildschirm.breite() * 0.12 || kugelD.hPosition() > bildschirm.breite() * 0.88 || kugelD.vPosition() < bildschirm.hoehe() * 0.22 || kugelD.vPosition() > bildschirm.hoehe() * 0.78) {
                speedD = speedD - 5; // Slow down by 3pixels per tick each time
                Bounce(bildschirm);
            }
        }
    }


    private static void Bounce(Bildschirm bildschirm) {
        reset = 0;

        // Set correct bounce angle
        if (kugelD.hPosition() < bildschirm.breite() * 0.12) {
            if (randomD.nextInt(1, 10) < 5) {
                angD = randomD.nextInt(1, 80);
            } else {
                angD = randomD.nextInt(260, 359);
            }

        } else if (kugelD.hPosition() > bildschirm.breite() * 0.88) {
            angD = randomD.nextInt(100, 260);
        } else if (kugelD.vPosition() < bildschirm.hoehe() * 0.22) {
            angD = randomD.nextInt(170, 350);
        } else if (kugelD.vPosition() > bildschirm.hoehe() * 0.78) {
            angD = randomD.nextInt(10, 170);
        } else {
            System.out.println("Illegal position");
        }
        kugelD.dreheBis(angD);
        System.out.println("Bounce angD: " + angD);
        Tisch.tischLoop();
    }
}