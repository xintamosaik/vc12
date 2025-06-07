package grapefrui.xyz.backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.beans.factory.annotation.Autowired;

@Document(collection = "intel")
record Intel(String id, String title, String description, String content) {}

@SpringBootApplication
@RestController
public class BackendApplication {
  @Autowired
  private IntelRepository intelRepository;

  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    return String.format("Hi %s!", name);
  }

  @PostMapping("/intel/new")
  public Intel postMethodName(@RequestBody Intel entity) {
    Intel saved = intelRepository.save(entity);
    System.out.println("Saved entity: " + saved);
    return saved;
  }
}