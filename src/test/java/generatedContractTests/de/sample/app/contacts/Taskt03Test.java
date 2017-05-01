/**
 * Created by HC on 01.05.2017.
 */
package de.sample.app.contacts;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import com.jayway.restassured.response.ResponseOptions;
import de.sample.app.contacts.util.ContractTesting;
import org.junit.Test;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

public class Taskt03Test extends ContractTesting {

	@Test
	public void validate_shouldAcceptValidPost() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json;charset=UTF-8")
					.body("{\"firstname\":\"Melanie\",\"lastname\":\"Muster\",\"mail\":\"melanie@muster.de\",\"child\":\"ID-1\"}");

		// when:
			ResponseOptions response = given().spec(request)
					.post("/contacts");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).isEqualTo("application/json;charset=UTF-8");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("lastname").isEqualTo("Muster");
			assertThatJson(parsedJson).field("firstname").isEqualTo("Melanie");
			assertThatJson(parsedJson).field("mail").isEqualTo("melanie@muster.de");
			assertThatJson(parsedJson).field("id").isEqualTo("ID-2");
			assertThatJson(parsedJson).field("child").isEqualTo("ID-1");
	}

	@Test
	public void validate_shouldNotAcceptAnInvalidPost() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json;charset=UTF-8")
					.body("{\"firstname\":\"Melanie\",\"lastname\":\"Muster\",\"mail\":\"melanie@muster.de\",\"child\":\"ID-3\"}");

		// when:
			ResponseOptions response = given().spec(request)
					.post("/contacts");

		// then:
			assertThat(response.statusCode()).isEqualTo(400);
			assertThat(response.header("Content-Type")).isEqualTo("application/problem+json");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
	}

}
