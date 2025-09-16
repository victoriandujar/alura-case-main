package br.com.alura.projeto.course.controllers;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryService;
import br.com.alura.projeto.course.dtos.CourseDTO;
import br.com.alura.projeto.course.dtos.CourseResponseDTO;
import br.com.alura.projeto.course.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/admin/courses")
public class CourseController {

    private final CourseService courseService;
    private final CategoryService categoryService;

    public CourseController(CourseService courseService, CategoryService categoryService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
    }

    // Listagem de cursos
    @GetMapping
    public String list(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CourseResponseDTO> coursePage = courseService.listCourses(pageable);

        model.addAttribute("coursePage", coursePage);
        return "admin/courses/list";
    }

    // Formulário para criar novo curso
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("newCourse", new CourseDTO());

        List<Category> categories = categoryService.listCategories();
        model.addAttribute("categories", categories);

        return "admin/courses/newForm";
    }

    // Salvar novo curso
    @PostMapping("/new")
    public String save(@Valid @ModelAttribute("newCourse") CourseDTO courseDTO,
                       BindingResult result,
                       Model model,
                       RedirectAttributes redirectAttributes) {

        List<Category> categories = categoryService.listCategories(); // carrega apenas uma vez

        if (result.hasErrors()) {
            model.addAttribute("categories", categories);
            return "admin/courses/newForm";
        }

        try {
            courseService.createCourse(courseDTO);
        } catch (IllegalArgumentException e) {
            result.rejectValue("code", "error.newCourse", e.getMessage());
            model.addAttribute("categories", categories);
            return "admin/courses/newForm";
        }

        redirectAttributes.addFlashAttribute("successMessage", "Curso cadastrado com sucesso!");
        return "redirect:/admin/courses";
    }

    // Inativar curso
    @PostMapping("/{code}/inactive")
    public String inactivateCourse(@PathVariable String code, RedirectAttributes redirectAttributes) {
        courseService.inactivateCourse(code);
        redirectAttributes.addFlashAttribute("successMessageInactive", "Curso inativado com sucesso!");
        return "redirect:/admin/courses";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        CourseDTO courseDTO = courseService.findCourseDTOById(id);
        List<Category> categories = categoryService.listCategories();

        model.addAttribute("editCourse", courseDTO);
        model.addAttribute("categories", categories);

        return "admin/courses/update";
    }

    @PostMapping("/edit/{id}")
    public String updateCourse(@PathVariable Long id,
                               @Valid @ModelAttribute("editCourse") CourseDTO courseDTO,
                               BindingResult result,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        List<Category> categories = categoryService.listCategories();

        if (result.hasErrors()) {
            model.addAttribute("categories", categories);
            return "admin/courses/update";
        }

        try {
            courseService.updateCourse(id, courseDTO);
        } catch (IllegalArgumentException e) {
            result.rejectValue("code", "error.editCourse", e.getMessage());
            model.addAttribute("categories", categories);
            return "admin/courses/update";
        }

        redirectAttributes.addFlashAttribute("successEditMessage", "Curso atualizado com sucesso!");
        return "redirect:/admin/courses";
    }

}
