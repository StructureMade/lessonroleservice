package de.structuremade.ms.lessonservice.util.database.repo;

import de.structuremade.ms.lessonservice.util.database.entity.School;
import de.structuremade.ms.lessonservice.util.database.entity.Schoolsettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolSettingsRepo extends JpaRepository<Schoolsettings, String> {
    Schoolsettings findBySchool(School school);
}
