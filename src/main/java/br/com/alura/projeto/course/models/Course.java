package br.com.alura.projeto.course.models;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.course.models.enums.CourseStatusEnum;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, length = 10)
    private String code;

    @Column(nullable = false)
    private String instructor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseStatusEnum status = CourseStatusEnum.ACTIVE;

    private LocalDate inactivation_date;

    public Course() {}

    public Course(String name, String code, String instructor, Category category, String description) {
        this.name = name;
        this.code = code;
        this.instructor = instructor;
        this.category = category;
        this.description = description;
        this.status = CourseStatusEnum.ACTIVE;
    }

    public Long getId() {
        return id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public CourseStatusEnum getStatus() { return status; }

    public LocalDate getInactivationDate() { return inactivation_date; }

    public void inactivation() {
        this.status = CourseStatusEnum.INACTIVE;
        this.inactivation_date = LocalDate.now();
    }
}
