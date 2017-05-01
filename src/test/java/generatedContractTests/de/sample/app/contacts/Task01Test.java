package de.sample.app.contacts;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import com.jayway.restassured.response.ResponseOptions;
import de.sample.app.contacts.util.ContractTesting;
import org.junit.Test;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

public class Task01Test extends ContractTesting {

	@Test
	public void validate_shouldGetAContact() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json;charset=UTF-8")
					.body("\"\"");

		// when:
			ResponseOptions response = given().spec(request)
					.get("/contacts/ID-1");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).isEqualTo("application/json;charset=UTF-8");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("lastname").isEqualTo("Muster");
			assertThatJson(parsedJson).field("firstname").isEqualTo("Max");
			assertThatJson(parsedJson).field("mail").isEqualTo("max@muster.de");
			assertThatJson(parsedJson).field("id").isEqualTo("ID-1");
	}

	@Test
	public void validate_shouldReturn404OnMissingContact() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json;charset=UTF-8")
					.body("\"\"");

		// when:
			ResponseOptions response = given().spec(request)
					.get("/contacts/ID-2");

		// then:
			assertThat(response.statusCode()).isEqualTo(404);
			assertThat(response.header("Content-Type")).isEqualTo("application/json;charset=UTF-8");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
	}

}
