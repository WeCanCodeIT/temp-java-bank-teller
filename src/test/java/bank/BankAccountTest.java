package bank;

import static java.math.BigDecimal.ZERO;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class BankAccountTest {

	@Test
	public void shouldRenderNicely() {
		BankAccount underTest = new BankAccount("account number", "account type", ZERO);
		
		String asString = underTest.toString();
		
		assertThat(asString, is("account number (account type)"));
	}
	
	/**
	 * Explicitly testing construction (constructor).
	 */
	@Test
	public void shouldAllowConstructionWithStringAmount() {
		BankAccount underTest = new BankAccount("account number", "account type", "42.00");
		
		BigDecimal balance = underTest.getBalance();
		
		assertThat(balance, is(new BigDecimal("42.00")));
	}
	
	/**
	 * Implicitly testing construction (constructor).
	 */
	@Test
	public void shouldWithdraw() {
		BankAccount underTest = new BankAccount("account number", "account type", new BigDecimal("42.00"));
		
		underTest.withdraw("19.00");
		
		assertThat(underTest.getBalance(), is(new BigDecimal("23.00")));
	}
}
