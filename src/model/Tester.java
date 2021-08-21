package model;

public class Tester {
    public static void main(String[] args) {
        Customer customer = new Customer("first", "second", "j@domain.com");
        System.out.println(customer);
        try {
            Customer customer1 = new Customer("first", "second", "email");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getLocalizedMessage());
        }

    }
}
