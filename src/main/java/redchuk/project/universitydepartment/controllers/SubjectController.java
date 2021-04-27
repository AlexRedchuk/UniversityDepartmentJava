package redchuk.project.universitydepartment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import redchuk.project.universitydepartment.dto.subject.SubjectRequestDTO;
import redchuk.project.universitydepartment.dto.subject.SubjectResponseDTO;
import redchuk.project.universitydepartment.entity.Subject;
import redchuk.project.universitydepartment.services.impls.SubjectService;

import java.util.Set;

@RestController
@RequestMapping("/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService service;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/")
    public Set<SubjectResponseDTO> getAll(@RequestParam(required = false, defaultValue = "10") Integer size, @RequestParam(required = false, defaultValue = "1") Integer page) {
        return service.getAll(size, page);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}")
    public SubjectResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public Subject create (@RequestBody SubjectRequestDTO subject) {
        return service.save(subject);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Subject update (@PathVariable Long id, @RequestBody SubjectRequestDTO subject) {
        return service.edit(subject, id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/byCode")
    public SubjectResponseDTO getByCode(@RequestParam int code) {
        return service.getSubjectByCode(code);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }


}
