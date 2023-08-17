package library.librarymanagement.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    BOOK_NOT_FOUND(404, "Book not found"),
    LOAN_NOT_FOUND(404, "Loan not found"),
    BOOK_ALREADY_ON_LOAN(409, "The book is already on loan"),
    CATEGORY_NOT_FOUND(404, "Category not found"),
    LOAN_ALREADY_RETURNED(409, "Loan has already been returned"),
    CANNOT_BORROW_BOOKS(409, "Can't borrow books"),
    DISPLAYNAME_EXISTS(409, "DisplayName exists"),
    INQUIRY_NOT_FOUND(404, "Inquiry not found"),
    EMAIL_EXISTS(409,"Email exists"),
    INVALID_VERIFICATION_CODE(403, "The code does not match"),
    SEARCH_NOT_FOUND(404, "Search not found"),
    USER_INPUT_ERROR(401, "Request is invalid"),
    TOKEN_EXPIRED(403, "Token has expired"),
    LOGIN_NOT_REQUIRED(401, "Login is required.");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}