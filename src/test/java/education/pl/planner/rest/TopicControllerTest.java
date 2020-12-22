package education.pl.planner.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import education.pl.planner.domain.Topic;
import education.pl.planner.service.TopicService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicControllerTest {

    @MockBean
    private TopicService topicService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();
    private Logger logger = Logger.getLogger(TopicControllerTest.class.getName());


    @Test
    public void shouldGetAllTopics() throws Exception {
        // given
        Topic sample1 = getSampleTopic(1, "title 1");
        Topic sample2 = getSampleTopic(2, "title 2");
        String expected = "[" +
                mapper.writeValueAsString(sample1) + "," +
                mapper.writeValueAsString(sample2) + "]";
        // when
        Mockito.when(topicService.getAllTopics()).thenReturn(List.of(sample1, sample2));
        MockHttpServletResponse response = getServletResponseForGet("topics");
        // then
        JSONAssert.assertEquals(expected, response.getContentAsString(), false);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void shouldReturnAddedTopic() throws Exception {
        // given
        Topic sample = getSampleTopic(1, "title 1");
        String topicToAddJson = "{\"title\": \"title 1\", \"daysForLearning\": 5}";
        String expected = mapper.writeValueAsString(sample);
        // when
        Mockito.when(topicService.add(Mockito.any(Topic.class))).thenReturn(sample);
        MockHttpServletResponse response = getServletResponseForPost("topics", topicToAddJson);
        // then
        logger.log(Level.INFO, "from response: " +response.getContentAsString());
        JSONAssert.assertEquals(expected, response.getContentAsString(), false);
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    private MockHttpServletResponse getServletResponseForGet(String url) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/" + url);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        return result.getResponse();
    }

    private MockHttpServletResponse getServletResponseForPost(String url, String topic) throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/" + url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(topic);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        return result.getResponse();
    }

    private Topic getSampleTopic(int id, String title) {
        return new Topic(id, title, Collections.emptySet(), 6, false, null);
    }
}
