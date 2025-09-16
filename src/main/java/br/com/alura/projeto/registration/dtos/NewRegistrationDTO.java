package br.com.alura.projeto.registration.dtos;
import jakarta.validation.constraints.NotNull;

public class NewRegistrationDTO {
    @NotNull
    private Long userId;

    @NotNull
    private Long courseId;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
}