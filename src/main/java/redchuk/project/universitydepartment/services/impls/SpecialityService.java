package redchuk.project.universitydepartment.services.impls;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.dto.speciality.SpecialityRequestDTO;
import redchuk.project.universitydepartment.dto.speciality.SpecialityResponseDTO;
import redchuk.project.universitydepartment.entity.Speciality;
import redchuk.project.universitydepartment.repositories.SpecialityRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialityService  {

    private final SpecialityRepository repo;
    private final ModelMapper modelMapper;

    public Speciality save(SpecialityRequestDTO speciality) {
        var mapped = modelMapper.map(speciality, Speciality.class);
        return repo.save(mapped);
    }

    public SpecialityResponseDTO getById(Long id) {
        Speciality speciality = repo.findById(id).orElseThrow();
        return modelMapper.map(speciality, SpecialityResponseDTO.class);
    }

    public Set<SpecialityResponseDTO> getAll(int size, int page) {
        List<Speciality> sList = repo.findAll();
        final int listSize = sList.size();
        int start = 0;
        int end = listSize;
        if (listSize > size) {
            start = Math.min(listSize, size * (page - 1));
            end = Math.min(listSize, page * size);
        }
        List<Speciality> list = sList.subList(start, end);
        return list.stream()
                .map((speciality) -> modelMapper.map(speciality, SpecialityResponseDTO.class))
                .collect(Collectors.toSet());
    }

    public Speciality edit(SpecialityRequestDTO speciality, Long id) {
        speciality.setId(id);
        var mapped = modelMapper.map(speciality, Speciality.class);
        return repo.save(mapped);
    }

    public SpecialityResponseDTO getSpecialityByCode(Integer code) {
        Speciality speciality = repo.getSpecialityByCode(code).orElseThrow();
        return modelMapper.map(speciality, SpecialityResponseDTO.class);
    }


    public void delete(Long id) {
        repo.deleteById(id);
    }
}
