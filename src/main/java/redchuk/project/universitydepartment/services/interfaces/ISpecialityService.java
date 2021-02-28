package redchuk.project.universitydepartment.services.interfaces;

import redchuk.project.universitydepartment.entity.Speciality;

import java.util.Set;

public interface ISpecialityService {
    Speciality save(Speciality speciality);
    Speciality getById(Long id);
    Set<Speciality> getAll();
    Speciality edit(Speciality speciality);
    void delete(Long id);
}
