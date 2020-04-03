package service;

import dao.PriceDAO;
import domain.Price;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PriceServiceImpl implements PriceService {

    private PriceDAO priceDAO;

    public PriceServiceImpl() {

    }

    public PriceServiceImpl(PriceDAO priceDAO) {
        this.priceDAO = priceDAO;
    }

    @Override
    public List<Price> mergePrices() {
        List<Price> result = new ArrayList<>();

        List<Price> pricesGroupedByProductCodeAndNumberAndDepartAndValue
                = priceDAO.getPricesGroupedByProductCodeAndNumberAndDepartAndValue();

        if (pricesGroupedByProductCodeAndNumberAndDepartAndValue.size() == 1) {
            return pricesGroupedByProductCodeAndNumberAndDepartAndValue;
        }

        AtomicInteger i = new AtomicInteger(1);
        pricesGroupedByProductCodeAndNumberAndDepartAndValue.stream().reduce((e1, e2) -> {
            if (e2.getProductCode().equals(e1.getProductCode())
                    && e2.getNumber().equals(e1.getNumber())
                    && e2.getDepart().equals(e1.getDepart())) {
                i.getAndIncrement();
                if (e2.getBegin().compareTo(e1.getBegin()) > 0 && e2.getBegin().compareTo(e1.getEnd()) < 0
                        && e2.getEnd().compareTo(e1.getEnd()) > 0) {
                    Price price = new Price();
                    price.setProductCode(e1.getProductCode());
                    price.setNumber(e1.getNumber());
                    price.setDepart(e1.getDepart());
                    price.setValue(e1.getValue());
                    price.setBegin(e1.getBegin());
                    price.setEnd(e2.getBegin());

                    Price price2 = new Price();
                    price2.setProductCode(e1.getProductCode());
                    price2.setNumber(e1.getNumber());
                    price2.setDepart(e1.getDepart());
                    price2.setValue(e2.getValue());
                    price2.setBegin(e2.getBegin());
                    price2.setEnd(e1.getEnd());

                    Price price3 = new Price();
                    price3.setProductCode(e1.getProductCode());
                    price3.setNumber(e1.getNumber());
                    price3.setDepart(e1.getDepart());
                    price3.setValue(e2.getValue());
                    price3.setBegin(e1.getEnd());
                    price3.setEnd(e2.getEnd());

                    result.add(price);
                    result.add(price2);
                    result.add(price3);
                } else if (e2.getBegin().compareTo(e1.getEnd()) < 0 && e2.getBegin().compareTo(e1.getBegin()) == 0) {
                    Price price = new Price();
                    price.setProductCode(e1.getProductCode());
                    price.setNumber(e1.getNumber());
                    price.setDepart(e1.getDepart());
                    price.setValue(e2.getValue());
                    price.setBegin(e1.getEnd());
                    price.setEnd(e2.getEnd());

                    Price price2 = new Price();
                    price2.setProductCode(e1.getProductCode());
                    price2.setNumber(e1.getNumber());
                    price2.setDepart(e1.getDepart());
                    price2.setValue(e1.getValue());
                    price2.setBegin(e1.getBegin());
                    price2.setEnd(e1.getEnd());

                    result.add(price2);

                    return price;
                } else if (e2.getBegin().compareTo(e1.getBegin()) > 0 && e2.getBegin().compareTo(e1.getEnd()) < 0
                        && e2.getEnd().compareTo(e1.getEnd()) < 0) {
                    Price price = new Price();
                    price.setProductCode(e1.getProductCode());
                    price.setNumber(e1.getNumber());
                    price.setDepart(e1.getDepart());
                    price.setValue(e1.getValue());
                    price.setBegin(e1.getBegin());
                    price.setEnd(e2.getBegin());

                    Price price2 = new Price();
                    price2.setProductCode(e1.getProductCode());
                    price2.setNumber(e1.getNumber());
                    price2.setDepart(e1.getDepart());
                    price2.setValue(e2.getValue());
                    price2.setBegin(e2.getBegin());
                    price2.setEnd(e2.getEnd());

                    Price price3 = new Price();
                    price3.setProductCode(e1.getProductCode());
                    price3.setNumber(e1.getNumber());
                    price3.setDepart(e1.getDepart());
                    price3.setValue(e1.getValue());
                    price3.setBegin(e2.getEnd());
                    price3.setEnd(e1.getEnd());

                    result.add(price);
                    result.add(price2);
                    result.add(price3);
                }
            } else if (i.get() == 1) {
                result.add(e1);
            } else {
                i.set(1);
            }

            return e2;
        });

        return result;
    }
}
