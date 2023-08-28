package ru.skyeng.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@MappedSuperclass
public abstract class UUIDEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Instant createdAt;
    @Column(nullable = false)
    private Instant updatedAt;


    @PrePersist
    private void onCreate() {
        Instant now = Instant.now().truncatedTo(ChronoUnit.MILLIS);
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    }
}
