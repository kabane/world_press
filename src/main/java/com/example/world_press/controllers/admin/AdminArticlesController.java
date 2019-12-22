package com.example.world_press.controllers.admin;

import com.example.world_press.entities.Articles;
import com.example.world_press.repositories.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/articles")
public class AdminArticlesController {
  @Autowired
  ArticlesRepository articlesRepository;

  @GetMapping
  public String getArticles(Model model) {
    List<Articles> articles=articlesRepository.findAll();
    model.addAttribute("articles", articles);
    return "/admin/articles/index";
  }

  @GetMapping("{id}")
  public String getArticle(@PathVariable Long id, Model model) {
    Optional<Articles> article = articlesRepository.findById(id);
    model.addAttribute("article", article.get());
    return "/admin/articles/show";
  }

  @GetMapping("new")
  public String newArticle(Model model) {
    return "/admin/articles/new";
  }

  @PostMapping
  public String createArticle(@ModelAttribute Articles article) {
    articlesRepository.save(article);
    return "redirect:/admin/articles";
  }

  @GetMapping("{id}/edit")
    public String editArticle(@PathVariable Long id, Model model) {
    Optional<Articles> article = articlesRepository.findById(id);
    model.addAttribute("article", article.get());
    return "/admin/articles/edit";
  }

  @PostMapping("{id}")
  public String updateArticle(@PathVariable Long id, @ModelAttribute Articles article) {
    article.setId(id);
    articlesRepository.save(article);
    return "redirect:/admin/articles/" + id;
  }

  @PostMapping("{id}/delete")
  public String destroyArticle(@PathVariable Long id) {
    Optional<Articles> article = articlesRepository.findById(id);
    articlesRepository.delete(article.get());
    return "redirect:/admin/articles";
  }
}
