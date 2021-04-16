package redchuk.project.universitydepartment.services.impls;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.dto.student.StudentRequestDTO;
import redchuk.project.universitydepartment.dto.student.StudentResponseDTO;
import redchuk.project.universitydepartment.entity.Student;
import redchuk.project.universitydepartment.repositories.StudentRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService  {

    private final StudentRepository repo;
    private final ModelMapper modelMapper;


    public Student save(StudentRequestDTO student) {
        var mapped = modelMapper.map(student, Student.class);
        return repo.save(mapped);
    }


    public StudentResponseDTO getById(Long id) {
        Student student = repo.findById(id).orElseThrow();
        return modelMapper.map(student, StudentResponseDTO.class);
    }


    public Set<StudentResponseDTO> getAll(int size, int page) {
        List<Student> sList = repo.findAll();
        final int listSize = sList.size();
        int start = 0;
        int end = listSize;
        if (listSize > size) {
            start = Math.min(listSize, size * (page - 1));
            end = Math.min(listSize, page * size);
        }
        List<Student> list = sList.subList(start, end);
        return list.stream()
                .map((student) -> modelMapper.map(student, StudentResponseDTO.class))
                .collect(Collectors.toSet());
    }


    public Student edit(StudentRequestDTO student, Long id) {
        student.setId(id);
        var mapped = modelMapper.map(student, Student.class);
        return repo.save(mapped);
    }


    public Set<StudentResponseDTO> getStudentsByGroup_Id(Long groupId) {
        List<Student> list = repo.getStudentsByUniversityGroup_Id(groupId);
        return list.stream()
                .map((student) -> modelMapper.map(student, StudentResponseDTO.class))
                .collect(Collectors.toSet());
    }


    public void delete(Long id) {
        repo.deleteById(id);
    }

}
