package redchuk.project.universitydepartment.services.impls;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.dto.subject.SubjectRequestDTO;
import redchuk.project.universitydepartment.dto.subject.SubjectResponseDTO;
import redchuk.project.universitydepartment.entity.Student;
import redchuk.project.universitydepartment.entity.Subject;
import redchuk.project.universitydepartment.repositories.SubjectRepository;
import redchuk.project.universitydepartment.services.interfaces.ISubjectService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectService implements ISubjectService {

    private final SubjectRepository repo;
    private final ModelMapper modelMapper;

    @Override
    public Subject save(SubjectRequestDTO subject) {
        var mapped = modelMapper.map(subject, Subject.class);
        return repo.save(mapped);
    }

    @Override
    public SubjectResponseDTO getById(Long id) {
        Subject subject = repo.findById(id).orElseThrow();
        return modelMapper.map(subject, SubjectResponseDTO.class);
    }

    @Override
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

    @Override
    public Subject edit(SubjectRequestDTO subject, Long id) {
        subject.setId(id);
        var mapped = modelMapper.map(subject, Subject.class);
        return repo.save(mapped);
    }

    @Override
    public SubjectResponseDTO getSubjectByCode(int code) {
        Subject subject = repo.getSubjectByCode(code).orElseThrow();
        return modelMapper.map(subject, SubjectResponseDTO.class);
    }


    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
