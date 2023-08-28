package ru.skyeng.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public abstract class UUIDEntityDto {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
}
