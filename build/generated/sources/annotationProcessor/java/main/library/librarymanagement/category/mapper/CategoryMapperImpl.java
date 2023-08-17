package library.librarymanagement.category.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import library.librarymanagement.category.dto.CategoryDto.Patch;
import library.librarymanagement.category.dto.CategoryDto.Post;
import library.librarymanagement.category.dto.CategoryDto.Response;
import library.librarymanagement.category.entity.Category;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-15T22:06:09+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 11.0.18 (Azul Systems, Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category categoryPostDtotoCategory(Post categoryPostDto) {
        if ( categoryPostDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setCategoryName( categoryPostDto.getCategoryName() );

        return category;
    }

    @Override
    public Category categoryPatchDtoToCategory(Patch categoryPatchDto) {
        if ( categoryPatchDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setCategoryName( categoryPatchDto.getCategoryName() );

        return category;
    }

    @Override
    public Response categoryToCategoryResponseDto(Category category) {
        if ( category == null ) {
            return null;
        }

        Response response = new Response();

        if ( category.getCategoryId() != null ) {
            response.setCategoryId( category.getCategoryId() );
        }
        response.setCategoryName( category.getCategoryName() );

        return response;
    }

    @Override
    public List<Response> categoryToCategoryResponseDtos(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<Response> list = new ArrayList<Response>( categories.size() );
        for ( Category category : categories ) {
            list.add( categoryToCategoryResponseDto( category ) );
        }

        return list;
    }
}
