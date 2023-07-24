package com.example.interactrivemenu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;


    @Autowired
    public SecurityConfig(@Lazy UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/admin/addDish", "/admin/updateDish/{dishId}", "/admin/deleteDish/{dishId}").hasAuthority("ADMIN") // Разрешаем доступ только админу
//                .antMatchers("/**").permitAll() // Для всех остальных URL разрешаем доступ без авторизации
//                .and()
//                .addFilter(new UsernamePasswordAuthenticationFilter(authenticationManager()))
//                .formLogin().loginPage("/login").permitAll(); // Включаем форму логина
//    }

    // Аутентификация: установите данные пользователя/пароля и укажите роль.

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin123")
                .roles("USER", "ADMIN");
    }

    // Авторизация: укажите, какая роль может получить доступ к какому URL-адресу
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/admin-login").hasRole("ADMIN")
                .antMatchers("/add-dish").hasRole("ADMIN")
                .antMatchers("/update-dish/**").hasRole("ADMIN")
                .antMatchers("/delete-dish/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setContentType("application/json");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    PrintWriter out = response.getWriter();
                    out.print("{\"message\":\"Вы ввели неверный логин или пароль\"}");
                    out.flush();
                });
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

