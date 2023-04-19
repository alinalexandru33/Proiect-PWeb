package com.example.project.configuration;

import com.example.project.security.CustomUserDetailsService;
import com.example.project.security.JwtAuthenticationEntryPoint;
import com.example.project.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String ADMIN = "ADMIN";
    public static final String MANAGER = "MANAGER";
    public static final String ROLE_ADMIN = "ROLE_" + ADMIN;
    public static final String ROLE_MANAGER = "ROLE_" + MANAGER;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return  new JwtAuthenticationFilter();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()

                .antMatchers(HttpMethod.POST, "/albums/create").hasAnyRole(ADMIN, MANAGER)
                .antMatchers(HttpMethod.GET, "/albums/{id}/delete").hasAnyRole(ADMIN, MANAGER)
                .antMatchers(HttpMethod.GET, "/albums/{id}").permitAll()
                .antMatchers("/albums").permitAll()

                .antMatchers(HttpMethod.POST,"/artists/create").hasRole(ADMIN)
                .antMatchers(HttpMethod.DELETE,"/artists/{id}/delete").hasRole(ADMIN)
                .antMatchers(HttpMethod.GET,"/artists/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/artists").permitAll()
                /*de modif*/
                .antMatchers("/recordlabels").authenticated()
                .antMatchers("/recordlabels/{id}").hasRole(ADMIN)


                .antMatchers("/songs/{id}").permitAll()
                .antMatchers("/songs/{id}/delete").hasAnyRole(ADMIN,MANAGER)


                .antMatchers("/managers/{id}/delete").hasRole(ADMIN)
                .antMatchers("/managers/{id}/edit").hasRole(ADMIN)
                .antMatchers("/managers/{id}").hasAnyRole(ADMIN, MANAGER)
                .antMatchers("/managers").hasRole(ADMIN)


                .antMatchers("/deals/{id}").hasAnyRole(ADMIN,MANAGER)

                .antMatchers("/consults/{id}/delete").hasRole(ADMIN)
                .antMatchers("/consults/{id}/edit").hasAnyRole(MANAGER, ADMIN)
                .antMatchers("/consults/add").hasAnyRole(MANAGER, ADMIN)
                .antMatchers("/consults/{id}").hasAnyRole(MANAGER, ADMIN)


                .antMatchers("/**/bootstrap/**").permitAll()

                .anyRequest()
                .authenticated();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
