package de.structuremade.ms.lessonservice.api.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateLessonJson {
    private String name;
    private String start;
    private String end;
    private String day;
    private List<String> user;
}
