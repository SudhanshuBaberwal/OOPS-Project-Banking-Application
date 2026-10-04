package repository;

import domain.Account;

import java.util.*;

public class AccountRepository {
    private static final Map<String , Account> accountsByNumber = new HashMap<>();

    public static Optional<Account> findByAccountNumber(String accountNumber) {
        return Optional.ofNullable(accountsByNumber.get(accountNumber));
    }

    public void save(Account account){
        accountsByNumber.put(account.getAccountNumber(), account);
    }

    public List<Account> findAll(){
        return new ArrayList<>(accountsByNumber.values());
    }

    public List<Account> findByCustomerId(String customerId) {
        List<Account> result = new ArrayList<>();
        for (Account a : accountsByNumber.values()){
            if (a.getCustomerId().equals(customerId)){
                result.add(a);
            }
        }
        return result;
    }
}
