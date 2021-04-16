package redchuk.project.universitydepartment.services.impls;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.dto.subject.SubjectRequestDTO;
import redchuk.project.universitydepartment.dto.subject.SubjectResponseDTO;
import redchuk.project.universitydepartment.entity.Subject;
import redchuk.project.universitydepartment.repositories.SubjectRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository repo;
    private final ModelMapper modelMapper;

    public Subject save(SubjectRequestDTO subject) {
        var mapped = modelMapper.map(subject, Subject.class);
        return repo.save(mapped);
    }

    public SubjectResponseDTO getById(Long id) {
        Subject subject = repo.findById(id).orElseThrow();
        return modelMapper.map(subject, SubjectResponseDTO.class);
    }

    public Set<SubjectResponseDTO> getAll(int size, int page) {
        List<Subject> sList = repo.findAll();
        final int listSize = sList.size();
        int start = 0;
        int end = listSize;
        if (listSize > size) {
            start = Math.min(listSize, size * (page - 1));
            end = Math.min(listSize, page * size);
        }
        List<Subject> list = sList.subList(start, end);
        return list.stream()
                .map((subject) -> modelMapper.map(subject, SubjectResponseDTO.class))
                .collect(Collectors.toSet());
    }

    public Subject edit(SubjectRequestDTO subject, Long id) {
        subject.setId(id);
        var mapped = modelMapper.map(subject, Subject.class);
        return repo.save(mapped);
    }

    public SubjectResponseDTO getSubjectByCode(Integer code) {
        Subject subject = repo.getSubjectByCode(code).orElseThrow();
        return modelMapper.map(subject, SubjectResponseDTO.class);
    }


    public void delete(Long id) {
        repo.deleteById(id);
    }
}
