package ru.skyeng.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.skyeng.app.dto.ParcelCreateDto;
import ru.skyeng.app.dto.ParcelDto;
import ru.skyeng.app.entity.Parcel;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ParcelMapper {
    public abstract ParcelDto toParcelDto(Parcel user);

    public abstract Parcel toParcel(ParcelDto parcelDto);
    public abstract Parcel toParcel(ParcelCreateDto createParcelDto);


}
