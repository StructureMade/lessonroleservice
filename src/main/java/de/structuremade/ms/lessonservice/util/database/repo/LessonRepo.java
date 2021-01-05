package de.structuremade.ms.lessonservice.util.database.repo;

import de.structuremade.ms.lessonservice.util.database.entity.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepo extends JpaRepository<Lessons, String> {
}
