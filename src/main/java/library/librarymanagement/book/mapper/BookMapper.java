package library.librarymanagement.book.mapper;

import library.librarymanagement.book.dto.BookPatchDto;
import library.librarymanagement.book.dto.BookPostDto;
import library.librarymanagement.book.dto.BookResponseDto;
import library.librarymanagement.book.entity.Book;
import library.librarymanagement.category.entity.Category;
import library.librarymanagement.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    default Book bookPostDtoToBook(BookPostDto bookPostDto) {

        Book book = new Book();
        Member member = new Member();
        Category category = new Category();

        member.setMemberId(bookPostDto.getMemberId());
        category.setCategoryId(bookPostDto.getCategoryId());

        book.setBookName(bookPostDto.getBookName());
        book.setAuthor(bookPostDto.getAuthor());
        book.setPublisher(bookPostDto.getPublisher());
        book.setBookContent(bookPostDto.getBookContent());
        book.setBookIndex(bookPostDto.getBookIndex());

        book.setMember(member);
        book.setCategory(category);

        return book;
    }

    Book bookPatchDtoToBook(BookPatchDto bookPatchDto);

    default BookResponseDto bookToBookResponseDto(Book book) {

        BookResponseDto bookResponseDto = new BookResponseDto();

        bookResponseDto.setBookId(book.getBookId());
        bookResponseDto.setMember(book.getMember());
        bookResponseDto.setCategory(book.getCategory());
        bookResponseDto.setBookName(book.getBookName());
        bookResponseDto.setAuthor(book.getAuthor());
        bookResponseDto.setPublisher(book.getPublisher());
        bookResponseDto.setBookContent(book.getBookContent());
        bookResponseDto.setBookIndex(book.getBookIndex());
        bookResponseDto.setBookStatus(book.getBookStatus());
        bookResponseDto.setCreatedAt(book.getCreatedAt());
        bookResponseDto.setModifiedAt(book.getModifiedAt());

        return bookResponseDto;
    }

    List<BookResponseDto> bookToBookResponseDtos(List<Book> books);

}
