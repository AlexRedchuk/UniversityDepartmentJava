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
import redchuk.project.universitydepartment.dto.group.UniversityGroupRequestDTO;
import redchuk.project.universitydepartment.dto.student.StudentRequestDTO;
import redchuk.project.universitydepartment.repositories.SpecialityRepository;
import redchuk.project.universitydepartment.repositories.StudentRepository;
import redchuk.project.universitydepartment.repositories.UniversityGroupRepository;
import redchuk.project.universitydepartment.stubs.GroupStub;
import redchuk.project.universitydepartment.stubs.SpecialityStub;
import redchuk.project.universitydepartment.stubs.StudentStub;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StudentRepository studentRepo;

    @MockBean
    UniversityGroupRepository groupRepo;



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
    void should_successfully_save_in_student() throws Exception {
        var request = StudentStub.getStudentRequestDTO();
        var expectedObject = StudentStub.getRandomStudent();
        var group = GroupStub.getRandomGroup();
        when(groupRepo.findById(any())).thenReturn(Optional.of(group));
        when(studentRepo.save(any())).thenReturn(expectedObject);
        mockMvc.perform(postRequest("/v1/students/", request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getFullName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getUniversityGroup().getName())));
    }

    // GET BY ID
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_id_admin_student() throws Exception {
        var expectedObject = StudentStub.getStudentResponseDTO();
        var object = StudentStub.getRandomStudent();
        when(studentRepo.findById(any())).thenReturn(Optional.of(object));
        mockMvc.perform(getRequest("/v1/students/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getFullName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getUniversityGroupName())));
    }

    // GET ALL
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_all_admin_student() throws Exception {
        var expectedObject = StudentStub.getStudentResponseDTO();
        var object = StudentStub.getRandomStudent();
        when(studentRepo.findAll()).thenReturn(Collections.singletonList(object));
        mockMvc.perform(getRequest("/v1/students/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getFullName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getUniversityGroupName())));
    }

    // EDIT
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_update_by_id_student() throws Exception {
        var request = StudentStub.getStudentRequestDTO();
        var expectedObject = StudentStub.getRandomStudent();
        request.setFullName("Name1");
        expectedObject.setFullName("Name1");
        var group = GroupStub.getRandomGroup();
        when(groupRepo.findById(any())).thenReturn(Optional.of(group));
        when(studentRepo.save(any())).thenReturn(expectedObject);
        mockMvc.perform(putRequest("/v1/students/1", request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getFullName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getUniversityGroup().getName())));
    }

    // CUSTOM
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_group_id_admin_student() throws Exception {
        var expectedObject = StudentStub.getStudentResponseDTO();
        var object = StudentStub.getRandomStudent();
        when(studentRepo.getStudentsByUniversityGroup_Id(any()))
                .thenReturn(Collections.singletonList(object));
        mockMvc.perform(getRequest("/v1/students/allByGroupId")
                .param("groupId",GroupStub.getRandomGroup().getId().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getFullName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getUniversityGroupName())));
    }



    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_deleted_by_id_student() throws Exception {
        mockMvc.perform(deleteRequest("/v1/students/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(studentRepo, atLeast(1)).deleteById(1L);
    }



    // HELPERS

    private MockHttpServletRequestBuilder postRequest(String url, StudentRequestDTO request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, StudentRequestDTO request) {
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