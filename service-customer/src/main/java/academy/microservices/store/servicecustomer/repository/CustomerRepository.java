package academy.microservices.store.servicecustomer.repository;

import academy.microservices.store.servicecustomer.entity.Customer;
import academy.microservices.store.servicecustomer.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findByNumberId(String numberId);

    public List<Customer> findByLastName(String lastname);

    public List<Customer> findByRegion(Region region);
}
