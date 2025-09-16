package br.com.alura.projeto.course.dtos;
import br.com.alura.projeto.course.models.Course;

public class CourseInfoDTO {
    private final String name;
    private final String code;
    private final String instructor;

    public CourseInfoDTO(Course course) {
        this.name = course.getName();
        this.code = course.getCode();
        this.instructor = course.getInstructor();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getInstructor() {
        return instructor;
    }
}