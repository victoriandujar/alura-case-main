package br.com.alura.projeto.registration;

import br.com.alura.projeto.registration.controllers.RegistrationController;
import br.com.alura.projeto.registration.dtos.NewRegistrationDTO;
import br.com.alura.projeto.registration.services.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegistrationService registrationService;

    @Test
    void createRegistration_shouldReturnCreated_whenDataIsValid() throws Exception {
        NewRegistrationDTO dto = new NewRegistrationDTO();
        dto.setUserId(1L);
        dto.setCourseId(1L);

        doNothing().when(registrationService).createRegistration(any(NewRegistrationDTO.class));

        mockMvc.perform(post("/registration/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }
}
