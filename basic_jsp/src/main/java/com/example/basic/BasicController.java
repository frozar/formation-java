package com.example.basic;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BasicController {

  // inject value via src/main/resources/application.properties
  @Value("${welcome.message:test}")
  private String message = "Hello World";

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Map<String, Object> model) {
    model.put("message", this.message);
    return "home";
  }

  @GetMapping("/greeting")
  public String greeting(
      @RequestParam(name = "name", required = false, defaultValue = "World") String name,
      Model model) {
    model.addAttribute("name", name);
    model.addAttribute("myVar", 42);
    model.addAttribute("myArray", Arrays.asList("toto", "titi"));
    return "greeting";
  }

  @GetMapping("/expression")
  public String expression() {
    return "00-expression";
  }

  @GetMapping("/scriplet")
  public String scriplet() {
    return "01-scriplet";
  }

  @GetMapping("/declaration")
  public String declaration() {
    return "02-declaration";
  }

  @GetMapping("/call-java")
  public String callJava() {
    return "03-call-java";
  }

  @GetMapping("/request-object")
  public String requestObject() {
    return "04-request-object";
  }

  @GetMapping("/include")
  public String include() {
    return "05-include";
  }

  @GetMapping("/form")
  public String form() {
    return "06-form";
  }

  @GetMapping("/form-confirmation")
  public String formConfirmation() {
    return "06-form-response";
  }

  @GetMapping("/form-dropdown")
  public String formDropDown() {
    return "07-form-dropdown";
  }

  @GetMapping("/form-dropdown-confirmation")
  public String formDropDownConfirmation() {
    return "07-form-dropdown-response";
  }

  @GetMapping("/form-radio")
  public String formRadio() {
    return "08-form-radio";
  }

  @GetMapping("/form-radio-confirmation")
  public String formRadioConfirmation() {
    return "08-form-radio-response";
  }

  @GetMapping("/form-checkbox")
  public String formCheckbox() {
    return "09-form-checkbox";
  }

  @GetMapping("/form-checkbox-confirmation")
  public String formCheckboxConfirmation() {
    return "09-form-checkbox-response";
  }

  @GetMapping("/cookies")
  public String cookies() {
    return "10-cookies-personalize-form";
  }

  @GetMapping("/cookies-response")
  public String cookiesConfirmation() {
    return "10-cookies-personalize-response";
  }

  @GetMapping("/cookies-homepage")
  public String cookiesHomepage() {
    return "10-cookies-homepage";
  }

}