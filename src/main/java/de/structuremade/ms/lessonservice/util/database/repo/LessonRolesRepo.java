package de.structuremade.ms.lessonservice.util.database.repo;

import de.structuremade.ms.lessonservice.util.database.entity.LessonRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRolesRepo extends JpaRepository<LessonRoles, String> {
}
