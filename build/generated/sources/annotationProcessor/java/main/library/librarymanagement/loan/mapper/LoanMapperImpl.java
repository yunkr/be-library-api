package library.librarymanagement.loan.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import library.librarymanagement.loan.dto.LoanPatchDto;
import library.librarymanagement.loan.dto.LoanResponseDto;
import library.librarymanagement.loan.entity.Loan;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-15T22:06:09+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 11.0.18 (Azul Systems, Inc.)"
)
@Component
public class LoanMapperImpl implements LoanMapper {

    @Override
    public Loan loanPatchDtoToLoan(LoanPatchDto loanPatchDto) {
        if ( loanPatchDto == null ) {
            return null;
        }

        Loan loan = new Loan();

        loan.setLoanId( loanPatchDto.getLoanId() );

        return loan;
    }

    @Override
    public List<LoanResponseDto> loanToLoanResponseDtos(List<Loan> loan) {
        if ( loan == null ) {
            return null;
        }

        List<LoanResponseDto> list = new ArrayList<LoanResponseDto>( loan.size() );
        for ( Loan loan1 : loan ) {
            list.add( loanToLoanResponseDto( loan1 ) );
        }

        return list;
    }
}
