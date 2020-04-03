package service;

import dao.PriceDAO;
import dao.PriceDAOImpl;
import domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PriceServiceImplTest {

    private PriceService priceService;

    @Mock
    private PriceDAO priceDAO;

    @BeforeEach
    void setUp() {
        priceDAO = mock(PriceDAOImpl.class);
        priceService = new PriceServiceImpl(priceDAO);
    }

    @Test
    void mergePrices() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Price price = new Price(null, "122856", 1, 1, format.parse("2013-01-01 00:00:00"),
                format.parse("2013-02-20 23:59:59"), 11000L);
        Price price1 = new Price(null, "122856", 2, 1, format.parse("2013-01-10 00:00:00"),
                format.parse("2013-01-20 23:59:59"), 99000L);
        Price price2 = new Price(null, "122856", 2, 1, format.parse("2013-01-15 00:00:00"),
                format.parse("2013-01-25 23:59:59"), 92000L);
        Price price3 = new Price(null, "6654", 1, 2, format.parse("2013-01-01 00:00:00"),
                format.parse("2013-01-02 23:59:59"), 7000L);
        Price price4 = new Price(null, "6654", 1, 2, format.parse("2013-01-01 00:00:00"),
                format.parse("2013-01-31 23:59:59"), 5000L);
        Price price5 = new Price(null, "6654", 1, 2, format.parse("2013-01-12 00:00:00"),
                format.parse("2013-01-13 23:59:59"), 4000L);

        Map<Long, List<Price>> map = new HashMap<>();
        map.put(Long.valueOf(
                price.getProductCode() + price.getNumber() + price.getDepart() + price.getValue(), 10)
                , Collections.singletonList(price));

        map.put(Long.valueOf(
                price1.getProductCode() + price1.getNumber() + price1.getDepart() + price1.getValue(), 10)
                , Collections.singletonList(price1));

        map.put(Long.valueOf(
                price2.getProductCode() + price2.getNumber() + price2.getDepart() + price2.getValue(), 10)
                , Collections.singletonList(price2));

        map.put(Long.valueOf(
                price3.getProductCode() + price3.getNumber() + price3.getDepart() + price3.getValue(), 10)
                , Collections.singletonList(price3));

        map.put(Long.valueOf(
                price4.getProductCode() + price4.getNumber() + price4.getDepart() + price4.getValue(), 10)
                , Collections.singletonList(price4));

        map.put(Long.valueOf(
                price5.getProductCode() + price5.getNumber() + price5.getDepart() + price5.getValue(), 10)
                , Collections.singletonList(price5));

        List<Price> prices = Arrays.asList(price, price1, price2, price3, price4, price5);

        when(priceDAO.getPricesGroupedByProductCodeAndNumberAndDepartAndValue()).thenReturn(prices);

        List<Price> mergePrices = priceService.mergePrices();
        for (Price mergePrice : mergePrices) {
            List<Price> pricesFromMap = map.get(Long.valueOf(
                    mergePrice.getProductCode() + mergePrice.getNumber() + mergePrice.getDepart() + mergePrice.getValue(), 10));
            assertNotNull(pricesFromMap);

            boolean inMap = false;
            for (Price foundPrice : pricesFromMap) {
                if (mergePrice.getBegin().compareTo(foundPrice.getBegin()) >= 0
                        && mergePrice.getBegin().compareTo(foundPrice.getEnd()) <= 0
                        && mergePrice.getEnd().compareTo(foundPrice.getBegin()) >= 0
                        && mergePrice.getEnd().compareTo(foundPrice.getEnd()) <= 0) {
                    inMap = true;
                }
            }

            assertTrue(inMap);
        }

    }
}