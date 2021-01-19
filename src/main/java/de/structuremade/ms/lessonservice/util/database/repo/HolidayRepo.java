package de.structuremade.ms.lessonservice.util.database.repo;

import de.structuremade.ms.lessonservice.util.database.entity.Holidays;
import de.structuremade.ms.lessonservice.util.database.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HolidayRepo extends JpaRepository<Holidays, String> {
    List<Holidays> findAllBySchool(School school);
}
