package bg.sofia.fmi.uni.clubhub.security;

import static bg.sofia.fmi.uni.clubhub.entity.UserEntity.Role.CLUB;
import static bg.sofia.fmi.uni.clubhub.entity.UserEntity.Role.CUSTOMER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
public class CustomerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //
                .authorizeRequests() //
                .antMatchers("/css/**", "/images/**").permitAll() //
                .antMatchers("/accounts/register*").permitAll() //
                .antMatchers("/accounts/login*").permitAll() //
                .antMatchers("/").permitAll() //
                .antMatchers("/index").permitAll() //
                .antMatchers("/h2/*").permitAll() //
                .antMatchers("/accounts/customers").hasRole(CUSTOMER.name()) //
                .antMatchers("/accounts/clubs").hasRole(CLUB.name()) //
                .anyRequest().authenticated() //
                .and().formLogin().loginPage("/accounts/login-customer") //
                .loginProcessingUrl("/accounts/login-customer").failureUrl("/accounts/login-customer?error=true")
                .and().logout().logoutSuccessUrl("/") //
                .invalidateHttpSession(true) //
                .deleteCookies("JSESSIONID") //
                .and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
