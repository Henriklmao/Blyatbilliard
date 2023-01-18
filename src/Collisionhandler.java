/**
 *      ---Collisionhandler by Henrik---
 *
 * Allows for multithreaded calculation of collisions between Kugel objects.
 *
 * I had to go out of my way for that commenting. Looks almost like disassembly mnemonic
 */

import java.util.ArrayList;

public class Collisionhandler extends Thread{
        ArrayList<Kugel> table;
        int radius;
    Collisionhandler(ArrayList<Kugel> table, double radius) {
        this.table=table;
        this.radius= (int) radius;
    }

    @Override
    public void run() {
        // Construct x and y Array lists
        var x = new ArrayList<Integer>();
        var y = new ArrayList<Integer>();

        // add X positions from subject table array to x memory
        for (Kugel kugel : table) { // for every Kugel Object
            x.add((int) kugel.GetX()); // Add x Positions to X array
        }
        for (Kugel kugel : table) { // for every Kugel Object
            y.add((int) kugel.GetY()); // Add y Positions to Y array
        }


        try {
            for (int i = 0; i < x.size(); i++) { // for every kugel in X-Array
                // create temp array list from sublist which only includes values of nearby Positions
                var temp = new ArrayList<>(x.subList((x.get(i) - radius), (x.get(i) + radius))); // add x values in range of (int)radius to sublist. Save sublist as Arraylist Temp
                // Re-Reference Temp list entrys with Kugel Objects.
                for (int o = 0; o < temp.size(); o++) { // For every Item in Reference Sublist
                    for (Kugel kugel : table) { // For every kugel Object
                        // Check for X matching
                        if (kugel.GetX() == x.get(o)) {
                            // Check for Y also matching
                            if (kugel.GetY() < table.get(i).GetY() + radius && kugel.GetY() > table.get(i).GetY() - radius) {
                                kugel.collission(x.get(o), y.get(o)); // call collision method of retrospective
                            }
                        }
                    }
                }
                System.out.println(temp);
            }
        } catch (Exception e) {
            System.out.println("Exception in arrays.");
        }
    }
}
