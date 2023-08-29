package ru.skyeng.app.mapper;

import org.junit.Test;
import ru.skyeng.app.dto.TrackingInputDto;
import ru.skyeng.app.entity.Parcel;
import ru.skyeng.app.entity.PostOffice;
import ru.skyeng.app.entity.Tracking;
import ru.skyeng.app.entity.TrackingStatus;


public class TrackingMapperTest {


    private final TrackingMapperImpl trackingMapper = new TrackingMapperImpl();


    @Test
    public void toTracking() {
//        given
        TrackingInputDto source = new TrackingInputDto();
        source.setStatus(TrackingStatus.ARRIVAL);
        source.setParcelId(2L);
        source.setPostOfficeId(3L);

        Tracking expected = new Tracking();
        expected.setStatus(TrackingStatus.ARRIVAL);
        Parcel parcel = new Parcel();
        parcel.setId(2L);
        PostOffice postOffice = new PostOffice();
        postOffice.setId(3L);
        expected.setParcel(parcel);
        expected.setPostOffice(postOffice);

//        when
        Tracking actual = trackingMapper.toTracking(source);

//        then
        assert actual.getParcel().getId().equals(expected.getParcel().getId());
        assert actual.getPostOffice().getId().equals(expected.getPostOffice().getId());
//        assertUser(source, target);

    }

    @Test
    public void toTrackingWithId() {
//        given
        TrackingInputDto source = new TrackingInputDto();
        source.setStatus(TrackingStatus.ARRIVAL);
        source.setParcelId(2L);
        source.setPostOfficeId(3L);

        Tracking expected = new Tracking();
        expected.setStatus(TrackingStatus.ARRIVAL);
        Parcel parcel = new Parcel();
        parcel.setId(2L);
        PostOffice postOffice = new PostOffice();
        postOffice.setId(3L);
        expected.setParcel(parcel);
        expected.setPostOffice(postOffice);

//        when
        Tracking actual = trackingMapper.toTracking(1L, source);

//        then
        assert actual.getId().equals(1L);
        assert actual.getParcel().getId().equals(expected.getParcel().getId());
        assert actual.getPostOffice().getId().equals(expected.getPostOffice().getId());
//        assertUser(source, target);

    }


}
