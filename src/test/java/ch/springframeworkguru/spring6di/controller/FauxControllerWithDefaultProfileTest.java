package ch.springframeworkguru.spring6di.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FauxControllerWithDefaultProfileTest {

    @Autowired
    FauxController fauxController;

    @Test
    void testGetDatasource() {
        String datasource = fauxController.getDatasource();
        assertEquals("dev", datasource);
    }

}
