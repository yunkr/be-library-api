package library.librarymanagement.category.service;

import library.librarymanagement.category.entity.Category;
import library.librarymanagement.category.mapper.CategoryMapper;
import library.librarymanagement.category.repository.CategoryRepository;
import library.librarymanagement.exception.BusinessLogicException;
import library.librarymanagement.exception.ExceptionCode;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {

        return categoryRepository.save(category);
    }

    public Category findCategory(long categoryId) {

        Category findCategory = categoryRepository.findById(categoryId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND));

        return findCategory;
    }
}
