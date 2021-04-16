package redchuk.project.universitydepartment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import redchuk.project.universitydepartment.dto.subjectPlan.SubjectPlanRequestDTO;
import redchuk.project.universitydepartment.repositories.SubjectPlanRepository;
import redchuk.project.universitydepartment.repositories.SubjectRepository;
import redchuk.project.universitydepartment.repositories.TutorRepository;
import redchuk.project.universitydepartment.repositories.UniversityGroupRepository;
import redchuk.project.universitydepartment.stubs.GroupStub;
import redchuk.project.universitydepartment.stubs.SubjectPlanStub;
import redchuk.project.universitydepartment.stubs.SubjectStub;
import redchuk.project.universitydepartment.stubs.TutorStub;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SubjectPlanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SubjectRepository subjectRepo;

    @MockBean
    TutorRepository tutorRepo;

    @MockBean
    UniversityGroupRepository groupRepo;



    @MockBean
    SubjectPlanRepository subjectPlanRepo;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // CREATE
    @Test
    //@WithMockUser(roles = "ADMIN")
    void should_successfully_save_in_subjectPlan() throws Exception {
        var request = SubjectPlanStub.getSubjectPlanRequestDTO();
        var expectedObject = SubjectPlanStub.getRandomSubjectPlan();
        var subject = SubjectStub.getRandomSubject();
        var tutor = TutorStub.getRandomTutor();
        var group = GroupStub.getRandomGroup();
        when(subjectRepo.findById(any())).thenReturn(Optional.of(subject));
        when(tutorRepo.findById(any())).thenReturn(Optional.of(tutor));
        when(groupRepo.findById(any())).thenReturn(Optional.of(group));
        when(subjectPlanRepo.save(any())).thenReturn(expectedObject);
        mockMvc.perform(postRequest("/v1/subjectplans/", request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getYear().toString())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getSubject().getName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getTutor().getFullName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getUniversityGroup().getName())));
    }

    // GET BY ID
    @Test
    //@WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_id_admin_subjectPlan() throws Exception {
        var expectedObject = SubjectPlanStub.getSubjectPlanResponseDTO();
        var object = SubjectPlanStub.getRandomSubjectPlan();
        when(subjectPlanRepo.findById(any())).thenReturn(Optional.of(object));
        mockMvc.perform(getRequest("/v1/subjectplans/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getYear().toString())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getTutorFullName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getSubjectName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getUniversityGroupName())));
    }

    // GET ALL
    @Test
    // @WithMockUser(roles = "ADMIN")
    void should_successfully_get_all_admin_subjectPlan() throws Exception {
        var expectedObject = SubjectPlanStub.getSubjectPlanResponseDTO();
        var object = SubjectPlanStub.getRandomSubjectPlan();
        when(subjectPlanRepo.findAll()).thenReturn(Collections.singletonList(object));
        mockMvc.perform(getRequest("/v1/subjectplans/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getYear().toString())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getTutorFullName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getSubjectName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getUniversityGroupName())));
    }

    // EDIT
    @Test
    //@WithMockUser(roles = "ADMIN")
    void should_successfully_update_by_id_subjectPlan() throws Exception {
        var request = SubjectPlanStub.getSubjectPlanRequestDTO();
        var expectedObject = SubjectPlanStub.getRandomSubjectPlan();
        request.setYear(1253);
        expectedObject.setYear(1253);
        var subject = SubjectStub.getRandomSubject();
        var tutor = TutorStub.getRandomTutor();
        var group = GroupStub.getRandomGroup();
        when(subjectRepo.findById(any())).thenReturn(Optional.of(subject));
        when(tutorRepo.findById(any())).thenReturn(Optional.of(tutor));
        when(groupRepo.findById(any())).thenReturn(Optional.of(group));
        when(subjectPlanRepo.save(any())).thenReturn(expectedObject);
        mockMvc.perform(putRequest("/v1/subjectplans/1", request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getYear().toString())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getSubject().getName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getTutor().getFullName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getUniversityGroup().getName())));
    }

    // CUSTOM
    @Test
    // @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_ByYearAndGroup_admin_subjectPlan() throws Exception {
        var expectedObject = SubjectPlanStub.getSubjectPlanResponseDTO();
        var object = SubjectPlanStub.getRandomSubjectPlan();
        when(subjectPlanRepo.getSubjectPlansByYearAndUniversityGroup_Id(any(), any())).thenReturn(Collections.singletonList(object));
        mockMvc.perform(getRequest("/v1/subjectplans/getByYearAndGroup")
                .param("year", object.getYear().toString())
                .param("groupId", object.getUniversityGroup().getId().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getYear().toString())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getTutorFullName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getSubjectName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getUniversityGroupName())));
    }



    @Test
        //@WithMockUser(roles = "ADMIN")
    void should_successfully_deleted_by_id_subjectPlan() throws Exception {
        mockMvc.perform(deleteRequest("/v1/subjectplans/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(subjectPlanRepo, atLeast(1)).deleteById(1L);
    }



    // HELPERS

    private MockHttpServletRequestBuilder postRequest(String url, SubjectPlanRequestDTO request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, SubjectPlanRequestDTO request) {
        return put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder getRequest(String url) {
        return get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

    private MockHttpServletRequestBuilder deleteRequest(String url) {
        return delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }

}