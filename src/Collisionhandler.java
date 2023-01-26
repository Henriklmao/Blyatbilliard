/**
 * ---Collisionhandler by Henrik---
 *
 * Allows for multithreaded calculation of collisions between Kugel objects.
 *
 * I had to go out of my way for that commenting. Looks almost like disassembly mnemonics
 * Probably most commented class I ever wrote...
 *
 * Try Catch: Important to not let Collisionhandler crash when there's no collisions at time of call.
 */
import java.util.ArrayList;

public class Collisionhandler extends Thread {
    ArrayList<Kugel> table;
    int radius;

    Collisionhandler(ArrayList<Kugel> table, double radius) {
        // Initialize constructor parameters as class variables.
        this.table = table;
        this.radius = (int) radius;
    }

    @Override
    public void run() {
        /** Construct x and y Array lists **/
        var x = new ArrayList<Double>();
        var y = new ArrayList<Double>();

        /** add X and Y positions from subject table array to new X/Y array **/
        for (Kugel kugel : table) { // for every Kugel Object
            x.add(kugel.GetX()); // Add x Positions to X array
        }
        for (Kugel kugel : table) { // for every Kugel Object
            y.add(kugel.GetY()); // Add y Positions to Y array
        }

        for (int i = 0; i < x.size(); i++) { // for every kugel in X-Array
             try { // Prevent crash due to multithreading array stuff.
                for (Kugel kugel : table) { // For every kugel Object
                    if (kugel.GetY() < x.get(i) + radius && kugel.GetX() > x.get(i) - radius) {
                        /** Check Kugel with correct value for correct Y value**/
                        if (kugel.GetY() < y.get(i) + radius && kugel.GetY() > y.get(i) - radius) { //If Y position of Kugel matches
                            kugel.collission(x.get(i), y.get(i)); // call collision method of retrospective
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Couldn't find values.");
            }

        }
    }
}
