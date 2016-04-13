package pl.com.bottega.ecommerce.sharedkernel;

import org.junit.Test;

import java.util.Currency;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

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

    @Test
    public void shouldAddMoneyInSameCurrency() throws Exception{
        // given
        Money money1 = new Money(20.32);
        Money money2 = new Money(1000.69);

        // when
        Money result = money1.add(money2);

        // then
        Money expectedResult = new Money(1021.01);
        assertThat(result, is(equalTo(expectedResult)));
    }
}