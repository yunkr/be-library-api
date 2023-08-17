package library.librarymanagement.category.controller;

import library.librarymanagement.category.dto.CategoryDto;
import library.librarymanagement.category.entity.Category;
import library.librarymanagement.category.mapper.CategoryMapper;
import library.librarymanagement.category.service.CategoryService;
import library.librarymanagement.dto.SingleResponseDto;
import library.librarymanagement.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@RestController
@RequestMapping("/categories")
@Validated
public class CategoryController {

    private final static String CATEGORY_DEFAULT_URL = "/categories";
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public ResponseEntity<?> postCategory(@Valid @RequestBody CategoryDto.Post requestBody) {

        Category category = categoryService.createCategory(categoryMapper.categoryPostDtotoCategory(requestBody));
        URI location = UriCreator.createUri(CATEGORY_DEFAULT_URL, category.getCategoryId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{category-id}")
    public ResponseEntity<?> getCategory(@PathVariable("category-id") @Positive long categoryId) {

        Category category = categoryService.findCategory(categoryId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(categoryMapper.categoryToCategoryResponseDto(category)), HttpStatus.OK);
    }

}
