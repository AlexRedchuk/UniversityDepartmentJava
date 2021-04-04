package redchuk.project.universitydepartment.services.impls;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.dto.tutor.TutorRequestDTO;
import redchuk.project.universitydepartment.dto.tutor.TutorResponseDTO;
import redchuk.project.universitydepartment.entity.Student;
import redchuk.project.universitydepartment.entity.Tutor;
import redchuk.project.universitydepartment.repositories.TutorRepository;
import redchuk.project.universitydepartment.services.interfaces.ITutorService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TutorService implements ITutorService {

    private final TutorRepository repo;
    private final ModelMapper modelMapper;

    @Override
    public Tutor save(TutorRequestDTO tutor) {
        var mapped = modelMapper.map(tutor, Tutor.class);
        return repo.save(mapped);
    }

    @Override
    public TutorResponseDTO getById(Long id) {
        Tutor tutor = repo.findById(id).orElseThrow();
        return modelMapper.map(tutor, TutorResponseDTO.class);
    }

    @Override
    public Set<TutorResponseDTO> getAll(int size, int page) {
        List<Tutor> sList = repo.findAll();
        final int listSize = sList.size();
        int start = 0;
        int end = listSize;
        if (listSize > size) {
            start = Math.min(listSize, size * (page - 1));
            end = Math.min(listSize, page * size);
        }
        List<Tutor> list = sList.subList(start, end);
        return list.stream()
                .map((tutor) -> modelMapper.map(tutor, TutorResponseDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Tutor edit(TutorRequestDTO tutor, Long id) {
        tutor.setId(id);
        var mapped = modelMapper.map(tutor, Tutor.class);
        return repo.save(mapped);
    }

    @Override
    public Set<TutorResponseDTO> getTutorsByPosition(String position) {
        List<Tutor> list = repo.getTutorsByPosition(position);
        return list.stream()
                .map((tutor) -> modelMapper.map(tutor, TutorResponseDTO.class))
                .collect(Collectors.toSet());
    }


    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
