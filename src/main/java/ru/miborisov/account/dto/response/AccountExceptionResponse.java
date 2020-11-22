package ru.miborisov.account.dto.response;

import ru.miborisov.account.model.AccountException;

public class AccountExceptionResponse {
    public final String error;

    private AccountExceptionResponse(String error) {
        this.error = error;
    }

    public static AccountExceptionResponse fromAccountException(AccountException e) {
        return new AccountExceptionResponse(e.getMessage());
    }
}
