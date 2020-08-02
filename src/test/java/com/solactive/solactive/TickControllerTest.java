package com.solactive.solactive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TickControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void AddTickInCaseOFSuccess() throws Exception {
        var content = new TickRequest();
        content.timestamp = System.currentTimeMillis();
        content.instrument = "test";
        content.price = 1.2;
        mvc.perform(MockMvcRequestBuilders.post("/ticks")
                .content(ToJson(content))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    public void AddTickIfOlderThanSixtySecond() throws Exception {
        var content = new TickRequest();
        content.timestamp = System.currentTimeMillis() - 600001;
        content.instrument = "test";
        content.price = 1.2;
        mvc.perform(MockMvcRequestBuilders.post("/ticks")
                .content(ToJson(content))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isAccepted());
    }

    @Test
    public void GetStatistics() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/statistics")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void GetStatisticsByInstrument() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/statistics/some_instrument")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk());
    }

    private String ToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
}