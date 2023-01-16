import sum.kern.Bildschirm;
import sum.kern.Buntstift;

import java.awt.*;
import java.util.Random;


public class Kugel {

    static double speed = 20;
    static double radius;
    Buntstift kugel = new Buntstift();

    public void zeichneKugel(Bildschirm bildschirm, double tWidth, double tHeight, double size, int id) {

        radius = size;
        kugel.bewegeBis(bildschirm.breite() * tWidth, bildschirm.hoehe() * tHeight);
        double cW = bildschirm.breite() * 0.35 / 2;
        double cH = bildschirm.hoehe() * 0.85 / 2;

        kugel.bewegeBis(cW + bildschirm.breite() * tWidth, cH + bildschirm.hoehe() * tHeight);
        kugel.zeichneKreis(size);
        kugel.setzeFuellmuster(1);
        kugel.setzeFarbe(Color.black);

        Random rng = new Random();
        double ang = rng.nextInt(359);
        kugel.dreheBis(ang);
        System.out.println("Random Angle " + id * -1 + " " + kugel.winkel() + "°");
    }

    public void bewegeKugel(Bildschirm bildschirm, double tWidth, double tHeight, double size, Tisch table) {

        // Erase
        kugel.radiere();
        kugel.zeichneKreis(size);
        kugel.normal();

        // Move and Redraw
        kugel.bewegeUm(speed);
        kugel.zeichneKreis(size);

        //slow down
        /*
        if (speed>.001) {
            speed = speed-.001;
        }else{
            speed=0;
        }
         */

        if (kugel.hPosition() + size + 3 >= table.getRechterRand()) { // +3/-3 as buffer
            bounce(bildschirm, size, false);
        } else if (kugel.hPosition() - size - 3 <= table.getLinkerRand()) {
            bounce(bildschirm, size, false);
        } else if (kugel.vPosition() - size - 3 <= table.getObererRand()) {
            bounce(bildschirm, size, true);
        } else if (kugel.vPosition() + size + 3 >= table.getUntererRand()) {
            bounce(bildschirm, size, true);
        }

    }

    public void bounce(Bildschirm bildschirm, double size, boolean vcol) {

        if (vcol) {
            kugel.dreheBis(-kugel.winkel());
        } else {
            kugel.dreheBis(180 - kugel.winkel());
        }
    }

    public boolean isAlive() {

        if (speed == 0) {
            return false;
        } else {
            return true;
        }
    }
}
