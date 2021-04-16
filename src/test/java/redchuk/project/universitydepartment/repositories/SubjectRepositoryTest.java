package redchuk.project.universitydepartment.repositories;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redchuk.project.universitydepartment.stubs.GroupStub;
import redchuk.project.universitydepartment.stubs.SubjectStub;


import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class SubjectRepositoryTest {
    public static final int CODE = SubjectStub.getRandomSubject().getCode();
    @Autowired
    private SubjectRepository repository;

    @Autowired
    private TestEntityManager entityManager;



    @Test
    void testFindSubjectByCode() {

        var expectedObject = SubjectStub.getRandomSubject();
        entityManager.persist(expectedObject);
        entityManager.flush();
        var actualObject = repository.getSubjectByCode(CODE);

        Assertions.assertThat(actualObject.get()).isEqualTo(expectedObject);
    }

    @Test
    void testFindSubjectByCodeNotSuccess() {
        var expectedObject = SubjectStub.getRandomSubject();
        entityManager.persist(expectedObject);
        entityManager.flush();

        var actualObject = repository
                .getSubjectByCode(CODE + 2);

        Assertions.assertThat(actualObject.isPresent()).isEqualTo(false);

    }
}