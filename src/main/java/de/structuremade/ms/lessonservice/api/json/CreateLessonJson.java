package de.structuremade.ms.lessonservice.api.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateLessonJson {
    private int settings;
    @NotNull(message = "name is required")
    private String name;
    private String teacher;
    private String room;
    private String classId;
    private List<String> user;
}
