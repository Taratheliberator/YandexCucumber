import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        // повышение читаемости вывода в консоли, заменяет нечитаемые символы
        monochrome = true,
        // плагины для форматирования вывода, отчетов
        plugin = {"pretty", "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm", "json:target/cucumber-report/report.json"},
        features = "src/test/java/features",
        glue = "org.example.stepdefs"
)
public class RunCucumberTest {
    // Пустой класс для запуска тестов Cucumber
}
