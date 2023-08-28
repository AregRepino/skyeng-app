package ru.skyeng.app.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.skyeng.app.entity.Parcel;
import ru.skyeng.app.repository.ParcelRepository;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class ParcelServiceTest {

    @Configuration
    @Import(ParcelService.class)
    static class Config {

    }

    @MockBean
    private ParcelRepository parcelRepositoryMock;

    @Autowired
    private ParcelService parcelService;


    @Test
    public void findById() {
//        given
        Parcel expected = new Parcel();
        expected.setId(1L);
        given(parcelRepositoryMock.findById(1L)).willReturn(Optional.of(expected));

//        when
        Parcel actual = parcelService.findById(1L);

//        then
        assert actual.equals(expected);
        verify(parcelRepositoryMock).findById(1L);
    }

    @Test
    public void create() {
//        given
        Parcel parcel = new Parcel();
        parcel.setName("Name");

        Parcel expected = new Parcel();
        expected.setName("Name");
        expected.setId(1L);
        given(parcelRepositoryMock.save(parcel)).willReturn(expected);

//        when
        Parcel actual = parcelService.create(parcel);

//        then
        assert actual.equals(expected);
        verify(parcelRepositoryMock).save(parcel);

    }

}
