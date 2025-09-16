package br.com.alura.projeto.registration;

import br.com.alura.projeto.course.models.Course;
import br.com.alura.projeto.course.repositories.CourseRepository;
import br.com.alura.projeto.registration.dtos.NewRegistrationDTO;
import br.com.alura.projeto.registration.models.Registration;
import br.com.alura.projeto.registration.repositories.RegistrationRepository;
import br.com.alura.projeto.registration.services.RegistrationService;
import br.com.alura.projeto.user.User;
import br.com.alura.projeto.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private RegistrationRepository registrationRepository;

    @InjectMocks
    private RegistrationService registrationService;

    @Test
    void createRegistration_shouldThrowException_whenCourseIsInactive() {
        NewRegistrationDTO dto = new NewRegistrationDTO();
        dto.setUserId(1L);
        dto.setCourseId(1L);

        User user = new User();
        Course inactiveCourse = new Course();
        inactiveCourse.inactivation();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(courseRepository.findById(1L)).thenReturn(Optional.of(inactiveCourse));

        assertThrows(IllegalArgumentException.class, () -> {
            registrationService.createRegistration(dto);
        });

        verify(registrationRepository, never()).save(any(Registration.class));
    }
}