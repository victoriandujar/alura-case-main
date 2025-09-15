package br.com.alura.projeto.course.dtos;

import br.com.alura.projeto.course.models.Course;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

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
    private Date inactivation_date;

    public CourseResponseDTO(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.code = course.getCode();
        this.instructor = course.getInstructor();
        this.category = course.getCategory() != null ? course.getCategory().getName() : null;
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
    public Date getInactivationDate() { return inactivation_date; }
}
