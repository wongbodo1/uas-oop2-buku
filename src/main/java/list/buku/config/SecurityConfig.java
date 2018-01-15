package list.buku.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import list.buku.util.CsrfHeaderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String SQL_LOGIN =
        "select username, password, active as enabled from login " +
            "where username = ?";

    private static final String SQL_PERMISSION =
        "select u.username, r.nama as authority from login u " +
            "join user_role ur on u.id = ur.id_user " +
            "join roles r on ur.id_role = r.id " +
            "where u.username = ?";

    @Autowired
    private DataSource ds;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
            .dataSource(ds)
            .usersByUsernameQuery(SQL_LOGIN)
            .authoritiesByUsernameQuery(SQL_PERMISSION);
}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/bootstrap/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin().permitAll()
            .and()
                .logout()
            .and()
                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                .csrf().csrfTokenRepository(csrfTokenRepository());
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository tokenRepo = new HttpSessionCsrfTokenRepository();
        tokenRepo.setHeaderName("X-XSRF-TOKEN");
        return tokenRepo;
    }

}