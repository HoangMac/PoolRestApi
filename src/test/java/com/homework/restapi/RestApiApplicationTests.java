package com.homework.restapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.restapi.api.PoolApi;
import com.homework.restapi.dto.request.PoolAppendRequest;
import com.homework.restapi.dto.request.PoolQueryRequest;
import com.homework.restapi.dto.result.PoolAppendResult;
import com.homework.restapi.dto.result.PoolQueryResult;

@SpringBootTest
@AutoConfigureMockMvc
class RestApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String APPEND_ENDPOINT = "/pools";
    private static final String QUERY_ENDPOINT = "/pools/percentile";
    private static final long POOL_ID = 123456L;

    @Test
    public void testAppend_ShoudReturnInserted() throws Exception {
        PoolAppendRequest requestInsert = new PoolAppendRequest(POOL_ID, Arrays.asList(1L, 7L, 2L, 6L));
        buildJsonAppendRequestAndExpectResultString(requestInsert, PoolAppendResult.INSERTED.name());
    }

    @Test
    public void testAppend_ShoudReturnInsertedWhenNull() throws Exception {
        PoolAppendRequest requestInsert = new PoolAppendRequest(null, new ArrayList<>());
        buildJsonAppendRequestAndExpectResultString(requestInsert, PoolAppendResult.INSERTED.name());
    }

    @Test
    public void testAppend_ShoudReturnAppended() throws Exception {
        PoolAppendRequest requestAppend = new PoolAppendRequest(POOL_ID, Arrays.asList(55L, 11L, 99L, 56L, 62L, 23L));
        buildJsonAppendRequestAndExpectResultString(requestAppend, PoolAppendResult.APPENDED.name());
    }

    @Test
    public void testQuery_ShoudReturnStatus400() throws Exception {
        buildJsonQueryRequest(new PoolQueryRequest())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testQuery_ShoudReturnStatus404() throws Exception {
        buildJsonQueryRequest(new PoolQueryRequest(999L, 50.0))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testQuery_ShoudReturnCorrectResult() throws Exception {
        MvcResult mvcResult = buildJsonQueryRequest(new PoolQueryRequest(POOL_ID, 50.0))
                .andReturn();
        PoolQueryResult queryResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), PoolQueryResult.class);
        Assertions.assertEquals(10, queryResult.getTotalCount());
        Assertions.assertEquals(11L, queryResult.getQuantile());
    }

    private void buildJsonAppendRequestAndExpectResultString(Object request, String expectedResult) throws Exception {
        mockMvc.perform(post(PoolApi.REQUEST_MAPPING + APPEND_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(expectedResult)));
    }

    private ResultActions buildJsonQueryRequest(Object request) throws Exception {
        return mockMvc.perform(post(PoolApi.REQUEST_MAPPING + QUERY_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));
    }
}
