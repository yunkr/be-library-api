package library.librarymanagement.loan.dto;

import library.librarymanagement.loan.entity.Loan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LoanPatchDto {

    @Positive
    private long loanId;

    //private Loan.LoanStatus loanStatus;

}
