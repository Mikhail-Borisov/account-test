package ru.miborisov.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Account {
    /**
     * Used as ID. Case-sensitive
     */
    private final String email;
    private long amount;

    public void increment(long byAmount) {
        this.amount += byAmount;
    }

    public void decrement(long byAmount) {
        this.amount -= byAmount;
    }
}
