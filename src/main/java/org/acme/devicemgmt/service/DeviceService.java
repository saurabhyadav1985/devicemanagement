package org.acme.devicemgmt.service;

import org.acme.devicemgmt.model.Device;
import org.acme.devicemgmt.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Device getDevice(Long id) {
        return deviceRepository.findById(id).orElse(null);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device updateDevice(Device device) {
        return deviceRepository.save(device);
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    public List<Device> searchByBrand(String brand) {
        return deviceRepository.findByBrand(brand);
    }
}