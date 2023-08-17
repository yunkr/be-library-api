package library.librarymanagement.book.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import library.librarymanagement.book.dto.BookPatchDto;
import library.librarymanagement.book.dto.BookResponseDto;
import library.librarymanagement.book.entity.Book;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-15T22:06:09+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 11.0.18 (Azul Systems, Inc.)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book bookPatchDtoToBook(BookPatchDto bookPatchDto) {
        if ( bookPatchDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setBookId( bookPatchDto.getBookId() );
        book.setBookName( bookPatchDto.getBookName() );
        book.setAuthor( bookPatchDto.getAuthor() );
        book.setPublisher( bookPatchDto.getPublisher() );
        book.setBookContent( bookPatchDto.getBookContent() );
        book.setBookIndex( bookPatchDto.getBookIndex() );

        return book;
    }

    @Override
    public List<BookResponseDto> bookToBookResponseDtos(List<Book> books) {
        if ( books == null ) {
            return null;
        }

        List<BookResponseDto> list = new ArrayList<BookResponseDto>( books.size() );
        for ( Book book : books ) {
            list.add( bookToBookResponseDto( book ) );
        }

        return list;
    }
}
