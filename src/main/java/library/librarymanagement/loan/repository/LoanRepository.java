package library.librarymanagement.loan.repository;

import library.librarymanagement.loan.entity.Loan;
import library.librarymanagement.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    long countByMemberAndReturnDateIsNull(Member member);

    boolean existsByMemberAndDueDateBeforeAndReturnDateIsNull(Member member, LocalDateTime currentDate);

    // 사용자의 대출 히스토리
    Page<Loan> findByMember(Member member, Pageable pageable);

}
