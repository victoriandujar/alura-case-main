package br.com.alura.projeto.courses;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryRepository;
import br.com.alura.projeto.course.dtos.CourseDTO;
import br.com.alura.projeto.course.models.Course;
import br.com.alura.projeto.course.repositories.CourseRepository;
import br.com.alura.projeto.course.services.CourseService;
import br.com.alura.projeto.user.User;
import br.com.alura.projeto.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    void createCourse_shouldThrowException_whenInstructorIsNotFound() {
        CourseDTO dto = new CourseDTO();
        dto.setInstructorId(99L);
        dto.setCategoryId(1L);

        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            courseService.createCourse(dto);
        });
    }

    @Test
    void createCourse_shouldSaveCourse_whenDataIsValid() {
        CourseDTO dto = new CourseDTO();
        dto.setName("Java Básico");
        dto.setCode("java-bsc");
        dto.setInstructorId(1L);
        dto.setCategoryId(1L);

        User instructor = new User();
        Category category = new Category();

        when(userRepository.findById(1L)).thenReturn(Optional.of(instructor));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        courseService.createCourse(dto);

        verify(courseRepository, times(1)).save(any(Course.class));
    }
}