package library.librarymanagement.loan.dto;

import library.librarymanagement.book.entity.Book;
import library.librarymanagement.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class LoanPostDto {

    @Positive
    private long memberId;

    @Positive
    private long bookId;

    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }

    public Book getBook() {
        Book book = new Book();
        book.setBookId(bookId);
        return book;
    }

}
