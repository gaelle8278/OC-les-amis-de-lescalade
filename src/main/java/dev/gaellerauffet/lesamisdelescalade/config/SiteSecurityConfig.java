package dev.gaellerauffet.lesamisdelescalade.config;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SiteSecurityConfig extends WebSecurityConfigurerAdapter {
	/*@Autowired
    private AccessDeniedHandler accessDeniedHandler;*/
	
	// roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {

    	 http.authorizeRequests()
         .antMatchers("/").permitAll()
         .antMatchers("/admin/**").access("hasRole('ADMIN')")
         .antMatchers("/membre/**").access("hasRole('USER')")
         .and().formLogin().loginPage("/connexion")
			.permitAll()
			.and()
			.logout()
			.permitAll();
         http.csrf().disable();
    }

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    	 auth
         .inMemoryAuthentication()
         .withUser("user").password("{noop}password").roles("USER").and()
         .withUser("admin").password("{noop}password").roles("USER","ADMIN");
    }*/
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

   /*http.
        authorizeRequests()
        	.antMatchers("/*").permitAll()
        	.antMatchers("/membre/**").hasAuthority("USER").anyRequest()
        .authenticated().and().formLogin()
        .loginPage("/connexion").failureUrl("/connexion?error=true")
        .defaultSuccessUrl("/membre/mon-compte", true)
        .usernameParameter("email")
        .passwordParameter("password")
        .and().logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/").and().exceptionHandling()
        .accessDeniedPage("/access-denied");*/
    
    	http.
        	authorizeRequests()
        		.antMatchers("/*").permitAll()
        		.antMatchers("/membre/**").hasAuthority("ROLE_USER")
        		.antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
        		.and()
        	.formLogin()
        		.loginPage("/connexion")
        		.failureUrl("/connexion?error=true")
        		.defaultSuccessUrl("/default", true)
        		.usernameParameter("email")
        		.passwordParameter("password")
        		.and()
        	.logout()
        		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        		.logoutSuccessUrl("/")
        		.and()
        	.exceptionHandling()
        		.accessDeniedPage("/access-denied");
    	
    	
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/assets/**", "/webjars/**");
    }
}
