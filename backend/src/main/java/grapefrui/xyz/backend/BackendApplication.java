package grapefrui.xyz.backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

record Intel(String title, String description, String content) {}

@SpringBootApplication
@RestController
public class BackendApplication {
  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    return String.format("Hi %s!", name);
  }

  @PostMapping("/intel/new")
  public Intel postMethodName(@RequestBody Intel entity) {
    System.out.println("Received entity: " + entity);
    System.out.println("Title: " + entity.title());
    System.out.println("Description: " + entity.description());
    System.out.println("Content: " + entity.content());
    
    return entity;
  }

  

}