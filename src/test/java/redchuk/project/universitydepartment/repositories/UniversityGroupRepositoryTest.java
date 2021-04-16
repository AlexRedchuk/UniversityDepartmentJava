package redchuk.project.universitydepartment.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redchuk.project.universitydepartment.stubs.GroupStub;


import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class UniversityGroupRepositoryTest {
    public static final String NAME = "343(3)";
    @Autowired
    private UniversityGroupRepository repository;

    @Autowired
    private TestEntityManager entityManager;



    @Test
    void testFindGroupByName() {

        var expectedObject = GroupStub.getRandomGroup();
        entityManager.persist(expectedObject);
        entityManager.flush();
        var actualObject = repository.getUniversityGroupByName(NAME);

        Assertions.assertThat(actualObject.get()).isEqualTo(expectedObject);
    }

    @Test
    void testFindGroupByNameNotSuccess() {
        var expectedObject = GroupStub.getRandomGroup();
        entityManager.persist(expectedObject);
        entityManager.flush();

        var actualObject = repository
                .getUniversityGroupByName(NAME+"s");

        Assertions.assertThat(actualObject.isPresent()).isEqualTo(false);

    }
}