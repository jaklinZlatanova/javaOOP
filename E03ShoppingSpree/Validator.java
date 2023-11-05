package E03ShoppingSpree;

public class Validator {

    private Validator(){

    }

    public static void validateString(String str) {
        if (str == null || str.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
    public static void validateValueNonNegative(double value){
        if (value < 0){
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }
}
