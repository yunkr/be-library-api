package library.librarymanagement.inquiry.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInquiry is a Querydsl query type for Inquiry
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquiry extends EntityPathBase<Inquiry> {

    private static final long serialVersionUID = 665870886L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInquiry inquiry = new QInquiry("inquiry");

    public final library.librarymanagement.audit.QAuditable _super = new library.librarymanagement.audit.QAuditable(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> inquiryId = createNumber("inquiryId", Long.class);

    public final library.librarymanagement.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath title = createString("title");

    public QInquiry(String variable) {
        this(Inquiry.class, forVariable(variable), INITS);
    }

    public QInquiry(Path<? extends Inquiry> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInquiry(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInquiry(PathMetadata metadata, PathInits inits) {
        this(Inquiry.class, metadata, inits);
    }

    public QInquiry(Class<? extends Inquiry> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new library.librarymanagement.member.entity.QMember(forProperty("member")) : null;
    }

}

