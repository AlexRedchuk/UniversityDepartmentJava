package redchuk.project.universitydepartment.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redchuk.project.universitydepartment.entity.Tutor;
import redchuk.project.universitydepartment.stubs.GroupStub;
import redchuk.project.universitydepartment.stubs.TutorStub;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class TutorRepositoryTest {
    public static final String POSITION = TutorStub.getRandomTutor().getPosition();
    @Autowired
    private TutorRepository repository;

    @Autowired
    private TestEntityManager entityManager;



    @Test
    void testFindTutorByPosition() {

        var expectedObject = TutorStub.getRandomTutor();
        List<Tutor> expectedObjects = new ArrayList<>();
        expectedObjects.add(expectedObject);
        entityManager.persist(expectedObject);
        entityManager.flush();
        var actualObjects = repository.getTutorsByPosition(POSITION);

        Assertions.assertThat(actualObjects).isEqualTo(expectedObjects);
    }

    @Test
    void testFindTutorByPositionNotSuccess() {
        var expectedObject = TutorStub.getRandomTutor();
        List<Tutor> expectedObjects = new ArrayList<>();
        expectedObjects.add(expectedObject);
        entityManager.persist(expectedObject);
        entityManager.flush();
        var actualObjects = repository.getTutorsByPosition(POSITION + "s");

        Assertions.assertThat(actualObjects.size()).isEqualTo(0);

    }
}