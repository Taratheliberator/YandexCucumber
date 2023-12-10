import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "org.example.stepdefs"
)
public class RunCucumberTest {
    // Пустой класс для запуска тестов Cucumber
}
