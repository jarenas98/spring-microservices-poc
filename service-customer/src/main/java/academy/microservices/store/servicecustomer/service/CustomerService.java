package academy.microservices.store.servicecustomer.service;

import academy.microservices.store.servicecustomer.entity.Customer;
import academy.microservices.store.servicecustomer.entity.Region;

import java.util.List;

public interface CustomerService {
    List<Customer> findAllCustomers();

    List<Customer> findCustomersByRegion(Region region);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer deleteCustomer(Customer customer);

    Customer getCustomer(Long id);
}
