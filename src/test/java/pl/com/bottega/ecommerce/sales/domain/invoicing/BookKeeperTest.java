package pl.com.bottega.ecommerce.sales.domain.invoicing;

import org.junit.Before;
import org.junit.Test;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.lang.reflect.Field;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by piotr on 13.04.2016.
 */
public class BookKeeperTest {
    private BookKeeper bookKeeper;

    @Before
    public void before(){
        bookKeeper = new BookKeeper();
        try {
            Field invoiceFactory = BookKeeper.class.getDeclaredField("invoiceFactory");
            invoiceFactory.setAccessible(true);
            invoiceFactory.set(bookKeeper, new InvoiceFactory());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void issuanceTest() throws Exception {
        // given
        ClientData clientData = new ClientData(Id.generate(), "Janusz");
        InvoiceRequest invoiceRequest = new InvoiceRequest(clientData);
        TaxPolicy taxPolicy = mockTaxPolicy(TaxPolicy.class);

        // when
        Invoice invoice = bookKeeper.issuance(invoiceRequest, taxPolicy);

        // then
        assertThat(invoice.getClient().getName(), is(equalTo("Janusz")));
        assertThat(invoice.getItems().size(), is(equalTo(0)));
    }

    private TaxPolicy mockTaxPolicy(Class<TaxPolicy> taxPolicyClass) {
        class MockedTaxPolicy implements TaxPolicy{
            @Override
            public Tax calculateTax(ProductType productType, Money net) {
                return new Tax(new Money(0.23), "VAT");
            }
        }

        return new MockedTaxPolicy();
    }

}