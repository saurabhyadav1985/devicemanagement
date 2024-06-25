package org.acme.devicemgmt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.acme.devicemgmt.model.Device;
import org.acme.devicemgmt.service.DeviceService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DeviceController.class)
class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceService deviceService;

    @Test
    @SneakyThrows
    void shouldAddDevice() {
        // instantiate a device object
        Device device = new Device();
        device.setId(1L);
        device.setBrand("Apple");
        device.setName("iPhone 12");

        // mock the service to return the device object
        when(deviceService.addDevice(device)).thenReturn(device);

        // perform a post request to add the device
        mockMvc.perform(post("/devices")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(asJsonString(device)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.brand", Matchers.is("Apple")))
               .andExpect(jsonPath("$.name", Matchers.is("iPhone 12")));
    }

    @Test
    @SneakyThrows
    void shouldGetDevice() {
        // instantiate a device object
        Device device = new Device();
        device.setId(1L);
        device.setBrand("Apple");
        device.setName("iPhone 12");

        // mock the service to return the device object when getDevice is called
        when(deviceService.getDevice(1L)).thenReturn(device);

        // perform a get request to fetch the device
        mockMvc.perform(get("/devices/{id}", 1L)
                       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.brand", Matchers.is("Apple")))
               .andExpect(jsonPath("$.name", Matchers.is("iPhone 12")));
    }

    @Test
    @SneakyThrows
    void shouldGetAllDevices() {
        // instantiate device objects
        Device device1 = new Device();
        device1.setId(1L);
        device1.setBrand("Apple");
        device1.setName("iPhone 12");

        Device device2 = new Device();
        device2.setId(2L);
        device2.setBrand("Samsung");
        device2.setName("Galaxy S21");

        // mock the service to return the list of devices
        when(deviceService.getAllDevices()).thenReturn(Arrays.asList(device1, device2));

        // perform a get request to fetch all devices
        mockMvc.perform(get("/devices")
                       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].brand", Matchers.is("Apple")))
               .andExpect(jsonPath("$[0].name", Matchers.is("iPhone 12")))
               .andExpect(jsonPath("$[1].brand", Matchers.is("Samsung")))
               .andExpect(jsonPath("$[1].name", Matchers.is("Galaxy S21")));
    }

    @Test
    @SneakyThrows
    void shouldUpdateDevice() {
        // instantiate a device object
        Device device = new Device();
        device.setId(1L);
        device.setBrand("Apple");
        device.setName("iPhone 12");

        // mock the service to return the updated device
        when(deviceService.updateDevice(device)).thenReturn(device);

        // perform a put request to update the device
        mockMvc.perform(put("/devices")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(asJsonString(device)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.brand", Matchers.is("Apple")))
               .andExpect(jsonPath("$.name", Matchers.is("iPhone 12")));
    }

    @Test
    @SneakyThrows
    void shouldDeleteDevice() {
        // perform a delete request to delete the device
        mockMvc.perform(delete("/devices/{id}", 1L)
                       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());

        verify(deviceService, times(1)).deleteDevice(1L);
    }

    @Test
    @SneakyThrows
    void shouldSearchByBrand() {
        // instantiate a device object
        Device device = new Device();
        device.setId(1L);
        device.setBrand("Apple");
        device.setName("iPhone 12");

        // mock the service to return the list of devices
        when(deviceService.searchByBrand("Apple")).thenReturn(List.of(device));

        // perform a get request to search devices by brand
        mockMvc.perform(get("/devices/search")
                       .param("brand", "Apple")
                       .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$[0].brand", Matchers.is("Apple")))
               .andExpect(jsonPath("$[0].name", Matchers.is("iPhone 12")));
    }

    private String asJsonString(Device device) {
        try {
            return new ObjectMapper().writeValueAsString(device);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}