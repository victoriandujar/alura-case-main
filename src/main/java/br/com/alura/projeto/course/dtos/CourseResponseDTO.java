package br.com.alura.projeto.course.dtos;

import br.com.alura.projeto.course.models.Course;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class CourseResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String code;
    private String instructor;
    private String category;
    private String description;
    private String status;
    private LocalDate inactivation_date;

    public CourseResponseDTO(@org.jetbrains.annotations.NotNull Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.code = course.getCode();
        this.instructor = course.getInstructor();
        this.category = course.getCategory();
        this.description = course.getDescription();
        this.status = course.getStatus().name();
        this.inactivation_date = course.getInactivationDate();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCode() { return code; }
    public String getInstructor() { return instructor; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public LocalDate getInactivationDate() { return inactivation_date; }
}
