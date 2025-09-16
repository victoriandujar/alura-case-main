package br.com.alura.projeto.registration.repositories;
import br.com.alura.projeto.registration.interfaces.IRegistrationReport;
import br.com.alura.projeto.registration.models.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);

    @Query(value = """
        SELECT
            c.name              AS courseName,
            c.code              AS courseCode,
            u.name              AS instructorName,
            u.email             AS instructorEmail,
            COUNT(r.course_id)  AS totalRegistrations
        FROM
            registration r
        JOIN
            course c ON r.course_id = c.id
        JOIN
            User u ON c.instructor_id = u.id
        GROUP BY
            c.id, u.id
        ORDER BY
            totalRegistrations DESC
    """, nativeQuery = true)
    List<IRegistrationReport> findRegistrationReport();
}