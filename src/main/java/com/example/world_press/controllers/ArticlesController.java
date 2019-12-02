package com.example.world_press.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.world_press.repositories.ArticlesRepository;
import com.example.world_press.entites.Articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/articles")
public class ArticlesController {
  @Autowired
  ArticlesRepository articlesRepository;

  @GetMapping
  public String getArticles(Model model) {
    List<Articles> articles=articlesRepository.findAll();
    model.addAttribute("articles", articles);
    return "articles/index";
  }

  @GetMapping("{id}")
  public String getArticle(@PathVariable Long id, Model model) {
    Optional<Articles> article = articlesRepository.findById(id);
    model.addAttribute("article", article.get());
    return "articles/show";
  }

  @GetMapping("new")
  public String newArticle(Model model) {
    return "articles/new";
  }

  @PostMapping
  public String createArticle(@ModelAttribute Articles article) {
    articlesRepository.save(article);
    return "redirect:/articles";
  }

  @GetMapping("{id}/edit")
    public String editArticle(@PathVariable Long id, Model model) {
    Optional<Articles> article = articlesRepository.findById(id);
    model.addAttribute("article", article.get());
    return "articles/edit";
  }

  @PostMapping("{id}")
  public String updateArticle(@PathVariable Long id, @ModelAttribute Articles article) {
    article.setId(id);
    articlesRepository.save(article);
    return "redirect:/articles/" + id;
  }

  @PostMapping("{id}/delete")
  public String destroyArticle(@PathVariable Long id) {
    Optional<Articles> article = articlesRepository.findById(id);
    articlesRepository.delete(article.get());
    return "redirect:/articles";
  }
}
