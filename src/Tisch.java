import sum.kern.Bildschirm;
import sum.kern.Buntstift;
import sum.werkzeuge.Uhr;

public class Tisch {
    static Bildschirm bildschirm = new Bildschirm(true);
    static Buntstift rand = new Buntstift();
    static Uhr uhr = new Uhr();
    static int debugdraw = 1; // Handle int like binary boolean.

    public Tisch() {

        bildschirm.setTitle("BlyatBillard by Henrik");
        /*
        var Kugeln = new ArrayList<Kugeluno>();

        for (int i=0; i<8; i++) {
            Kugeluno kugel = new Kugeluno();

            Kugeln.add(kugel);

        }
        */
        Kugeluno.zeichneKugel(bildschirm);
        /*
        Kugeldos.zeichneKugel(bildschirm);
        Kugeltres.zeichneKugel(bildschirm);
        Kugelcuatro.zeichneKugel(bildschirm);
        */
        zeichnen();
        tischLoop();
    }

    public static void tischLoop() {
        while (true) {
            Kugeluno.bewegeKugel(bildschirm);
            /*
            Kugeldos.bewegeKugel(bildschirm, reset);
            Kugeltres.bewegeKugel(bildschirm, reset);
            Kugelcuatro.bewegeKugel(bildschirm, reset);
            */
            zeichnen();
        }

    }

    public static void zeichnen() {
        // initial position

        if (debugdraw<2) {
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
            if (debugdraw==1) {debugdraw++;}
        }
        uhr.warte(2); // normal=20
        bildschirm.zeichneDich();
    }
}
