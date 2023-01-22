/**
 *      ---Kugel Class by Henrik---
 *
 *      Ball meant to be used as an Object in an Array for Blytbilliard.
 *
 */

import sum.kern.Bildschirm;
import sum.kern.Buntstift;

import java.awt.*;
import java.util.Random;

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

        center();
    }

    private void center() {
        kugel.bewegeBis(tisch.width / 2 + tisch.getLinkerRand(), tisch.height / 2 + tisch.getObererRand());
    }

    public void zeichneKugel() {
        kugel.zeichneKreis(radius);
        kugel.setzeFuellmuster(1);
        kugel.setzeFarbe(Color.black);

        Random rng = new Random();
        double ang = rng.nextDouble(359);
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

    public void collission(double enemyX, double enemyY) { // Calculate enemy angle of attack and reflect

        /** Trigonometrische Beziehungen in der Berechnung von AoA fuer phys. korrekte Reflektion.
         * a = Hypotenuse
         * X = Gegenkatete
         * Y = Ankatete
         */
        double deltaX = (enemyX - kugel.hPosition()); // get X axis difference
        //if (deltaX<0) deltaX = -deltaX;

        double deltaY = (enemyY - kugel.vPosition());
        //if (deltaY<0) deltaY = -deltaY;

        double ang = Math.toDegrees(Math.atan(deltaX / deltaY));
        //*180/pi
        //double ang = Math.toDegrees(rad);
        //double ang = rad*180/Math.PI;
        System.out.println(ang + "°");

        kugel.dreheBis(180 - ang);
    }

    public double GetX() {
        return kugel.hPosition();
    }

    public double GetY() {
        return kugel.vPosition();
    }

    public boolean isAlive() {

        return speed != 0;
    }
}
