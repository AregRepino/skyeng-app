package ru.skyeng.app.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.skyeng.app.dto.ParcelCreateDto;
import ru.skyeng.app.dto.ParcelDto;
import ru.skyeng.app.entity.Parcel;
import ru.skyeng.app.mapper.ParcelMapper;
import ru.skyeng.app.service.ParcelService;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class ParcelControllerTest {

    @Configuration
    @Import(ParcelController.class)
    static class Config {
    }


    @MockBean
    private ParcelService parcelServiceMock;

    @MockBean
    private ParcelMapper parcelMapperMock;

    @Autowired
    private ParcelController parcelController;

    @Test
    public void getParcel() {
//        given
        ParcelDto expected = new ParcelDto();
        expected.setId(1L);
        Parcel parcel = new Parcel();
        parcel.setId(1L);
        given(parcelServiceMock.findById(1L)).willReturn(parcel);
        given(parcelMapperMock.toParcelDto(parcel)).willReturn(expected);

//        when
        ParcelDto actual = parcelController.getParcel(1L);
//        then
        assert actual.equals(expected);

    }


    @Test
    public void createParcel() {
//        given
        ParcelCreateDto parcelDto = new ParcelCreateDto();

        ParcelDto expectedParcelDto = new ParcelDto();
        expectedParcelDto.setId(1L);

        Parcel parcel = new Parcel();
        Parcel dbParcel = new Parcel();
        dbParcel.setId(1L);

        given(parcelMapperMock.toParcel(parcelDto)).willReturn(parcel);
        given(parcelServiceMock.create(parcel)).willReturn(dbParcel);
        given(parcelMapperMock.toParcelDto(dbParcel)).willReturn(expectedParcelDto);

//        when
        ParcelDto actual = parcelController.createParcel(parcelDto);

//        then
        assert actual.equals(expectedParcelDto);

    }

}
