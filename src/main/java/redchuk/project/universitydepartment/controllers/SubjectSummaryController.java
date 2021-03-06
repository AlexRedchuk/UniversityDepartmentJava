package redchuk.project.universitydepartment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import redchuk.project.universitydepartment.dto.subjectSummary.SubjectSummaryRequestDTO;
import redchuk.project.universitydepartment.dto.subjectSummary.SubjectSummaryResponseDTO;
import redchuk.project.universitydepartment.entity.SubjectSummary;
import redchuk.project.universitydepartment.services.impls.SubjectSummaryService;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/subjectsummaries")
@RequiredArgsConstructor
public class SubjectSummaryController {

    private final SubjectSummaryService service;
    @GetMapping("/")
    public Set<SubjectSummaryResponseDTO> getAll(@RequestParam(required = false, defaultValue = "10") Integer size, @RequestParam(required = false, defaultValue = "1") Integer page) {
        return service.getAll(size, page);
    }
    @GetMapping("/{id}")
    public SubjectSummaryResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/")
    public SubjectSummary create (@RequestBody SubjectSummaryRequestDTO subjectSummary) {
        return service.save(subjectSummary);
    }

    @PutMapping("/{id}")
    public SubjectSummary update (@PathVariable Long id, @RequestBody SubjectSummaryRequestDTO subjectSummary) {
        return service.edit(subjectSummary, id);
    }

    @GetMapping("/byStudentIdAndYearAndSemester")
    public Set<SubjectSummaryResponseDTO> getSubjectSummariesByStudent_IdAndYearAndSemester
            (@RequestParam Long studentId, @RequestParam int year, @RequestParam int semester) {
        return service.getSubjectSummariesByStudent_IdAndYearAndSemester(studentId, semester, year);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }

}
