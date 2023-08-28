package ru.skyeng.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "trackings")
public class Tracking extends UUIDEntity {
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "parcel_id", referencedColumnName = "id", nullable = false)
    private Parcel parcel;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "post_office_id", referencedColumnName = "id", nullable = false)
    private PostOffice postOffice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrackingStatus status;
}


//    регистрации почтового отправления, -> создание сущность Parcel -> POST create
//        его прибытие в промежуточное почтовое отделение, -> Tracking (1, 2, ARRIVAL)
//        его убытие из почтового отделения, Tracking (1, 2, DEPARTURE)
//        его получение адресатом,
//        просмотр статуса и полной истории движения почтового отправления. -> GET ?parcelId=1


//    findAllByParcelId(1)
//1,2, ARRIVAL
//1,2, DEPARTURE
//1,3, ARRIVAL
//1,3, DEPARTURE
//1,100, ARRIVAL
//1,100, TAKEN

//    регистрации почтового отправления,
//        его прибытие в промежуточное почтовое отделение,
//        его убытие из почтового отделения,
//        его получение адресатом,
//        просмотр статуса и полной истории движения почтового отправления.
