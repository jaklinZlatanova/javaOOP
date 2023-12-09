package goldDigger.models.discoverer;

import goldDigger.models.museum.Museum;

public abstract class BaseDiscoverer implements Discoverer {

    private String name;
    private double energy;
    private Museum museum;

    public BaseDiscoverer(String name, double energy) {
        this.name = name;
        this.energy = energy;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public double getEnergy() {
        return 0;
    }

    @Override
    public boolean canDig() {
        return false;
    }

    @Override
    public Museum getMuseum() {
        return null;
    }

    @Override
    public void dig() {

    }
}
