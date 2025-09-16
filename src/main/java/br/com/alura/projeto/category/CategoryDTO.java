package br.com.alura.projeto.category;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class CategoryDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "O código é obrigatório")
    @Length(min = 4, max = 10)
    private String code;

    @NotBlank(message = "A cor é obrigatória")
    private String color;

    @Min(value = 1, message = "A ordem deve ser no mínimo 1")
    private int order;

    public CategoryDTO() {
    }

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
        this.color = category.getColor();
        this.order = category.getOrder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getOrder() { return order; }
    public void setOrder(int order) { this.order = order; }
}