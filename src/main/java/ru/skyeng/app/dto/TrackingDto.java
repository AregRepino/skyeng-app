package ru.skyeng.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.skyeng.app.entity.TrackingStatus;

@Getter
@Setter
public class TrackingDto extends UUIDEntityDto {
    @NotNull
    private Long parcelId;
    @NotNull
    private Long postOfficeId;
    @NotNull
    private TrackingStatus status;
}

