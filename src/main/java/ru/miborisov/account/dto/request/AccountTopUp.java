package ru.miborisov.account.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountTopUp {
    public final String email;
    public final long amount;

    @JsonCreator
    public AccountTopUp(
            @JsonProperty(value="email", required = true) String email,
            @JsonProperty(value="amount", required = true) long amount
    ) {
        this.email = email;
        this.amount = amount;
    }
}
