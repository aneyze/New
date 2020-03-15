package Runner;

        import org.junit.runner.RunWith;
        import cucumber.api.CucumberOptions;
        import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features={"src/test/resources/features/web2.feature"},
        glue={"stepDefinitionsWeb2"})

public class RunnerWeb2 {


}
