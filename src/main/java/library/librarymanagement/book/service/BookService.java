package library.librarymanagement.book.service;

import library.librarymanagement.book.entity.Book;
import library.librarymanagement.book.repository.BookRepository;
import library.librarymanagement.exception.BusinessLogicException;
import library.librarymanagement.exception.ExceptionCode;
import library.librarymanagement.member.entity.Member;
import library.librarymanagement.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final MemberService memberService;

    public BookService(BookRepository bookRepository, MemberService memberService) {
        this.bookRepository = bookRepository;
        this.memberService = memberService;
    }

    public Book createBook(Book book) {

        verifyBook(book);

        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {

        Book findbook = findBook(book.getBookId());

        Optional.ofNullable(book.getBookName())
                .ifPresent(bookName -> findbook.setBookName(bookName));

        Optional.ofNullable(book.getAuthor())
                .ifPresent(author -> findbook.setAuthor(author));

        Optional.ofNullable(book.getPublisher())
                .ifPresent(publisher -> findbook.setPublisher(publisher));

        Optional.ofNullable(book.getBookContent())
                .ifPresent(bookContent -> findbook.setBookContent(bookContent));

        Optional.ofNullable(book.getBookIndex())
                .ifPresent(bookIndex -> findbook.setBookIndex(bookIndex));

        return bookRepository.save(findbook);
    }

    public Book findBook(long bookId) {

        Book findBook = bookRepository.findById(bookId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.BOOK_NOT_FOUND));

        return findBook;
    }

    public Page<Book> findBooks(int page, int size) {

        return bookRepository.findAll(PageRequest.of(page, size));
    }

    public Book deleteBook(long bookId) {
        Book findBook = findBook(bookId);
        bookRepository.delete(findBook);

        return findBook;
    }


    // member 가 존재하는지 확인
    public void verifyBook(Book book) {

        Member member = memberService.findMember(book.getMember().getMemberId());
        book.setMember(member);

    }

}
