package redchuk.project.universitydepartment.services.interfaces;

import redchuk.project.universitydepartment.dto.student.StudentRequestDTO;
import redchuk.project.universitydepartment.dto.student.StudentResponseDTO;
import redchuk.project.universitydepartment.entity.Student;

import java.util.Set;

public interface IStudentService {
    Student save(StudentRequestDTO student);
    StudentResponseDTO getById(Long id);
    Set<StudentResponseDTO> getAll(int size, int page);
    Student edit(StudentRequestDTO student, Long id);
    Set<StudentResponseDTO> getStudentsByGroup_Id(Long groupId);
    void delete(Long id);
}
