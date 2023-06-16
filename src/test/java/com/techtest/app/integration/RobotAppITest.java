package com.techtest.app.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class RobotAppITest {

    @Autowired
    private MockMvc mockMvc;

    public static Stream<Arguments> exampleValues() {
        return Stream.of(
                Arguments.of("5 5\n1 2 N\nLMLMLMLMM", "1 3 N"),
                Arguments.of("5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM", "1 3 N\n5 1 E")
        );
    }

    @Test
    void shouldThrowErrorWhenScenarioContainsInvalidInitialPosition() throws Exception {
        mockMvc.perform(
                        post("/")
                                .contentType(MediaType.TEXT_PLAIN_VALUE)
                                .content("5 5\n1 -2 N\nLMLMLMLMM"))
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.errorMessage").value("Initial position cannot contain more " +
                                "than 3 chars in which the first 2 chars must be positive numbers and the third must " +
                                "be one of N,W,E,S")
                );
    }

    @Test
    void shouldThrowErrorWhenScenarioContainsInvalidInstructions() throws Exception {
        mockMvc.perform(
                        post("/")
                                .contentType(MediaType.TEXT_PLAIN_VALUE)
                                .content("5 5\n1 2 N\n3MLMLMLMM"))
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.errorMessage").value("Instruction not valid (3)")
                );
    }

    @Test
    void shouldThrowErrorWhenNoInstructionsSent() throws Exception {
        mockMvc.perform(
                        post("/")
                                .contentType(MediaType.TEXT_PLAIN_VALUE)
                                .content(""))
                .andExpectAll(
                        status().isBadRequest()
                );
    }

    @ParameterizedTest
    @ValueSource(strings = {"5 -5\n1 2 N\nLMLMLMLMM", "q 1\n1 2 N\nLMLMLMLMM"})
    void shouldThrowErrorWhenWrongWorkplaceSent(String content) throws Exception {
        mockMvc.perform(
                        post("/")
                                .contentType(MediaType.TEXT_PLAIN_VALUE)
                                .content(content))
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.errorMessage").value("Invalid workplace size dimensions")
                );
    }

    @ParameterizedTest
    @ValueSource(strings = {"5 5\n1 2 N", "5 5\n1 2 N\nLMLMLMLMM\n3 3 E"})
    void shouldThrowErrorWhenNotEnoughInstructionsSent(String content) throws Exception {
        mockMvc.perform(
                        post("/")
                                .contentType(MediaType.TEXT_PLAIN_VALUE)
                                .content(content))
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.errorMessage").value("Error while introducing instructions." +
                                " An even number of instructions must be provided along with the size of the workplace")
                );
    }

    @ParameterizedTest
    @MethodSource("exampleValues")
    void shouldFinalizeAsExpected(String content, String expected) throws Exception {
        mockMvc.perform(
                        post("/")
                                .contentType(MediaType.TEXT_PLAIN_VALUE)
                                .content(content))
                .andExpectAll(
                        status().is2xxSuccessful(),
                        content().contentType(new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8).toString()),
                        content().string(expected)
                );
    }
}
