package br.com.alura.projeto.category.dtos;
import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.course.dtos.CourseInfoDTO;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryWithCoursesDTO {
    private final String name;
    private final String code;
    private final String color;
    private final List<CourseInfoDTO> courses;

    public CategoryWithCoursesDTO(Category category) {
        this.name = category.getName();
        this.code = category.getCode();
        this.color = category.getColor();
        this.courses = category.getCourses().stream()
                .map(CourseInfoDTO::new)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<CourseInfoDTO> getCourses() {
        return courses;
    }

    public String getColor() {
        return color;
    }
}