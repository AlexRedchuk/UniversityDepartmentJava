package redchuk.project.universitydepartment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import redchuk.project.universitydepartment.entity.Groups;
import redchuk.project.universitydepartment.services.impls.GroupsService;

import java.util.Set;

@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupsService service;

    @GetMapping("/")
    public Set<Groups> getAll(@RequestParam(required = false, defaultValue = "10") Integer size, @RequestParam(required = false, defaultValue = "1") Integer page) {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Groups getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/")
    public Groups create (@RequestBody Groups group) {
        return service.save(group);
    }

    @PutMapping("/{id}")
    public Groups update (@PathVariable Long id, @RequestBody Groups group) {
        return service.edit(group);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        service.delete(id);
    }

}
