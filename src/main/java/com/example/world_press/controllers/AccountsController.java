package com.example.world_press.controllers;

import com.example.world_press.entities.Accounts;
import com.example.world_press.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/accounts")
public class AccountsController {
  @Autowired
  AccountsRepository AccountsRepository;


  @GetMapping("{id}")
  public String getArticle(@PathVariable Long id, Model model) {
    Optional<Accounts> account = AccountsRepository.findById(id);
    model.addAttribute("accounts", account.get());
    return "articles/show";
  }

//  @GetMapping("new")
//  public String newArticle(Model model) {
//    return "articles/new";
//  }

//  @PostMapping
//  public String createArticle(@ModelAttribute Articles article) {
//    articlesRepository.save(article);
//    return "redirect:/articles";
//  }

//  @GetMapping("{id}/edit")
//    public String editArticle(@PathVariable Long id, Model model) {
//    Optional<Articles> article = articlesRepository.findById(id);
//    model.addAttribute("article", article.get());
//    return "articles/edit";
//  }

//  @PostMapping("{id}")
//  public String updateArticle(@PathVariable Long id, @ModelAttribute Articles article) {
//    article.setId(id);
//    articlesRepository.save(article);
//    return "redirect:/articles/" + id;
//  }

//  @PostMapping("{id}/delete")
//  public String destroyArticle(@PathVariable Long id) {
//    Optional<Articles> article = articlesRepository.findById(id);
//    articlesRepository.delete(article.get());
//    return "redirect:/articles";
//  }
}
