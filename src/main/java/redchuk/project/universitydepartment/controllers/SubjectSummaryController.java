package redchuk.project.universitydepartment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import redchuk.project.universitydepartment.entity.SubjectSummary;
import redchuk.project.universitydepartment.services.impls.SubjectSummaryService;

import java.util.Set;

@RestController
@RequestMapping("/v1/subjectsummaries")
@RequiredArgsConstructor
public class SubjectSummaryController {

    private final SubjectSummaryService service;

    @GetMapping("/")
    public Set<SubjectSummary> getAll(@RequestParam(required = false, defaultValue = "10") Integer size, @RequestParam(required = false, defaultValue = "1") Integer page) {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public SubjectSummary getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/")
    public SubjectSummary create (@RequestBody SubjectSummary subjectSummary) {
        return service.save(subjectSummary);
    }

    @PutMapping("/{id}")
    public SubjectSummary update (@PathVariable Long id, @RequestBody SubjectSummary subjectSummary) {
        return service.edit(subjectSummary);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }

}
