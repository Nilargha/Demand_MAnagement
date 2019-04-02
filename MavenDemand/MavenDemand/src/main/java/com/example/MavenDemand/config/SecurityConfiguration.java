package com.example.MavenDemand.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import javax.sql.DataSource;


@Configuration

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private DataSource ds;

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource ds() {
        return DataSourceBuilder.create().build();
    }

    /* Spring Security Configurations Start */
    @Autowired
    public void configureAMBuilder(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(ds)
            .authoritiesByUsernameQuery("select USERNAME, ROLE FROM USER where USERNAME=?")
            .usersByUsernameQuery("SELECT USERNAME,PASS as password,1 FROM USER where USERNAME=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers("/**").permitAll()
            .antMatchers("/api/**").authenticated();
        http.csrf().disable();
    }
    /* Spring Security Configurations End */

}