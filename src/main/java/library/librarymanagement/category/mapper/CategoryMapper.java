package library.librarymanagement.category.mapper;

import library.librarymanagement.category.dto.CategoryDto;
import library.librarymanagement.category.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category categoryPostDtotoCategory(CategoryDto.Post categoryPostDto);

    Category categoryPatchDtoToCategory(CategoryDto.Patch categoryPatchDto);


    CategoryDto.Response categoryToCategoryResponseDto(Category category);

    List<CategoryDto.Response> categoryToCategoryResponseDtos(List<Category> categories);

}
