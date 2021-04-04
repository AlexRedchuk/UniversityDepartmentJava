package redchuk.project.universitydepartment.stubs;

import redchuk.project.universitydepartment.dto.student.StudentRequestDTO;
import redchuk.project.universitydepartment.entity.Student;

public final class StudentStub {
    public static final Long ID = 1L;
    public static Student getRandomStudent() {
        return Student
                .builder()
                .id(ID)
                .fullName("Alexander Redchuk")
                .group(GroupStub.getRandomGroup())
                .build();
    }

    public static StudentRequestDTO getStudentRequestDTO() {
        return StudentRequestDTO
                .builder()
                .id(ID)
                .fullName("Alexander Redchuk")
                .groupId(GroupStub.getRandomGroup().getId())
                .build();
    }
}
