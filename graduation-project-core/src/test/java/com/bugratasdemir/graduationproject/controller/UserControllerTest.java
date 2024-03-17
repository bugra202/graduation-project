package com.bugratasdemir.graduationproject.controller;

import com.bugratasdemir.graduationproject.GraduationProjectCoreApplication;
import com.bugratasdemir.graduationproject.client.RecommentApi;
import com.bugratasdemir.graduationproject.entity.User;
import com.bugratasdemir.graduationproject.faker.UserFaker;
import com.bugratasdemir.graduationproject.repository.CommentRepository;
import com.bugratasdemir.graduationproject.repository.UserRepository;
import com.bugratasdemir.graduationproject.request.SuggestedRequest;
import com.bugratasdemir.graduationproject.request.UserSaveRequest;
import com.bugratasdemir.graduationproject.request.UserUpdateRequest;
import com.bugratasdemir.graduationproject.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {GraduationProjectCoreApplication.class})
class UserControllerTest extends BaseControllerTest{

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    UserServiceImpl userService;

    @MockBean
    private RecommentApi recommentApi;
    private MockMvc mockMvc;
    private final UserFaker userFaker = new UserFaker(Faker.instance());

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }
    @Test
    void shouldSave() throws Exception{
        UserSaveRequest userSaveRequest = userFaker.createUserSaveRequest();

        String requestAsString = objectMapper.writeValueAsString(userSaveRequest);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldUpdate() throws Exception {
        User user = userFaker.createUser();
        UserUpdateRequest userUpdateRequest = userFaker.createUserUpdateRequest(user);

        String requestAsString = objectMapper.writeValueAsString(userUpdateRequest);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/1")
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldRestaurants() throws Exception{
        User user = userFaker.createUser();
        SuggestedRequest suggestedRequest = userFaker.createSuggestedRequest(user);

        String requestAsString = objectMapper.writeValueAsString(suggestedRequest);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/restaurants/1")
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldDelete() throws Exception{

         mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/{id}", 123L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

    }
}