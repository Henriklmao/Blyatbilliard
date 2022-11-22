import sum.kern.Bildschirm;
import sum.kern.Buntstift;
import sum.werkzeuge.Uhr;

public class Tisch {
    static Bildschirm bildschirm = new Bildschirm();
    static Buntstift rand = new Buntstift();
    static Kugeluno Kugeluno = new Kugeluno();
    static Kugeldos Kugeldos = new Kugeldos();
    static Uhr uhr = new Uhr();
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


        Kugeluno.zeichneKugel(bildschirm);
        Kugeldos.zeichneKugel(bildschirm);
        tischLoop();
    }

    public static void tischLoop() {
        while (1 == 1) {
            Kugeluno.bewegeKugel(bildschirm, reset);
            Kugeldos.bewegeKugel(bildschirm, reset);
            // Rand.bewegeBis(bildschirm.breite()*0.1, bildschirm.hoehe()*0.1);
            reset++;
        }

    }

    public void zeichnen() {
        rand.bewegeBis(bildschirm.breite() * 0.1, bildschirm.hoehe() * 0.2);
        rand.bewegeBis(bildschirm.breite() * 0.1, bildschirm.hoehe() * 0.2);
        rand.runter();
        rand.bewegeBis(bildschirm.breite() * 0.1, bildschirm.hoehe() * 0.1);

    }
}
