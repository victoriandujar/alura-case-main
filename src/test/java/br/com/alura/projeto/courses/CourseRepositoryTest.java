package br.com.alura.projeto.courses;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryRepository;
import br.com.alura.projeto.course.models.Course;
import br.com.alura.projeto.course.repositories.CourseRepository;
import br.com.alura.projeto.user.User;
import br.com.alura.projeto.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndFindCourse() {
        Category category = new Category("Cursos", "CURS1", "#FF0000", 1);
        categoryRepository.save(category);

        User instructor = new User();
        instructor.setName("Professor Teste");
        instructor.setEmail("prof@test.com");
        userRepository.save(instructor);

        Course course = new Course();
        course.setName("Curso Teste");
        course.setCode("TEST1");
        course.setCategory(category);
        course.setInstructor(instructor);

        courseRepository.save(course);

        List<Course> courses = courseRepository.findAll();
        assertThat(courses).hasSize(1);
        assertThat(courses.get(0).getName()).isEqualTo("Curso Teste");
    }
}
