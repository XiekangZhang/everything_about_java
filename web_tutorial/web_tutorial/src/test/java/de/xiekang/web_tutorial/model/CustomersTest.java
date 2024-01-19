package de.xiekang.web_tutorial.model;

import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class CustomersTest {
    final static Logger LOGGER = Logger.getLogger(CustomersTest.class.getName());

    @Test
    public void testCustomers() {
        Customer customer = new Customer();
        customer.setFirstName("Max");
        customer.setLastName("Mustermann");
        customer.setCity("Musterstadt");

        Customers customers = new Customers();
        customers.addCustomer(customer);

        LOGGER.log(Level.INFO, "Customer {0} added.", customer.getFirstName() + " " + customer.getLastName() + " " + customer.getCity());
    }

}