package hristian.iliev.airsofter.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
  @GetMapping("/app")
  public String app() {
    return "app";
  }
}
