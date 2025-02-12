package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "111").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(content().string("10001"));
    }

    @Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "111").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value("111"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value("1010"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("10001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    // --------------------------------------------------------------
    // NEW TEST CASES FOR THE NEW OPERATORS
    // --------------------------------------------------------------

    @Test
    public void multiplyAPI() throws Exception {
        // 101 (5) * 10 (2) = 1010 (10)
        this.mvc.perform(get("/mul").param("operand1", "101").param("operand2", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string("1010"));
    }

    @Test
    public void andAPI() throws Exception {
        // 101 & 110 = 100
        this.mvc.perform(get("/and").param("operand1", "101").param("operand2", "110"))
                .andExpect(status().isOk())
                .andExpect(content().string("100"));
    }

    @Test
    public void orAPI() throws Exception {
        // 101 | 110 = 111
        this.mvc.perform(get("/or").param("operand1", "101").param("operand2", "110"))
                .andExpect(status().isOk())
                .andExpect(content().string("111"));
    }
}
