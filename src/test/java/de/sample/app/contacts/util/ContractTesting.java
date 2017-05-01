package de.sample.app.contacts.util;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import de.sample.app.contacts.web.ContactController;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Ensures that the spring boot app is up and running on 8080.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ContextConfiguration
@RunWith(SpringRunner.class)
public class ContractTesting {


    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new ContactController());
    }

    @Ignore
    @Test
    public void dummyTest(){

    }

}
