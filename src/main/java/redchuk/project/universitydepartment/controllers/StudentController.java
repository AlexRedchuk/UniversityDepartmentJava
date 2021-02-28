package redchuk.project.universitydepartment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import redchuk.project.universitydepartment.entity.Student;
import redchuk.project.universitydepartment.services.impls.StudentService;

import java.util.Set;

@RestController
@RequestMapping("/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping("/")
    public Set<Student> getAll(@RequestParam(required = false, defaultValue = "10") Integer size, @RequestParam(required = false, defaultValue = "1") Integer page) {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/")
    public Student create (@RequestBody Student student) {
        return service.save(student);
    }

    @PutMapping("/{id}")
    public Student update (@PathVariable Long id, @RequestBody Student student) {
        return service.edit(student);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }

}
