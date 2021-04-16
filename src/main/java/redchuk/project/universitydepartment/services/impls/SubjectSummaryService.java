package redchuk.project.universitydepartment.services.impls;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.dto.subjectSummary.SubjectSummaryRequestDTO;
import redchuk.project.universitydepartment.dto.subjectSummary.SubjectSummaryResponseDTO;
import redchuk.project.universitydepartment.entity.SubjectSummary;
import redchuk.project.universitydepartment.repositories.SubjectSummaryRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectSummaryService {

    private final SubjectSummaryRepository repo;
    private final ModelMapper modelMapper;


    public SubjectSummary save(SubjectSummaryRequestDTO subjectSummary) {
        var mapped = modelMapper.map(subjectSummary, SubjectSummary.class);
        return repo.save(mapped);
    }


    public SubjectSummaryResponseDTO getById(Long id) {
        SubjectSummary subjectSummary = repo.findById(id).orElseThrow();
        return modelMapper.map(subjectSummary, SubjectSummaryResponseDTO.class);
    }


    public Set<SubjectSummaryResponseDTO> getAll(int size, int page) {
        List<SubjectSummary> sList = repo.findAll();
        final int listSize = sList.size();
        int start = 0;
        int end = listSize;
        if (listSize > size) {
            start = Math.min(listSize, size * (page - 1));
            end = Math.min(listSize, page * size);
        }
        List<SubjectSummary> list = sList.subList(start, end);
        return list.stream()
                .map((subjectSummary) -> modelMapper.map(subjectSummary, SubjectSummaryResponseDTO.class))
                .collect(Collectors.toSet());
    }


    public SubjectSummary edit(SubjectSummaryRequestDTO subjectSummary, Long id) {
        subjectSummary.setId(id);
        var mapped = modelMapper.map(subjectSummary, SubjectSummary.class);
        return repo.save(mapped);
    }


    public Set<SubjectSummaryResponseDTO> getSubjectSummariesByStudent_IdAndYearAndSemester(Long studentId, Integer semester, Integer year) {
        List<SubjectSummary> list = repo.getSubjectSummariesByStudent_IdAndYearAndSemester(studentId, semester, year);
        return list.stream()
                .map((subjectSummary) -> modelMapper.map(subjectSummary, SubjectSummaryResponseDTO.class))
                .collect(Collectors.toSet());
    }


    public void delete(Long id) {
        repo.deleteById(id);
    }
}
