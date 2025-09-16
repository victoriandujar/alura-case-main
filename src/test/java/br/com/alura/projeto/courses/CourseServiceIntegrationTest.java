package br.com.alura.projeto.courses;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryRepository;
import br.com.alura.projeto.course.dtos.CourseDTO;
import br.com.alura.projeto.course.repositories.CourseRepository;
import br.com.alura.projeto.course.services.CourseService;
import br.com.alura.projeto.user.User;
import br.com.alura.projeto.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class CourseServiceIntegrationTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCreateCourse() {
        Category category = new Category("Cursos", "CURS1", "#FF0000", 1);
        categoryRepository.save(category);

        User instructor = new User();
        instructor.setName("Prof Teste");
        instructor.setEmail("prof@test.com");
        userRepository.save(instructor);

        CourseDTO dto = new CourseDTO();
        dto.setName("Curso Teste");
        dto.setCode("CURS2");
        dto.setCategoryId(category.getId());
        dto.setInstructorId(instructor.getId());
        dto.setDescription("Descrição do curso");

        courseService.createCourse(dto);

        assertThat(courseRepository.findAll()).hasSize(1);
        assertThat(courseRepository.findAll().get(0).getName()).isEqualTo("Curso Teste");
    }

    @Test
    void shouldThrowWhenInstructorNotFound() {
        Category category = new Category("Cursos", "CURS1", "#FF0000", 1);
        categoryRepository.save(category);

        CourseDTO dto = new CourseDTO();
        dto.setName("Curso Teste");
        dto.setCode("CURS3");
        dto.setCategoryId(category.getId());
        dto.setInstructorId(999L);

        assertThrows(EntityNotFoundException.class, () -> courseService.createCourse(dto));
    }
}
