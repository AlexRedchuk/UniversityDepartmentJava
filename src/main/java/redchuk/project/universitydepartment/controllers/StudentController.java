package redchuk.project.universitydepartment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import redchuk.project.universitydepartment.dto.student.StudentRequestDTO;
import redchuk.project.universitydepartment.dto.student.StudentResponseDTO;
import redchuk.project.universitydepartment.entity.Student;
import redchuk.project.universitydepartment.services.impls.StudentService;

import java.util.Set;

@RestController
@RequestMapping("/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/")
    public Set<StudentResponseDTO> getAll(@RequestParam(required = false, defaultValue = "10") Integer size, @RequestParam(required = false, defaultValue = "1") Integer page) {
        return service.getAll(size, page);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}")
    public StudentResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public Student create (@RequestBody StudentRequestDTO student) {
        return service.save(student);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Student update (@PathVariable Long id, @RequestBody StudentRequestDTO student) {
        return service.edit(student, id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/allByGroupId")
    public Set<StudentResponseDTO> getAllByGroupId (@RequestParam Long groupId) {
        return service.getStudentsByGroup_Id(groupId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }


}
