package redchuk.project.universitydepartment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import redchuk.project.universitydepartment.dto.speciality.SpecialityRequestDTO;
import redchuk.project.universitydepartment.dto.speciality.SpecialityResponseDTO;
import redchuk.project.universitydepartment.entity.Speciality;
import redchuk.project.universitydepartment.services.impls.SpecialityService;

import java.util.Set;

@RestController
@RequestMapping("/v1/specialities")
@RequiredArgsConstructor
public class SpecialityController {

    private final SpecialityService service;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/")
    public Set<SpecialityResponseDTO> getAll(@RequestParam(required = false, defaultValue = "10") Integer size, @RequestParam(required = false, defaultValue = "1") Integer page) {
        return service.getAll(size, page);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}")
    public SpecialityResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public Speciality create (@RequestBody SpecialityRequestDTO speciality) {
        return service.save(speciality);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Speciality update (@PathVariable Long id, @RequestBody SpecialityRequestDTO speciality) {
        return service.edit(speciality, id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/byCode")
    public SpecialityResponseDTO getByCode(@RequestParam Integer code) {
        return  service.getSpecialityByCode(code);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }


}
