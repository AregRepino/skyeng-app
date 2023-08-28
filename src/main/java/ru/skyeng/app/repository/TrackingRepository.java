package ru.skyeng.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skyeng.app.entity.Tracking;

import java.util.List;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {

    List<Tracking> findAllByParcelId(Long parcelId);
}
