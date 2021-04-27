package redchuk.project.universitydepartment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import redchuk.project.universitydepartment.dto.subjectPlan.SubjectPlanRequestDTO;
import redchuk.project.universitydepartment.dto.subjectPlan.SubjectPlanResponseDTO;
import redchuk.project.universitydepartment.entity.SubjectPlan;
import redchuk.project.universitydepartment.services.impls.SubjectPlanService;

import java.util.Set;

@RestController
@RequestMapping("/v1/subjectplans")
@RequiredArgsConstructor
public class SubjectPlanController {

    private final SubjectPlanService service;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/")
    public Set<SubjectPlanResponseDTO> getAll(@RequestParam(required = false, defaultValue = "10") Integer size, @RequestParam(required = false, defaultValue = "1") Integer page) {
        return service.getAll(size, page);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}")
    public SubjectPlanResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public SubjectPlan create (@RequestBody SubjectPlanRequestDTO subjectPlan) {
        return service.save(subjectPlan);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public SubjectPlan update (@PathVariable Long id, @RequestBody SubjectPlanRequestDTO subjectPlan) {
        return service.edit(subjectPlan, id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/getByYearAndGroup")
    public Set<SubjectPlanResponseDTO> getSubjectPlansByYearAndGroup_Id(@RequestParam int year, @RequestParam Long groupId){
        return service.getSubjectPlansByYearAndGroup_Id(year, groupId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }

}
