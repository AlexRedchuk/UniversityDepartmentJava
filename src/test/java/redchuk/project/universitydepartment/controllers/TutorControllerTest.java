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
import redchuk.project.universitydepartment.dto.speciality.SpecialityRequestDTO;
import redchuk.project.universitydepartment.dto.tutor.TutorRequestDTO;
import redchuk.project.universitydepartment.repositories.TutorRepository;
import redchuk.project.universitydepartment.stubs.SpecialityStub;
import redchuk.project.universitydepartment.stubs.TutorStub;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TutorRepository tutorRepo;


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
    void should_successfully_save_in_tutor() throws Exception {
        var request = TutorStub.getTutorRequestDTO();
        var expectedObject = TutorStub.getRandomTutor();
        when(tutorRepo.save(any())).thenReturn(expectedObject);
        mockMvc.perform(postRequest("/v1/tutors/", request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getFullName())));
    }

    // GET BY ID
    @Test
    //@WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_id_admin_tutor() throws Exception {
        var expectedObject = TutorStub.getTutorResponseDTO();
        var object = TutorStub.getRandomTutor();
        when(tutorRepo.findById(any())).thenReturn(Optional.of(object));
        mockMvc.perform(getRequest("/v1/tutors/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getFullName())));
    }

    // GET ALL
    @Test
    // @WithMockUser(roles = "ADMIN")
    void should_successfully_get_all_admin_tutor() throws Exception {
        var expectedObject = TutorStub.getTutorResponseDTO();
        var object = TutorStub.getRandomTutor();
        when(tutorRepo.findAll()).thenReturn(Collections.singletonList(object));
        mockMvc.perform(getRequest("/v1/tutors/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getFullName())));
    }

    // EDIT
    @Test
    //@WithMockUser(roles = "ADMIN")
    void should_successfully_update_by_id_tutor() throws Exception {
        var request = TutorStub.getTutorRequestDTO();
        var expectedObject = TutorStub.getRandomTutor();
        request.setFullName("Name1");
        expectedObject.setFullName("Name1");
        when(tutorRepo.save(any())).thenReturn(expectedObject);
        mockMvc.perform(putRequest("/v1/tutors/1", request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getFullName())));
    }

    // CUSTOM
    @Test
    // @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_position_admin_tutor() throws Exception {
        var expectedObject = TutorStub.getTutorResponseDTO();
        var object = TutorStub.getRandomTutor();
        when(tutorRepo.getTutorsByPosition(any())).thenReturn(Collections.singletonList(object));
        mockMvc.perform(getRequest("/v1/tutors/byPosition")
                .param("position", object.getPosition()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getFullName())));
    }



    @Test
        //@WithMockUser(roles = "ADMIN")
    void should_successfully_deleted_by_id_tutor() throws Exception {
        mockMvc.perform(deleteRequest("/v1/tutors/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(tutorRepo, atLeast(1)).deleteById(1L);
    }



    // HELPERS

    private MockHttpServletRequestBuilder postRequest(String url, TutorRequestDTO request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, TutorRequestDTO request) {
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