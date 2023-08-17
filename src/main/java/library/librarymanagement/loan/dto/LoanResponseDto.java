package library.librarymanagement.loan.dto;

import library.librarymanagement.loan.entity.Loan;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LoanResponseDto {

    private long loanId;

    private long memberId;

    private long bookId;

    private LocalDateTime loanDate;

    private LocalDateTime returnDate;

    private LocalDateTime dueDate;

}
