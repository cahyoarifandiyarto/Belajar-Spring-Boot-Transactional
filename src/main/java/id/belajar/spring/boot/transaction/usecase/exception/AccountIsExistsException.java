package id.belajar.spring.boot.transaction.usecase.exception;

public class AccountIsExistsException extends RuntimeException {

    public AccountIsExistsException(String message) {
        super(message);
    }

}
