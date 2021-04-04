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
import redchuk.project.universitydepartment.entity.SubjectPlan;
import redchuk.project.universitydepartment.repositories.SubjectPlanRepository;
import redchuk.project.universitydepartment.stubs.GroupStub;
import redchuk.project.universitydepartment.stubs.SubjectPlanStub;
import redchuk.project.universitydepartment.stubs.SubjectStub;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class SubjectPlanServiceTest {
    private SubjectPlanService service;

    @Mock
    ModelMapper modelMapper;

    @Mock
    private SubjectPlanRepository repo;

    @BeforeEach
    void setup () {
        modelMapper = new ModelMapper();
        service = new SubjectPlanService(repo, modelMapper);
    }

    @Test
    void testSuccesfulGetById() {
        var subjectPlan = SubjectPlanStub.getRandomSubjectPlan();

        when(repo.findById(any())).thenReturn(Optional.of(subjectPlan));

        var res = service.getById(GroupStub.ID);

        assertAll(
                ()-> assertEquals(res.getId(), subjectPlan.getId()),
                ()-> assertEquals(res.getGroupName(), subjectPlan.getGroup().getName()),
                ()-> assertEquals(res.getTutorFullName(), subjectPlan.getTutor().getFullName()),
                ()-> assertEquals(res.getType(), subjectPlan.getType()),
                ()-> assertEquals(res.getYear(), subjectPlan.getYear())
        );
    }

    @Test
    void testExceptionThrowGetById() {
        when(repo.findById(any())).thenReturn(Optional.empty());
        var err = assertThrows(NoSuchElementException.class, () -> service.getById(SubjectPlanStub.ID));
        assertEquals(err.getMessage(), "No value present");
    }

    @Test
    void testSuccesfulGetAll() {
        var subjectPlan = List.of(SubjectPlanStub.getRandomSubjectPlan());

        when(repo.findAll()).thenReturn(subjectPlan);

        var res = service.getAll(1,1);

        assertEquals(res.size(), subjectPlan.size());
    }

    @Test
    void testSuccessfulSave() {
        var captor = ArgumentCaptor.forClass(SubjectPlan.class);
        var subjectPlan = SubjectPlanStub.getRandomSubjectPlan();
        when(repo.save(any())).thenReturn(subjectPlan);
        var result = service.save(SubjectPlanStub.getSubjectPlanRequestDTO());
        Mockito.verify(repo, atLeast(1)).save(captor.capture());
        assertEquals(subjectPlan, result);
    }

    @Test
    void testSuccesfulEdit() {
        var captor = ArgumentCaptor.forClass(SubjectPlan.class);
        var subjectPlan = SubjectPlanStub.getRandomSubjectPlan();
        when(repo.save(any())).thenReturn(subjectPlan);
        var result = service.edit(SubjectPlanStub.getSubjectPlanRequestDTO(), GroupStub.ID);
        Mockito.verify(repo, atLeast(1)).save(captor.capture());
        assertEquals(subjectPlan, result);
    }

    @Test
    void testSuccessfulDelete() {
        service.delete(GroupStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(repo, atLeast(1)).deleteById(captor.capture());
        assertEquals(GroupStub.ID, captor.getValue());
    }

    @Test
    void testSucessfulGetSubjectPlansByYearAndGroup() {
        var subjectPlan = List.of(SubjectPlanStub.getRandomSubjectPlan());

        when(repo.getSubjectPlansByYearAndGroup_Id(2020, 1L)).thenReturn(subjectPlan);

        var res = service.getSubjectPlansByYearAndGroup_Id(2020,
                1L);

        assertEquals(res.size(), subjectPlan.size());
    }
}