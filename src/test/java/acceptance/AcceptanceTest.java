package acceptance;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/** Acceptance Test */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:feature/calculator.feature",
        glue = "acceptance")
public class AcceptanceTest {
}