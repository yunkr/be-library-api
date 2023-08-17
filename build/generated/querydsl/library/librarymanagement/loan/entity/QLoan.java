package library.librarymanagement.loan.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLoan is a Querydsl query type for Loan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLoan extends EntityPathBase<Loan> {

    private static final long serialVersionUID = -431788288L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLoan loan = new QLoan("loan");

    public final library.librarymanagement.audit.QAuditable _super = new library.librarymanagement.audit.QAuditable(this);

    public final library.librarymanagement.book.entity.QBook book;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> dueDate = createDateTime("dueDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> loanDate = createDateTime("loanDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> loanId = createNumber("loanId", Long.class);

    public final library.librarymanagement.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final DateTimePath<java.time.LocalDateTime> returnDate = createDateTime("returnDate", java.time.LocalDateTime.class);

    public QLoan(String variable) {
        this(Loan.class, forVariable(variable), INITS);
    }

    public QLoan(Path<? extends Loan> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLoan(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLoan(PathMetadata metadata, PathInits inits) {
        this(Loan.class, metadata, inits);
    }

    public QLoan(Class<? extends Loan> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new library.librarymanagement.book.entity.QBook(forProperty("book"), inits.get("book")) : null;
        this.member = inits.isInitialized("member") ? new library.librarymanagement.member.entity.QMember(forProperty("member")) : null;
    }

}

