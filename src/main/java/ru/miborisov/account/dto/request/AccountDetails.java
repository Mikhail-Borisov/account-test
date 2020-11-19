package ru.miborisov.account.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDetails {
    public final String email;

    @JsonCreator
    public AccountDetails(
            @JsonProperty(value="email", required = true) String email
    ) {
        this.email = email;
    }
}
