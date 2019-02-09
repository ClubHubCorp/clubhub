package bg.sofia.fmi.uni.clubhub.configuration;

import bg.sofia.fmi.uni.clubhub.repository.CustomerRepository;
import bg.sofia.fmi.uni.clubhub.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfigurer {
    @Autowired
    private CustomerRepository customerRepository;

    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        return new CustomerService(customerRepository);
    }

    @Bean
    public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
