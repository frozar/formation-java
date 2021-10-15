package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShifumiController {

  @GetMapping("/")
  public String index(@RequestParam(name = "id") String idAction) {
    return "Shifumi action: " + idAction;
  }

}
