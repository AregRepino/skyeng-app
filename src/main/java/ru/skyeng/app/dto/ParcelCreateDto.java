package ru.skyeng.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.skyeng.app.entity.ParcelType;
@Getter
@Setter
public class ParcelCreateDto {
    @NotNull
    private ParcelType type;
    @NotNull
    private String postCode;
    @NotNull
    private String address;
    @NotNull
    private String name;
}
