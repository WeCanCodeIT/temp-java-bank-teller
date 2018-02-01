package bank;

import static java.math.BigDecimal.ZERO;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class BankTest {

	private static final String ACCOUNT_TYPE = "foo";

	private static final String ACCOUNT_NUMBER = "12345";
	
	private Bank underTest;

	private BankAccount account;

	@Before
	public void setup() {
		//arrange
		underTest = new Bank();
		
		account = new BankAccount(ACCOUNT_NUMBER, ACCOUNT_TYPE, ZERO);
	}
	
	@Test
	public void shouldAddAccount() {
		// act
		underTest.add(account);

		// assert
		BankAccount retrieved = underTest.findAccount(ACCOUNT_NUMBER);
		assertThat(retrieved, is(account));
	}

	@Test
	public void shouldBeAbleToAddMultipleAccounts() {
		// arrange
		String anotherAccountNumber = "34567";
		BankAccount anotherAccount = new BankAccount(anotherAccountNumber, ACCOUNT_TYPE, ZERO);

		// act
		underTest.add(account);
		underTest.add(anotherAccount);

		// assert
		Collection<BankAccount> accounts = underTest.accounts();

		// using matchers
		assertThat(accounts, containsInAnyOrder(account, anotherAccount));

		// using assertEquals/True
		assertTrue(accounts.contains(account));
		assertTrue(accounts.contains(anotherAccount));
		assertEquals(2, accounts.size());
	}

	@Test
	public void shouldCloseAccount() {
		// arrange
		underTest.add(account);
		
		// act
		underTest.close(ACCOUNT_NUMBER);
		
		// assert
		BankAccount found = underTest.findAccount(ACCOUNT_NUMBER);
		assertThat(found, is(nullValue()));
	}
	
	@Test
	public void shouldWithdrawFromAccount() {
		BankAccount account = new BankAccount(ACCOUNT_NUMBER, ACCOUNT_TYPE, "86.00");
		underTest.add(account);
		
		underTest.withdraw(ACCOUNT_NUMBER, "44.00");
		
		assertThat(account.getBalance(), is(new BigDecimal("42.00")));
	}
}
