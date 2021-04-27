package redchuk.project.universitydepartment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import redchuk.project.universitydepartment.dto.tutor.TutorRequestDTO;
import redchuk.project.universitydepartment.dto.tutor.TutorResponseDTO;
import redchuk.project.universitydepartment.entity.Tutor;
import redchuk.project.universitydepartment.services.impls.TutorService;

import java.util.Set;

@RestController
@RequestMapping("/v1/tutors")
@RequiredArgsConstructor
public class TutorController {

    private final TutorService service;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/")
    public Set<TutorResponseDTO> getAll(@RequestParam(required = false, defaultValue = "10") Integer size, @RequestParam(required = false, defaultValue = "1") Integer page) {
        return service.getAll(size, page);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}")
    public TutorResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public Tutor create (@RequestBody TutorRequestDTO tutor) {
        return service.save(tutor);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Tutor update (@PathVariable Long id, @RequestBody TutorRequestDTO tutor) {
        return service.edit(tutor, id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/byPosition")
    public Set<TutorResponseDTO> getTutorsByPosition(@RequestParam String position) {
        return service.getTutorsByPosition(position);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }


}
