package bg.sofia.fmi.uni.clubhub.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class CustomerSecurityConfiguration extends WebSecurityConfigurerAdapter {
    public CustomerSecurityConfiguration() {
        super();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/accounts/register").permitAll()
                .antMatchers("/accounts/login").permitAll();

        http.authorizeRequests()
                .antMatchers("/accounts/register-customer").permitAll()
                .antMatchers("/accounts/login-customer").permitAll()
                .antMatchers("/customers/**").hasAuthority("CUSTOMER").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/accounts/login-customer").failureUrl("/account/login-customer?error=true")
                .defaultSuccessUrl("/customers")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/accounts/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");

        http.authorizeRequests()
                .antMatchers("/accounts/register-club").permitAll()
                .antMatchers("/accounts/login-club").permitAll()
                .antMatchers("/clubs/**").hasAuthority("CLUB").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/accounts/login-club").failureUrl("/account/login-club?error=true")
                .defaultSuccessUrl("/clubs")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().deleteCookies("JSESSIONID")
                .logoutRequestMatcher(new AntPathRequestMatcher("/accounts/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }
}
