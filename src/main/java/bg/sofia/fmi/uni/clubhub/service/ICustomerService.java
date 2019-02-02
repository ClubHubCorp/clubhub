package bg.sofia.fmi.uni.clubhub.service;

import java.util.Optional;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.model.Customer;

public interface ICustomerService {

    Optional<Customer> getById(UUID id);

    Optional<Customer> getByUsername(String username);

    Customer createNew(Customer customer);

    void deleteById(UUID id);
}
