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
import redchuk.project.universitydepartment.dto.group.UniversityGroupRequestDTO;
import redchuk.project.universitydepartment.repositories.SpecialityRepository;
import redchuk.project.universitydepartment.repositories.UniversityGroupRepository;
import redchuk.project.universitydepartment.stubs.GroupStub;
import redchuk.project.universitydepartment.stubs.SpecialityStub;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UniversityGroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SpecialityRepository specialityRepo;

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
    //@WithMockUser(roles = "ADMIN")
    void should_successfully_save_in_group() throws Exception {
        var request = GroupStub.getGroupRequestDTO();
        var expectedObject = GroupStub.getRandomGroup();
        var speciality = SpecialityStub.getRandomSpeciality();
        when(specialityRepo.findById(any())).thenReturn(Optional.of(speciality));
        when(groupRepo.save(any())).thenReturn(expectedObject);
        mockMvc.perform(postRequest("/v1/groups/", request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getSpeciality().getName())));
    }

    // GET BY ID
    @Test
    //@WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_id_admin_group() throws Exception {
        var expectedObject = GroupStub.getGroupResponseDTO();
        var object = GroupStub.getRandomGroup();
        when(groupRepo.findById(any())).thenReturn(Optional.of(object));
        mockMvc.perform(getRequest("/v1/groups/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getSpecialityName())));
    }

    // GET ALL
    @Test
   // @WithMockUser(roles = "ADMIN")
    void should_successfully_get_all_admin_group() throws Exception {
        var expectedObject = GroupStub.getGroupResponseDTO();
        var object = GroupStub.getRandomGroup();
        when(groupRepo.findAll()).thenReturn(Collections.singletonList(object));
        mockMvc.perform(getRequest("/v1/groups/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getSpecialityName())));
    }

    // EDIT
    @Test
    //@WithMockUser(roles = "ADMIN")
    void should_successfully_update_by_id_group() throws Exception {
        var request = GroupStub.getGroupRequestDTO();
        var expectedObject = GroupStub.getRandomGroup();
        request.setName("Name1");
        expectedObject.setName("Name1");
        var speciality = SpecialityStub.getRandomSpeciality();
        when(specialityRepo.findById(any())).thenReturn(Optional.of(speciality));
        when(groupRepo.save(any())).thenReturn(expectedObject);
        mockMvc.perform(putRequest("/v1/groups/1", request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getSpeciality().getName())));
    }

    // CUSTOM
    @Test
    // @WithMockUser(roles = "ADMIN")
    void should_successfully_get_by_name_admin_group() throws Exception {
        var expectedObject = GroupStub.getGroupResponseDTO();
        var object = GroupStub.getRandomGroup();
        when(groupRepo.getUniversityGroupByName(any())).thenReturn(Optional.of(object));
        mockMvc.perform(getRequest("/v1/groups/byName")
                .param("name",GroupStub.getRandomGroup().getName()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getName())))
                .andExpect(MockMvcResultMatchers.content()
                        .string(containsString(expectedObject.getSpecialityName())));
    }



    @Test
    //@WithMockUser(roles = "ADMIN")
    void should_successfully_deleted_by_id_group() throws Exception {
        mockMvc.perform(deleteRequest("/v1/groups/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(groupRepo, atLeast(1)).deleteById(1L);
    }



    // HELPERS

    private MockHttpServletRequestBuilder postRequest(String url, UniversityGroupRequestDTO request) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(request));
    }

    private MockHttpServletRequestBuilder putRequest(String url, UniversityGroupRequestDTO request) {
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