package library.librarymanagement.book.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBook extends EntityPathBase<Book> {

    private static final long serialVersionUID = -1524355726L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBook book = new QBook("book");

    public final library.librarymanagement.audit.QAuditable _super = new library.librarymanagement.audit.QAuditable(this);

    public final StringPath author = createString("author");

    public final StringPath bookContent = createString("bookContent");

    public final NumberPath<Long> bookId = createNumber("bookId", Long.class);

    public final StringPath bookIndex = createString("bookIndex");

    public final StringPath bookName = createString("bookName");

    public final EnumPath<Book.BookStatus> bookStatus = createEnum("bookStatus", Book.BookStatus.class);

    public final library.librarymanagement.category.entity.QCategory category;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final library.librarymanagement.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath publisher = createString("publisher");

    public QBook(String variable) {
        this(Book.class, forVariable(variable), INITS);
    }

    public QBook(Path<? extends Book> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBook(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBook(PathMetadata metadata, PathInits inits) {
        this(Book.class, metadata, inits);
    }

    public QBook(Class<? extends Book> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new library.librarymanagement.category.entity.QCategory(forProperty("category")) : null;
        this.member = inits.isInitialized("member") ? new library.librarymanagement.member.entity.QMember(forProperty("member")) : null;
    }

}

