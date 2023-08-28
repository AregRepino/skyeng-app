package ru.skyeng.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "post_offices")
public class PostOffice extends UUIDEntity {
    @Column(nullable = false)
    private String postCode;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;

}
