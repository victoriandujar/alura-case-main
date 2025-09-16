package br.com.alura.projeto.category;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryRepository;
import br.com.alura.projeto.category.dtos.CategoryWithCoursesDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public List<CategoryWithCoursesDTO> getCategoriesWithCourses() {
        List<Category> categoriesFromDb = categoryRepository.findAllWithCourses();

        return categoriesFromDb.stream()
                .map(CategoryWithCoursesDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateCategory(Long id, CategoryDTO form) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com id: " + id));

        category.setName(form.getName());
        category.setCode(form.getCode());
        category.setColor(form.getColor());
        category.setOrder(form.getOrder());
    }

}
