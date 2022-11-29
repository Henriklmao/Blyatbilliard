import sum.kern.Bildschirm;
import sum.kern.Buntstift;

public class Tisch {
    static Bildschirm bildschirm = new Bildschirm();
    static Buntstift rand = new Buntstift();
    static int reset;

    public Tisch() {

        bildschirm.setTitle("BlyatBillard by Henrik");
        /*
        var Kugeln = new ArrayList<Kugeluno>();

        for (int i=0; i<8; i++) {
            Kugeluno kugel = new Kugeluno();

            Kugeln.add(kugel);

        }
        */

        zeichnen();
        Kugeluno.zeichneKugel(bildschirm);
        Kugeldos.zeichneKugel(bildschirm);
        Kugeltres.zeichneKugel(bildschirm);
        Kugelcuatro.zeichneKugel(bildschirm);
        tischLoop();
    }

    public static void tischLoop() {
        while (1 == 1) {
            Kugeluno.bewegeKugel(bildschirm, reset);
            Kugeldos.bewegeKugel(bildschirm, reset);
            Kugeltres.bewegeKugel(bildschirm, reset);
            Kugelcuatro.bewegeKugel(bildschirm, reset);
            zeichnen();
            reset++;
        }

    }

    public static void zeichnen() {
        // initial position
        rand.bewegeBis(bildschirm.breite() * 0.1, bildschirm.hoehe() * 0.2);
        rand.runter();
        //Oben Rechts
        rand.bewegeBis(bildschirm.breite() * 0.9, bildschirm.hoehe() * 0.2);
        // unten rechts
        rand.bewegeBis(bildschirm.breite() * 0.9, bildschirm.hoehe() * 0.8);
        // unten links
        rand.bewegeBis(bildschirm.breite() * 0.1, bildschirm.hoehe() * 0.8);
        // initial position
        rand.bewegeBis(bildschirm.breite() * 0.1, bildschirm.hoehe() * 0.2);
        rand.hoch();
    }
}
