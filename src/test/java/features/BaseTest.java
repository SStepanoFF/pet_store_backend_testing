package features;

import io.restassured.RestAssured;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.steps.StepFactory;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(SerenityJUnit5Extension.class)
public abstract class BaseTest {

    protected static StepFactory stepFactory;
    protected static EnvironmentVariables environmentVariables;
    public static String baseUrl;

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    @BeforeAll
    static void globalSetup() {
        environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        baseUrl = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("base.url");
        RestAssured.baseURI = baseUrl;
        LOGGER.info("BaseUrl set to " + baseUrl);
    }

}