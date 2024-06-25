package org.acme.devicemgmt.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String brand;

    LocalDateTime creationTime;

    @PrePersist
    public void prePersist() {
        creationTime = LocalDateTime.now();
    }
}
