package library.librarymanagement.inquiry.entity;

import library.librarymanagement.audit.Auditable;
import library.librarymanagement.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "INQUIRY")
public class Inquiry extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryId;

    // 문의 글 제목
    @Column(nullable = false)
    private String title;

    // 문의 글 내용
    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
