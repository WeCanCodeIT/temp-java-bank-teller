package bank;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Bank {

	private Map<String, BankAccount> accounts = new HashMap<>();

	public void add(BankAccount account) {
		accounts.put(account.getNumber(), account);
	}

	public BankAccount findAccount(String accountNumber) {
		return accounts.get(accountNumber);
	}

	public Collection<BankAccount> accounts() {
		return accounts.values();
	}

	public void close(String accountNumber) {
		accounts.remove(accountNumber);
	}

	public void withdraw(String accountNumber, String amount) {
		BankAccount withdrawFromAccount = findAccount(accountNumber);
		withdrawFromAccount.withdraw(amount);
	}

}
