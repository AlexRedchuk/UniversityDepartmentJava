package redchuk.project.universitydepartment.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redchuk.project.universitydepartment.entity.SubjectSummary;
import redchuk.project.universitydepartment.entity.Tutor;
import redchuk.project.universitydepartment.stubs.GroupStub;
import redchuk.project.universitydepartment.stubs.SubjectSummaryStub;
import redchuk.project.universitydepartment.stubs.TutorStub;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class SubjectSummaryRepositoryTest {
    public final Long STUDENTID = SubjectSummaryStub
            .getRandomSubjectSummary()
            .getStudent()
            .getId();
    public final int YEAR = SubjectSummaryStub
            .getRandomSubjectSummary()
            .getYear();
    public final int SEMESTER =
            SubjectSummaryStub
                    .getRandomSubjectSummary()
                    .getSemester();
    @Autowired
    private SubjectSummaryRepository repository;

    @Autowired
    private TestEntityManager entityManager;



    @Test
    void testFindSubjectSummariesByStudent_IdAndYearAndSemester() {

        var expectedObject = SubjectSummaryStub.getRandomSubjectSummary();
        List<SubjectSummary> expectedObjects = new ArrayList<>();
        expectedObjects.add(expectedObject);
        entityManager.persist(expectedObject);
        entityManager.flush();
        var actualObjects = repository
                .getSubjectSummariesByStudent_IdAndYearAndSemester(STUDENTID,
                        YEAR,
                        SEMESTER);

        Assertions.assertThat(actualObjects).isEqualTo(expectedObjects);
    }

    @Test
    void testFindSubjectSummariesByStudent_IdAndYearAndSemesterNotSuccess() {
        var expectedObject = SubjectSummaryStub.getRandomSubjectSummary();
        List<SubjectSummary> expectedObjects = new ArrayList<>();
        expectedObjects.add(expectedObject);
        entityManager.persist(expectedObject);
        entityManager.flush();
        var actualObjects = repository
                .getSubjectSummariesByStudent_IdAndYearAndSemester(STUDENTID+2,
                        YEAR+1,
                        SEMESTER+8);

        Assertions.assertThat(actualObjects.size()).isEqualTo(0);

    }
}