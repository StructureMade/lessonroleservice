package de.structuremade.ms.lessonservice.util.database.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Lessons")
@Getter
@Setter
public class Lessons {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String day;

    @Column
    private String room;

    @Column
    private int state;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "teacherId", foreignKey = @ForeignKey(name = "fk_teacherid"))
    private User teacher;

    @ManyToOne(targetEntity = Lessonsettings.class)
    @JoinColumn(name = "settingsid", foreignKey = @ForeignKey(name = "fk_lessonsettings"))
    private Lessonsettings settings;

    @ManyToOne(targetEntity = School.class)
    @JoinColumn(name = "schoolid", foreignKey = @ForeignKey(name = "fk_schoolid"))
    private School school;

    @ManyToOne(targetEntity = LessonRoles.class)
    @JoinColumn(name = "lessonroleid", foreignKey = @ForeignKey(name = "fk_lessonroleid"))
    private LessonRoles lessonRoles;

    @ManyToMany(targetEntity = TimeTableTimes.class, fetch = FetchType.LAZY)
    @JoinTable(name = "lessontimes", schema = "services", joinColumns = @JoinColumn(name = "lessonid", foreignKey = @ForeignKey(name = "fk_userid"))
            , inverseJoinColumns = @JoinColumn(name = "timeid", foreignKey = @ForeignKey(name = "fk_timeid")))
    private List<TimeTableTimes> times;

    @OneToMany(targetEntity = Homework.class)
    @JoinColumn(name = "lesson", foreignKey = @ForeignKey(name = "fk_lessonrole"))
    private List<Homework> homework;

    @OneToMany(targetEntity = LessonSubstitutes.class)
    @JoinColumn(name = "lessonid")
    private List<LessonSubstitutes> substitutes;
}
