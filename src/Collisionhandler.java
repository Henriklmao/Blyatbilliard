/**
 * ---Collisionhandler by Henrik---
 * <p>
 * Allows for multithreaded calculation of collisions between Kugel objects.
 * <p>
 * I had to go out of my way for that commenting. Looks almost like disassembly mnemonics
 * Probably most commented class I ever wrote...
 * <p>
 * Try Catch: Important to not let Collisionhandler crash when there's no collisions at time of call.
 * Otherwise, it would wait for the next call of thread if there's no collisions
 */


import java.util.ArrayList;

public class Collisionhandler extends Thread {
    ArrayList<Kugel> table;
    int radius;

    Collisionhandler(ArrayList<Kugel> table, double radius) {
        // Initialize constructor parameters as class variables.
        this.table = table;
        this.radius = (int) radius + 1;
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
            checkCollision(x, y, i);
        }
    }

    private void checkCollision(ArrayList<Double> x, ArrayList<Double> y, int i) {

        try { // Prevent crash due to multithreading array stuff.
            for (Kugel kugel : table) { // For every kugel Object
                if (kugel.GetX() < x.get(i) + radius && kugel.GetX() > x.get(i) - radius) { // Check if X pos is in range of Kugel
                    /** Check Kugel with x in range for correct Y in range**/
                    if (kugel.GetY() < y.get(i) + radius && kugel.GetY() > y.get(i) - radius) { //If Y position of Kugel matches
                        kugel.collission(x.get(i), y.get(i)); // collision method of retrospective kugel
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Couldn't find values.");
        }
    }
}
