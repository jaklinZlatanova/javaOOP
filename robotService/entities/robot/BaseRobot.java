package robotService.entities.robot;

import robotService.common.ExceptionMessages;

import static robotService.common.ExceptionMessages.*;

public abstract class BaseRobot implements Robot{
    private String name;
    private String kind;
    private int kilograms;
    private double price;

    public BaseRobot(String name, String kind, int kilograms, double price) {
        this.setName(name);
        this.setKind(kind);
        this.setKilograms(kilograms);
        this.setPrice(price);
    }

    @Override
    public void eating() {
        this.kilograms++;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()){
            throw new NullPointerException(ROBOT_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setKind(String kind) {
        if (kind == null || kind.isBlank()){
            throw new NullPointerException(ROBOT_KIND_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.kind = kind;
    }
    public String getKind() {
        return kind;
    }
    public void setKilograms(int kilograms) {
        this.kilograms = kilograms;
    }
    public int getKilograms() {
        return kilograms;
    }
    public void setPrice(double price) {
        if (price <= 0){
            throw new IllegalArgumentException(ROBOT_PRICE_CANNOT_BE_BELOW_OR_EQUAL_TO_ZERO);
        }
        this.price = price;
    }

    public double getPrice() {
        return price;
    }





}
