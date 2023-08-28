package ru.skyeng.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skyeng.app.entity.Parcel;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {
}
