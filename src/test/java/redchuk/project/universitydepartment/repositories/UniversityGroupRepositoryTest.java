package redchuk.project.universitydepartment.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redchuk.project.universitydepartment.stubs.GroupStub;

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

        var expectedCategory = GroupStub.getRandomGroup();
        entityManager.persist(expectedCategory);
        entityManager.flush();
        var actualCategory = repository.getGroupsByName(NAME);

        Assertions.assertThat(actualCategory.get()).isEqualTo(expectedCategory);
    }

    @Test
    void testFindCategoryByDescriptionNotSuccess() {
        var expectedCategory = GroupStub.getRandomGroup();
        entityManager.persist(expectedCategory);
        entityManager.flush();

        var actualCategory = repository
                .getGroupsByName(NAME+"s");

        Assertions.assertThat(actualCategory.isPresent()).isEqualTo(false);

    }
}