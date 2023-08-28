package ru.skyeng.app.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.skyeng.app.entity.Parcel;
import ru.skyeng.app.entity.PostOffice;
import ru.skyeng.app.entity.Tracking;
import ru.skyeng.app.entity.TrackingStatus;
import ru.skyeng.app.repository.ParcelRepository;
import ru.skyeng.app.repository.PostOfficeRepository;
import ru.skyeng.app.repository.TrackingRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class TrackingServiceTest {

    @Configuration
    @Import(TrackingService.class)
    static class Config {

    }

    @MockBean
    private TrackingRepository trackingRepositoryMock;
    @MockBean
    private ParcelRepository parcelRepositoryMock;

    @MockBean
    private PostOfficeRepository postOfficeRepositoryMock;
    @Autowired
    private TrackingService trackingService;


    @Test
    public void findById() {
//        given
        Tracking expected = new Tracking();
        expected.setId(1L);
        given(trackingRepositoryMock.findById(1L)).willReturn(Optional.of(expected));

//        when
        Tracking actual = trackingService.findById(1L);

//        then
        assert actual.equals(expected);
        verify(trackingRepositoryMock).findById(1L);
    }

    @Test
    public void create() {
//        given
        Tracking tracking = new Tracking();
        tracking.setStatus(TrackingStatus.ARRIVAL);

        Parcel parcel = new Parcel();
        parcel.setId(1L);
        PostOffice postOffice = new PostOffice();
        postOffice.setId(2L);
        tracking.setParcel(parcel);
        tracking.setPostOffice(postOffice);

        Tracking expected = new Tracking();
        expected.setStatus(TrackingStatus.ARRIVAL);
        expected.setId(1L);
        given(trackingRepositoryMock.save(tracking)).willReturn(expected);
        given(parcelRepositoryMock.findById(tracking.getParcel().getId())).willReturn(Optional.of(parcel));
        given(postOfficeRepositoryMock.findById(tracking.getPostOffice().getId())).willReturn(Optional.of(postOffice));

//        when
        Tracking actual = trackingService.create(tracking);

//        then
        assert actual.equals(expected);
        verify(trackingRepositoryMock).save(tracking);
        verify(parcelRepositoryMock).findById(tracking.getParcel().getId());
        verify(postOfficeRepositoryMock).findById(tracking.getPostOffice().getId());

    }


    @Test
    public void update() {
//        given
        Tracking tracking = new Tracking();
        tracking.setId(1L);
        tracking.setStatus(TrackingStatus.ARRIVAL);

        Parcel parcel = new Parcel();
        parcel.setId(1L);
        PostOffice postOffice = new PostOffice();
        postOffice.setId(2L);
        tracking.setParcel(parcel);
        tracking.setPostOffice(postOffice);
        tracking.setStatus(TrackingStatus.ARRIVAL);

        Tracking dbTracking = new Tracking();
        dbTracking.setParcel(parcel);
        PostOffice dbPostOffice = new PostOffice();
        dbPostOffice.setId(10L);
        dbTracking.setPostOffice(dbPostOffice);
        dbTracking.setPostOffice(dbPostOffice);


        given(trackingRepositoryMock.findById(tracking.getId())).willReturn(Optional.of(dbTracking));
        given(trackingRepositoryMock.save(tracking)).willReturn(tracking);
        given(postOfficeRepositoryMock.findById(tracking.getPostOffice().getId())).willReturn(Optional.of(postOffice));

//        when
        Tracking actual = trackingService.update(tracking);

//        then
        assert actual.equals(tracking);
        verify(trackingRepositoryMock).save(tracking);
        verify(postOfficeRepositoryMock).findById(tracking.getPostOffice().getId());

    }


    @Test
    public void findAllByParcelId() {
//        given
        Tracking tracking = new Tracking();
        tracking.setId(1L);
        List<Tracking> expected = List.of(tracking);
        given(trackingRepositoryMock.findAllByParcelId(1L)).willReturn(expected);

//        when
        List<Tracking> actual = trackingService.findByParcelId(1L);

//        then
        assert !actual.isEmpty();
        assert actual.size() == expected.size();
        verify(trackingRepositoryMock).findAllByParcelId(1L);
    }

}
