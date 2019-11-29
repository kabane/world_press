package com.example.world_press.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.world_press.entites.Articles;

@Repository
public interface ArticlesRepository extends JpaRepository<Articles, Long> {

}
