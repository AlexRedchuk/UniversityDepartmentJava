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
import redchuk.project.universitydepartment.repositories.SpecialityRepository;
import redchuk.project.universitydepartment.stubs.GroupStub;
import redchuk.project.universitydepartment.stubs.SpecialityStub;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class SpecialityServiceTest {
    private SpecialityService service;

    @Mock
    ModelMapper modelMapper;

    @Mock
    private SpecialityRepository repo;

    @BeforeEach
    void setup () {
        modelMapper = new ModelMapper();
        service = new SpecialityService(repo, modelMapper);
    }

    @Test
    void testSuccessfulGetById() {
        var speciality = SpecialityStub.getRandomSpeciality();

        when(repo.findById(any())).thenReturn(Optional.of(speciality));

        var res = service.getById(SpecialityStub.ID);

        assertAll(
                ()-> assertEquals(res.getId(), speciality.getId()),
                ()-> assertEquals(res.getName(), speciality.getName()),
                ()-> assertEquals(res.getCode(), speciality.getCode()),
                ()-> assertEquals(res.getNumberOfCredits(), speciality.getNumberOfCredits()),
                ()-> assertEquals(res.getYearOfAdding(), speciality.getYearOfAdding())
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
        var specialities = List.of(SpecialityStub.getRandomSpeciality());

        when(repo.findAll()).thenReturn(specialities);

        var res = service.getAll(1,1);

        assertEquals(res.size(), specialities.size());
    }

    @Test
    void testSuccessfulSave() {
        var captor = ArgumentCaptor.forClass(Speciality.class);
        var group = SpecialityStub.getRandomSpeciality();
        when(repo.save(any())).thenReturn(group);
        var result = service.save(SpecialityStub.getSpecialityRequestDTO());
        Mockito.verify(repo, atLeast(1)).save(captor.capture());
        assertEquals(group, result);
    }

    @Test
    void testSuccesfulEdit() {
        var captor = ArgumentCaptor.forClass(Speciality.class);
        var speciality = SpecialityStub.getRandomSpeciality();
        when(repo.save(any())).thenReturn(speciality);
        var result = service.edit(SpecialityStub.getSpecialityRequestDTO(), GroupStub.ID);
        Mockito.verify(repo, atLeast(1)).save(captor.capture());
        assertEquals(speciality, result);
    }

    @Test
    void testSuccessfulDelete(){
        service.delete(GroupStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(repo, atLeast(1)).deleteById(captor.capture());
        assertEquals(GroupStub.ID,captor.getValue());
    }


    @Test
    void testSuccessfulGetByCode() {
        var speciality = SpecialityStub.getRandomSpeciality();

        when(repo.getSpecialityByCode(121)).thenReturn(Optional.of(speciality));

        var res = service.getSpecialityByCode(121);

        assertAll(
                ()-> assertEquals(res.getId(), speciality.getId()),
                ()-> assertEquals(res.getName(), speciality.getName()),
                ()-> assertEquals(res.getCode(), speciality.getCode()),
                ()-> assertEquals(res.getNumberOfCredits(), speciality.getNumberOfCredits()),
                ()-> assertEquals(res.getYearOfAdding(), speciality.getYearOfAdding())
        );
    }

    @Test
    void testExceptionThrowGetByName() {
        when(repo.getSpecialityByCode(155)).thenReturn(Optional.empty());
        var err = assertThrows(NoSuchElementException.class, () -> service.getSpecialityByCode(155));
        assertEquals(err.getMessage(), "No value present");
    }

}