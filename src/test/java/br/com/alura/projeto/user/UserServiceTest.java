package br.com.alura.projeto.user;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Test
    void shouldReturnAllInstructors() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        User instructor = new User("Instructor", "inst@test.com", Role.INSTRUCTOR, "123456");
        when(userRepository.findByRole(Role.INSTRUCTOR)).thenReturn(List.of(instructor));

        List<UserListItemDTO> result = userService.findAllInstructors();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEmail()).isEqualTo("inst@test.com");
    }
}
