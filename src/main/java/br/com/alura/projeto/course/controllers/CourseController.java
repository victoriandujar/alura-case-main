package br.com.alura.projeto.course.controllers;

import br.com.alura.projeto.course.dtos.CourseDTO;
import br.com.alura.projeto.course.dtos.CourseResponseDTO;
import br.com.alura.projeto.course.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String list(Model model) {
        List<CourseResponseDTO> courses = courseService.listCourses();
        model.addAttribute("courses", courses);
        return "admin/courses/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("newCourse", new CourseDTO());
        return "/admin/courses/newForm";
    }

    @PostMapping("/new")
    public String save(@Valid @ModelAttribute("newCourse") CourseDTO courseRequestDTO,
                       BindingResult result,
                       Model model) {
        if (result.hasErrors()) {
            return "/admin/courses/newForm";
        }
        courseService.createCourse(courseRequestDTO);
        return "redirect:/admin/courses";
    }

    @PostMapping("/{code}/inactive")
    public ResponseEntity<?> updateStatus(@PathVariable("code") String courseCode) {
        // TODO: implementar service.inactivateCourse(courseCode);
        return ResponseEntity.ok().build();
    }
}
