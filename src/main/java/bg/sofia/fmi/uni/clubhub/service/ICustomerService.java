package bg.sofia.fmi.uni.clubhub.service;

import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.model.Customer;

public interface ICustomerService {

    Optional<Customer> getById(UUID id);

    Optional<Customer> getByUsername(String username);

    List<Customer> getAll(PageRequest page);

    Customer createNew(Customer customer);

    void deleteById(UUID id);
}
