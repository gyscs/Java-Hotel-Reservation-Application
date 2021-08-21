package model;

import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;

        String emailRegex = "^(.+)@(.+).com$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(pattern.matcher(email).matches()) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    @Override
    public String toString() {
        return "customer name: " + firstName + " " + lastName + " email: " + email;
    }
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null) return false;
        if(this.getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return (firstName.equals(customer.firstName)) && (lastName.equals(customer.lastName)) && (email.equals(customer.email));
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (firstName == null ? 0 : firstName.hashCode());
        hash = 31 * hash + (lastName == null ? 0 : lastName.hashCode());
        hash = 31 * hash + (email == null ? 0 : email.hashCode());
        return hash;
    }
}
