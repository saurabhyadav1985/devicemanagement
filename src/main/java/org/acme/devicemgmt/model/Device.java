package org.acme.devicemgmt.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String brand;

    private LocalDateTime creationTime;

    // getters and setters
}