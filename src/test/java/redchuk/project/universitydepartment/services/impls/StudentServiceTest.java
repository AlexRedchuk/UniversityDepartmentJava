package redchuk.project.universitydepartment.services.impls;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import redchuk.project.universitydepartment.entity.Speciality;
import redchuk.project.universitydepartment.entity.Student;
import redchuk.project.universitydepartment.repositories.StudentRepository;
import redchuk.project.universitydepartment.stubs.GroupStub;
import redchuk.project.universitydepartment.stubs.SpecialityStub;
import redchuk.project.universitydepartment.stubs.StudentStub;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class StudentServiceTest {
    private StudentService service;

    @Mock
    ModelMapper modelMapper;

    @Mock
    private StudentRepository repo;

    @BeforeEach
    void setup() {
        modelMapper = new ModelMapper();
        service = new StudentService(repo, modelMapper);
    }

    @Test
    void testSuccessfulGetById() {
        var student = StudentStub.getRandomStudent();

        when(repo.findById(any())).thenReturn(Optional.of(student));

        var res = service.getById(GroupStub.ID);

        assertAll(
                () -> assertEquals(res.getId(), student.getId()),
                () -> assertEquals(res.getFullName(), student.getFullName()),
                () -> assertEquals(res.getGroupName(), student.getGroup().getName())
        );
    }

    @Test
    void testExceptionThrowGetById() {
        when(repo.findById(any())).thenReturn(Optional.empty());
        var err = assertThrows(NoSuchElementException.class, () -> service.getById(GroupStub.ID));
        assertEquals(err.getMessage(), "No value present");
    }

    @Test
    void testSuccesfulGetAll() {
        var students = List.of(StudentStub.getRandomStudent());

        when(repo.findAll()).thenReturn(students);

        var res = service.getAll(1,1);

        assertEquals(res.size(), students.size());
    }

    @Test
    void testSuccessfulSave() {
        var captor = ArgumentCaptor.forClass(Student.class);
        var student = StudentStub.getRandomStudent();
        when(repo.save(any())).thenReturn(student);
        var result = service.save(StudentStub.getStudentRequestDTO());
        Mockito.verify(repo, atLeast(1)).save(captor.capture());
        assertEquals(student, result);
    }

    @Test
    void testSuccesfulEdit() {
        var captor = ArgumentCaptor.forClass(Student.class);
        var student = StudentStub.getRandomStudent();
        when(repo.save(any())).thenReturn(student);
        var result = service.edit(StudentStub.getStudentRequestDTO(), GroupStub.ID);
        Mockito.verify(repo, atLeast(1)).save(captor.capture());
        assertEquals(student, result);
    }

    @Test
    void testSuccessfulDelete() {
        service.delete(GroupStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(repo, atLeast(1)).deleteById(captor.capture());
        assertEquals(GroupStub.ID, captor.getValue());
    }

    @Test
    void testSucessfulGetStudentsByGroup () {
        var students = List.of(StudentStub.getRandomStudent());

        when(repo.getStudentsByGroup_Id(1L)).thenReturn(students);

        var res = service.getStudentsByGroup_Id(1L);

        assertEquals(res.size(), students.size());
    }
}