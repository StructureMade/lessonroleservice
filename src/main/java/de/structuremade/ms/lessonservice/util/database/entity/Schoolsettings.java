package de.structuremade.ms.lessonservice.util.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schoolsettings")
@Getter
@Setter
public class Schoolsettings {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private Date dateOfBegin;

    @Column
    private Date dateOfEnd;

    @ManyToOne(targetEntity = School.class)
    @JoinColumn(name = "schoolid")
    private School school;

}
