import java.awt.Color;
import java.util.Random;

import sum.kern.Bildschirm;
import sum.kern.Buntstift;

public class Kugel {

    static double speed = 2;
    double radius;
    Buntstift kugel = new Buntstift();
    Tisch tisch;
    Bildschirm bildschirm;
    int id;

    Kugel(int id, double radius, Bildschirm bildschirm, Tisch tisch) {
        this.id = id;
        this.radius = radius;
        this.bildschirm = bildschirm;
        this.tisch = tisch;

        mitte();
    }

    private void mitte() {
        kugel.bewegeBis(tisch.width / 2 + tisch.getLinkerRand(), tisch.height / 2 + tisch.getObererRand());
    }

    public void zeichneKugel() {
        kugel.zeichneKreis(radius);
        kugel.setzeFuellmuster(1);
        kugel.setzeFarbe(Color.black);

        Random rng = new Random();
        double ang = rng.nextInt(359);
        kugel.dreheBis(ang);
        // System.out.println("Random Angle " + id * -1 + " " + kugel.winkel() + "°");
    }

    public void bewegeKugel() {

        // Erase
        kugel.radiere();
        kugel.zeichneKreis(radius);
        kugel.normal();

        // Move and Redraw
        kugel.bewegeUm(speed);
        kugel.zeichneKreis(radius);

        // slow down
        /*
         * if (speed>.001) {
         * speed = speed-.001;
         * }else{
         * speed=0;
         * }
         */

        if (kugel.hPosition() + radius >= tisch.getRechterRand()) { // +3/-3 as buffer
            bounce(false);
        } else if (kugel.hPosition() - radius <= tisch.getLinkerRand()) {
            bounce(false);
        } else if (kugel.vPosition() - radius <= tisch.getObererRand()) {
            bounce(true);
        } else if (kugel.vPosition() + radius >= tisch.getUntererRand()) {
            bounce(true);
        }

    }

    public void bounce(boolean vcol) {

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
