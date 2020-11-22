package ru.miborisov.account.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountTransfer {
    public final String sourceEmail;
    public final String targetEmail;
    public final long amount;

    @JsonCreator
    public AccountTransfer(
            @JsonProperty(value="source", required = true) String sourceEmail,
            @JsonProperty(value="target", required = true) String targetEmail,
            @JsonProperty(value="amount", required = true) long amount
    ) {
        this.sourceEmail = sourceEmail;
        this.targetEmail = targetEmail;
        this.amount = amount;
    }
}
