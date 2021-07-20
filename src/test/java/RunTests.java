

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/report"},
        features = {"src/main/resources/features"},
        glue = {"automation"},
        strict = true
)
public class RunTests {
@Test
public void tests(){

}

}
