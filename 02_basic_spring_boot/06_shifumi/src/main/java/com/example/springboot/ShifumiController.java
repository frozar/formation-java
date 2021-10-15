package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShifumiController {

  // curl http://localhost:8080/?id=2
  @GetMapping("/")
  public String index(@RequestParam(name = "id") String idAction) {
    return "1 Shifumi action: " + idAction;
  }

  // curl http://localhost:8080/2
  @GetMapping("/{idAction}")
  public String pathVariable(@PathVariable String idAction) {
    return "2 Shifumi action: " + idAction;
  }

}
