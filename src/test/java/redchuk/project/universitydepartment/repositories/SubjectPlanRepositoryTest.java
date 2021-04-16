package redchuk.project.universitydepartment.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redchuk.project.universitydepartment.entity.SubjectPlan;
import redchuk.project.universitydepartment.entity.SubjectSummary;
import redchuk.project.universitydepartment.stubs.SubjectPlanStub;
import redchuk.project.universitydepartment.stubs.SubjectSummaryStub;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class SubjectPlanRepositoryTest {
    public final Long GROUPID = SubjectPlanStub
            .getRandomSubjectPlan()
            .getUniversityGroup()
            .getId();
    public final int YEAR = SubjectPlanStub
            .getRandomSubjectPlan()
            .getYear();
    @Autowired
    private SubjectPlanRepository repository;

    @Autowired
    private TestEntityManager entityManager;



    @Test
    void testFindSubjectPlansByYearAndUniversityGroup_Id() {

        var expectedObject = SubjectPlanStub.getRandomSubjectPlan();
        List<SubjectPlan> expectedObjects = new ArrayList<>();
        expectedObjects.add(expectedObject);
        entityManager.persist(expectedObject);
        entityManager.flush();
        var actualObjects = repository
                .getSubjectPlansByYearAndUniversityGroup_Id(YEAR, GROUPID);

        Assertions.assertThat(actualObjects).isEqualTo(expectedObjects);
    }

    @Test
    void testFindSubjectPlansByYearAndUniversityGroup_IdNotSuccess() {
        var expectedObject = SubjectPlanStub.getRandomSubjectPlan();
        List<SubjectPlan> expectedObjects = new ArrayList<>();
        expectedObjects.add(expectedObject);
        entityManager.persist(expectedObject);
        entityManager.flush();
        var actualObjects = repository
                .getSubjectPlansByYearAndUniversityGroup_Id(YEAR +2, GROUPID +1);

        Assertions.assertThat(actualObjects.size()).isEqualTo(0);

    }
}