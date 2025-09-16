package br.com.alura.projeto.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndFindUserByEmail() {
        User user = new User("Matheus", "matheus@test.com", Role.STUDENT, "123456");
        userRepository.save(user);

        assertThat(userRepository.existsByEmail("matheus@test.com")).isTrue();
    }

    @Test
    void shouldFindAllInstructors() {
        User instructor = new User("Instructor", "instructor@test.com", Role.INSTRUCTOR, "123456");
        userRepository.save(instructor);

        List<User> instructors = userRepository.findByRole(Role.INSTRUCTOR);
        assertThat(instructors).hasSize(1);
        assertThat(instructors.get(0).getEmail()).isEqualTo("instructor@test.com");
    }
}
