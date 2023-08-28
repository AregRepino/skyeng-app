package ru.skyeng.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skyeng.app.dto.TrackingDto;
import ru.skyeng.app.entity.Tracking;
import ru.skyeng.app.mapper.TrackingMapper;
import ru.skyeng.app.service.TrackingService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trackings")
public class TrackingController {

    private final TrackingService trackingService;
    private final TrackingMapper trackingMapper;


    @GetMapping("/{id}")
    public TrackingDto getTracking(@PathVariable Long id) {
        Tracking tracking = trackingService.findById(id);
        return trackingMapper.toTrackingDto(tracking);
    }
    @GetMapping
    public List<TrackingDto> getTrackingHistory(@RequestParam("parcelId") Long parcelId) {
        List<Tracking> trackings = trackingService.findByParcelId(parcelId);
        return trackingMapper.toTrackingDtos(trackings);
    }


    @PostMapping
    public TrackingDto createTracking(@RequestBody @Validated TrackingDto trackingDto) {
        Tracking tracking = trackingMapper.toTracking(trackingDto);
        Tracking createdTracking = trackingService.create(tracking);
        return trackingMapper.toTrackingDto(createdTracking);
    }

    @PutMapping("/{id}")
    public TrackingDto updateTracking(@PathVariable Long id, @RequestBody @Validated TrackingDto trackingDto) {
        Tracking tracking = trackingMapper.toTracking(id, trackingDto);
        Tracking updatedTracking = trackingService.update(tracking);
        return trackingMapper.toTrackingDto(updatedTracking);
    }
}
