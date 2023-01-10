import sum.kern.Bildschirm;
import sum.kern.Buntstift;

import java.util.Random;

public class Kugeluno {
    static Buntstift kugelA = new Buntstift();
    static Random rng = new Random();
    static double speedA = 20; // Verzögerung zwischen bewegungen in 1/1000 sec.
    static int timeout;

    static void zeichneKugel(Bildschirm bildschirm) {

        int angA = rng.nextInt(359);
        System.out.println("Random bearing angA:" + angA + "°");
        kugelA.bewegeBis(bildschirm.breite() * 0.5, bildschirm.hoehe() * 0.5);
        kugelA.normal();
        kugelA.zeichneKreis(20);
        kugelA.dreheBis(angA); // Normal: angA.       For debug double 0-359
    }

    static void bewegeKugel(Bildschirm bildschirm) {

        kugelA.radiere();
        kugelA.zeichneKreis(20);
        kugelA.normal();
        kugelA.bewegeUm(speedA); // Normal speed: speedA / 20px    For debug use 1
        kugelA.zeichneKreis(20);
        // uhr.warte(20);

        // Speed reduction per tick. - Commented out for easing current developement
        //if (speedA < 5) {
            speedA = speedA - 0.1;
            if (speedA == 1) {
                while(true) {
                    System.out.println("Object Kugeluno stopped.");
                }

            }
        //}

        // Test for wall colision
        if (timeout>0) {timeout--;}
        if (timeout == 0) {
            if (kugelA.hPosition() < bildschirm.breite() * 0.12 || kugelA.hPosition() > bildschirm.breite() * 0.88 || kugelA.vPosition() < bildschirm.hoehe() * 0.22 || kugelA.vPosition() > bildschirm.hoehe() * 0.78) {
                speedA = speedA - .1; // Slow down by .1 per tick each time
                Bounce(bildschirm);
            }
        }
    }


    static void Bounce(Bildschirm bildschirm) {
        timeout = 20;
        boolean vcol = false;
        // Set vcol
        if (kugelA.vPosition() < bildschirm.hoehe() * 0.22) {
             vcol = true;
        } else if (kugelA.vPosition() > bildschirm.hoehe() * 0.78) {
             vcol = true;
        }

        kugelA.dreheBis(Physics.getReflection(vcol, kugelA.winkel()));
        System.out.println("Bounce Angle"+kugelA.winkel()+"°");
    }
}