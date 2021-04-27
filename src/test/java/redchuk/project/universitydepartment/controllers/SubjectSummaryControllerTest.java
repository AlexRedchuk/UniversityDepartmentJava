package redchuk.project.universitydepartment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import redchuk.project.universitydepartment.dto.subjectSummary.SubjectSummaryRequestDTO;
import redchuk.project.universitydepartment.repositories.StudentRepository;
import redchuk.project.universitydepartment.repositories.SubjectRepository;
import redchuk.project.universitydepartment.repositories.SubjectSummaryRepository;
import redchuk.project.universitydepartment.repositories.TutorRepository;
import redchuk.project.universitydepartment.stubs.StudentStub;
import redchuk.project.universitydepartment.stubs.SubjectStub;
import redchuk.project.universitydepartment.stubs.SubjectSummaryStub;
import redchuk.project.universitydepartment.stubs.TutorStub;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SubjectSummaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SubjectRepository subjectRepo;

    @MockBean
    TutorRepository tutorRepo;

    @MockBean
    StudentRepository studentRepo;

    @MockBean
    SubjectSummaryRepository subjectSummaryRepo;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // CREATE
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_save_in_subjectSummary() throws Exception {
        var request = SubjectSummaryStub.getSubjectSummaryRequestDTO();
        var expectedObject = SubjectSummaryStub.getRandomSubjectSummary();
        var subject = SubjectStub.getRandomSubject();
        var tutor = TutorStub.getRandomTutor();
        var student = StudentStub.getRandomStudent();
        when(subjectRepo.findById(any())).thenReturn(Optional.of(subject));
        when(tutorRepo.findById(any())).thenReturn(Optional.of(tutor));
        when(studentRepo.findById(any())).thenReturn(Optional.of(student));
        when(subjectSummaryRepo.save(any())).thenReturn(expectedObject);
        mockMvc.perform(postRequest("/v1/subjectsummaries/", request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getYear().toString())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getSubject().getName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getTutor().getFullName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getStudent().getFullName())));
    }

    // GET BY ID
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_id_admin_subjectSummary() throws Exception {
        var expectedObject = SubjectSummaryStub.getSubjectSummaryResponseDTO();
        var object = SubjectSummaryStub.getRandomSubjectSummary();
        when(subjectSummaryRepo.findById(any())).thenReturn(Optional.of(object));
        mockMvc.perform(getRequest("/v1/subjectsummaries/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getYear().toString())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getTutorFullName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getSubjectName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getStudentFullName())));
    }

    // GET ALL
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_all_admin_subjectSummary() throws Exception {
        var expectedObject = SubjectSummaryStub.getSubjectSummaryResponseDTO();
        var object = SubjectSummaryStub.getRandomSubjectSummary();
        when(subjectSummaryRepo.findAll()).thenReturn(Collections.singletonList(object));
        mockMvc.perform(getRequest("/v1/subjectsummaries/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getYear().toString())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getTutorFullName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getSubjectName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getStudentFullName())));
    }

    // EDIT
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_update_by_id_subjectSummary() throws Exception {
        var request = SubjectSummaryStub.getSubjectSummaryRequestDTO();
        var expectedObject = SubjectSummaryStub.getRandomSubjectSummary();
        request.setYear(1253);
        expectedObject.setYear(1253);
        var subject = SubjectStub.getRandomSubject();
        var tutor = TutorStub.getRandomTutor();
        var student = StudentStub.getRandomStudent();
        when(subjectRepo.findById(any())).thenReturn(Optional.of(subject));
        when(tutorRepo.findById(any())).thenReturn(Optional.of(tutor));
        when(studentRepo.findById(any())).thenReturn(Optional.of(student));
        when(subjectSummaryRepo.save(any())).thenReturn(expectedObject);
        mockMvc.perform(putRequest("/v1/subjectsummaries/1", request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getYear().toString())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getSubject().getName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getTutor().getFullName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getStudent().getFullName())));
    }

    // CUSTOM
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_ByStudent_IdAndYearAndSemester_admin_subjectSummary() throws Exception {
        var expectedObject = SubjectSummaryStub.getSubjectSummaryResponseDTO();
        var object = SubjectSummaryStub.getRandomSubjectSummary();
        when(subjectSummaryRepo.getSubjectSummariesByStudent_IdAndYearAndSemester(any(), any(), any())).thenReturn(Collections.singletonList(object));
        mockMvc.perform(getRequest("/v1/subjectsummaries/byStudentIdAndYearAndSemester")
                .param("studentId", object.getStudent().getId().toString())
                .param("year", object.getYear().toString())
                .param("semester", object.getSemester().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getYear().toString())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getTutorFullName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getSubjectName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getStudentFullName())));
    }



    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_deleted_by_id_subjectSummary() throws Exception {
        mockMvc.perform(deleteRequest("/v1/subjectsummaries/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(subjectSummaryRepo, atLeast(1)).deleteById(1L);
    }



    // HELPERS

    private MockHttpServletRequestBuilder postRequest(String url, SubjectSummaryRequestDTO request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, SubjectSummaryRequestDTO request) {
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