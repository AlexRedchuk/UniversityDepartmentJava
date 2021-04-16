package redchuk.project.universitydepartment.repositories;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redchuk.project.universitydepartment.entity.Speciality;
import redchuk.project.universitydepartment.stubs.GroupStub;
import redchuk.project.universitydepartment.stubs.SpecialityStub;
import redchuk.project.universitydepartment.stubs.SubjectStub;


import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class SpecialityRepositoryTest {
    public static final int CODE = SpecialityStub.getRandomSpeciality().getCode();
    @Autowired
    private SpecialityRepository repository;

    @Autowired
    private TestEntityManager entityManager;



    @Test
    void testFindSubjectByCode() {

        var expectedObject = SpecialityStub.getRandomSpeciality();
        entityManager.persist(expectedObject);
        entityManager.flush();
        var actualObject = repository.getSpecialityByCode(CODE);

        Assertions.assertThat(actualObject.get()).isEqualTo(expectedObject);
    }

    @Test
    void testFindSubjectByCodeNotSuccess() {
        var expectedObject = SubjectStub.getRandomSubject();
        entityManager.persist(expectedObject);
        entityManager.flush();

        var actualObject = repository
                .getSpecialityByCode(CODE + 2);

        Assertions.assertThat(actualObject.isPresent()).isEqualTo(false);

    }
}