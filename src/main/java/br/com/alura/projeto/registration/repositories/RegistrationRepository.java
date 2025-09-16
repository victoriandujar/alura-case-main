package br.com.alura.projeto.registration.repositories;
import br.com.alura.projeto.registration.models.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);
}