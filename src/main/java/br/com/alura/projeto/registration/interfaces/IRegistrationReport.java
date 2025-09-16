package br.com.alura.projeto.registration.interfaces;

public interface IRegistrationReport {
    String getCourseName();
    String getCourseCode();
    String getInstructorName();
    String getInstructorEmail();
    Long getTotalRegistrations();
}
