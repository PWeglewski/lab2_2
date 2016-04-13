package pl.com.bottega.ecommerce.sharedkernel;

import org.junit.Test;

import java.util.Currency;

/**
 * Created by piotr on 13.04.2016.
 */
public class MoneyTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailToAddMoneyInDifferentCurrency() throws Exception {
        // given
        Money euros = new Money(10.50, Currency.getInstance("EUR"));
        Money dollars = new Money(23.45, Currency.getInstance("USD"));

        // when
        Money result = euros.add(dollars);

        // then
        // IllegalArgumentException expected
    }

}