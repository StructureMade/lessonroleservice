package de.structuremade.ms.lessonservice.util.database.repo;

import de.structuremade.ms.lessonservice.util.database.entity.Lessonsettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonSettingsRepo extends JpaRepository<Lessonsettings, Integer> {
}
