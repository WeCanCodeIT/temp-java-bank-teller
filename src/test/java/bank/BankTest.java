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

import org.junit.Test;

public class BankTest {

	@Test
	public void shouldAddAccount() {
		
		//arrange
		Bank underTest = new Bank();
		
		BankAccount account = new BankAccount("12345", "foo", ZERO);
		
		// act
		underTest.add(account);

		// assert
		BankAccount retrieved = underTest.findAccount("12345");
		assertThat(retrieved, is(account));
	}

	@Test
	public void shouldBeAbleToAddMultipleAccounts() {

		//arrange
		Bank underTest = new Bank();
		
		BankAccount account = new BankAccount("12345", "foo", ZERO);
		
		// arrange
		String anotherAccountNumber = "34567";
		BankAccount anotherAccount = new BankAccount(anotherAccountNumber, "foo", ZERO);

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

		//arrange
		Bank underTest = new Bank();
		
		BankAccount account = new BankAccount("12345", "foo", ZERO);
		
		// arrange
		underTest.add(account);
		
		// act
		underTest.close("12345");
		
		// assert
		BankAccount found = underTest.findAccount("12345");
		assertThat(found, is(nullValue()));
	}
	
	@Test
	public void shouldWithdrawFromAccount() {
		
		//arrange
		Bank underTest = new Bank();
		
		BankAccount account = new BankAccount("12345", "foo", "86.00");
		underTest.add(account);
		
		underTest.withdraw("12345", "44.00");
		
		assertThat(account.getBalance(), is(new BigDecimal("42.00")));
	}
}
