package ru.skyeng.app.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.skyeng.app.entity.PostOffice;
import ru.skyeng.app.repository.PostOfficeRepository;


import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class PostOfficeServiceTest {

    @Configuration
    @Import(PostOfficeService.class)
    static class Config {

    }

    @MockBean
    private PostOfficeRepository postOfficeRepositoryMock;

    @Autowired
    private PostOfficeService postOfficeService;


    @Test
    public void findById() {
//        given
        PostOffice expected = new PostOffice();
        expected.setId(1L);
        given(postOfficeRepositoryMock.findById(1L)).willReturn(Optional.of(expected));

//        when
        PostOffice actual = postOfficeService.findById(1L);

//        then
        assert actual.equals(expected);
        verify(postOfficeRepositoryMock).findById(1L);
    }

    @Test
    public void create() {
//        given
        PostOffice postOffice = new PostOffice();
        postOffice.setName("Name");

        PostOffice expected = new PostOffice();
        expected.setName("Name");
        expected.setId(1L);
        given(postOfficeRepositoryMock.save(postOffice)).willReturn(expected);

//        when
        PostOffice actual = postOfficeService.create(postOffice);

//        then
        assert actual.equals(expected);
        verify(postOfficeRepositoryMock).save(postOffice);

    }

}
