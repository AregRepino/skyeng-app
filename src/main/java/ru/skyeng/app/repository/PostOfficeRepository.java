package ru.skyeng.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skyeng.app.entity.PostOffice;

@Repository
public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {
}
