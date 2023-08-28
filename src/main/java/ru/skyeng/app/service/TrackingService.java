package ru.skyeng.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skyeng.app.entity.Parcel;
import ru.skyeng.app.entity.PostOffice;
import ru.skyeng.app.entity.Tracking;
import ru.skyeng.app.entity.TrackingStatus;
import ru.skyeng.app.exceptions.BadRequestException;
import ru.skyeng.app.exceptions.ResourceNotFoundException;
import ru.skyeng.app.repository.ParcelRepository;
import ru.skyeng.app.repository.PostOfficeRepository;
import ru.skyeng.app.repository.TrackingRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TrackingService {
    private final TrackingRepository repository;
    private final ParcelRepository parcelRepository;
    private final PostOfficeRepository postOfficeRepository;

    public Tracking findById(Long id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Tracking create(Tracking tracking) {
        tracking.setId(null);
        Parcel parcel = parcelRepository.findById(tracking.getParcel().getId()).orElseThrow(ResourceNotFoundException::new);
        PostOffice postOffice = postOfficeRepository.findById(tracking.getPostOffice().getId()).orElseThrow(ResourceNotFoundException::new);
        tracking.setParcel(parcel);
        tracking.setPostOffice(postOffice);
        tracking.setStatus(TrackingStatus.ARRIVAL);
        return repository.save(tracking);
    }

    public Tracking update(Tracking tracking) {
        Tracking dbTracking = this.findById(tracking.getId());

//        Мы не обновляем parcel. А только postOffice
        if (!dbTracking.getParcel().getId().equals(tracking.getParcel().getId())) {
            throw new BadRequestException("Parcel can not be changed");
        }

//        Нет изменений
        if (dbTracking.getPostOffice().getId().equals(tracking.getPostOffice().getId())
                && dbTracking.getStatus().equals(tracking.getStatus())) {
            return dbTracking;
        }


//        set entities
        tracking.setParcel(dbTracking.getParcel());
        PostOffice postOffice = postOfficeRepository.findById(tracking.getPostOffice().getId()).orElseThrow(ResourceNotFoundException::new);
        tracking.setPostOffice(postOffice);

//
//        Обновление - это создание новой записи, чтобы была история
        tracking.setId(null);
        return repository.save(tracking);
    }


    public List<Tracking> findByParcelId(Long parcelId) {
        return repository.findAllByParcelId(parcelId);
    }
}
