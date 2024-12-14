package ch.springframeworkguru.spring6di.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("uat")
class FauxControllerWithUatProfileTest {

    @Autowired
    FauxController fauxController;

    @Test
    void testGetDatasource() {
        String datasource = fauxController.getDatasource();
        assertEquals("uat", datasource);
    }

}
