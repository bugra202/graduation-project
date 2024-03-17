package com.bugratasdemir.graduationproject.controller;

import com.bugratasdemir.graduationproject.GraduationProjectCoreApplication;
import com.bugratasdemir.graduationproject.entity.User;
import com.bugratasdemir.graduationproject.faker.CommentFaker;
import com.bugratasdemir.graduationproject.faker.UserFaker;
import com.bugratasdemir.graduationproject.repository.CommentRepository;
import com.bugratasdemir.graduationproject.request.CommentSaveRequest;
import com.bugratasdemir.graduationproject.request.CommentUpdateContentAndScoreRequest;
import com.bugratasdemir.graduationproject.service.impl.CommentServiceImpl;
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
class CommentControllerTest extends BaseControllerTest{
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    CommentServiceImpl commentService;
    private MockMvc mockMvc;
    private final CommentFaker commentFaker = new CommentFaker(Faker.instance());
    private final UserFaker userFaker = new UserFaker(Faker.instance());
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void shouldSave() throws Exception{
        User user = userFaker.createUser();
        CommentSaveRequest commentSaveRequest = commentFaker.createCommentSaveRequest(user);

        String requestAsString = objectMapper.writeValueAsString(commentSaveRequest);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/comments")
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }
    @Test
    void shouldEditContentAndScore() throws Exception{
        CommentUpdateContentAndScoreRequest commentUpdateContentAndScoreRequest = commentFaker.createCommentUpdateRequest();

        String requestAsString = objectMapper.writeValueAsString(commentUpdateContentAndScoreRequest);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/comments/1/contentAndScore")
                        .content(requestAsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        boolean success = isSuccess(mvcResult);
        assertTrue(success);
    }

    @Test
    void shouldDelete() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/comments/{id}", 123L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }
}