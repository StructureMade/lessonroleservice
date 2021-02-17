package de.structuremade.ms.lessonservice.api.json;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
public class SetLessonsJson {
    @NotNull
    private String user;

    @NotNull
    private List<String> lessons;
}
