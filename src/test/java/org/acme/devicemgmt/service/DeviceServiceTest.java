package org.acme.devicemgmt.service;

import org.acme.devicemgmt.model.Device;
import org.acme.devicemgmt.repository.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    @Test
    public void testAddDevice() {
        Device device = new Device();
        device.setId(1L);
        when(deviceRepository.save(device)).thenReturn(device);

        Device result = deviceService.addDevice(device);

        assertEquals(device, result);
        verify(deviceRepository, times(1)).save(device);
    }

    @Test
    public void testGetDevice() {
        Device device = new Device();
        device.setId(1L);
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));

        Device result = deviceService.getDevice(1L);

        assertEquals(device, result);
        verify(deviceRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetDeviceNotFound() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        Device result = deviceService.getDevice(1L);

        assertNull(result);
        verify(deviceRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllDevices() {
        Device device1 = new Device();
        device1.setId(1L);
        Device device2 = new Device();
        device2.setId(2L);
        when(deviceRepository.findAll()).thenReturn(Arrays.asList(device1, device2));

        List<Device> result = deviceService.getAllDevices();

        assertEquals(2, result.size());
        verify(deviceRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateDevice() {
        Device device = new Device();
        device.setId(1L);
        when(deviceRepository.save(device)).thenReturn(device);

        Device result = deviceService.updateDevice(device);

        assertEquals(device, result);
        verify(deviceRepository, times(1)).save(device);
    }

    @Test
    public void testDeleteDevice() {
        doNothing().when(deviceRepository).deleteById(1L);

        deviceService.deleteDevice(1L);

        verify(deviceRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testSearchByBrand() {
        Device device = new Device();
        device.setId(1L);
        device.setBrand("Apple");
        when(deviceRepository.findByBrand("Apple")).thenReturn(Arrays.asList(device));

        List<Device> result = deviceService.searchByBrand("Apple");

        assertEquals(1, result.size());
        assertEquals("Apple", result.get(0).getBrand());
        verify(deviceRepository, times(1)).findByBrand("Apple");
    }
}