package de.structuremade.ms.lessonservice.util.database.repo;

import de.structuremade.ms.lessonservice.util.database.entity.LessonSubstitutes;
import de.structuremade.ms.lessonservice.util.database.entity.Lessons;
import de.structuremade.ms.lessonservice.util.database.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;

public interface LessonSubstitutesRepo extends JpaRepository<LessonSubstitutes, String> {
    LessonSubstitutes findByDateOfSubstituteAndLesson(Date date, Lessons lessons);
    Iterable<? extends LessonSubstitutes> findAllByLesson(Lessons lesson);
    Collection<? extends LessonSubstitutes> findALlByDateOfSubstitute(Date time);

    Collection<? extends LessonSubstitutes> findALlByDateOfSubstituteAndSchool(Date time, School school);
}
