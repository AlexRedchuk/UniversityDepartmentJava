package redchuk.project.universitydepartment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import redchuk.project.universitydepartment.entity.Speciality;
import redchuk.project.universitydepartment.services.impls.SpecialityService;

import java.util.Set;

@RestController
@RequestMapping("/v1/specialities")
@RequiredArgsConstructor
public class SpecialityController {

    private final SpecialityService service;

    @GetMapping("/")
    public Set<Speciality> getAll(@RequestParam(required = false, defaultValue = "10") Integer size, @RequestParam(required = false, defaultValue = "1") Integer page) {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Speciality getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/")
    public Speciality create (@RequestBody Speciality speciality) {
        return service.save(speciality);
    }

    @PutMapping("/{id}")
    public Speciality update (@PathVariable Long id, @RequestBody Speciality speciality) {
        return service.edit(speciality);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }

}
