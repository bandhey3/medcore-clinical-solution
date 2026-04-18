package com.medcore.medcoreclinicalsolution.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity
@Table(name = "contacts")
@Getter
@Setter
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String company;

    private String fields;

    private String email;

    private String phone;

    @Column(length = 500)
    private String message;

    private boolean status = false;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
    }
}