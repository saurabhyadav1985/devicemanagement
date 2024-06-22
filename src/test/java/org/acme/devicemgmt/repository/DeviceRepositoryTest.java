package org.acme.devicemgmt.repository;

import org.acme.devicemgmt.model.Device;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DeviceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    public void whenFindByBrand_thenReturnDevices() {
        // given
        Device appleDevice = new Device();
        appleDevice.setBrand("Apple");
        entityManager.persistAndFlush(appleDevice);

        // when
        List<Device> foundDevices = deviceRepository.findByBrand(appleDevice.getBrand());

        // then
        assertThat(foundDevices).isNotEmpty();
        assertThat(foundDevices.get(0).getBrand()).isEqualTo(appleDevice.getBrand());
    }
}