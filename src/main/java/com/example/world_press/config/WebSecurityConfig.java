package com.example.world_press.config;

import com.example.world_press.services.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AccountsService userService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //TODO: 最低限の実装。cssなどのstaticファイルなどの許可を追加する必要あります。
    http
      .authorizeRequests()
      .antMatchers("/login", "/login-error", "/articles/**").permitAll()
      .antMatchers("/**").hasRole("USER")
      .antMatchers("/admin/**").hasRole("ADMIN")
      .and()
      .formLogin()
      .loginPage("/login").failureUrl("/login-error");
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userService)
      .passwordEncoder(passwordEncoder());
    userService.registerAdmin("admin", "secret", "admin@localhost");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
