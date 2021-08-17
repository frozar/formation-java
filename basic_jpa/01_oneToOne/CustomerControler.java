package com.example.accessingdatajpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerControler {

  @RequestMapping("/")
  public String home() {
    return "home";
  }
}
