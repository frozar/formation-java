package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//  @RequestMapping(method = RequestMethod.GET, value = "/")
//  public String index(Model model) {
//      model.addAttribute("message", "Hello, world!");
//      return "index";
//  }

  @GetMapping(value = "/")
  public String index() {
    return "index";
  }

  @GetMapping(value = "/t")
  public String t() {
    return "t";
  }
}