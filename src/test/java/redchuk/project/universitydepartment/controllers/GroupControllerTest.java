package redchuk.project.universitydepartment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import redchuk.project.universitydepartment.repositories.UniversityGroupRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GroupControllerTest {
    @MockBean
    UniversityGroupRepository repo;

    @Autowired
    private MockMvc mvc;


//    @Test
//    void name() throws Exception {
//        var groups = GroupStub.getRandomGroup();
//        var list = new ArrayList<Groups>();
//        var add = list.add(groups);
//        when(repo.findAll()).thenReturn(list);
//
//        mvc.perform(get("/v1/categories")
//                .accept(MediaType.APPLICATION_JSON)
//        )
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(content().string(containsString(groups.getName())));
//    }

}