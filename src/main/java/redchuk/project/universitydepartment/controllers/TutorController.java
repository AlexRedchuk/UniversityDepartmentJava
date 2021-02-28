package redchuk.project.universitydepartment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import redchuk.project.universitydepartment.entity.Tutor;
import redchuk.project.universitydepartment.services.impls.TutorService;

import java.util.Set;

@RestController
@RequestMapping("/v1/tutors")
@RequiredArgsConstructor
public class TutorController {

    private final TutorService service;

    @GetMapping("/")
    public Set<Tutor> getAll(@RequestParam(required = false, defaultValue = "10") Integer size, @RequestParam(required = false, defaultValue = "1") Integer page) {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Tutor getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/")
    public Tutor create (@RequestBody Tutor tutor) {
        return service.save(tutor);
    }

    @PutMapping("/{id}")
    public Tutor update (@PathVariable Long id, @RequestBody Tutor tutor) {
        return service.edit(tutor);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }

}
