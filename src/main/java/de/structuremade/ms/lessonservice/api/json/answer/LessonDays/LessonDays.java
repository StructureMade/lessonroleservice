package de.structuremade.ms.lessonservice.api.json.answer.LessonDays;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LessonDays {
    private String name;
    private int state;
    private String teacher;
    private String room;
    private int settings;
    private String substituteTeacher;
    private String substituteRoom;
    private List<String> times;


    public LessonDays(Lessons lesson, LessonSubstitutes lessonSubstitutes, String name, User teacher, User substituteTeacher, List<String> times, int settings) {
        this.name = name;
        this.state = lesson.getState();
        this.room = lesson.getRoom();
        this.teacher = teacher.getAbbreviation();
        this.substituteTeacher = substituteTeacher.getAbbreviation();
        this.substituteRoom = lessonSubstitutes.getSubstituteRoom();
        this.settings = settings;
        this.times.addAll(times);


    }
}
