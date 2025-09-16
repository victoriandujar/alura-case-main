package br.com.alura.projeto.registrations;

import br.com.alura.projeto.course.models.Course;
import br.com.alura.projeto.course.models.enums.CourseStatusEnum;
import br.com.alura.projeto.course.repositories.CourseRepository;
import br.com.alura.projeto.registration.dtos.NewRegistrationDTO;
import br.com.alura.projeto.registration.repositories.RegistrationRepository;
import br.com.alura.projeto.user.Role;
import br.com.alura.projeto.user.User;
import br.com.alura.projeto.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

        student = new User("João", "joao@test.com", Role.STUDENT, "123456");
        userRepository.save(student);

        course = new Course();
        course.setName("Curso Teste");
        course.setCode("TEST");
        courseRepository.save(course);
    }

    @Test
    void shouldCreateRegistrationSuccessfully() throws Exception {
        NewRegistrationDTO dto = new NewRegistrationDTO();
        dto.setUserId(student.getId());
        dto.setCourseId(course.getId());

        mockMvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Matrícula realizada com sucesso."));
    }

    @Test
    void shouldReturnNotFoundWhenUserDoesNotExist() throws Exception {
        NewRegistrationDTO dto = new NewRegistrationDTO();
        dto.setUserId(999L);
        dto.setCourseId(course.getId());

        mockMvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNotFoundWhenCourseDoesNotExist() throws Exception {
        NewRegistrationDTO dto = new NewRegistrationDTO();
        dto.setUserId(student.getId());
        dto.setCourseId(999L);

        mockMvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }
}
