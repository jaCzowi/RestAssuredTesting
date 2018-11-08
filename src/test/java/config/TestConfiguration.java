package config;


import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Properties;

public abstract class TestConfiguration {

    protected final String headerToken = "X-Ninja-Token";
    protected String tokenValue;
    protected String apiEndpoint;

    protected Properties props = new Properties();

    @BeforeMethod
    public void loadTestProperties() throws IOException {
        props.load(getPropertyFile());
        apiEndpoint = props.getProperty("rest.api.url.endpoint");
        tokenValue = props.getProperty("ninja.token");
    }

    private InputStream getPropertyFile() {
        return Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResourceAsStream("test.properties"));
    }

    protected String getEndpointWithResource(HttpResources resources) {
        switch (resources) {
            case PRODUCTS:
                return apiEndpoint.concat(props.getProperty("rest.api.product.resource"));
            case CLIENTS:
                return apiEndpoint.concat(props.getProperty("rest.api.client.resource"));
            default:
                throw new NoSuchElementException();
        }
    }

}

