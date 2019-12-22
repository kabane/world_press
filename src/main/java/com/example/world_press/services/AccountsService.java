package com.example.world_press.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.world_press.repositories.AccountsRepository;
import com.example.world_press.entities.Accounts;

@Service
public class AccountsService implements UserDetailsService {

  @Autowired
  private AccountsRepository repository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Accounts loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username == null || "".equals(username)) {
      throw new UsernameNotFoundException("Username is empty");
    }

    Accounts user = repository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found: " + username);
    }

    return user;
  }

  //adminを登録するメソッド
  @Transactional
  public void registerAdmin(String username, String password, String mailAddress) {
    Accounts user = new Accounts(username, passwordEncoder.encode(password), mailAddress);
    user.setAdmin(true);
    repository.save(user);
  }

  //一般ユーザを登録するメソッド
  @Transactional
  public void registerUser(String username, String password, String mailAddress) {
    Accounts user = new Accounts(username, passwordEncoder.encode(password), mailAddress);
    repository.save(user);
  }

}
