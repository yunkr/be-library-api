package library.librarymanagement.book.controller;

import library.librarymanagement.book.dto.BookPatchDto;
import library.librarymanagement.book.dto.BookPostDto;
import library.librarymanagement.book.entity.Book;
import library.librarymanagement.book.mapper.BookMapper;
import library.librarymanagement.book.repository.BookRepository;
import library.librarymanagement.book.service.BookService;
import library.librarymanagement.dto.MultiResponseDto;
import library.librarymanagement.dto.SingleResponseDto;
import library.librarymanagement.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    private final static String BOOK_DEFAULT_URL = "/books";
    private final BookRepository bookRepository;
    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookRepository bookRepository, BookService bookService, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping
    public ResponseEntity<?> postBook(@Valid @RequestBody BookPostDto requestBody) {

        Book book = bookService.createBook(bookMapper.bookPostDtoToBook(requestBody));
        URI location = UriCreator.createUri(BOOK_DEFAULT_URL, book.getBookId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{book-id}")
    public ResponseEntity<?> patchBook(@PathVariable("book-id") @Positive long bookId,
                                       @Valid @RequestBody BookPatchDto requestBody) {

        requestBody.setBookId(bookId);
        Book book = bookService.updateBook(bookMapper.bookPatchDtoToBook(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(bookMapper.bookToBookResponseDto(book))
                , HttpStatus.OK);
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<?> getBook(@PathVariable("book-id") @Positive long bookId) {

        Book book = bookService.findBook(bookId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(bookMapper.bookToBookResponseDto(book))
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getBooks(@RequestParam int page, @RequestParam int size) {

        Page<Book> pageBooks = bookService.findBooks(page - 1, size);

        List<Book> books = pageBooks.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(bookMapper.bookToBookResponseDtos(books), pageBooks)
                , HttpStatus.OK);
    }

    @DeleteMapping("/{book-id}")
    public ResponseEntity<?> deleteBook(@PathVariable("book-id") @Positive long bookId) {

        bookService.deleteBook(bookId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
