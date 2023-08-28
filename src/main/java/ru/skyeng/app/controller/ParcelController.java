package ru.skyeng.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skyeng.app.dto.ParcelDto;
import ru.skyeng.app.entity.Parcel;
import ru.skyeng.app.mapper.ParcelMapper;
import ru.skyeng.app.service.ParcelService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/parcels")
public class ParcelController {

    private final ParcelService parcelService;
    private final ParcelMapper parcelMapper;


    @GetMapping("/{id}")
    public ParcelDto getParcel(@PathVariable Long id) {
        Parcel parcel = parcelService.findById(id);
        return parcelMapper.toParcelDto(parcel);
    }


    @PostMapping
    public ParcelDto createParcel(@RequestBody @Validated ParcelDto userDto) {
        Parcel parcel = parcelMapper.toParcel(userDto);
        Parcel createdParcel = parcelService.create(parcel);
        return parcelMapper.toParcelDto(createdParcel);
    }
}
