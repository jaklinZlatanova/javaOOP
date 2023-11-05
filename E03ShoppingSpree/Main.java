package E03ShoppingSpree;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedHashMap<String, Person> people = Arrays.stream(scanner.nextLine().split(";"))
                .map(s -> createEntity(s, Person::new))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(Person::getName, p -> p, (x, y) -> y, LinkedHashMap::new));

        LinkedHashMap<String, Product> products = Arrays.stream(scanner.nextLine().split(";"))
                .map(s -> createEntity(s, Product::new))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(Product::getName, p -> p, (x, y) -> y, LinkedHashMap::new));

        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("end")) {

            String[] tokens = input.split("\\s+");

            Person person = people.get(tokens[0]);
            Product product = products.get(tokens[1]);

            if (person != null && products != null) {

                if (person.getMoney() < product.getCost()) {
                    System.out.println(person.getName() + " can't afford " + product.getName());
                } else {
                    System.out.println(person.getName() + " bought " + product.getName());
                    person.buyProduct(product);
                }
            }


            input = scanner.nextLine();
        }
        System.out.println(people.values().stream()
                .map(Person::toString)
                .collect(Collectors.joining(System.lineSeparator())));


    }

    public static <T> Optional<T> createEntity(String data, BiFunction<String, Double, T> createEntity) {
        String[] tokens = data.split("=");
        String name = tokens[0];
        double money = Double.parseDouble(tokens[1]);

        Optional<T> entity = Optional.empty();

        try {
            entity = Optional.of(createEntity.apply(name, money));

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return entity;
    }
}
