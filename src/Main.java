/*
Create a Simulation object
Load Items for Phase-1 and Phase-2
Load a fleet of U1 rockets for Phase-1 and then for Phase-2
Run the simulation using the fleet of U1 rockets and display the total budget required.
Repeat the same for U2 rockets and display the total budget for that.
 */


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {



        ArrayList<Item> itemsPhase1 = new ArrayList();
        ArrayList<Item> itemsPhase2 = new ArrayList();

        Simulation simulation = new Simulation();

        ArrayList<Rocket> fleetU1Phase1;    // declare ArrayList object fleetU1 for phase 1
        ArrayList<Rocket> fleetU2Phase1;    // declare ArrayList object fleetU2 for phase 1
        ArrayList<Rocket> fleetU1Phase2;    // declare ArrayList object fleetU1 for phase 2
        ArrayList<Rocket> fleetU2Phase2;    // declare ArrayList object fleetU2 for phase 2


        // Load items from files
        itemsPhase1 = simulation.loadItems(itemsPhase1, 1);
        itemsPhase2 = simulation.loadItems(itemsPhase2, 2);


        // Load Fleet for phase 1
        fleetU1Phase1 = simulation.loadU1(itemsPhase1);
        int p1U1Rockets = fleetU1Phase1.size();
        System.out.println("If no incidents occur, Fleet U1 Phase 1 requires " + p1U1Rockets + " rockets.");

        fleetU2Phase1 = simulation.loadU2(itemsPhase1);
        int p1U2Rockets = fleetU2Phase1.size();
        System.out.println("If no incidents occur, Fleet U2 Phase 1 requires " + p1U2Rockets + " rockets.");


        // Load Fleet for phase 2
        fleetU1Phase2 = simulation.loadU1(itemsPhase2);
        int p2U1Rockets = fleetU1Phase2.size();
        System.out.println("If no incidents occur, Fleet U1 Phase 2 requires " + p2U1Rockets + " rockets.");
        fleetU2Phase2 = simulation.loadU2(itemsPhase2);
        int p2U2Rockets = fleetU2Phase2.size();
        System.out.println("If no incidents occur, Fleet U2 Phase 2 requires " + p2U2Rockets + " rockets.");


        // run simulation using the fleet of U1 rockets and display the total budget required
        int totalBudgetP1U1 = simulation.runSimulation(fleetU1Phase1);
        System.out.println("Simulation Phase 1 using fleet of U1 Rockets: budget = $" + totalBudgetP1U1);
        int totalBudgetP2U1 = simulation.runSimulation(fleetU1Phase2);
        System.out.println("Simulation Phase 2 using fleet of U1 Rockets: budget = $ " + totalBudgetP2U1);



        // run simulation using the fleet of U1 rockets and display the total budget required
        int totalBudgetP1U2 = simulation.runSimulation(fleetU2Phase1);
        System.out.println("Simulation Phase 1 using fleet of U2 Rockets = $" + totalBudgetP1U2);
        int totalBudgetP2U2 = simulation.runSimulation(fleetU2Phase2);
        System.out.println("Simulation Phase 2 using fleet of U2 Rockets = $" + totalBudgetP2U2);
        System.out.println();
        System.out.println("Total cost using U1 Rockets = $" + (totalBudgetP1U1 + totalBudgetP2U1));
        System.out.println("Total cost using U2 Rockets = $" + (totalBudgetP1U2 + totalBudgetP2U2));

        //System.out.println(fleetU1Phase1);

    }
}

/*
        U2 testU2 = new U2();
        Item myCargo = new Item();

        System.out.println(Math.random());
        System.out.println(Math.random());
        System.out.println(Math.random());
        System.out.println(Math.random());
        System.out.println(Math.random());
        System.out.println(Math.random());

        myCargo.weight = 9;
        myCargo.name = "stuff";
        System.out.println("myCargo wt (beginning) = " + myCargo.weight);
        System.out.println("current wt (beginning) = " + testU2.currentWeight);
        System.out.println("current cargo capacity (beginning) = " + testU2.currentCargoCapacity);

        System.out.println("will cargo fit? = " + testU2.canCarry(myCargo));
        testU2.carry(myCargo);
        System.out.println("current wt (after carry method) = " + testU2.currentWeight);
        System.out.println("current cargo capacity (after carry method) = " + testU2.currentCargoCapacity);

        testU2.launch();

         //Item test2 = itemsPhase1.get(1);
        //System.out.println(test2.weight);
        //System.out.println(test2.name);
        //System.out.println(itemsPhase1.size());
*/