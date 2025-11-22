package ch.springframeworkguru.spring6di.rest;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.micrometer.tracing.test.autoconfigure.AutoConfigureTracing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTracing
class DatasourceRestControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Nested
    @ActiveProfiles("dev")
    @DisplayName("Profile: Dev")
    class DevProfileTest {
        @Test
        void testGetDatasource() throws Exception {
            mockMvc.perform(get("/datasource"))
                .andExpect(status().isOk())
                .andExpect(content().string("dev"));
        }
    }

    @Nested
    @ActiveProfiles("qa")
    @DisplayName("Profile: QA")
    class QaProfileTest {
        @Test
        void testGetDatasource() throws Exception {
            mockMvc.perform(get("/datasource"))
                .andExpect(status().isOk())
                .andExpect(content().string("qa"));
        }
    }

    @Nested
    @ActiveProfiles("uat")
    @DisplayName("Profile: UAT")
    class UatProfileTest {
        @Test
        void testGetDatasource() throws Exception {
            mockMvc.perform(get("/datasource"))
                .andExpect(status().isOk())
                .andExpect(content().string("uat"));
        }
    }

    @Nested
    @ActiveProfiles("prod")
    @DisplayName("Profile: Prod")
    class ProdProfileTest {
        @Test
        void testGetDatasource() throws Exception {
            mockMvc.perform(get("/datasource"))
                .andExpect(status().isOk())
                .andExpect(content().string("prod"));
        }
    }

    @Nested
    @DisplayName("Profile: Default (kein explizites Profil)")
    class DefaultProfileTest {
        @Test
        void testGetDatasource() throws Exception {
            mockMvc.perform(get("/datasource"))
                .andExpect(status().isOk())
                .andExpect(content().string("dev"));
        }
    }

    @Test
    void test_logsMessage() throws Exception {
        Logger logger = (Logger) LoggerFactory.getLogger(DatasourceRestController.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        mockMvc.perform(get("/datasource"))
            .andExpect(status().isOk())
            .andExpect(content().string("dev"));

        List<ILoggingEvent> logEvents = listAppender.list;
        assertAll(
            () -> assertNotNull(logEvents),
            () -> assertEquals(1, logEvents.size()),
            () -> assertThat(logEvents.getFirst().getFormattedMessage()).isEqualTo("Datasource requested"),
            () -> assertThat(logEvents.getFirst().getMDCPropertyMap().get("traceId")).isNotBlank().matches("[0-9a-f]{32}"),
            () -> assertThat(logEvents.getFirst().getMDCPropertyMap().get("spanId")).as("span_id").isNotBlank().matches("[0-9a-f]{16}")
        );

        logger.detachAppender(listAppender);
        listAppender.stop();

    }

}