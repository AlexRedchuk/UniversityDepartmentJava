package redchuk.project.universitydepartment.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redchuk.project.universitydepartment.entity.Student;
import redchuk.project.universitydepartment.entity.Tutor;
import redchuk.project.universitydepartment.stubs.GroupStub;
import redchuk.project.universitydepartment.stubs.StudentStub;
import redchuk.project.universitydepartment.stubs.TutorStub;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class StudentRepositoryTest {
    public static final Long GROUPID = StudentStub
            .getRandomStudent()
            .getUniversityGroup()
            .getId();
    @Autowired
    private StudentRepository repository;

    @Autowired
    private TestEntityManager entityManager;



    @Test
    void testFindStudentsByUniversityGroup_Id() {

        var expectedObject = StudentStub.getRandomStudent();
        List<Student> expectedObjects = new ArrayList<>();
        expectedObjects.add(expectedObject);
        entityManager.persist(expectedObject);
        entityManager.flush();
        var actualObjects = repository.getStudentsByUniversityGroup_Id(GROUPID);

        Assertions.assertThat(actualObjects).isEqualTo(expectedObjects);
    }

    @Test
    void testFindStudentsByUniversityGroup_IdNotSuccess() {
        var expectedObject = StudentStub.getRandomStudent();
        List<Student> expectedObjects = new ArrayList<>();
        expectedObjects.add(expectedObject);
        entityManager.persist(expectedObject);
        entityManager.flush();
        var actualObjects = repository.getStudentsByUniversityGroup_Id(GROUPID + 2);

        Assertions.assertThat(actualObjects.size()).isEqualTo(0);

    }
}