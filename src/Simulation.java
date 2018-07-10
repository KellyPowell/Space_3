/*
Simulation class that is responsible for reading item data and filling up the rockets.
The Simulation class should include these methods:

loadItems: this method loads all items from a text file and returns an ArrayList of Items:
Each line in the text file consists of the item name followed by = then its weigh in kg. For example:
habitat=100000
colony=50000
food=50000

loadItems should read the text file line by line and create an Item object for each and then add
it to an ArrayList of Items. The method should then return that ArrayList.

loadU1: this method takes the ArrayList of Items returned from loadItems and starts creating
U1 rockets. It first tries to fill up 1 rocket with as many items as possible before creating a new rocket
object and filling that one until all items are loaded. The method then returns the ArrayList of those U1
rockets that are fully loaded.

loadU2: this method also takes the ArrayList of Items and starts creating U2 rockets and filling
them with those items the same way as with U1 until all items are loaded. The method then returns the
ArrayList of those U2 rockets that are fully loaded.

runSimulation: this method takes an ArrayList of Rockets and calls launch and land methods for
each of the rockets in the ArrayList. Every time a rocket explodes or crashes (i.e if launch or land return false)
it will have to send that rocket again. All while keeping track of the total budget required to send each rocket
safely to Mars. runSimulation then returns the total budget required to send all rockets (including the crashed ones).
 */

import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Simulation {

    // loadItems method:  should take in either Phase1.txt or phase2.txt (if statement)
    // return error if filenotfoundexception (try/catch)
    // create scanner that will read the file
    // parse the line, splitting it on the = sign
    // create new Item, assign item.name and item.weight
    // add the Item to the ArrayList
    // repeat until all items added
    // return the ArrayList

    public ArrayList<Item> loadItems(ArrayList<Item> phase, int phaseNbr) {

        String lineRead;
        String fileName = "";


        try {

            if (phaseNbr == 1) {
                fileName = "Phase1.txt";
            } else if (phaseNbr == 2) {
                fileName = "Phase2.txt";
            } else {
                System.out.println("Invalid entry, phaseNbr must be 1 or 2");
            }

            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                lineRead = scanner.nextLine();
                int i = lineRead.indexOf("=");
                Item item = new Item();
                item.name = lineRead.substring(0, i);
                item.weight = Integer.parseInt(lineRead.substring(i + 1));
                phase.add(item);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!");
        }

        return phase;


    }

   /* loadU1: this method takes the ArrayList of Items returned from loadItems and starts creating
    U1 rockets. It first tries to fill up 1 rocket with as many items as possible before creating a new rocket
    object and filling that one until all items are loaded. The method then returns the ArrayList of those U1
    rockets that are fully loaded. */



    public ArrayList<Rocket> loadU1(ArrayList<Item> items) {

        ArrayList<Rocket> fleetU1 = new ArrayList<>();  // create the ArrayList for our U2 fleet

        int i = 0;
        int rocketCtr = 0;
        while (i < items.size()) {   // continue until we've looped thru all the items
            U1 u1 = new U1();
            rocketCtr += 1;
            // System.out.println("Created new U1 rocket " + rocketCtr);


            // while loop, check to see if current u1 is over its max weight
            while (u1.currentWeight <= u1.maxWeight) {
                //check to see if item will fit into a U1 rocket at all...
                if (items.get(i).weight > u1.cargoLimit) {
                    System.out.println("Item too heavy for U1 rocket! Check your cargo list....");
                    System.exit(1);
                }
                if (u1.canCarry(items.get(i))) {   // if item will fit, add it to current u2 rocket
                    u1.carry(items.get(i));
                    //System.out.println("     " + items.get(i).name + "/" + items.get(i).weight + " added to Rocket " + rocketCtr);
                    i += 1;
                    // check whether that was our last item to load
                    if (i >= items.size()) {
                        u1.rocketStatus = "loaded";
                        fleetU1.add(u1);
                        //     System.out.println("Rocket " + rocketCtr + " added to fleet!");
                        break;
                    }

                } else {   // current u2 can't fit the item
                    u1.rocketStatus = "loaded";
                    fleetU1.add(u1);  // then add rocket to fleet and exit our loop (takes us back to checking list, creating another u2)
                    //    System.out.println("Rocket " + rocketCtr + " added to fleet!");
                    break;
                }

            }
        } // exit our main while loop, we've loaded all the items!!

        return fleetU1;

    }


    public ArrayList<Rocket> loadU2(ArrayList<Item> items) {

        ArrayList<Rocket> fleetU2 = new ArrayList<>();  // create the ArrayList for our U2 fleet

        int i = 0;
        int rocketCtr = 0;
        while (i < items.size()) {   // continue until we've looped thru all the items
            U2 u2 = new U2();
            rocketCtr += 1;
            //System.out.println("Created new U2 rocket " + rocketCtr);


            // while loop, check to see if current u2 is over its max weight
            while (u2.currentWeight <= u2.maxWeight) {
                //check to see if item will fit into a U2 rocket at all...
                if (items.get(i).weight > u2.cargoLimit) {
                    System.out.println("Item too heavy for U2 rocket! Check your cargo list....");
                    System.exit(1);
                }
                if (u2.canCarry(items.get(i))) {   // if item will fit, add it to current u2 rocket
                    u2.carry(items.get(i));
                    //System.out.println("     " + items.get(i).name + "/" + items.get(i).weight + " added to Rocket " + rocketCtr);
                    i += 1;
                    // check whether that was our last item to load
                    if (i >= items.size()) {
                        u2.rocketStatus = "loaded";
                        fleetU2.add(u2);
                        // System.out.println("Rocket " + rocketCtr + " added to fleet!");
                        break;
                    }

                } else {   // current u2 can't fit the item
                    u2.rocketStatus = "loaded";
                    fleetU2.add(u2);  // then add rocket to fleet and exit our loop (takes us back to checking list, creating another u2)
                    // System.out.println("Rocket " + rocketCtr + " added to fleet!");
                    break;
                }

            }
        } // exit our main while loop, we've loaded all the items!!

        return fleetU2;

    }

    /*
    runSimulation: this method takes an ArrayList of Rockets and calls launch and land methods for
each of the rockets in the ArrayList. Every time a rocket explodes or crashes (i.e if launch or land return false)
it will have to send that rocket again. All while keeping track of the total budget required to send each rocket
safely to Mars. runSimulation then returns the total budget required to send all rockets (including the crashed ones).
     */
    public int runSimulation(ArrayList fleet) {

        int totalBudget;

        Rocket uTemp = new Rocket();

        int rocketCtr = 1;  // this number will match the rocket we're trying to launch. If it takes 3 tries to launch rocket # 4, it
                            // will still be referred to as 4 in rocketCtr
        for (int i = 0; i < fleet.size(); i++) {
            uTemp = (Rocket) fleet.get(i);

            // launch the rocket
            if (uTemp.launch()) {
                System.out.println("Rocket " + rocketCtr + " successfully launched!");
                uTemp.rocketStatus = "launched";
                // land the rocket
                if (uTemp.land()) {
                    System.out.println("Rocket " + rocketCtr + " successfully landed!");
                    uTemp.rocketStatus = "landed";
                    rocketCtr += 1;

                } else {
                    System.out.println("Rocket " + rocketCtr + " crashed on landing! Repeat launch with same cargo.");
                    uTemp.rocketStatus = "crashed";
                    // land the rocket
                    fleet = insertRocket(i, fleet);

                }
            } else {
                System.out.println("Rocket " + rocketCtr + " exploded at launch. Repeat launch with same cargo.");
                uTemp.rocketStatus = "exploded";
                fleet = insertRocket(i, fleet);
            }
        }
        totalBudget = fleet.size() * uTemp.rocketCost;
        System.out.println("You used " + fleet.size() + " rockets.");
        return totalBudget;
    }

    public ArrayList<Rocket> insertRocket(int failedRocket, ArrayList fleet) {


        Rocket uTemp = (Rocket) fleet.get(failedRocket);

        System.out.println("Loading replacement rocket...");

        if (fleet.get(failedRocket) instanceof U1) {
            U1 u1New = new U1();
            u1New.rocketStatus = "loaded";
            u1New.cargoCarried = uTemp.cargoCarried;
            u1New.currentWeight = uTemp.currentWeight;
            fleet.add(failedRocket + 1, u1New);
        } else if (fleet.get(failedRocket) instanceof U2) {
            U2 u2New = new U2();
            u2New.rocketStatus = "loaded";
            u2New.cargoCarried = uTemp.cargoCarried;
            u2New.currentWeight = uTemp.currentWeight;
            fleet.add(failedRocket + 1, u2New);

        } else {
            System.out.println("What the heck type of rocket are you working with???");
            System.exit(2);
        }
        return fleet;
    }
}