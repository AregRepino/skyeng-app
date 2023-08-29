package ru.skyeng.app.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.skyeng.app.dto.TrackingDto;
import ru.skyeng.app.dto.TrackingInputDto;
import ru.skyeng.app.entity.Tracking;
import ru.skyeng.app.entity.TrackingStatus;
import ru.skyeng.app.mapper.TrackingMapper;
import ru.skyeng.app.service.TrackingService;

import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class TrackingControllerTest {

    @Configuration
    @Import(TrackingController.class)
    static class Config {
    }


    @MockBean
    private TrackingService trackingServiceMock;

    @MockBean
    private TrackingMapper trackingMapperMock;

    @Autowired
    private TrackingController trackingController;

    @Test
    public void getTracking() {
//        given
        TrackingDto expected = new TrackingDto();
        expected.setId(1L);
        Tracking tracking = new Tracking();
        tracking.setId(1L);
        given(trackingServiceMock.findById(1L)).willReturn(tracking);
        given(trackingMapperMock.toTrackingDto(tracking)).willReturn(expected);

//        when
        TrackingDto actual = trackingController.getTracking(1L);
//        then
        assert actual.equals(expected);

    }


    @Test
    public void createTracking() {
//        given
        TrackingInputDto trackingDto = new TrackingInputDto();

        TrackingDto expectedTrackingDto = new TrackingDto();
        expectedTrackingDto.setId(1L);

        Tracking tracking = new Tracking();
        Tracking dbTracking = new Tracking();
        dbTracking.setId(1L);

        given(trackingMapperMock.toTracking(trackingDto)).willReturn(tracking);
        given(trackingServiceMock.create(tracking)).willReturn(dbTracking);
        given(trackingMapperMock.toTrackingDto(dbTracking)).willReturn(expectedTrackingDto);

//        when
        TrackingDto actual = trackingController.createTracking(trackingDto);

//        then
        assert actual.equals(expectedTrackingDto);

    }


    @Test
    public void updateTracking() {
//        given
        TrackingInputDto trackingInputDto = new TrackingInputDto();
        trackingInputDto.setStatus(TrackingStatus.ARRIVAL);

        TrackingDto expectedTrackingDto = new TrackingDto();
        expectedTrackingDto.setId(1L);

        Tracking tracking = new Tracking();
        Tracking dbTracking = new Tracking();
        dbTracking.setId(1L);

        given(trackingMapperMock.toTracking(1L, trackingInputDto)).willReturn(tracking);
        given(trackingServiceMock.update(tracking)).willReturn(dbTracking);
        given(trackingMapperMock.toTrackingDto(dbTracking)).willReturn(expectedTrackingDto);

//        when
        TrackingDto actual = trackingController.updateTracking(1L, trackingInputDto);

//        then
        assert actual.equals(expectedTrackingDto);

    }

    @Test
    public void getTrackingHistory() {
//        given
        TrackingDto expected = new TrackingDto();
        expected.setId(1L);
        Tracking tracking = new Tracking();
        tracking.setId(1L);
        List<Tracking> trackings = List.of(tracking);
        List<TrackingDto> trackingDtos = List.of(expected);
        given(trackingServiceMock.findByParcelId(1L)).willReturn(trackings);
        given(trackingMapperMock.toTrackingDtos(trackings)).willReturn(trackingDtos);

//        when
        List<TrackingDto> actual = trackingController.getTrackingHistory(1L);
//        then
        assert !actual.isEmpty();
        assert actual.size() == trackingDtos.size();

    }

}
