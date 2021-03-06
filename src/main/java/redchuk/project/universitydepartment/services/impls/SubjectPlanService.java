package redchuk.project.universitydepartment.services.impls;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.dto.subjectPlan.SubjectPlanRequestDTO;
import redchuk.project.universitydepartment.dto.subjectPlan.SubjectPlanResponseDTO;
import redchuk.project.universitydepartment.entity.SubjectPlan;
import redchuk.project.universitydepartment.repositories.SubjectPlanRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectPlanService {

    private final SubjectPlanRepository repo;
    private final ModelMapper modelMapper;


    public SubjectPlan save(SubjectPlanRequestDTO subjectPlan) {
        var mapped = modelMapper.map(subjectPlan, SubjectPlan.class);
        return repo.save(mapped);
    }


    public SubjectPlanResponseDTO getById(Long id) {
        SubjectPlan subjectPlan = repo.findById(id).orElseThrow();
        return modelMapper.map(subjectPlan, SubjectPlanResponseDTO.class);
    }


    public Set<SubjectPlanResponseDTO> getAll(int size, int page) {
        List<SubjectPlan> sList = repo.findAll();
        final int listSize = sList.size();
        int start = 0;
        int end = listSize;
        if (listSize > size) {
            start = Math.min(listSize, size * (page - 1));
            end = Math.min(listSize, page * size);
        }
        List<SubjectPlan> list = sList.subList(start, end);
        return list.stream()
                .map((subjectPlan) -> modelMapper.map(subjectPlan, SubjectPlanResponseDTO.class))
                .collect(Collectors.toSet());
    }


    public SubjectPlan edit(SubjectPlanRequestDTO subjectPlan, Long id) {
        subjectPlan.setId(id);
        var mapped = modelMapper.map(subjectPlan, SubjectPlan.class);
        return repo.save(mapped);
    }


    public Set<SubjectPlanResponseDTO> getSubjectPlansByYearAndGroup_Id(Integer year, Long groupId) {
        List<SubjectPlan> list = repo.getSubjectPlansByYearAndUniversityGroup_Id(year, groupId);
        return list.stream()
                .map((subjectPlan) -> modelMapper.map(subjectPlan, SubjectPlanResponseDTO.class))
                .collect(Collectors.toSet());
    }



    public void delete(Long id) {
        repo.deleteById(id);
    }
}
