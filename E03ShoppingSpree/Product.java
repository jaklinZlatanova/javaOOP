package E03ShoppingSpree;

import javax.sound.midi.VoiceStatus;

public class Product {

    private String name;
    private double cost;


    public Product(String name, double cost) {
        setName(name);
        setCost(cost);
    }

    private void setName(String name) {
        Validator.validateString(name);
    }

    private void setCost(double cost) {
        Validator.validateValueNonNegative(cost);
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
