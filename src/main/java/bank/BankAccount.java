package bank;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

public class BankAccount {

	private String number;
	private String type;
	private BigDecimal balance;

	public BankAccount(String accountNumber, String type, BigDecimal balance) {
		this.number = accountNumber;
		this.type = type;
		this.balance = balance;
	}

	public BankAccount(String accountNumber, String type, String balance) {
		this(accountNumber, type, new BigDecimal(balance));
	}
	
	public String getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return number + " (" + type + ")";
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void withdraw(String amount) {
		BigDecimal subtrahend = new BigDecimal(amount);
		balance = balance.subtract(subtrahend);
	}

}
