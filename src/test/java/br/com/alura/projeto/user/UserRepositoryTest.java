package br.com.alura.projeto.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static br.com.alura.projeto.user.Role.INSTRUCTOR;
import static br.com.alura.projeto.user.Role.STUDENT;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByRole_shouldReturnOnlyInstructors() {
        User instructor1 = new User("Charles", "charles@alura.com.br", STUDENT, "mudar123");
        User instructor2 = new User("Charles", "charles@alura.com.br", INSTRUCTOR, "mudar123");

        entityManager.persist(instructor1);
        entityManager.persist(instructor2);

        List<User> instructors = userRepository.findByRole(INSTRUCTOR);

        assertThat(instructors).hasSize(2);
        assertThat(instructors).contains(instructor1, instructor2);
    }
}