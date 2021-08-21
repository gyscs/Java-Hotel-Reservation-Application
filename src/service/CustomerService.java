package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CustomerService {
    private final static Map<String, Customer> customers = new HashMap<String, Customer>();
    private static CustomerService customerService = null;

    private CustomerService() {};
    public static CustomerService getInstance() {
        if(customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    public static void addCustomer(String email, String firstName, String lastName) {
        customers.put(email, new Customer(firstName, lastName, email));
    }
    public static Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }
    public static Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}
