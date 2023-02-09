package com.omar.scrapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockHttpServletRequestDsl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void testScrapePost() throws Exception {
        Url url = new Url();
        url.setUrl("https://www.kingarthurbaking.com/recipes/chocolate-chip-cookies-recipe");

        final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/scrape")
                .content(asJsonString(url))
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testScrapePost2() throws Exception {
        Url url = new Url();
        url.setUrl("https://does_not_exist");

        final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/scrape")
                .content(asJsonString(url))
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
