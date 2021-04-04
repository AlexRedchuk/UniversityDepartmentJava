package redchuk.project.universitydepartment.services.impls;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import redchuk.project.universitydepartment.entity.Student;
import redchuk.project.universitydepartment.entity.Subject;
import redchuk.project.universitydepartment.repositories.SubjectRepository;
import redchuk.project.universitydepartment.stubs.GroupStub;
import redchuk.project.universitydepartment.stubs.StudentStub;
import redchuk.project.universitydepartment.stubs.SubjectStub;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class SubjectServiceTest {
    private SubjectService service;

    @Mock
    ModelMapper modelMapper;

    @Mock
    private SubjectRepository repo;

    @BeforeEach
    void setup () {
        modelMapper = new ModelMapper();
        service = new SubjectService(repo, modelMapper);
    }

    @Test
    void testSuccesfulGetById() {
        var subject = SubjectStub.getRandomSubject();

        when(repo.findById(any())).thenReturn(Optional.of(subject));

        var res = service.getById(GroupStub.ID);

        assertAll(
                ()-> assertEquals(res.getId(), subject.getId()),
                ()-> assertEquals(res.getName(), subject.getName()),
                ()-> assertEquals(res.getCode(), subject.getCode())
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
        var subject = List.of(SubjectStub.getRandomSubject());

        when(repo.findAll()).thenReturn(subject);

        var res = service.getAll(1,1);

        assertEquals(res.size(), subject.size());
    }

    @Test
    void testSuccessfulSave() {
        var captor = ArgumentCaptor.forClass(Subject.class);
        var subject = SubjectStub.getRandomSubject();
        when(repo.save(any())).thenReturn(subject);
        var result = service.save(SubjectStub.getSubjectRequestDTO());
        Mockito.verify(repo, atLeast(1)).save(captor.capture());
        assertEquals(subject, result);
    }

    @Test
    void testSuccesfulEdit() {
        var captor = ArgumentCaptor.forClass(Subject.class);
        var subject = SubjectStub.getRandomSubject();
        when(repo.save(any())).thenReturn(subject);
        var result = service.edit(SubjectStub.getSubjectRequestDTO(), GroupStub.ID);
        Mockito.verify(repo, atLeast(1)).save(captor.capture());
        assertEquals(subject, result);
    }

    @Test
    void testSuccessfulDelete() {
        service.delete(GroupStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(repo, atLeast(1)).deleteById(captor.capture());
        assertEquals(GroupStub.ID, captor.getValue());
    }

    @Test
    void testSucessfulGetByCode() {
        var subject = SubjectStub.getRandomSubject();

        when(repo.getSubjectByCode(1224)).thenReturn(Optional.of(subject));

        var res = service.getSubjectByCode(1224);

        assertAll(
                ()-> assertEquals(res.getId(), subject.getId()),
                ()-> assertEquals(res.getName(), subject.getName()),
                ()-> assertEquals(res.getCode(), subject.getCode())
        );
    }

    @Test
    void testExceptionThrowGetByCode() {
        when(repo.getSubjectByCode(1226)).thenReturn(Optional.empty());
        var err = assertThrows(NoSuchElementException.class, () -> service.getSubjectByCode(1226));
        assertEquals(err.getMessage(), "No value present");
    }


}