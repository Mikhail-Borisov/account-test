package ru.miborisov.account.service;

import ru.miborisov.account.dto.response.AccountDetails;
import ru.miborisov.account.model.Account;
import ru.miborisov.account.model.AccountException;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Service class that performs all operations on {@link Account}s
 * <br/>
 * <br/>
 * Notes: <br/>
 * 1) We could use some 'Result' class instead of throwing exceptions. That would perform better since runtime collects
 * stack frames when exception is thrown. Used exceptions for simplicity <br/>
 * 2) We do not let {@link Account} instances escape this service so that they can only be mutated in this service in
 * order to maintain consistent state <br/>
 * 3) We store accounts in memory so everything is lost after restart
 */
public class AccountService {

    /**
     * In memory storage for accounts
     */
    private final ConcurrentMap<String, Account> accounts = new ConcurrentHashMap<>();

    /**
     * Creates an account
     *
     * @param email email of the created account
     * @return account details of the newly created account
     */
    public AccountDetails create(String email) {
        var existingAccount = accounts.putIfAbsent(email, new Account(email, 0));
        if (existingAccount != null) throw new AccountException("Account with email '" + email + "' already exists");
        return AccountDetails.fromAccount(accounts.get(email));
    }

    /**
     * Get account details
     *
     * @param email account to get details of
     * @return account details
     */
    public AccountDetails details(String email) {
        var existingAccount = accounts.get(email);
        if (existingAccount == null) throw new AccountException("Account with email '" + email + "' does not exist");
        return AccountDetails.fromAccount(existingAccount);
    }

    /**
     * Transfers money from one account to another
     *
     * @param sourceAccountEmail account from which money is trasferred
     * @param targetAccountEmail account to which money is transferred
     * @param amount             amount of money to transfer
     */
    public void transfer(String sourceAccountEmail, String targetAccountEmail, long amount) {
        var sourceAccount = accounts.get(sourceAccountEmail);
        if (sourceAccount == null)
            throw new AccountException("Source account with email '" + sourceAccountEmail + "' does not exist");
        var targetAccount = accounts.get(targetAccountEmail);
        if (targetAccount == null)
            throw new AccountException("Target account with email '" + targetAccountEmail + "' does not exist");
        // We need to acquire locks in some global order to avoid deadlocks
        boolean directLockingOrder = sourceAccountEmail.compareTo(targetAccountEmail) < 0;
        var firstAccountLock = directLockingOrder ? sourceAccount : targetAccount;
        var secondAccountLock = directLockingOrder ? targetAccount : sourceAccount;
        synchronized (firstAccountLock) {
            synchronized (secondAccountLock) {
                if (sourceAccount.getAmount() > amount) {
                    sourceAccount.decrement(amount);
                    targetAccount.increment(amount);
                } else {
                    throw new AccountException("Account with email '" + sourceAccountEmail + "' does not have enough money to transfer");
                }
            }
        }
    }

    /**
     * Withdraws money from account
     *
     * @param fromAccountEmail account from which money is withdrawn
     * @param amount           amount of money to withdraw
     */
    public void withdraw(String fromAccountEmail, long amount) {
        var fromAccount = accounts.get(fromAccountEmail);
        if (fromAccount == null)
            throw new AccountException("Account with email '" + fromAccountEmail + "' does not exist");
        synchronized (fromAccount) {
            if (fromAccount.getAmount() > amount) {
                fromAccount.decrement(amount);
            } else {
                throw new AccountException("Account with email '" + fromAccountEmail + "' does not have enough money to withdraw");
            }
        }
    }

    /**
     * Tops up account with money
     *
     * @param accountEmail account to top up
     * @param amount       amount of money to top up
     */
    public void topUp(String accountEmail, long amount) {
        var account = accounts.get(accountEmail);
        if (account == null) throw new AccountException("Account with email '" + accountEmail + "' does not exist");
        synchronized (account) {
            account.increment(amount);
        }
    }
}
