package ru.skyeng.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostOfficeCreateDto {
    @NotNull
    private String postCode;
    @NotNull
    private String name;
    @NotNull
    private String address;
}
