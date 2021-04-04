package redchuk.project.universitydepartment.services.impls;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.dto.group.UniversityGroupRequestDTO;
import redchuk.project.universitydepartment.dto.group.UniversityGroupResponseDTO;
import redchuk.project.universitydepartment.entity.UniversityGroup;
import redchuk.project.universitydepartment.repositories.UniversityGroupRepository;
import redchuk.project.universitydepartment.services.interfaces.IUniversityGroupsService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityGroupsService implements IUniversityGroupsService {

    private final UniversityGroupRepository repo;
    private final ModelMapper modelMapper;

    @Override
    public UniversityGroup save(UniversityGroupRequestDTO group) {
        var mapped = modelMapper.map(group, UniversityGroup.class);
        return repo.save(mapped);
    }

    @Override
    public UniversityGroup edit(UniversityGroupRequestDTO group, Long id) {
        group.setId(id);
        var mapped = modelMapper.map(group, UniversityGroup.class);
        return repo.save(mapped);
    }

    @Override
    public UniversityGroupResponseDTO getById(Long id) {
        UniversityGroup group = repo.findById(id).orElseThrow();
        return modelMapper.map(group, UniversityGroupResponseDTO.class);
    }

    @Override
    public Set<UniversityGroupResponseDTO> getAll(int size, int page) {
        List<UniversityGroup> sList = repo.findAll();
        final int listSize = sList.size();
        int start = 0;
        int end = listSize;
        if (listSize > size) {
            start = Math.min(listSize, size * (page - 1));
            end = Math.min(listSize, page * size);
        }
        List<UniversityGroup> list = sList.subList(start, end);
        return list.stream()
                .map((group) -> modelMapper.map(group, UniversityGroupResponseDTO.class))
                .collect(Collectors.toSet());
    }


    @Override
    public UniversityGroupResponseDTO getGroupByName(String name) {
        UniversityGroup group = repo.getGroupsByName(name).orElseThrow();
        return modelMapper.map(group, UniversityGroupResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
