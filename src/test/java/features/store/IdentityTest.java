package features.store;

import features.BaseTest;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.WithTag;
import org.junit.jupiter.api.Test;
import steps.StoreApiAsserts;
import steps.StoreApiSteps;


public class IdentityTest extends BaseTest {

    @Steps
    private StoreApiSteps storeApiSteps;
    @Steps
    private StoreApiAsserts storeApiAsserts;


    @Test
    @WithTag("store")
    public void getIdentityTest() {
        Response getInventoryResponse = storeApiSteps.getInventory();
        storeApiAsserts.validateInventory(getInventoryResponse);
    }
}
