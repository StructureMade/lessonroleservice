package de.structuremade.ms.lessonservice.util.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lessonsettings")
@Getter
@Setter
public class Lessonsettings {
    @Id
    private int id;

    @Column
    private String option;

    @OneToMany(targetEntity = Lessons.class)
    @JoinColumn(name = "settingsid")
    private List<Lessons> lessons;

}
