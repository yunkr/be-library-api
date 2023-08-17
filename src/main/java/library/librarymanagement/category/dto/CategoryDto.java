package library.librarymanagement.category.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class CategoryDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {

        @NotBlank
        private String categoryName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {

        @NotBlank
        private String categoryName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {

        private long categoryId;
        private String categoryName;
    }
}
