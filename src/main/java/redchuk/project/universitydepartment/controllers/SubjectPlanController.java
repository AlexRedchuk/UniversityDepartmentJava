package redchuk.project.universitydepartment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import redchuk.project.universitydepartment.entity.SubjectPlan;
import redchuk.project.universitydepartment.services.impls.SubjectPlanService;

import java.util.Set;

@RestController
@RequestMapping("/v1/subjectplans")
@RequiredArgsConstructor
public class SubjectPlanController {

    private final SubjectPlanService service;

    @GetMapping("/")
    public Set<SubjectPlan> getAll(@RequestParam(required = false, defaultValue = "10") Integer size, @RequestParam(required = false, defaultValue = "1") Integer page) {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public SubjectPlan getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/")
    public SubjectPlan create (@RequestBody SubjectPlan subjectPlan) {
        return service.save(subjectPlan);
    }

    @PutMapping("/{id}")
    public SubjectPlan update (@PathVariable Long id, @RequestBody SubjectPlan subjectPlan) {
        return service.edit(subjectPlan);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }

}
