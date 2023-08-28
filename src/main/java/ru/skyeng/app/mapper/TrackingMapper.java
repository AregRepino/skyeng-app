package ru.skyeng.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.skyeng.app.dto.TrackingDto;
import ru.skyeng.app.entity.Tracking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TrackingMapper {

    @Mapping(source = "parcel.id", target = "parcelId")
    @Mapping(source = "postOffice.id", target = "postOfficeId")
    public abstract TrackingDto toTrackingDto(Tracking tracking);

    @Mapping(source = "parcelId", target = "parcel.id")
    @Mapping(source = "postOfficeId", target = "postOffice.id")
    public abstract Tracking toTracking(TrackingDto trackingDto);


    public Tracking toTracking(Long id, TrackingDto trackingDto) {
        Tracking tracking = toTracking(trackingDto);
        tracking.setId(id);
        return tracking;
    }


    public List<TrackingDto> toTrackingDtos(List<Tracking> trackings) {
        if (trackings == null) {
            return new ArrayList<>();
        }
        return trackings.stream().map(this::toTrackingDto).collect(Collectors.toList());

    }
}
