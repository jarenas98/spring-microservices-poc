package academy.microservices.store.servicecustomer.service;

import academy.microservices.store.servicecustomer.entity.Customer;
import academy.microservices.store.servicecustomer.entity.Region;
import academy.microservices.store.servicecustomer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public List<Customer> findCustomersByRegion(Region region) {
        return this.customerRepository.findByRegion(region);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerDB = this.customerRepository.findByNumberId(customer.getNumberId());
        if (customerDB != null) {
            return customerDB;
        }
        customer.setState("CREATED");
        customerDB = this.customerRepository.save(customer);

        return customerDB;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDB = this.getCustomer(customer.getId());
        if (customerDB == null) {
            return null;
        }
        customerDB.setFirstName(customer.getFirstName());
        customerDB.setLastName(customer.getLastName());
        customerDB.setEmail(customer.getEmail());
        customerDB.setPhotoUrl(customer.getPhotoUrl());

        return this.customerRepository.save(customerDB);
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        Customer customerDB = this.getCustomer(customer.getId());
        if (customerDB == null) {
            return null;
        }

        customerDB.setState("DELETED");

        return this.customerRepository.save(customerDB);
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}
