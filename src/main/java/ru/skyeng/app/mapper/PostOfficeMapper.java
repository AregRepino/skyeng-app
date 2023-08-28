package ru.skyeng.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.skyeng.app.dto.PostOfficeDto;
import ru.skyeng.app.entity.PostOffice;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PostOfficeMapper {
    public abstract PostOfficeDto toPostOfficeDto(PostOffice user);

    public abstract PostOffice toPostOffice(PostOfficeDto userDto);


}
