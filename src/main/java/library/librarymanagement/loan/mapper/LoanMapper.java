package library.librarymanagement.loan.mapper;

import library.librarymanagement.book.entity.Book;
import library.librarymanagement.loan.dto.LoanPatchDto;
import library.librarymanagement.loan.dto.LoanPostDto;
import library.librarymanagement.loan.dto.LoanResponseDto;
import library.librarymanagement.loan.entity.Loan;
import library.librarymanagement.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    default Loan loanPostDtoToLoan(LoanPostDto loanPostDto) {

        Loan loan = new Loan();
        Member member = new Member();
        Book book = new Book();

        member.setMemberId(loanPostDto.getMemberId());
        book.setBookId(loanPostDto.getBookId());

        loan.setMember(loanPostDto.getMember());
        loan.setBook(loanPostDto.getBook());

        return loan;
    }

    Loan loanPatchDtoToLoan(LoanPatchDto loanPatchDto);

    default LoanResponseDto loanToLoanResponseDto(Loan loan) {

        LoanResponseDto loanResponseDto = new LoanResponseDto();

        loanResponseDto.setLoanId(loan.getLoanId());
        loanResponseDto.setMemberId(loan.getMember().getMemberId());
        loanResponseDto.setBookId(loan.getBook().getBookId());
        loanResponseDto.setLoanDate(loan.getLoanDate());
        loanResponseDto.setReturnDate(loan.getReturnDate());
        loanResponseDto.setDueDate(loan.getDueDate());

        return loanResponseDto;
    }

    List<LoanResponseDto> loanToLoanResponseDtos(List<Loan> loan);

}
