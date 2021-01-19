package de.structuremade.ms.lessonservice.util.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "homework", schema = "services")
@Getter
@Setter
public class Homework {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Date validTill;

    @ManyToOne(targetEntity = LessonSubstitutes.class)
    @JoinColumn(name = "lessonid", foreignKey = @ForeignKey(name = "fk_lessonid"))
    private LessonSubstitutes lesson;
}
