package bg.sofia.fmi.uni.clubhub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import bg.sofia.fmi.uni.clubhub.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, UUID> {

    Optional<CustomerEntity> findByUsername(String username);
}
