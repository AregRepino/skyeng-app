package ru.skyeng.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skyeng.app.entity.Parcel;
import ru.skyeng.app.exceptions.ResourceNotFoundException;
import ru.skyeng.app.repository.ParcelRepository;

@RequiredArgsConstructor
@Service
public class ParcelService {
    private final ParcelRepository repository;

    public Parcel findById(Long id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Parcel create(Parcel parcel) {
        parcel.setId(null);
        return repository.save(parcel);
    }
}
