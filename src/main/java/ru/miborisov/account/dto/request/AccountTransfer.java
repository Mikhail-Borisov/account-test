package ru.miborisov.account.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountTransfer {
    public final String source;
    public final String target;
    public final long amount;

    @JsonCreator
    public AccountTransfer(
            @JsonProperty(value="source", required = true) String source,
            @JsonProperty(value="target", required = true) String target,
            @JsonProperty(value="amount", required = true) long amount
    ) {
        this.source = source;
        this.target = target;
        this.amount = amount;
    }
}
