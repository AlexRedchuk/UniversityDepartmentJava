package redchuk.project.universitydepartment.services.impls;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import redchuk.project.universitydepartment.entity.UniversityGroup;
import redchuk.project.universitydepartment.repositories.UniversityGroupRepository;
import redchuk.project.universitydepartment.stubs.GroupStub;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class UniversityGroupServiceTest {
    private UniversityGroupsService service;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private UniversityGroupRepository repo;

    @BeforeEach
    void setup () {
        modelMapper = new ModelMapper();
        service = new UniversityGroupsService(repo, modelMapper);
    }


    @Test
    void testSuccessfulGetById() {
        var group = GroupStub.getRandomGroup();

        when(repo.findById(any())).thenReturn(Optional.of(group));

        var res = service.getById(GroupStub.ID);

        assertAll(
                ()-> assertEquals(res.getId(), group.getId()),
                ()-> assertEquals(res.getName(), group.getName()),
                ()-> assertEquals(res.getSpecialityName(), group.getSpeciality().getName())
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
        var groups = List.of(GroupStub.getRandomGroup());

        when(repo.findAll()).thenReturn(groups);

        var res = service.getAll(1,1);

        assertEquals(res.size(), groups.size());
    }

    @Test
    void testSuccessfulSave() {
        var captor = ArgumentCaptor.forClass(UniversityGroup.class);
        var group = GroupStub.getRandomGroup();
        when(repo.save(any())).thenReturn(group);
        var result = service.save(GroupStub.getGroupRequestDTO());
        Mockito.verify(repo, atLeast(1)).save(captor.capture());
        assertEquals(group, result);
    }

    @Test
    void testSuccesfulEdit() {
        var captor = ArgumentCaptor.forClass(UniversityGroup.class);
        var group = GroupStub.getRandomGroup();
        when(repo.save(any())).thenReturn(group);
        var result = service.edit(GroupStub.getGroupRequestDTO(), GroupStub.ID);
        Mockito.verify(repo, atLeast(1)).save(captor.capture());
        assertEquals(group, result);
    }

    @Test
    void testSuccessfulDelete(){
        service.delete(GroupStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(repo, atLeast(1)).deleteById(captor.capture());
        assertEquals(GroupStub.ID,captor.getValue());
    }

    @Test
    void testSuccesfulGetByName() {
        var group = GroupStub.getRandomGroup();

        when(repo.getUniversityGroupByName(any())).thenReturn(Optional.of(group));

        var res = service.getGroupByName("Random string");

        assertAll(
                ()-> assertEquals(res.getId(), group.getId()),
                ()-> assertEquals(res.getName(), group.getName()),
                ()-> assertEquals(res.getSpecialityName(), group.getSpeciality().getName())
        );
    }

    @Test
    void testExceptionThrowGetByName() {
        when(repo.getUniversityGroupByName(any())).thenReturn(Optional.empty());
        var err = assertThrows(NoSuchElementException.class, () -> service.getGroupByName("Vitya"));
        assertEquals(err.getMessage(), "No value present");
    }

}