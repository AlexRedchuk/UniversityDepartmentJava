package redchuk.project.universitydepartment.services.impls;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import redchuk.project.universitydepartment.entity.Subject;
import redchuk.project.universitydepartment.entity.SubjectSummary;
import redchuk.project.universitydepartment.repositories.SubjectSummaryRepository;
import redchuk.project.universitydepartment.stubs.GroupStub;
import redchuk.project.universitydepartment.stubs.SubjectStub;
import redchuk.project.universitydepartment.stubs.SubjectSummaryStub;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class SubjectSummaryServiceTest {
    private SubjectSummaryService service;

    @Mock
    ModelMapper modelMapper;

    @Mock
    private SubjectSummaryRepository repo;

    @BeforeEach
    void setup () {
        modelMapper = new ModelMapper();
        service = new SubjectSummaryService(repo, modelMapper);
    }

    @Test
    void testSuccesfulGetById() {
        var subjectSummary = SubjectSummaryStub.getRandomSubjectSummary();

        when(repo.findById(any())).thenReturn(Optional.of(subjectSummary));

        var res = service.getById(GroupStub.ID);

        assertAll(
                ()-> assertEquals(res.getId(), subjectSummary.getId()),
                ()-> assertEquals(res.getMark(), subjectSummary.getMark()),
                ()-> assertEquals(res.getSemester(), subjectSummary.getSemester()),
                ()-> assertEquals(res.getYear(), subjectSummary.getYear()),
                ()-> assertEquals(res.getStudentFullName(), subjectSummary.getStudent().getFullName()),
                ()-> assertEquals(res.getSubjectName(), subjectSummary.getSubject().getName()),
                ()-> assertEquals(res.getTutorFullName(), subjectSummary.getTutor().getFullName())
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
        var subjectSummary = List.of(SubjectSummaryStub.getRandomSubjectSummary());

        when(repo.findAll()).thenReturn(subjectSummary);

        var res = service.getAll(1,1);

        assertEquals(res.size(), subjectSummary.size());
    }

    @Test
    void testSuccessfulSave() {
        var captor = ArgumentCaptor.forClass(SubjectSummary.class);
        var subjectSummary = SubjectSummaryStub.getRandomSubjectSummary();
        when(repo.save(any())).thenReturn(subjectSummary);
        var result = service.save(SubjectSummaryStub.getSubjectSummaryRequestDTO());
        Mockito.verify(repo, atLeast(1)).save(captor.capture());
        assertEquals(subjectSummary, result);
    }

    @Test
    void testSuccesfulEdit() {
        var captor = ArgumentCaptor.forClass(SubjectSummary.class);
        var subjectSummary = SubjectSummaryStub.getRandomSubjectSummary();
        when(repo.save(any())).thenReturn(subjectSummary);
        var result = service.edit(SubjectSummaryStub.getSubjectSummaryRequestDTO(), GroupStub.ID);
        Mockito.verify(repo, atLeast(1)).save(captor.capture());
        assertEquals(subjectSummary, result);
    }

    @Test
    void testSuccessfulDelete() {
        service.delete(GroupStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(repo, atLeast(1)).deleteById(captor.capture());
        assertEquals(GroupStub.ID, captor.getValue());
    }

    @Test
    void testSucessfulgetSubjectSummariesByStudent_IdAndYearAndSemester() {
        var subjectSummary = List.of(SubjectSummaryStub.getRandomSubjectSummary());

        when(repo.getSubjectSummariesByStudent_IdAndYearAndSemester(1L, 2020, 1)).thenReturn(subjectSummary);

        var res = service
                .getSubjectSummariesByStudent_IdAndYearAndSemester(1L, 2020, 1);

        assertEquals(res.size(), subjectSummary.size());
    }

}