package grapefrui.xyz.backend;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class IntelController {
    @Autowired
    private IntelRepository intelRepository;

    @PostMapping("/intel/new")
    public Intel postMethodName(@RequestBody Intel entity) {
        return intelRepository.save(entity);
    }

    @GetMapping("/intel/all")
    public Iterable<Intel> getAllIntel() {
        return intelRepository.findAll();
    }
}