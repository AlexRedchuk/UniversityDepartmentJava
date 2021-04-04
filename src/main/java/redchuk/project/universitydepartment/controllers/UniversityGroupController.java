package redchuk.project.universitydepartment.controllers;

import lombok.RequiredArgsConstructor;
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

    @GetMapping("/")
    public Set<UniversityGroupResponseDTO> getAll(@RequestParam(required = false, defaultValue = "10") Integer size,
                                                  @RequestParam(required = false, defaultValue = "1") Integer page) {
        return service.getAll(size, page);
    }
    @GetMapping("/{id}")
    public UniversityGroupResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/byName")
    public UniversityGroupResponseDTO getByName(@RequestParam String name) {
        return service.getGroupByName(name);
    }

    @PostMapping("/")
    public UniversityGroup create (@RequestBody UniversityGroupRequestDTO group) {
        return service.save(group);
    }

    @PutMapping("/{id}")
    public UniversityGroup update (@PathVariable Long id, @RequestBody UniversityGroupRequestDTO group) {
        return service.edit(group, id);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }


}
