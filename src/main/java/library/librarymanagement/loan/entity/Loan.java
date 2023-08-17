package library.librarymanagement.loan.entity;

import library.librarymanagement.audit.Auditable;
import library.librarymanagement.book.entity.Book;
import library.librarymanagement.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/* ToDo
도서 조회(상세) API
도서 대출 API
도서 반납 API
사용자 삭제 -> 삭제 가능 기능 추가
 */

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "LOAN")
public class Loan extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    // 대출 일자
    @CreatedDate
    @Column(name = "loan_date", updatable = false)
    private LocalDateTime loanDate = LocalDateTime.now();

    // 실제 반납 일자
    @LastModifiedDate
    @Column(name = "return_date")
    private LocalDateTime returnDate;

    public void markAsReturned() {
        if (returnDate == null) {
            returnDate = LocalDateTime.now();
        }
    }

    // 반납 예정 일자
    @Column(nullable = false)
    private LocalDateTime dueDate;

    @PrePersist
    public void calculateDueDate() {
        if (loanDate != null) {
            dueDate = loanDate.plusDays(14);
        }
    }

//    @Column(nullable = false)
//    private String totalLoanQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

}
