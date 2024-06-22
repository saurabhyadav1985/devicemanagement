package org.acme.devicemgmt.controller;

import org.acme.devicemgmt.model.Device;
import org.acme.devicemgmt.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<Device> addDevice(@RequestBody Device device) {
        Device createdDevice = deviceService.addDevice(device);
        return new ResponseEntity<>(createdDevice, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Device getDevice(@PathVariable Long id) {
        return deviceService.getDevice(id);
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @PutMapping
    public Device updateDevice(@RequestBody Device device) {
        return deviceService.updateDevice(device);
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
    }

    @GetMapping("/search")
    public List<Device> searchByBrand(@RequestParam String brand) {
        return deviceService.searchByBrand(brand);
    }
}