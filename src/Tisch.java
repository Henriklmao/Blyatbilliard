import sum.kern.Buntstift;


public class Tisch {

    static double width;
    static double height;
    static Buntstift rand = new Buntstift();

    public static double getLinkerRand() {
        return rand.hPosition();
    }

    public static double getRechterRand() {
        return rand.hPosition() + width;
    }

    public static double getObererRand() {
        return rand.vPosition();
    }

    public static double getUntererRand() {
        return rand.vPosition() + height;
    }

    public void zeichnen(double sWidth, double sHeight, double fInitW, double fInitH) { //fBreite = Faktor Breite
        width = sWidth * 0.35;
        height = sHeight * 0.85;
        rand.bewegeBis(sWidth * fInitW, sHeight * fInitH);
        rand.runter();
        rand.zeichneRechteck(sWidth * 0.35, sHeight * 0.85);        //(width ,height);
        rand.hoch();
    }

}
