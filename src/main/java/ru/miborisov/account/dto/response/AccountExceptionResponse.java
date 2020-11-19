package ru.miborisov.account.dto.response;

import ru.miborisov.account.model.AccountException;

public class AccountExceptionResponse {
    public final String exception;

    private AccountExceptionResponse(String exception) {
        this.exception = exception;
    }

    public static AccountExceptionResponse fromAccountException(AccountException e) {
        return new AccountExceptionResponse(e.getMessage());
    }
}
