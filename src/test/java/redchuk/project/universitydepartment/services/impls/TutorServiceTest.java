package redchuk.project.universitydepartment.services.impls;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import redchuk.project.universitydepartment.entity.Tutor;
import redchuk.project.universitydepartment.repositories.TutorRepository;
import redchuk.project.universitydepartment.stubs.GroupStub;
import redchuk.project.universitydepartment.stubs.TutorStub;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class TutorServiceTest {
    private TutorService service;

    @Mock
    ModelMapper modelMapper;

    @Mock
    private TutorRepository repo;

    @BeforeEach
    void setup () {
        modelMapper = new ModelMapper();
        service = new TutorService(repo, modelMapper);
    }

    @Test
    void testSuccessfulGetById() {
        var tutor = TutorStub.getRandomTutor();

        when(repo.findById(any())).thenReturn(Optional.of(tutor));

        var res = service.getById(GroupStub.ID);

        assertAll(
                ()-> assertEquals(res.getId(), tutor.getId()),
                ()-> assertEquals(res.getFullName(), tutor.getFullName()),
                ()-> assertEquals(res.getDateOfBirth(), tutor.getDateOfBirth()),
                ()-> assertEquals(res.getDegree(), tutor.getDegree()),
                ()-> assertEquals(res.getSalary(), tutor.getSalary()),
                ()-> assertEquals(res.getTabNumber(), tutor.getTabNumber()),
                ()-> assertEquals(res.getPosition(), tutor.getPosition())
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
        var tutor = List.of(TutorStub.getRandomTutor());

        when(repo.findAll()).thenReturn(tutor);

        var res = service.getAll(1,1);

        assertEquals(res.size(), tutor.size());
    }

    @Test
    void testSuccessfulSave() {
        var captor = ArgumentCaptor.forClass(Tutor.class);
        var tutor = TutorStub.getRandomTutor();
        when(repo.save(any())).thenReturn(tutor);
        var result = service.save(TutorStub.getTutorRequestDTO());
        Mockito.verify(repo, atLeast(1)).save(captor.capture());
        assertEquals(tutor, result);
    }

    @Test
    void testSuccesfulEdit() {
        var captor = ArgumentCaptor.forClass(Tutor.class);
        var tutor = TutorStub.getRandomTutor();
        when(repo.save(any())).thenReturn(tutor);
        var result = service.edit(TutorStub.getTutorRequestDTO(), GroupStub.ID);
        Mockito.verify(repo, atLeast(1)).save(captor.capture());
        assertEquals(tutor, result);
    }

    @Test
    void testSuccessfulDelete() {
        service.delete(GroupStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(repo, atLeast(1)).deleteById(captor.capture());
        assertEquals(GroupStub.ID, captor.getValue());
    }


    @Test
    void testSucessfulGetByPosition() {
        var tutor = List.of(TutorStub.getRandomTutor());

        when(repo.getTutorsByPosition(any())).thenReturn(tutor);

        var res = service.getTutorsByPosition(TutorStub.getRandomTutor().getPosition());

        assertEquals(res.size(), tutor.size());
    }
}