package acceptance;

import com.matestodolocura.continuousdelivery.application.dto.DtoTwoNumbers;
import io.cucumber.java.en.And;
import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;

public class CalculatorStepDefinitions {

    private String server = System.getProperty("calculator.url");

    private RestTemplate restTemplate = new RestTemplate();

    private DtoTwoNumbers dtoTwoNumbers;
    private String a;
    private String b;
    private String resultWithRequestBody;
    private String resultWithRequestParams;
    private String resultWithPathVariables;

    @Given("^I have two numbers: (.*) and (.*)$")
    public void i_have_two_numbers(String a, String b) throws Throwable {
        this.a = a;
        this.b = b;
        this.dtoTwoNumbers = new DtoTwoNumbers(a, b);
    }

    @When("^the calculator sums them$")
    public void the_calculator_sums_them() throws Throwable {
        String url = String.format("%s/api/v1/calculator/add", server);
        resultWithRequestBody = restTemplate.postForObject(url, dtoTwoNumbers, String.class);
    }

    @And("^with request params$")
    public void with_request_params() {
        String url = String.format("%s/api/v1/calculator/add?number1=%s&number2=%s", server, a, b);
        resultWithRequestParams = restTemplate.getForObject(url, String.class);
    }

    @And("^with path variables$")
    public void with_path_variables() {
        String url = String.format("%s/api/v1/calculator/add/num1/%s/num2/%s", server, a, b);
        resultWithPathVariables = restTemplate.getForObject(url, String.class);
    }

    @Then("^I receive (.*) as a result$")
    public void i_receive_as_a_result(String expectedResult) throws Throwable {
        assertEquals(expectedResult, resultWithRequestBody);
    }

    @And("^the result (.*) with request params")
    public void the_result_with_request_params(String expectedResult){
        assertEquals(expectedResult, resultWithRequestParams);
    }

    @And("^the result (.*) with path variables")
    public void the_result_with_path_variables(String expectedResult){
        assertEquals(expectedResult, resultWithPathVariables);
    }
}