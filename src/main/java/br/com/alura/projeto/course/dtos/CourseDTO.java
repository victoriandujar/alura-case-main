package br.com.alura.projeto.course.dtos;
import jakarta.validation.constraints.*;

import java.io.Serial;
import java.io.Serializable;

public class CourseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Inform the name of the course")
    private String name;

    @NotBlank(message = "Inform the course code")
    @Pattern(regexp = "^[a-zA-Z]+(-[a-zA-Z]+)*$", message = "Invalid code. Only letters and hyphens are allowed, no spaces or numbers.")
    @Size(min = 4, max = 10, message = "The code must be between 4 and 10 characters long.")
    private String code;

    @NotBlank(message = "Inform the instructor")
    private String instructor;

    @NotBlank(message = "Enter the category")
    private String category;

    private String description;

    public CourseDTO() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
