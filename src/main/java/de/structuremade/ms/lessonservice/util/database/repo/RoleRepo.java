package de.structuremade.ms.lessonservice.util.database.repo;

import de.structuremade.ms.lessonservice.util.database.entity.Role;
import de.structuremade.ms.lessonservice.util.database.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, String> {
    Role findAlldById(String id);

    List<Role> findByName(String name);

    List<Role> findAllBySchool(School school);
}
