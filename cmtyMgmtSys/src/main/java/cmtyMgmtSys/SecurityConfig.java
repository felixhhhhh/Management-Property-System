package cmtyMgmtSys;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                .loginPage("/login");
        http
                .authorizeRequests()
                .antMatchers("/url/**").hasAuthority("Role_USER")
                .antMatchers("/.../...").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/.../...").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication().withUser("useremail@email.com").password("123").authorities("ROLE_ADMIN");

        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT emailId, password, enabled FROM users WHERE emailId=?")
                .authoritiesByUsernameQuery("SELECT emailId, authorities FROM authorities WHERE emailId=?");

    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
