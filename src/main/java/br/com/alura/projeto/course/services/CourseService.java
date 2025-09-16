package br.com.alura.projeto.course.services;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryRepository;
import br.com.alura.projeto.course.dtos.CourseDTO;
import br.com.alura.projeto.course.dtos.CourseResponseDTO;
import br.com.alura.projeto.course.models.Course;
import br.com.alura.projeto.course.repositories.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

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

    public Page<CourseResponseDTO> listCourses(Pageable pageable) {
        return courseRepository.findAllWithCategory(pageable)
                .map(CourseResponseDTO::new);
    }

    @Transactional
    public void inactivateCourse(String code) {
        Course course = courseRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with code: " + code));

        course.inactivation();
        courseRepository.save(course);
    }

    public CourseDTO findCourseDTOById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com id: " + id));

        CourseDTO dto = new CourseDTO();

        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setCode(course.getCode());
        dto.setInstructor(course.getInstructor());
        dto.setDescription(course.getDescription());
        dto.setCategoryId(course.getCategory().getId());

        return dto;
    }

    public void updateCourse(Long id, CourseDTO dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com id: " + id));

        course.setName(dto.getName());
        course.setInstructor(dto.getInstructor());
        course.setDescription(dto.getDescription());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Categoria inválida"));
        course.setCategory(category);

        course.setCode(dto.getCode());

        courseRepository.save(course);
    }
}
