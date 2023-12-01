package de.makiti.catalog.api;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringJUnitConfig
public abstract class ResourcesTest {
    protected WebTestClient webTestClient;

    public ResourcesTest() {

    }

    @BeforeEach
    public void initWebTestClient() {

    }

    protected abstract java.lang.Object getResources();
}