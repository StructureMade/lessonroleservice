package de.structuremade.ms.lessonservice.util.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "timetabletimes", schema = "services")
@Getter
@Setter
public class TimeTableTimes {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String name;

    @Column
    private Time starttime;

    @Column
    private Time endtime;

    @ManyToOne(targetEntity = School.class)
    @JoinColumn(name = "school")
    private School school;


}
