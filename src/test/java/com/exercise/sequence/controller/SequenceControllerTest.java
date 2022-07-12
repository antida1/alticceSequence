package com.exercise.sequence.controller;

import com.exercise.sequence.service.SequenceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = SequenceController.class)
@WithMockUser
public class SequenceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private SequenceService sequenceService;

    private List<Integer> sequence;

    private Integer numberSequence;

    private String URI = "/alticci/";

    @BeforeEach
    void setUp(){
        sequence = Arrays.asList(0, 1, 1, 1, 2, 2, 3, 4, 5, 7, 9);
        numberSequence = sequence.get(10);
    }


    @Test
    public void getNumberOfSequenceByIndex() throws Exception {

        Mockito.when(
                sequenceService.getNumberOfSequenceByIndex(Mockito.anyInt())).thenReturn(numberSequence);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URI+"10").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "9";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    public void getSequenceByIndexTest() throws Exception {

        Mockito.when(sequenceService.geSequenceByIndex(Mockito.anyInt())).thenReturn(sequence);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI+"{n}",10)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String sequenceJson = result.getResponse().getContentAsString();
        Assertions.assertThat(sequenceJson).isEqualToIgnoringCase(mapper.writeValueAsString(sequence));
    }
}
