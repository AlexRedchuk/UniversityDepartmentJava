package redchuk.project.universitydepartment.controllers;

import lombok.RequiredArgsConstructor;
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

    @GetMapping("/")
    public Set<SubjectResponseDTO> getAll(@RequestParam(required = false, defaultValue = "10") Integer size, @RequestParam(required = false, defaultValue = "1") Integer page) {
        return service.getAll(size, page);
    }
    @GetMapping("/{id}")
    public SubjectResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/")
    public Subject create (@RequestBody SubjectRequestDTO subject) {
        return service.save(subject);
    }

    @PutMapping("/{id}")
    public Subject update (@PathVariable Long id, @RequestBody SubjectRequestDTO subject) {
        return service.edit(subject, id);
    }

    @GetMapping("/byCode")
    public SubjectResponseDTO getByCode(@RequestParam int code) {
        return service.getSubjectByCode(code);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }


}
