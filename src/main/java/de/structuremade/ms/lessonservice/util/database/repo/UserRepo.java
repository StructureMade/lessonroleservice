package de.structuremade.ms.lessonservice.util.database.repo;

import de.structuremade.ms.lessonservice.util.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
