/**
 * ---Tisch Class by Henrik---
 * <p>
 * Table for Blyatbilliard. Used for visual representation of collisionborders for Kugel objects and
 * as positional reference.
 */
import sum.kern.Buntstift;

public class Tisch {

    double width;
    double height;
    Buntstift rand = new Buntstift();

    Tisch (double w, double h) {
        width = w;
        height = h;
    }

    public void bewegeBis(double x, double y) {
        rand.bewegeBis(x, y);
    }

    public double getLinkerRand() {
        return rand.hPosition();
    }

    public double getRechterRand() {
        return rand.hPosition() + width;
    }

    public double getObererRand() {
        return rand.vPosition();
    }

    public double getUntererRand() {
        return rand.vPosition() + height;
    }

    public void zeichnen() { // fBreite = Faktor Breite
        rand.zeichneRechteck(width, height); // (width ,height);
    }

}
