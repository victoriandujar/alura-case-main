package br.com.alura.projeto.registration.services;

import br.com.alura.projeto.course.models.Course;
import br.com.alura.projeto.course.models.enums.CourseStatusEnum;
import br.com.alura.projeto.course.repositories.CourseRepository;
import br.com.alura.projeto.registration.dtos.NewRegistrationDTO;
import br.com.alura.projeto.registration.models.Registration;
import br.com.alura.projeto.registration.repositories.RegistrationRepository;
import br.com.alura.projeto.user.User;
import br.com.alura.projeto.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public RegistrationService(RegistrationRepository registrationRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void createRegistration(NewRegistrationDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));

        if (course.getStatus() != CourseStatusEnum.ACTIVE) {
            throw new IllegalArgumentException("Não é possível se matricular em um curso inativo.");
        }

        if (registrationRepository.existsByUserIdAndCourseId(user.getId(), course.getId())) {
            throw new IllegalStateException("Usuário já matriculado neste curso.");
        }

        Registration newRegistration = new Registration(user, course);
        registrationRepository.save(newRegistration);
    }
}