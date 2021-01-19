package de.structuremade.ms.lessonservice.util.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "lessonroles")
@Getter
@Setter
public class LessonRoles {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "schoolid", foreignKey = @ForeignKey(name = "fk_schoolid"))
    private School school;

    @OneToMany(targetEntity = Lessons.class)
    @JoinColumn(name = "lessonroleid")
    private List<Lessons> lessons;
}
