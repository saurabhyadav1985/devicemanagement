package org.acme.devicemgmt.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
public class DeviceTest {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testDeviceCreation() {
        Device device = new Device();
        device.setName("Test Device");
        device.setBrand("Test Brand");
        device.setCreationTime(LocalDateTime.now());

        // Persist the device
        entityManager.persist(device);

        // Retrieve the device
        Device foundDevice = entityManager.find(Device.class, device.getId());

        // Assert that the retrieved device's name matches the one we set
        assertThat(foundDevice.getName()).isEqualTo(device.getName());
    }


    @Test
    void testPrePersist() {
        // create a new device and check whether the creation time is set
        Device device = new Device();
        device.setName("Test Device");
        device.setBrand("Test Brand");

        // Persist the device
        entityManager.persist(device);

        // Retrieve the device
        Device foundDevice = entityManager.find(Device.class, device.getId());

        // Assert that the creation time is set
        assertThat(foundDevice.getCreationTime()).isNotNull();
    }

}