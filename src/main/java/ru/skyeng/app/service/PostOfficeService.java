package ru.skyeng.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skyeng.app.entity.PostOffice;
import ru.skyeng.app.exceptions.ResourceNotFoundException;
import ru.skyeng.app.repository.PostOfficeRepository;

@RequiredArgsConstructor
@Service
public class PostOfficeService {
    private final PostOfficeRepository repository;

    public PostOffice findById(Long id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public PostOffice create(PostOffice postOffice) {
        postOffice.setId(null);
        return repository.save(postOffice);
    }
}
