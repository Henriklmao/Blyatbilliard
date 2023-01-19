/**
 *      ---Collisionhandler by Henrik---
 *
 * Allows for multithreaded calculation of collisions between Kugel objects.
 *
 * I had to go out of my way for that commenting. Looks almost like disassembly mnemonic
 * Probably most commented class i ever wrote...
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
        /** Construct x and y Array lists **/
        var x = new ArrayList<Integer>();
        var y = new ArrayList<Integer>();

        /** add X and Y positions from subject table array to new X/Y array **/
        for (Kugel kugel : table) { // for every Kugel Object
            x.add((int) kugel.GetX()); // Add x Positions to X array
        }
        for (Kugel kugel : table) { // for every Kugel Object
            y.add((int) kugel.GetY()); // Add y Positions to Y array
        }

       try {   // Try to avoid errors.
            for (int i = 0; i < x.size(); i++) { // for every kugel in X-Array
                /** create temp array list from sublist which only includes values of nearby Positions **/
                var temp = new ArrayList<>(x.subList((x.get(i) - radius), (x.get(i) + radius))); // add x values in range of (int)radius to sublist. Save sublist as Arraylist Temp
                System.out.println(temp);
                /**Re-Reference Temp list entrys with Kugel Objects.**/
                for (int o = 0; o < temp.size(); o++) { // For every value in Reference Sublist
                    for (Kugel kugel : table) { // For every kugel Object
                        /** Check for X matching with temp list entry. **/
                        if (kugel.GetX() == x.get(o)) {
                            /** Check Kugel with correct value for correct Y value**/
                            if (kugel.GetY() < table.get(i).GetY() + radius && kugel.GetY() > table.get(i).GetY() - radius) { //If Y position of Kugel matches
                                kugel.collission(x.get(o), y.get(o)); // call collision method of retrospective
                            }
                        }
                    }
                }
                temp.clear();
            }
        } catch (Exception e) {
           System.out.println("Exception in arrays.");
        }

        /**CleanUp**/
        x.clear();
        y.clear();
    }
}
