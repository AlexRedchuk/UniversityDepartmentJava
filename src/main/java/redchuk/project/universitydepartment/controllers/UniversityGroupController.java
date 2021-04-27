package redchuk.project.universitydepartment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import redchuk.project.universitydepartment.dto.group.UniversityGroupRequestDTO;
import redchuk.project.universitydepartment.dto.group.UniversityGroupResponseDTO;
import redchuk.project.universitydepartment.entity.UniversityGroup;
import redchuk.project.universitydepartment.services.impls.UniversityGroupsService;

import java.util.Set;

@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class UniversityGroupController {

    private final UniversityGroupsService service;


    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/")
    public Set<UniversityGroupResponseDTO> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                                                  @RequestParam(required = false, defaultValue = "1") Integer page) {
        return service.getAll(size, page);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}")
    public UniversityGroupResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/byName")
    public UniversityGroupResponseDTO getByName(@RequestParam String name) {
        return service.getGroupByName(name);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public UniversityGroup create (@RequestBody UniversityGroupRequestDTO group) {
        return service.save(group);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public UniversityGroup update (@PathVariable Long id, @RequestBody UniversityGroupRequestDTO group) {
        return service.edit(group, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }


}
