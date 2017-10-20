package dev.hltech.alexaauthmock;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Mateusz Urbański <matek2305@gmail.com>.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("murbanski").password("password").roles("USER")
                .and().withUser("amichalik").password("password").roles("USER")
                .and().withUser("mpolec").password("password").roles("USER")
                .and().withUser("bzwolinski").password("password").roles("USER")
                .and().withUser("kmol").password("password").roles("USER");
    }
}
