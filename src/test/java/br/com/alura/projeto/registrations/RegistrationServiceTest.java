package br.com.alura.projeto.registrations;

import br.com.alura.projeto.course.models.Course;
import br.com.alura.projeto.course.models.enums.CourseStatusEnum;
import br.com.alura.projeto.course.repositories.CourseRepository;
import br.com.alura.projeto.registration.dtos.NewRegistrationDTO;
import br.com.alura.projeto.registration.repositories.RegistrationRepository;
import br.com.alura.projeto.registration.services.RegistrationService;
import br.com.alura.projeto.user.Role;
import br.com.alura.projeto.user.User;
import br.com.alura.projeto.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RegistrationServiceTest {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    private User student;
    private Course course;

    @BeforeEach
    void setup() {
        registrationRepository.deleteAll();
        courseRepository.deleteAll();
        userRepository.deleteAll();

        student = new User("Maria", "maria@test.com", Role.STUDENT, "123456");
        userRepository.save(student);

        course = new Course();
        course.setName("Curso Teste Service");
        course.setCode("SERV");
        courseRepository.save(course);
    }

    @Test
    void shouldCreateRegistration() {
        NewRegistrationDTO dto = new NewRegistrationDTO();
        dto.setUserId(student.getId());
        dto.setCourseId(course.getId());

        registrationService.createRegistration(dto);

        assertThat(registrationRepository.existsByUserIdAndCourseId(student.getId(), course.getId())).isTrue();
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        NewRegistrationDTO dto = new NewRegistrationDTO();
        dto.setUserId(999L);
        dto.setCourseId(course.getId());

        assertThrows(EntityNotFoundException.class, () -> registrationService.createRegistration(dto));
    }

    @Test
    void shouldThrowExceptionWhenCourseNotFound() {
        NewRegistrationDTO dto = new NewRegistrationDTO();
        dto.setUserId(student.getId());
        dto.setCourseId(999L);

        assertThrows(EntityNotFoundException.class, () -> registrationService.createRegistration(dto));
    }
}
