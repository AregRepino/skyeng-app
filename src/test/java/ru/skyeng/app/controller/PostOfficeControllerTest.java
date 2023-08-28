package ru.skyeng.app.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.skyeng.app.dto.PostOfficeDto;
import ru.skyeng.app.entity.PostOffice;
import ru.skyeng.app.mapper.PostOfficeMapper;
import ru.skyeng.app.service.PostOfficeService;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class PostOfficeControllerTest {

    @Configuration
    @Import(PostOfficeController.class)
    static class Config {
    }


    @MockBean
    private PostOfficeService postServiceMock;

    @MockBean
    private PostOfficeMapper postOfficeMapperMock;

    @Autowired
    private PostOfficeController postOfficeController;

    @Test
    public void getPostOffice() {
//        given
        PostOfficeDto expected = new PostOfficeDto();
        expected.setId(1L);
        PostOffice postOffice = new PostOffice();
        postOffice.setId(1L);
        given(postServiceMock.findById(1L)).willReturn(postOffice);
        given(postOfficeMapperMock.toPostOfficeDto(postOffice)).willReturn(expected);

//        when
        PostOfficeDto actual = postOfficeController.getPostOffice(1L);
//        then
        assert actual.equals(expected);

    }


    @Test
    public void createPostOffice() {
//        given
        PostOfficeDto postOfficeDto = new PostOfficeDto();

        PostOfficeDto expectedPostOfficeDto = new PostOfficeDto();
        expectedPostOfficeDto.setId(1L);

        PostOffice postOffice = new PostOffice();
        PostOffice dbPostOffice = new PostOffice();
        dbPostOffice.setId(1L);

        given(postOfficeMapperMock.toPostOffice(postOfficeDto)).willReturn(postOffice);
        given(postServiceMock.create(postOffice)).willReturn(dbPostOffice);
        given(postOfficeMapperMock.toPostOfficeDto(dbPostOffice)).willReturn(expectedPostOfficeDto);

//        when
        PostOfficeDto actual = postOfficeController.createPostOffice(postOfficeDto);

//        then
        assert actual.equals(expectedPostOfficeDto);

    }

}
