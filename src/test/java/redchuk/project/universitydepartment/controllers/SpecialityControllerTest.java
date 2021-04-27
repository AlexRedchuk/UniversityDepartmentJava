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
import redchuk.project.universitydepartment.dto.speciality.SpecialityRequestDTO;
import redchuk.project.universitydepartment.repositories.SpecialityRepository;
import redchuk.project.universitydepartment.stubs.SpecialityStub;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SpecialityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SpecialityRepository specialityRepo;


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
    void should_successfully_save_in_speciality() throws Exception {
        var request = SpecialityStub.getSpecialityRequestDTO();
        var expectedObject = SpecialityStub.getRandomSpeciality();
        when(specialityRepo.save(any())).thenReturn(expectedObject);
        mockMvc.perform(postRequest("/v1/specialities/", request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getName())));
    }

    // GET BY ID
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_id_admin_speciality() throws Exception {
        var expectedObject = SpecialityStub.getSpecialityResponseDTO();
        var object = SpecialityStub.getRandomSpeciality();
        when(specialityRepo.findById(any())).thenReturn(Optional.of(object));
        mockMvc.perform(getRequest("/v1/specialities/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getName())));
    }

    // GET ALL
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_all_admin_speciality() throws Exception {
        var expectedObject = SpecialityStub.getSpecialityResponseDTO();
        var object = SpecialityStub.getRandomSpeciality();
        when(specialityRepo.findAll()).thenReturn(Collections.singletonList(object));
        mockMvc.perform(getRequest("/v1/specialities/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getName())));
    }

    // EDIT
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_update_by_id_speciality() throws Exception {
        var request = SpecialityStub.getSpecialityRequestDTO();
        var expectedObject = SpecialityStub.getRandomSpeciality();
        request.setName("Name1");
        expectedObject.setName("Name1");
        when(specialityRepo.save(any())).thenReturn(expectedObject);
        mockMvc.perform(putRequest("/v1/specialities/1", request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getName())));
    }

    // CUSTOM
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_code_admin_speciality() throws Exception {
        var expectedObject = SpecialityStub.getSpecialityResponseDTO();
        var object = SpecialityStub.getRandomSpeciality();
        when(specialityRepo.getSpecialityByCode(any())).thenReturn(Optional.of(object));
        mockMvc.perform(getRequest("/v1/specialities/byCode")
                .param("code",SpecialityStub.getRandomSpeciality().getCode().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getName())));
    }



    @Test
    @WithMockUser(roles = "ADMIN")
    void should_successfully_deleted_by_id_speciality() throws Exception {
        mockMvc.perform(deleteRequest("/v1/specialities/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(specialityRepo, atLeast(1)).deleteById(1L);
    }



    // HELPERS

    private MockHttpServletRequestBuilder postRequest(String url, SpecialityRequestDTO request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, SpecialityRequestDTO request) {
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