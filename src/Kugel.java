/**
 * ---Kugel Class by Henrik---
 * <p>
 * Ball meant to be used as an Object in an Array for Blytbilliard.
 */

import sum.kern.Bildschirm;
import sum.kern.Buntstift;

import java.awt.*;
import java.util.Random;

public class Kugel {

    static double speed = 2;
    double radius;
    static double mass; // Mass Equals the surface area of the kugel object.
    Buntstift kugel = new Buntstift();
    Tisch tisch;
    Bildschirm bildschirm;
    int justCollided;
    boolean isDead = false;

    Kugel(int id, double r, Bildschirm bildschirm, Tisch tisch) {
        this.radius = r;
        this.bildschirm = bildschirm;
        this.tisch = tisch;
        mass = r*r*Math.PI/10; // r*r because it is faster than Math.pow as exponentation is so much slower.
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
        kugel.gibFrei();

        // Kill object if it stopped.
        if (speed < 0) {
            isDead = true;
        }

        // Test for out of bounds
        if (kugel.hPosition() + radius >= tisch.getRechterRand()) {
            bounce(false);
        } else if (kugel.hPosition() - radius <= tisch.getLinkerRand()) {
            bounce(false);
        } else if (kugel.vPosition() - radius <= tisch.getObererRand()) {
            bounce(true);
        } else if (kugel.vPosition() + radius >= tisch.getUntererRand()) {
            bounce(true);
        }
        if (justCollided > 0) {
            this.justCollided = justCollided - (int) speed;
        }
    }

    public void bounce(boolean vcol) {

        if (vcol) {
            kugel.dreheBis(-kugel.winkel());
        } else {
            kugel.dreheBis(180 - kugel.winkel());
        }
        speed = speed - .0001;
    }

    /** Trigonometrische Beziehungen in der Berechnung von AoA für phys. korrekte Reflektion.
     * a = Hypotenuse
     * X = Gegenkatete
     * Y = Ankatete
     **/
    public int collission(double x, double y) { // public int to avoid nesting

        if (justCollided != 0) return 0;
        // Ignore if Collisionhandler detects wrong collisions.
        if (x == kugel.hPosition() && y == kugel.vPosition()) return 0;
        // get X/Y axis differences
        double deltaX = (x - kugel.hPosition());
        double deltaY = (y - kugel.vPosition());

        double ang = Math.toDegrees(Math.atan(deltaX / deltaY));
        //double ang = ang*180/Math.PI;
        System.out.println(ang + "°");
        kugel.dreheUm(ang - 180);
        this.justCollided = (int) speed * 20;
        speed = speed - .0005;

        return 0;
    }

    public double GetX() {
        return kugel.hPosition();
    }

    public double GetY() {
        return kugel.vPosition();
    }

    public boolean isAlive() {
        return isDead;
    }
}
