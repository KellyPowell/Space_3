/* Create classes U1 and U2 that extend the Rocket class and override the land
and launch methods to calculate the corresponding chance of exploding and return
either true or false based on a random number using the probability equation for each.
 */

public class U1 extends Rocket {

    // constructor
    U1() {
        emptyWeight = 10000;
        maxWeight = 18000;
        rocketCost = 100;

        currentWeight = emptyWeight;
        currentCargoCapacity = maxWeight - currentWeight;

        cargoCarried = currentWeight - emptyWeight;
        cargoLimit = maxWeight - emptyWeight;

        chanceExplosionOnLaunch = 0.05;
        chanceCrashOnLand = 0.01;
        rocketStatus = "not loaded";
    }

    @Override
    public boolean launch() {
        double launchRandom = Math.random();
        //System.out.println("launchRandom = " + launchRandom);
        //System.out.println("cargoCarried = " + cargoCarried);
        //System.out.println(" odds of exploding " + (double) chanceExplosionOnLaunch * ((double) this.cargoCarried / (double) this.cargoLimit));
        //System.out.println("successful? " + (launchRandom > chanceExplosionOnLaunch * ((double) cargoCarried / (double) cargoLimit)));
        return (launchRandom > chanceExplosionOnLaunch * ((double) cargoCarried / (double) cargoLimit));
    }

    @Override
    public boolean land() {
        double landRandom = Math.random();
        return (landRandom > chanceCrashOnLand * ((double) cargoCarried / (double) cargoLimit));

    }
}
