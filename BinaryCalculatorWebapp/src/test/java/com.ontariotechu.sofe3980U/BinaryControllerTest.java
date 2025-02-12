package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/")) // GET request
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("operand1", ""))
                .andExpect(model().attribute("operand1Focused", false));
    }

    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1", "111"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("operand1", "111"))
                .andExpect(model().attribute("operand1Focused", true));
    }

    @Test
    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1", "111").param("operator", "+").param("operand2", "111"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1110"))
                .andExpect(model().attribute("operand1", "111"));
    }

    // --------------------------------------------------------------
    // NEW TEST CASES FOR THE NEW OPERATORS
    // --------------------------------------------------------------

    @Test
    public void multiplyWeb() throws Exception {
        // 101 (5) * 10 (2) = 1010 (10)
        this.mvc.perform(post("/")
                .param("operand1", "101")
                .param("operator", "*")
                .param("operand2", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1010")); // 5*2=10 decimal, which is 1010 binary
    }

    @Test
    public void andWeb() throws Exception {
        // 101 & 110 = 100
        this.mvc.perform(post("/")
                .param("operand1", "101")
                .param("operator", "&")
                .param("operand2", "110"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "100")); // 5 & 6 = 4 decimal => 100 binary
    }

    @Test
    public void orWeb() throws Exception {
        // 101 | 110 = 111
        this.mvc.perform(post("/")
                .param("operand1", "101")
                .param("operator", "|")
                .param("operand2", "110"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "111")); // 5 | 6 = 7 decimal => 111 binary
    }
}
