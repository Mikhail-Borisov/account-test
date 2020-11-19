package ru.miborisov.account.dto.response;

import ru.miborisov.account.model.Account;

public class AccountDetails {
    public final String email;
    public final long amount;

    private AccountDetails(String email, long amount) {
        this.email = email;
        this.amount = amount;
    }

    public static AccountDetails fromAccount(Account account) {
        return new AccountDetails(account.getEmail(), account.getAmount());
    }
}
