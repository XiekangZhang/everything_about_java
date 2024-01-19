package de.xiekang.web_tutorial.model;



import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customers {
    private List<Customer> customers;
    private static final Logger LOGGER = Logger.getLogger(Customers.class.getName());

    public Customers() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        if (!containsCustomer(customer)) {
            this.customers.add(customer);
        }
    }

    public void deleteCustomer(Customer customer) {
        if (containsCustomer(customer)) {
            this.customers.remove(customer);
        }
    }

    public boolean containsCustomer(Customer customer) {
        for (Customer c : this.customers) {
            if (c.getFirstName().equals(customer.getFirstName()) && c.getLastName().equals(customer.getLastName())
                    && c.getCity().equals(customer.getCity())) {
                LOGGER.log(Level.WARNING, "Customer already exists.");
                return true;
            }
        }
        return false;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}