package bg.sofia.fmi.uni.clubhub.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bg.sofia.fmi.uni.clubhub.entity.ClubEntity;
import bg.sofia.fmi.uni.clubhub.entity.CustomerEntity;
import bg.sofia.fmi.uni.clubhub.repository.ClubRepository;
import bg.sofia.fmi.uni.clubhub.repository.CustomerRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final ClubRepository clubRepository;

    @Autowired
    public CustomerUserDetailsService(CustomerRepository customerRepository, ClubRepository clubRepository) {
        this.customerRepository = customerRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomerEntity> customer = customerRepository.findByUsername(username);
        if (customer.isPresent()) {
            return customer.get();
        }

        Optional<ClubEntity> club = clubRepository.findByUsername(username);

        if (club.isPresent()) {
            return club.get();
        }

        throw new UsernameNotFoundException("User with username '" + username + "' does not exist!");
    }
}
