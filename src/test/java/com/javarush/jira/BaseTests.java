package com.javarush.jira;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;

@SpringBootTest
@ActiveProfiles("test")
abstract class BaseTests {

    private static final int CONTAINER_PORT = 5433;
    private static final int LOCAL_PORT = 5432;
    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.3");

    @BeforeAll
    static void runContainer() {
                 container
                .withExposedPorts(LOCAL_PORT)
                .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                        new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(CONTAINER_PORT), new ExposedPort(LOCAL_PORT)))
                ));
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);

    }
}


