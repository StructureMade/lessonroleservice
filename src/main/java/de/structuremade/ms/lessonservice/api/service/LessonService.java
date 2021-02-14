package de.structuremade.ms.lessonservice.api.service;

import de.structuremade.ms.lessonservice.api.json.CreateLessonJson;
import de.structuremade.ms.lessonservice.api.json.SetLessonsJson;
import de.structuremade.ms.lessonservice.util.Converter;
import de.structuremade.ms.lessonservice.util.JWTUtil;
import de.structuremade.ms.lessonservice.util.database.entity.*;
import de.structuremade.ms.lessonservice.util.database.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class LessonService {

    private final Logger LOGGER = LoggerFactory.getLogger(TimeService.class);
    @Autowired
    UserRepo userRepo;
    @Autowired
    SchoolRepo schoolRepo;
    @Autowired
    TimeRepo timeRepo;
    @Autowired
    LessonRolesRepo lessonRolesRepo;
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    Converter converter;


    public int create(CreateLessonJson lessonJson, String jwt){
        /*Variables*/
            LessonRoles lr = new LessonRoles();
        /*End of Variables*/
        try {
            LOGGER.info("Initialize Lesson Entity");
            lr.setName(lessonJson.getName());
            try {
                if (jwtUtil.isTokenExpired(jwt)) {
                    LOGGER.info("JWT was expired");
                    return 2;
                }
                LOGGER.info("Check if Teacher was send to API");
                if (!lessonJson.getTeacher().isEmpty() && lessonJson.getTeacher() != null) {
                    LOGGER.info("Teacher is set");
                    lr.setTeacher(userRepo.getOne(lessonJson.getTeacher()));
                }
                LOGGER.info("Set school");
                lr.setSchool(schoolRepo.getOne(jwtUtil.extractSpecialClaim(jwt, "schoolid")));
                lessonRolesRepo.save(lr);
                return 0;
            } catch (Exception e) {
                LOGGER.error("Create Lesson failed", e.fillInStackTrace());
                e.printStackTrace();
                return 1;
            }
        }catch (Exception e) {
            LOGGER.error("Create Lesson failed", e.fillInStackTrace());
            return 1;
        }
    }

    public int setToUser(SetLessonsJson slj, String jwt){
        User user;
        try {
            if (jwtUtil.isTokenExpired(jwt)) {
                LOGGER.info("JWT was expired");
                return 3;
            }
            if(slj.getLesson() == null && slj.getLessons() == null || slj.getLesson() != null && slj.getLessons() != null) {
                return 2;
            }
            LOGGER.info("Get Lesson and User");
            user = userRepo.getOne(slj.getUser());
            List<LessonRoles> lessonRoles = user.getLessonRoles();
            if (slj.getLessons() == null) {
                lessonRoles.add(lessonRolesRepo.getOne(slj.getLesson()));
            }else {
                slj.getLessons().forEach(lesson ->{
                    lessonRoles.add(lessonRolesRepo.getOne(lesson));
                });
            }
            LOGGER.info("Set lesson to user");
            user.setLessonRoles(lessonRoles);
            userRepo.save(user);
            return 0;
        }catch (Exception e){
            LOGGER.error("Couldn't set Lesson to User", e.fillInStackTrace());
            return 1;
        }
    }
}
    /*
    variables
    List<Times> times = new ArrayList<>();
    List<LessonSubstitutes> allLessons = new ArrayList<>();
    List<LessonDays> monday = new ArrayList<>();
    List<LessonDays> tuesday = new ArrayList<>();
    List<LessonDays> wednesday = new ArrayList<>();
    List<LessonDays> thursday = new ArrayList<>();
    List<LessonDays> friday = new ArrayList<>();
    List<String> timeIds = new ArrayList<>();
    LessonDays lessonsJsonArray;
    TimeTable timeTable;
    end of variables
        try {
        User user = userRepo.getOne(jwtUtil.extractIdOrEmail(jwt));
        timeTable = timeTableRepo.findByUser(user);
        if (user.getId() != null) {
            if (timeTable == null) {
                for (Lessons lessons : user.getLessonRoles()) {
                    switch (lessons.getSettings().getId()) {
                        case 0:
                            System.out.println("every week");
                        case 1:
                            System.out.println("even Week");
                        case 2:
                            System.out.println("odd week");
                        case 3:
                            System.out.println("1st half year");
                        case 4:
                            System.out.println("2nd half year");
                    }
                    for (TimeTableTimes ttt : lessons.getTimes()) {
                        timeIds.add(ttt.getId());
                    }
                    lessonsJsonArray = new LessonDays(lessons, lessons.getTeacher(), lessons.getSubstituteTeacher(), timeIds);
                    switch (lessons.getDay()) {
                        case "monday" -> monday.add(lessonsJsonArray);
                        case "tuesday" -> tuesday.add(lessonsJsonArray);
                        case "wednesday" -> wednesday.add(lessonsJsonArray);
                        case "thursday" -> thursday.add(lessonsJsonArray);
                        case "friday" -> friday.add(lessonsJsonArray);
                    }
                    allLessons.add(lessons);
                }
                timeTable = new TimeTable();
                timeTable.setLessons(allLessons);
            } else {
                for (LessonSubstitutes lesson : timeTable.getLessons()) {
                    for (TimeTableTimes ttt : lesson.getTimes()) {
                        timeIds.add(ttt.getId());
                    }
                    lessonsJsonArray = new LessonDays(lesson, lesson.getTeacher(), lesson.getSubstituteTeacher(), timeIds);
                    switch (lesson.getDay()) {
                        case "monday" -> monday.add(lessonsJsonArray);
                        case "tuesday" -> tuesday.add(lessonsJsonArray);
                        case "wednesday" -> wednesday.add(lessonsJsonArray);
                        case "thursday" -> thursday.add(lessonsJsonArray);
                        case "friday" -> friday.add(lessonsJsonArray);
                    }
                }

            }
            for (TimeTableTimes ttt : timeRepo.findAllBySchool(schoolRepo.getOne(jwtUtil.extractSpecialClaim(jwt, "schoolid")))) {
                Date start = new Date(ttt.getStarttime().getTime());
                Date end = new Date(ttt.getEndtime().getTime());
                times.add(new Times(ttt.getId(), sdf.format(start), sdf.format(end)));
            }
            return new GetMyLessonsJson(times, monday, tuesday, wednesday, thursday, friday);
        } else {
            return new GetMyLessonsJson();
        }
    } catch (
    Exception e) {
        e.printStackTrace();
        return null;
    }

    public int create(CreateLessonJson lessonJson, String jwt) {
        List<LessonRoles> lessonRoles;
        LessonRoles lroles = new LessonRoles();
        Lessons lessons = new Lessons();
        User user;
        School school;
        try {
            LOGGER.info("Initialize Lesson Entity");
            lroles.setName(lessonJson.getName());
            lessons.setSettings(lessonSettingsRepo.getOne(lessonJson.getSettings()));
            lessons.setState(0);
            lessons.setRoom(lessonJson.getRoom());
            try {
                if (!jwtUtil.isTokenExpired(jwt)) {
                    LOGGER.info("JWT was expired");
                    return 2;
                }
                LOGGER.info("Check if Teacher was send to API");
                if (!lessonJson.getTeacher().isEmpty() && lessonJson.getTeacher() != null) {
                    LOGGER.info("Teacher is set");
                    lessons.setTeacher(userRepo.getOne(lessonJson.getTeacher()));
                }
                LOGGER.info("Set school");
                lessons.setSchool((school = schoolRepo.getOne(jwtUtil.extractSpecialClaim(jwt, "schoolid"))));
                lroles.setSchool(school);
            } catch (Exception e) {
                LOGGER.error("Create Lesson failed", e.fillInStackTrace());
                e.printStackTrace();
                return 1;
            }
            LOGGER.info("Save Lessonrole and set to Lesson");
            lessonRolesRepo.save(lroles);
            lessons.setLessonRoles(lroles);
            LOGGER.info("Save Lesson");
            lessonRepo.save(lessons);
            LOGGER.info("Set Lessonrole to Users");
            for (String userid : lessonJson.getUser()) {
                user = userRepo.getOne(userid);
                lessonRoles = user.getLessonRoles();
                lessonRoles.add(lroles);
                user.setLessonRoles(lessonRoles);
            }
            return 0;
        } catch (Exception e) {
            LOGGER.error("Create Lesson failed", e.fillInStackTrace());
            return 1;
        }
    }
    */

