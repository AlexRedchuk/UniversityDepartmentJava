package redchuk.project.universitydepartment.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redchuk.project.universitydepartment.entity.Student;
import redchuk.project.universitydepartment.repositories.StudentRepository;
import redchuk.project.universitydepartment.services.interfaces.IStudentService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final StudentRepository repo;

    @Override
    public Student save(Student student) {
        return repo.save(student);
    }

    @Override
    public Student getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Set<Student> getAll() {
        List<Student> list = repo.findAll();
        return new HashSet<>(list);
    }

    @Override
    public Student edit(Student student) {
        return repo.save(student);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
