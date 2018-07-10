/* Create a class Rocket that implements the SpaceShip Interface and hence implements all the methods above.

launch and land methods in the Rocket class should always return true.
When U1 and U2 classes extend the Rocket class they will override these methods to return true or false based
on the actual probability of each type.

carry and canCarry should be implemented here and will not need to be overridden in the U1 and U2 classes

*/

public class Rocket implements Spaceship{

    int emptyWeight;
    int maxWeight;
    int rocketCost;
    int currentWeight;
    int currentCargoCapacity;
    int cargoCarried;
    int cargoLimit;


    double chanceExplosionOnLaunch;
    double chanceCrashOnLand;

    String rocketStatus;

    public Rocket() {
        super();
    }

    // constructor

@Override
    public boolean launch() {
        return true;
    }
@Override
    public boolean land() {
        return true;
    }
@Override
    public boolean canCarry(Item cargo) {
        return (cargo.weight <= currentCargoCapacity);

    }
@Override
    public void carry(Item cargo) {
        this.currentWeight = this.currentWeight + cargo.weight;
        this.currentCargoCapacity = maxWeight - this.currentWeight;
        this.cargoCarried = this.currentWeight - this.emptyWeight;  // was surprised that I had to explicitly update these
                                                                    // I thought the fact that I updated currentweight would
                                                                    // update cargoCarried and currentCargoCapacity ????
    }


}
