package br.com.alura.projeto.course.services;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryRepository;
import br.com.alura.projeto.course.dtos.CourseDTO;
import br.com.alura.projeto.course.dtos.CourseResponseDTO;
import br.com.alura.projeto.course.models.Course;
import br.com.alura.projeto.course.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;

    public CourseService(CourseRepository courseRepository, CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void createCourse(CourseDTO dto) {
        if (courseRepository.existsByCode(dto.getCode())) {
            throw new IllegalArgumentException("Course code already exists");
        }

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        Course course = new Course();
        course.setName(dto.getName());
        course.setCode(dto.getCode());
        course.setInstructor(dto.getInstructor());
        course.setCategory(category);
        course.setDescription(dto.getDescription());

        courseRepository.save(course);
    }

    public List<CourseResponseDTO> listCourses() {
        return courseRepository.findAll()
                .stream()
                .map(CourseResponseDTO::new)
                .collect(Collectors.toList());
    }
}
