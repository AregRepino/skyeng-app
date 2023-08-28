package ru.skyeng.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "parcels")
public class Parcel  extends UUIDEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParcelType type;
    @Column(nullable = false)
    private String postCode;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String name;

}
