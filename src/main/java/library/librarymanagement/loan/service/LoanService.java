package library.librarymanagement.loan.service;

import library.librarymanagement.book.entity.Book;
import library.librarymanagement.book.service.BookService;
import library.librarymanagement.exception.BusinessLogicException;
import library.librarymanagement.exception.ExceptionCode;
import library.librarymanagement.loan.entity.Loan;
import library.librarymanagement.loan.repository.LoanRepository;
import library.librarymanagement.member.entity.Member;
import library.librarymanagement.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final MemberService memberService;
    private final BookService bookService;

    public LoanService(LoanRepository loanRepository, MemberService memberService, BookService bookService) {
        this.loanRepository = loanRepository;
        this.memberService = memberService;
        this.bookService = bookService;
    }

    public Loan creatLoan(Loan loan) {

        verifyLoanAndCheckAvailability(loan);

        // 대출 상태 변경
        Book book = loan.getBook();
        book.setBookStatus(Book.BookStatus.ON_BOOK_LOAN);
        bookService.updateBook(book);

        return loanRepository.save(loan);
    }

    public Loan updateLoan(Loan loan) {

        Loan findLoan = findLoan(loan.getLoanId());

        /*
        Optional.ofNullable(loan.getLoanStatus())
                .ifPresent(loanStatus -> findLoan.setLoanStatus(loanStatus));

         */

        return loanRepository.save(findLoan);
    }

    public Loan findLoan(long loanId) {

        Loan findLoan = loanRepository.findById(loanId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.LOAN_NOT_FOUND));

        return findLoan;
    }

    public Page<Loan> findLoans(int page, int size) {

        return loanRepository.findAll(PageRequest.of(page, size));
    }

    /*
    // returnLoan
    public Loan deleteLoan(long loanId) {

        Loan findLoan = findLoan(loanId);

        // 이미 반납되었는지 확인
        if (findLoan.getReturnDate() != null) {
            throw new BusinessLogicException(ExceptionCode.LOAN_ALREADY_RETURNED);
        }

        // 반납 처리
        findLoan.markAsReturned();

        // 책의 대출 상태 변경
        Book book = findLoan.getBook();
        book.setBookStatus(Book.BookStatus.BOOK_LOAN_AVAILABLE);
        bookService.updateBook(book);

        return loanRepository.save(findLoan);
    }
     */

    // returnLoan
    public Loan deleteLoan(Long memberId, long loanId) {

        memberService.findMember(memberId);
        Loan findLoan = findLoan(loanId);

        // 이미 반납되었는지 확인
        if (findLoan.getReturnDate() != null) {
            throw new BusinessLogicException(ExceptionCode.LOAN_ALREADY_RETURNED);
        }

        // 반납 처리
        findLoan.markAsReturned();

        // 책의 대출 상태 변경
        Book book = findLoan.getBook();
        book.setBookStatus(Book.BookStatus.BOOK_LOAN_AVAILABLE);
        bookService.updateBook(book);

        //return loanRepository.save(findLoan);

        loanRepository.delete(findLoan);

        return findLoan;

    }

    // member, book 존재 확인, book 상태 확인
    public void verifyLoanAndCheckAvailability(Loan loan) {
        Member member = memberService.findMember(loan.getMember().getMemberId());
        loan.setMember(member);

        Book book = bookService.findBook(loan.getBook().getBookId());

        if (!memberService.canBorrowBooks(member)) {
            throw new BusinessLogicException(ExceptionCode.CANNOT_BORROW_BOOKS);
        }

        if (book.getBookStatus() == Book.BookStatus.ON_BOOK_LOAN) {
            throw new BusinessLogicException(ExceptionCode.BOOK_ALREADY_ON_LOAN);
        }

        // 대출 가능한 상태로 변경
        book.setBookStatus(Book.BookStatus.ON_BOOK_LOAN);
        loan.setBook(book);
    }

    // 사용자의 대출 히스토리
    public Page<Loan> getMyLoanHistoryByMemberId(Long memberId, int page, int size) {

        Member member = memberService.findMember(memberId);
        Pageable pageable = PageRequest.of(page, size, Sort.by("loanId").descending());

        return loanRepository.findByMember(member, pageable);
    }
}
