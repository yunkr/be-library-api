package library.librarymanagement.loan.controller;

import library.librarymanagement.book.dto.BookPostDto;
import library.librarymanagement.book.entity.Book;
import library.librarymanagement.dto.MultiResponseDto;
import library.librarymanagement.dto.SingleResponseDto;
import library.librarymanagement.loan.dto.LoanPatchDto;
import library.librarymanagement.loan.dto.LoanPostDto;
import library.librarymanagement.loan.entity.Loan;
import library.librarymanagement.loan.mapper.LoanMapper;
import library.librarymanagement.loan.repository.LoanRepository;
import library.librarymanagement.loan.service.LoanService;
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
@RequestMapping("/loans")
@Validated
public class LoanController {

    private final static String LOAN_DEFAULT_URL = "/loans";
    private final LoanRepository loanRepository;
    private final LoanService loanService;
    private final LoanMapper loanMapper;

    public LoanController(LoanRepository loanRepository, LoanService loanService, LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.loanService = loanService;
        this.loanMapper = loanMapper;
    }

    @PostMapping
    public ResponseEntity<?> postLoan(@Valid @RequestBody LoanPostDto requestBody) {

        Loan loan = loanService.creatLoan(loanMapper.loanPostDtoToLoan(requestBody));
        URI location = UriCreator.createUri(LOAN_DEFAULT_URL, loan.getLoanId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{loan-id}")
    public ResponseEntity<?> patchLoan(@PathVariable("loan-id") @Positive long loanId,
                                       @Valid @RequestBody LoanPatchDto requestBody) {

        requestBody.setLoanId(loanId);
        Loan loan = loanService.updateLoan(loanMapper.loanPatchDtoToLoan(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(loanMapper.loanToLoanResponseDto(loan))
                , HttpStatus.OK);
    }

    @GetMapping("/{loan-id}")
    public ResponseEntity<?> getLoan(@PathVariable("loan-id") @Positive long loanId) {

        Loan loan = loanService.findLoan(loanId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(loanMapper.loanToLoanResponseDto(loan))
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getLoans(@RequestParam int page, @RequestParam int size) {

        Page<Loan> pageLoans = loanService.findLoans(page - 1, size);

        List<Loan> loans = pageLoans.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(loanMapper.loanToLoanResponseDtos(loans), pageLoans)
                , HttpStatus.OK);
    }

    /*
    @DeleteMapping("/{loan-id}")
    public ResponseEntity<?> deleteLoan(@PathVariable("loan-id") @Positive long loanId) {

        loanService.deleteLoan(loanId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
     */

    @DeleteMapping("/{member-id}/{loan-id}")
    public ResponseEntity<?> deleteLoan(@PathVariable("member-id") @Positive long memberId,
                                        @PathVariable("loan-id") @Positive long loanId) {

        loanService.deleteLoan(memberId, loanId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 사용자의 대출 히스토리
    @GetMapping("/member/{member-id}")
    public ResponseEntity<?> getMyLoanHistory(@PathVariable("member-id") @Positive long memberId,
                                                   @Positive @RequestParam int page,
                                                   @Positive @RequestParam int size) {

        Page<Loan> pageLoans = loanService.getMyLoanHistoryByMemberId(memberId,page - 1, size);

        List<Loan> loans = pageLoans.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(loanMapper.loanToLoanResponseDtos(loans), pageLoans),
                HttpStatus.OK);

    }
}
