package ru.skyeng.app;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.skyeng.app.entity.TrackingStatus;

@Getter
@Setter
public class TrackingUpdateDto {
    @NotNull
    private Long parcelId;
    @NotNull
    private Long postOfficeId;
    @NotNull
    private TrackingStatus status;
}

