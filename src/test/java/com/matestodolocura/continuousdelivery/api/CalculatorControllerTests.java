package com.matestodolocura.continuousdelivery.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matestodolocura.continuousdelivery.application.dto.DtoTwoNumbers;
import com.matestodolocura.continuousdelivery.domain.actions.CalculatorAction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CalculatorControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CalculatorAction calculatorAction;

    @Test
    void add_with_two_numbers_with_path_variable() throws Exception {

        MockHttpServletResponse response = mvc.perform(get("/api/v1/calculator/add/num1/1/num2/2")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        String result = response.getContentAsString();
        assertThat(result).isEqualTo("3");
    }

    @Test
    void add_with_two_numbers_with_request_param() throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("number1", "1");
        params.add("number2", "2");

        MockHttpServletResponse response = mvc.perform(get("/api/v1/calculator/add")
                .params(params)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        String result = response.getContentAsString();
        assertThat(result).isEqualTo("3");
    }

    @Test
    void add_with_two_numbers_with_request_body() throws Exception {

        DtoTwoNumbers dtoTwoNumbers = DtoTwoNumbers.builder()
                .number1("1")
                .number2("2")
                .build();

        MockHttpServletResponse response = mvc.perform(post("/api/v1/calculator/add")
                .content(objectMapper.writeValueAsString(dtoTwoNumbers))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        String result = response.getContentAsString();
        assertThat(result).isEqualTo("3");
    }

    @Test
    void testingAddAction(){
        DtoTwoNumbers dtoTwoNumbers = DtoTwoNumbers.builder()
                .number1("1")
                .number2("2")
                .build();

        assertThat(calculatorAction.add(dtoTwoNumbers)).isEqualTo("3");
    }
}
