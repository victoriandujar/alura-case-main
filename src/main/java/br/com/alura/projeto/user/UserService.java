package br.com.alura.projeto.user;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserListItemDTO> findAllInstructors() {
        List<User> instructors = userRepository.findByRole(Role.INSTRUCTOR);
        return instructors.stream()
                .map(UserListItemDTO::new)
                .toList();
    }
}