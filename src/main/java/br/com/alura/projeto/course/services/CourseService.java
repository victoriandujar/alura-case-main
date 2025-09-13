package br.com.alura.projeto.course.services;

import br.com.alura.projeto.course.dtos.CourseDTO;
import br.com.alura.projeto.course.dtos.CourseResponseDTO;
import br.com.alura.projeto.course.models.Course;
import br.com.alura.projeto.course.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void createCourse(CourseDTO dto) {
        if (courseRepository.existsByCode(dto.getCode())) {
            throw new IllegalArgumentException("Course code already exists");
        }

        Course course = new Course(
                dto.getName(),
                dto.getCode(),
                dto.getInstructor(),
                dto.getCategory(),
                dto.getDescription()
        );

        Course savedCourse = courseRepository.save(course);
        new CourseResponseDTO(savedCourse);
    }

    public List<CourseResponseDTO> listCourses() {
        return courseRepository.findAll()
                .stream()
                .map(CourseResponseDTO::new)
                .collect(Collectors.toList());
    }
}
