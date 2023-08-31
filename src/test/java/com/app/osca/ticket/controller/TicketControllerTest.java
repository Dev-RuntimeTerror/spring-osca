package com.app.osca.ticket.controller;

import com.app.osca.domain.TicketVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;


@SpringBootTest
public class TicketControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void goToMyCafeListTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cafe/ticket-purchase").param("cafeId", "101"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void buyTicketTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/cafe/ticket-purchase")
                    .param("cafeAdId", "101")
                    .param("ticketDuration", "7")
                    .param("ticketPoint", "10000")
                )
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }

}
