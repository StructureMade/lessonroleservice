package de.structuremade.ms.lessonservice.util.database.repo;

import de.structuremade.ms.lessonservice.util.database.entity.School;
import de.structuremade.ms.lessonservice.util.database.entity.TimeTableTimes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeRepo extends JpaRepository<TimeTableTimes, String> {
    List<TimeTableTimes> findAllBySchool(School school);
    List<TimeTableTimes> findAllBySchoolAndId(School school, String id);
}
