package com.example.world_press.controllers;

import java.util.List;
import javax.transaction.Transactional;
import com.example.world_press.repositories.ArticlesRepository;
import com.example.world_press.entites.Articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArticlesController {
  @Autowired
  ArticlesRepository articlesRepository;
  @RequestMapping(value = {"/", "/articles"})

  public String index(Model model) {
    List<Articles> articles=articlesRepository.findAll();
    model.addAttribute("articles", articles);
    return "articles/index";
  }

}
