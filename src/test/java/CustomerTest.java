import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;



import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.jayway.restassured.RestAssured.*;


public class CustomerTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8090);

    public void setupStub() {

        stubFor(get(urlEqualTo("/rest/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withBody("You've reached a valid WireMock endpoint")));
    }

    @Test
    public void testStatusCodePositive() {

        setupStub();

        given().
                when().
                get("http://localhost:8090/rest/1").
                then().
                assertThat().statusCode(200);
    }
}
