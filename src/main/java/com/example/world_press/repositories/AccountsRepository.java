package com.example.world_press.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.world_press.entities.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
  public Accounts findByUsername(String username);
}
