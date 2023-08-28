package ru.skyeng.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skyeng.app.dto.PostOfficeDto;
import ru.skyeng.app.entity.PostOffice;
import ru.skyeng.app.mapper.PostOfficeMapper;
import ru.skyeng.app.service.PostOfficeService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post-offices")
public class PostOfficeController {

    private final PostOfficeService postOfficeService;
    private final PostOfficeMapper postOfficeMapper;


    @GetMapping("/{id}")
    public PostOfficeDto getPostOffice(@PathVariable Long id) {
        PostOffice postOffice = postOfficeService.findById(id);
        return postOfficeMapper.toPostOfficeDto(postOffice);
    }


    @PostMapping
    public PostOfficeDto createPostOffice(@RequestBody @Validated PostOfficeDto userDto) {
        PostOffice postOffice = postOfficeMapper.toPostOffice(userDto);
        PostOffice createdPostOffice = postOfficeService.create(postOffice);
        return postOfficeMapper.toPostOfficeDto(createdPostOffice);
    }
}
