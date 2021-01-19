package de.structuremade.ms.lessonservice.util.database.repo;

import de.structuremade.ms.lessonservice.util.database.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionsRepo extends JpaRepository<Permissions, String> {
}
