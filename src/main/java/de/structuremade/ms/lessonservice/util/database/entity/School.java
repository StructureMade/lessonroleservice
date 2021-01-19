package de.structuremade.ms.lessonservice.util.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schools", schema = "services", indexes = {
        @Index(name = "id_schoolid", columnList = "id", unique = true)
})
@Getter
@Setter
public class School {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String name;

    @Column
    private String email;

    @OneToMany(targetEntity = Role.class, orphanRemoval = true)
    @JoinColumn(name = "schoolid", foreignKey = @ForeignKey(name = "fk_schoolid"))
    private List<Role> roles;

    @OneToMany(targetEntity = Lessons.class, orphanRemoval = true)
    @JoinColumn(name = "schoolid", foreignKey = @ForeignKey(name = "fk_schoolid"))
    private List<Lessons> lessons;

    @OneToMany(targetEntity = Holidays.class)
    @JoinColumn(name = "schoolid")
    private List<Holidays> holidays;

    @OneToMany(targetEntity = Schoolsettings.class)
    @JoinColumn(name = "schoolid")
    private List<Schoolsettings> schoolSettings;

    @OneToMany(targetEntity = LessonSubstitutes.class)
    @JoinColumn(name = "schoolid")
    List<LessonSubstitutes> lessonSubstitutes;

}
