package ch.springframeworkguru.spring6di;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.ALWAYS)
@Slf4j
class Spring6diApplicationTests {

    @Test
    void contextLoads() {
        log.info("Spring Boot 6 DI Application started successfully");
    }

}
