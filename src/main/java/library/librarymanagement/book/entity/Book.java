package library.librarymanagement.book.entity;

import library.librarymanagement.audit.Auditable;
import library.librarymanagement.category.entity.Category;
import library.librarymanagement.loan.entity.Loan;
import library.librarymanagement.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "BOOK")
public class Book extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String bookContent;

    @Column(nullable = false)
    private String bookIndex;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus bookStatus = BookStatus.BOOK_LOAN_AVAILABLE;

    // 대출 상태
    public enum BookStatus {
        ON_BOOK_LOAN("책 대출 중"),
        BOOK_LOAN_AVAILABLE("책 대출 가능");

        @Getter
        private final String status;

        BookStatus(String status) {
            this.status = status;
        }

    }

}
