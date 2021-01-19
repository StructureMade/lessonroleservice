package de.structuremade.ms.lessonservice.api.routes;

import com.google.gson.Gson;
import de.structuremade.ms.lessonservice.api.json.CreateLessonJson;
import de.structuremade.ms.lessonservice.api.json.GetLessonJson;
import de.structuremade.ms.lessonservice.api.json.answer.GetMyLessonsJson;
import de.structuremade.ms.lessonservice.api.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/service/lesson")
public class LessonRoutes {

    @Autowired
    LessonService service;

    @PostMapping("create")
    public void createLesson(@RequestBody CreateLessonJson lessonJson, HttpServletRequest request, HttpServletResponse response) {
        switch (service.create(lessonJson, request.getHeader("Authorization").substring(7))) {
            case 0 -> response.setStatus(HttpStatus.CREATED.value());
            case 1 -> response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            case 2 -> response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    @GetMapping("/getmy")
    public Object getAllMyLessons(@RequestBody GetLessonJson lessonJson, HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();
        GetMyLessonsJson glj = (GetMyLessonsJson) service.getUserLessons(lessonJson,request.getHeader("Authorization").substring(7));
        if (glj != null) {
            if (glj.getMonday() != null) {
                response.setStatus(HttpStatus.ACCEPTED.value());
                return gson.toJson(glj);
            } else {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                return null;
            }
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return null;
        }
    }
}
