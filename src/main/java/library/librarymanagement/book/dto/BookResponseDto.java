package library.librarymanagement.book.dto;

import library.librarymanagement.book.entity.Book;
import library.librarymanagement.category.entity.Category;
import library.librarymanagement.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BookResponseDto {

    private Long bookId;

    @Setter(AccessLevel.NONE)       // AccessLevel : 접근권한
    private Long memberId;

    private Long categoryId;

    private String categoryName;

    private String bookName;

    private String author;

    private String publisher;

    private String bookContent;

    private String bookIndex;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Book.BookStatus bookStatus;

    public void setMember(Member member) {
        if (member != null) {
            this.memberId = member.getMemberId();
        }
    }

    public void setCategory(Category category) {
        if (category != null) {
            this.categoryId = category.getCategoryId();
            this.categoryName = category.getCategoryName();
        }
    }

}
