package de.structuremade.ms.lessonservice.util.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class Holidays {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String name;

    @Column
    private Date begin;

    @Column
    private Date end;

    @ManyToOne
    @JoinColumn(name = "schoolid", foreignKey = @ForeignKey(name = "fk_schoolid"))
    private School school;
}
