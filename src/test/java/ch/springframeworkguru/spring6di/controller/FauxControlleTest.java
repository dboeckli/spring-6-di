package ch.springframeworkguru.spring6di.controller;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FauxControlleTest {


    abstract static class AbstractFauxControllerTest {

        @Autowired
        FauxController fauxController;

        @Autowired
        Environment environment;

        @Test
        void testGetDatasource() {
            String datasource = fauxController.getDatasource();
            String activeProfile = environment.getActiveProfiles().length > 0
                ? environment.getActiveProfiles()[0]
                : "dev";
            assertEquals(activeProfile, datasource);
        }

    }

    @Nested
    @SpringBootTest
    @ActiveProfiles("uat")
    class FauxControllerUatTest extends AbstractFauxControllerTest {

    }

    @Nested
    @SpringBootTest
    @ActiveProfiles("qa")
    class FauxControllerQaTest extends AbstractFauxControllerTest {

    }

    @Nested
    @SpringBootTest
    @ActiveProfiles("prod")
    class FauxControllerProdTest extends AbstractFauxControllerTest {

    }

    @Nested
    @SpringBootTest
    @ActiveProfiles("dev")
    class FauxControllerDevTest extends AbstractFauxControllerTest {

    }

    @Nested
    @SpringBootTest
    class FauxControllerDefaultTest extends AbstractFauxControllerTest {

    }



}
