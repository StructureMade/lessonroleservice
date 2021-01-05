package de.structuremade.ms.lessonservice.util.database.repo;

import de.structuremade.ms.lessonservice.util.database.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepo extends JpaRepository<School, String> {
}
