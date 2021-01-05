package de.structuremade.ms.lessonservice.api.service;

import de.structuremade.ms.lessonservice.api.json.CreateLessonJson;
import de.structuremade.ms.lessonservice.util.JWTUtil;
import de.structuremade.ms.lessonservice.util.database.entity.Lessons;
import de.structuremade.ms.lessonservice.util.database.repo.LessonRepo;
import de.structuremade.ms.lessonservice.util.database.repo.SchoolRepo;
import de.structuremade.ms.lessonservice.util.database.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {

    @Autowired
    LessonRepo lessonRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    SchoolRepo schoolRepo;

    @Autowired
    JWTUtil jwtUtil;

    public int create(CreateLessonJson lessonJson, String jwt){
        /*Variables*/
        Lessons lessons = new Lessons();
        /*End of Variables*/
        /*Set Variables*/
        lessons.setName(lessonJson.getName());
        lessons.setDay(lessonJson.getName());
        lessons.setState(0);
        lessons.setStartOfLesson(lessonJson.getStart());
        lessons.setEndOfLesson(lessonJson.getEnd());
        lessons.setSchool(schoolRepo.getOne(jwtUtil.extractSpecialClaim(jwt, "schoolid")));
        lessons.setUser(userRepo.getOne(jwtUtil.extractIdOrEmail(jwt)));
        try {
            lessonRepo.save(lessons);
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }
    public Object getUserLessons(){
        return null;
    }
}
