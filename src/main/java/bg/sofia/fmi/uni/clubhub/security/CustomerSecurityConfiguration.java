package bg.sofia.fmi.uni.clubhub.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class CustomerSecurityConfiguration extends WebSecurityConfigurerAdapter {
    public CustomerSecurityConfiguration() {
        super();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/customer*")
                .authorizeRequests()
                .anyRequest()
                .hasRole("CUSTOMER")

                .and()
                .formLogin()
                .loginPage("/accounts/login-customer")
                .loginProcessingUrl("/accounts/loginCustomer")
                .failureUrl("/")
                .defaultSuccessUrl("/")

                .and()
                .logout()
                .logoutUrl("/account/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")

                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")

                .and()
                .csrf().disable();
    }
}
